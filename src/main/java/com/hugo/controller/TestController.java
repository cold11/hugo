package com.hugo.controller;

import com.hugo.common.redis.session.JedisShiroSessionRepository;
import com.hugo.controller.base.BaseController;
import com.hugo.util.ContextUtil;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * @author ohj
 * @create 2018-01-10 15:09
 **/
@Controller
@RequestMapping("/test")
public class TestController extends BaseController {
    @Autowired
    private JedisShiroSessionRepository shiroSessionRepository;
    @ResponseBody
    @RequestMapping("")
    public String test(){
        System.out.println(ContextUtil.getUserId());
        Collection<Session> sessions = shiroSessionRepository.getAllSessions();
        for (Session session:sessions) {
            Collection keys = session.getAttributeKeys();
            for (Object o : keys){
                System.out.println(o+">>>>>>>>>>>>>>>>>>>>"+session.getAttribute(o));
            }
        }
        return "";
    }
}
