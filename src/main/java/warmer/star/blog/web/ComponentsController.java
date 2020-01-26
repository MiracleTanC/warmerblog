package warmer.star.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ComponentsController extends BaseController {

	@RequestMapping("/transfer")
	public String transfer(Model model) {
		return "components/transfer";
	}
	@RequestMapping("/scrollreveal")
	public String scrollreveal(Model model) {
		return "components/scrollreveal";
	}
	@RequestMapping("/magicphoto")
	public String magicphoto(Model model) {
		return "components/magicphoto";
	}
	@RequestMapping("/timeline")
	public String timeline(Model model) {
		return "components/timeline";
	}
	@RequestMapping("/wordcloud")
	public String wordcloud(Model model) {
		return "components/wordcloud";
	}
}