package com.hugo.controller;

import com.hugo.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/10/4.
 */
@Controller
public class UserController extends BaseController {
    @RequestMapping(value = "signUp")
    public void signUp(){

    }
}
