package com.hugo.controller;

import com.hugo.controller.base.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/10/4.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {
    @RequestMapping(value = "home")
    public String home(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("ROLE_TRANS")){
            return "redirect:/task/trans/myTransTask";
        }
        return "";
    }
}
