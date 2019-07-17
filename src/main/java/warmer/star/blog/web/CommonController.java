package warmer.star.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class CommonController {
    /**
     * 404 error
     * @return
     */
    @RequestMapping("/404")
    public String error404() {
        return "common/404";
    }

    /**
     * 500 error
     * @return
     */
    @RequestMapping("/500")
    public String error500() {
        return "common/500";
    }
}
