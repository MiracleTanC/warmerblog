package warmer.star.blog.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import warmer.star.blog.dto.CategorySubmitItem;
import warmer.star.blog.model.Category;
import warmer.star.blog.service.CategoryService;
import warmer.star.blog.util.DateTimeHelper;
import warmer.star.blog.util.R;

@Controller
@RequestMapping("/")
public class CategoryController extends BaseController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/category")
	public String index() {
		return "category/index";
	}
	@RequestMapping("/getCategoryDropdownlist")
	@ResponseBody
	public R getCategoryDropdownlist() {

		List<Category> categoryList = categoryService.getCategoryList(0);
		List<HashMap<String, Object>> maps = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> cateModeldefault  = new HashMap<String, Object>();
		cateModeldefault.put("id", 0);
		cateModeldefault.put("code", "");
		cateModeldefault.put("name", "全部");
		maps.add(cateModeldefault);
		for (Category cate : categoryList) {
			HashMap<String, Object> cateModel = new HashMap<String, Object>();
			cateModel.put("id", cate.getId().toString());
			cateModel.put("code", cate.getcategoryCode());
			cateModel.put("name", cate.getCategoryName());
			maps.add(cateModel);
		}
		return R.success().put("data", maps);
	}
	@RequestMapping("/getCategorylist")
	@ResponseBody
	public R getCategorylist(int parentId) {

		List<Category> categoryList = categoryService.getCategoryList(parentId);
		List<HashMap<String, Object>> maps = new ArrayList<HashMap<String, Object>>();
		for (Category cate : categoryList) {
			HashMap<String, Object> cateModel = new HashMap<String, Object>();
			cateModel.put("id", cate.getId().toString());
			cateModel.put("code", cate.getcategoryCode());
			cateModel.put("name", cate.getCategoryName());
			cateModel.put("sort", cate.getSort());
			cateModel.put("level", cate.getLevel().toString());
			cateModel.put("isLeaf", cate.isParent == 0);
			cateModel.put("children", new ArrayList<HashMap<String, Object>>());
			maps.add(cateModel);
		}
		return R.success().put("data", maps);
	}
	@RequestMapping("/getTreelist")
	@ResponseBody
	public R getTreelist(int parentId) {
		List<Category> data = categoryService.getAll();
		List<HashMap<String, Object>> maps = getTree(parentId,data);
		return R.success().put("data", maps);

	}
	private List<HashMap<String, Object>> getTree(int parentId,List<Category> nodelList) {
		List<HashMap<String, Object>> maps=new ArrayList<HashMap<String, Object>>();
		Iterator<Category> treeList=nodelList.stream().filter(m->m.parentId==parentId).iterator();
		 while (treeList.hasNext()) {  
			 Category cate = (Category) treeList.next();  
			 HashMap<String, Object> cateModel = new HashMap<String, Object>();
				cateModel.put("id", cate.getId().toString());
				cateModel.put("code", cate.getcategoryCode());
				cateModel.put("name", cate.getCategoryName());
				cateModel.put("sort", cate.getSort());
				cateModel.put("level", cate.getLevel().toString());
				cateModel.put("isLeaf", cate.getIsParent() == 0);
				cateModel.put("parentId", cate.getParentId().toString());
				List<HashMap<String, Object>> childrenList=getTree( cate.getId(),nodelList);
				if(!childrenList.isEmpty())
				{
					cateModel.put("children",childrenList);
				}
				maps.add(cateModel);
         }  
		return maps;
	}
	@RequestMapping("/category/saveCate")
	@ResponseBody
	public R saveCate(CategorySubmitItem submitItem) {
		boolean result=false;
		try {
			if(submitItem.getId()==0)
			{
				submitItem.setStatus(1);
				submitItem.setCreateon(DateTimeHelper.GetDateTimeNow());
				submitItem.setUpdateon(DateTimeHelper.GetDateTimeNow());
				result=categoryService.saveCategory(submitItem);
			}else {
				submitItem.setUpdateon(DateTimeHelper.GetDateTimeNow());
				result=categoryService.updateCategory(submitItem);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		if(result)
		{
			return R.success("操作成功");
		}
		return R.error("操作失败");
	}
	@RequestMapping("/category/deleteCategory")
	@ResponseBody
	public R deleteCategory(int id) {
		boolean result=false;
		try {
			result=categoryService.deleteCategoryById(id);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		if(result)
		{
			return R.success("操作成功");
		}
		return R.error("操作失败");
	}
}