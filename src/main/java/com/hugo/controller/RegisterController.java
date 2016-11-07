package com.hugo.controller;

import com.google.common.collect.Maps;
import com.hugo.controller.base.BaseController;
import com.hugo.entity.SysUser;
import com.hugo.model.vo.SysUserVO;
import com.hugo.service.ISysUserService;
import com.hugo.util.ContextUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.hugo.common.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/4.
 */
@Controller
@RequestMapping("/signUp")
public class RegisterController extends BaseController {
    private Log log = LogFactory.getLog(RegisterController.class);
    @Autowired
    private ISysUserService sysUserService;
    @RequestMapping("/regRelease")
    public ModelAndView regRelease(){
        return new ModelAndView("/reg_release");
    }
    @RequestMapping("/regTranslator")
    public ModelAndView regTranslator(){
        return new ModelAndView("/reg_translator");
    }
    @RequestMapping(value = "/saveReleaseUser",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody  Map<String,Object> saveReleaseUser(HttpServletRequest request,SysUserVO userVO){
        boolean success = true;
        String message = "注册成功";
        SysUser sysUser = new SysUser();
        try {
            BeanUtils.copyProperties(sysUser, userVO);
            sysUser.setLoginIp(ContextUtil.getClientIP(request));
            sysUserService.saveUser(sysUser);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }catch (Exception e){
            log.error(sysUser.getUsername()+"注册失败",e);
            success = false;
            message = "注册失败";
        }
        return jsonResult(success,message);
    }

    @RequestMapping(value = "/saveTransUser")
    public String saveTransUser(Model model,MultipartHttpServletRequest request,SysUserVO userVO){
        boolean success = true;
        String message = "注册成功";
        SysUser sysUser = new SysUser();
        try {
            BeanUtils.copyProperties(sysUser,userVO);
            sysUser.setLoginIp(ContextUtil.getClientIP(request));
            Iterator<String> it = request.getFileNames();
            while (it.hasNext()){
                MultipartFile file = request.getFile(it.next());
                String fileName = file.getOriginalFilename();
                String path = FileUtil.getFilePath(request, sysUser.getPhone());
                String storeName = FileUtil.getRandName(fileName);
                String saveFile = path+File.separator+storeName;
                File file1 = new File(saveFile);
                try {
                    FileUtils.copyInputStreamToFile(file.getInputStream(), file1);
                    sysUser.setFilePath(saveFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            sysUserService.saveUser(sysUser);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }catch (Exception e){
            log.error(sysUser.getUsername()+"注册失败",e);
            success = false;
            message = "注册失败";
        }
        model.addAttribute("message",message);
        model.addAttribute("success",success);
        return "/common/message";
    }
    /**
     * 用户验证
     * @param userVO
     * @return
     */
    @RequestMapping(value = "/validateUser",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String,Object> validateUser(SysUserVO userVO){
        Map<String,Object> map = Maps.newHashMap();
        if(StringUtils.isNotBlank(userVO.getUsername())){
            if(sysUserService.validateUsername(userVO.getUsername())!=null){
                map.put("valid",false);
            }else map.put("valid",true);
        }else if(StringUtils.isNotBlank(userVO.getEmail())){
            if(sysUserService.validateEmail(userVO.getEmail())!=null){
                map.put("valid",false);
            }else map.put("valid",true);
        }else if(StringUtils.isNotBlank(userVO.getPhone())){
            if(sysUserService.validatePhone(userVO.getPhone())!=null){
                map.put("valid",false);
            }else map.put("valid",true);
        }
        return map;
    }
}
