package warmer.star.blog.web;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import warmer.star.blog.dto.BannerItem;
import warmer.star.blog.dto.BannerQueryItem;
import warmer.star.blog.dto.BannerSubmitItem;
import warmer.star.blog.service.BannerService;
import warmer.star.blog.util.DateTimeHelper;
import warmer.star.blog.util.PageRecord;
import warmer.star.blog.util.R;

@Controller
@RequestMapping("/") 
public class BannerController extends BaseController {
	@Autowired
	private BannerService BannerService;

	@RequestMapping("/banner")
	public String index(BannerQueryItem queryItem) {
		return "banner/index";
	}
	@RequestMapping("/getBannerlist")
	@ResponseBody
	public R getBannerlist(BannerQueryItem query){
		PageHelper.startPage(query.getPageIndex(),query.getPageSize(),true);
		List<BannerItem> BannerList = BannerService.getBannerList(query);
		PageInfo<BannerItem> pageInfo = new PageInfo<BannerItem>(BannerList);
		long total= pageInfo.getTotal();
		int pages = pageInfo.getPages();
		PageRecord<BannerItem> pageRecord = new PageRecord<BannerItem>();
		pageRecord.setRows(BannerList);
		pageRecord.setCurrentPage(query.getPageIndex());
		pageRecord.setCurrentPageSize(query.getPageSize());
		pageRecord.setTotalCount(total);
		pageRecord.setTotalPage(pages);
		return R.success().put("data", pageRecord);
	}
	@RequestMapping("/banner/edit/{bannerId}")
	public String edit(@PathVariable("bannerId")int bannerId,Model model) {
		BannerItem BannerItem=new BannerItem();
		if(bannerId!=0)
		{
			BannerItem=BannerService.getById(bannerId);
			model.addAttribute("bannerModel",BannerItem);
		}
		return "banner/edit";
	}
	@RequestMapping("/banner/saveBanner")
	@ResponseBody
	public R saveBanner(BannerSubmitItem submitItem) {
		
		boolean result=false; 
		try {
			if(submitItem.getId()==0)
			{
				submitItem.setCreateOn(DateTimeHelper.getNowDate());
				submitItem.setUpdateOn(DateTimeHelper.getNowDate());
				submitItem.setIsDeleted(0);
				result=BannerService.saveBanner(submitItem);
			}
			else {
				submitItem.setUpdateOn(DateTimeHelper.getNowDate());
				result=BannerService.updateBanner(submitItem);
			}
			
		} catch (Exception e) {
			log.error("操作失败:{0}", e);
			log.error(e.getMessage());
			result=false;
		}
		if(result)
		{
			return R.success("操作成功");
		}
		return R.error("操作失败");
	}
	@RequestMapping("/banner/deleteBanner")
	@ResponseBody
	public R deleteBanner(int bannerId) {
		boolean result = false;
		try {
			result = BannerService.deleteBanner(bannerId);
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