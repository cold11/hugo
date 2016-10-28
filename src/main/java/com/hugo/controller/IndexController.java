package com.hugo.controller;

import com.hugo.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ohj on 2015/4/23.
 */
@Controller
public class IndexController extends BaseController {
    @RequestMapping("/")
    public String index(){
        return "/index";
    }
    @RequestMapping("/error")
    public String error(){
        return "/error.jsp";
    }

}
