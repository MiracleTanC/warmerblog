package warmer.star.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class RoleController {

public String Index(Model model){
    return "role/index";
}
}
