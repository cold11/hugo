package com.hugo.controller;

import com.hugo.controller.base.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/10/4.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {
    @RequestMapping(value = "home")
    public String home(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasAllRoles(Arrays.asList("ROLE_AUTHOR", "ROLE_TRANS"))){//作者/翻译公司
            return "redirect:/author/home";
        }else if(subject.hasRole("ROLE_TRANS")){//翻译公司
            return "redirect:/task/mytrans/myTransTask";
        }else if(subject.hasRole("ROLE_AUTHOR")){//作者
            return "redirect:/author/home";
        }else if(subject.hasRole("ROLE_EDITOR")){//编辑
            return "redirect:/editor/home";
        }
        return "";
    }
}
