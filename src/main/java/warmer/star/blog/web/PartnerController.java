package warmer.star.blog.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import warmer.star.blog.model.Partner;
import warmer.star.blog.service.PartnerService;
import warmer.star.blog.util.R;

@Controller
@RequestMapping("/")
public class PartnerController extends BaseController {
	@Autowired
	private PartnerService partnerService;

	@RequestMapping("/partner")
	public String index() {
		return "partner/index";
	}

	@RequestMapping("/getPartnerlist")
	@ResponseBody
	public R getPartnerlist() {
		List<Partner> partnerList = partnerService.getAll();
		return R.success().put("data", partnerList);
	}

	@RequestMapping("/partner/savePartner")
	@ResponseBody
	public R savePartner(Partner submitItem) {

		int id = 0;
		try {
			if (submitItem.getId()==0) {
				partnerService.savePartner(submitItem);
				id = submitItem.getId();
			} else {
				boolean result = partnerService.updatePartner(submitItem);
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

	@RequestMapping("/partner/deletePartner")
	@ResponseBody
	public R deletePartner(int PartnerId) {

		boolean result = false;
		try {
			if (PartnerId > 0) {
				result = partnerService.deletePartner(PartnerId);
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