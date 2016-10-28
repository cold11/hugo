package com.hugo.controller;

import com.google.common.collect.Maps;
import com.hugo.common.CommonConstants;
import com.hugo.controller.base.BaseController;
import com.hugo.entity.SysUser;
import com.hugo.model.vo.SysUserVO;
import com.hugo.service.ISysUserService;
import com.hugo.util.ContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;


/**
 * Created by ohj on 2016/10/8.
 */
@Controller
public class LoginController extends BaseController {
    private static Logger log = LogManager.getLogger(LoginController.class);
    @Autowired
    private ISysUserService sysUserService;
    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("openLogin",true);
        return "/index";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public Map<String,Object> doLogin(HttpServletRequest request,SysUserVO userVO){
        log.info(userVO);
        if(StringUtils.isBlank(userVO.getUsername())||StringUtils.isBlank(userVO.getPassword())){
            return jsonResult(false,"登录名/密码不能为空!");
        }
        SysUser user = sysUserService.loginSysUser(userVO.getUsername());
        if(user==null){
            return jsonResult(false, "用户不存在！");
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userVO.getUsername(), userVO.getPassword());
        try {
            subject.login(token);
            if (subject.isAuthenticated()) {
                if(user!=null){
                    //验证用户是否激活
                    if (user.getIsActivate().equals(CommonConstants.IS_ACTIVATE_0)) {
                        return jsonResult(false, "用户未激活！");
                    }
                    //验证用户是否被禁用
                    if (user.getIsDisable().equals(CommonConstants.IS_DISABLE_1)) {
                        if(user.getDisableDate()!=null)
                            return jsonResult(false, "用户被禁用30天！");
                        else return jsonResult(false, "用户被禁用！");
                    }
                    user.setLoginIp(ContextUtil.getClientIP(request));
                    user.setLoginDate(new Date());
                    userVO.setUserId(user.getUserId());
                    Session session = subject.getSession();
                    //把用户信息存入session中
                    session.setAttribute(CommonConstants.USER_SESSION_KEY, userVO);
                    sysUserService.update(user);
                    return jsonResult(true, "");
                }
            }
        }catch (Exception e){
            log.error(userVO.getUsername()+"登陆失败!",e);
        }
        return jsonResult(false, "登陆失败!");
    }

    /**
     * 用户验证
     * @param userVO
     * @return
     */
    @RequestMapping(value = "/validateUser4all",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String,Object> validateUser4all(SysUserVO userVO){
        Map<String,Object> map = Maps.newHashMap();
        if(StringUtils.isNotBlank(userVO.getUsername())){
            if(sysUserService.loginSysUser(userVO.getUsername())==null){
                map.put("valid",false);
            }else map.put("valid",true);
        }
        return map;
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletResponse response) throws IOException {
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        session.removeAttribute(CommonConstants.USER_SESSION_KEY);
        currentUser.logout();
        //return "redirect:/";
        return "redirect:/";
    }
}
