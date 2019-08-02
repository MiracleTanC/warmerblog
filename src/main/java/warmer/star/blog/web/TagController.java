package warmer.star.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import warmer.star.blog.dto.TagItem;
import warmer.star.blog.model.Tag;
import warmer.star.blog.service.TagService;
import warmer.star.blog.util.R;
import warmer.star.blog.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/")
public class TagController extends BaseController {
	@Autowired
	private TagService tagService;
	@PreAuthorize("hasAnyRole('ADMIN','OWNER')")
	@RequestMapping("/tag")
	public String index() {
		return "tag/index";
	}

	@RequestMapping("/getTaglist")
	@ResponseBody
	public R getTaglist() {
		List<HashMap<String, String>> maps = new ArrayList<HashMap<String, String>>();
		List<Tag> tagList = tagService.getAll();
		for (Tag tag : tagList) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("value", tag.getTagName());
			map.put("alia", tag.getAliasName());
			map.put("color", tag.getColor());
			map.put("id", tag.getId().toString());
			maps.add(map);
		}
		return R.success().put("data", maps);
	}
	@RequestMapping("/getTagByIds")
	@ResponseBody
	public R getTagByIds(String tagIds) {
		try {
			if(StringUtil.isBlank(tagIds)){
				return R.error("tagIds不能为空");
			}
			List<HashMap<String, String>> maps = new ArrayList<HashMap<String, String>>();
			String [] ids=tagIds.split(",");
			List<String> tags= Arrays.asList(ids);
			List<Tag> tagList = tagService.getTagByIds(tags);
			for (Tag tag : tagList) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("value", tag.getTagName());
				map.put("alia", tag.getAliasName());
				map.put("color", tag.getColor());
				map.put("id", tag.getId().toString());
				maps.add(map);
			}
			return R.success().put("data", maps);
		}
		catch (Exception ex){
			log.error("操作失败:{0}", ex);
			log.error(ex.getMessage());
		}
		return R.error("获取失败");
	}
	@RequestMapping("/tag/saveTag")
	@ResponseBody
	public R saveTag(TagItem submitItem) {

		int id = 0;
		try {
			if (submitItem.getId() == 0) {
				tagService.saveTag(submitItem);
				id = submitItem.getId();
			} else {
				boolean result = tagService.updateTag(submitItem);
				id=result?submitItem.getId():0;
			}

		} catch (Exception e) {
			log.error("操作失败:{0}", e);
			log.error(e.getMessage());
		}
		if (id>0) {
			R r = R.success();
			r.put("id", id);
			r.put("msg", "操作成功");
			return r;
		}
		return R.error("操作失败");
	}

	@RequestMapping("/tag/deleteTag")
	@ResponseBody
	public R deleteTag(int tagId) {

		boolean result = false;
		try {
			if (tagId > 0) {
				result = tagService.deleteTag(tagId);
			}
		} catch (Exception e) {
			log.error("操作失败:{0}", e);
			log.error(e.getMessage());
			result = false;
		}
		if (result) {
			return R.success("操作成功");
		}
		return R.error("操作失败");
	}
}