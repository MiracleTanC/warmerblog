package warmer.star.blog.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import warmer.star.blog.dto.TagItem;
import warmer.star.blog.model.Tag;
import warmer.star.blog.service.TagService;
import warmer.star.blog.util.R;

@Controller
@RequestMapping("/")
public class TagController extends BaseController {

	// 文章service
	@Autowired
	private TagService tagService;

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