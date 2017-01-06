package com.hugo.controller;

import com.google.common.collect.Maps;
import com.hugo.common.util.BeanUtilsEx;
import com.hugo.common.util.Config;
import com.hugo.common.util.FileUtil;
import com.hugo.controller.base.BaseController;
import com.hugo.entity.SysUser;
import com.hugo.model.vo.SysUserVO;
import com.hugo.service.ISysUserService;
import com.hugo.util.ContextUtil;
import com.hugo.util.MD5Util;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/4.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {
    @Autowired
    private ISysUserService sysUserService;
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
    @RequestMapping(value = "/updatePsd")
    public String updatePsd(){
        return "/common/update_password";
    }
    @RequestMapping(value = "/userInfo")
    public String userInfo(Model model){
        SysUser sysUser = sysUserService.findEntityById(SysUser.class, ContextUtil.getUserId());
        model.addAttribute("user",sysUser);
        return "/common/update_user_info";
    }
    /**
     * 用户验证(信息修改)
     * @param userVO
     * @return
     */
    @RequestMapping(value = "/validateUserInfo",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Map<String,Object> validateUserInfo(SysUserVO userVO){
        Long userId = ContextUtil.getUserId();
        SysUser sysUser = sysUserService.findEntityById(SysUser.class,userId);
        String email = sysUser.getEmail();
        String phone = sysUser.getPhone();
        Map<String,Object> map = Maps.newHashMap();
        if(StringUtils.isNotBlank(userVO.getEmail())){
            if(!email.equals(userVO.getEmail())&&sysUserService.validateEmail(userVO.getEmail())!=null){
                map.put("valid",false);
            }else map.put("valid",true);
        }else if(StringUtils.isNotBlank(userVO.getPhone())){
            if(!phone.equals(userVO.getPhone())&&sysUserService.validatePhone(userVO.getPhone())!=null){
                map.put("valid",false);
            }else map.put("valid",true);
        }else if(StringUtils.isNotBlank(userVO.getPassword())){
            String password = MD5Util.MD5(userVO.getPassword());
            String oldPassword = sysUser.getPassword();
            if(!password.equals(oldPassword)){
                map.put("valid",false);
            }else map.put("valid",true);
        }
        return map;
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> upload(HttpServletRequest request, String inputFileName) throws IOException {
        String path = Config.getConfig("serverFile.path", "");
        DefaultMultipartHttpServletRequest defaultRequest = (DefaultMultipartHttpServletRequest) request;
        String username = ContextUtil.getUserName();
        MultiValueMap<String, MultipartFile> fileMap = defaultRequest.getMultiFileMap();
        if (fileMap.containsKey("file"))
            inputFileName = "file";
        List<MultipartFile> fileList = fileMap.get(inputFileName);
        String filePath = "";
        for(MultipartFile file : fileList){
            String fileName = file.getOriginalFilename();
            String storeName = FileUtil.getRandName(fileName);
            String storePath = File.separator+username+File.separator+"upload"+FileUtil.getDatePath();
            filePath = storePath+storeName;
            String savePath = path+filePath;
            FileUtil.mkDirs(path+storePath);
            File saveFile = new File(savePath);
            FileUtils.copyInputStreamToFile(file.getInputStream(), saveFile);

        }
        return jsonResult(true,filePath);
    }
    @RequestMapping(value = "/updatePassword",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> updatePassword(String oldPassword,String password) {
        boolean success = true;
        String message = "修改成功";
        Long userId = ContextUtil.getUserId();
        SysUser sysUser = sysUserService.findEntityById(SysUser.class, userId);
        String _p = sysUser.getPassword();
        if (StringUtils.isNotBlank(oldPassword)) {
            String newpassword = MD5Util.MD5(password);
            String _oldPassword = MD5Util.MD5(oldPassword);
            if (!_p.equals(_oldPassword)) {
               success = false;
                message = "原密码错误";
            }else{
                sysUser.setPassword(newpassword);
                sysUserService.update(sysUser);
            }
        }
        return jsonResult(success,message);
    }

    @RequestMapping(value = "/updateUserInfo",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> updateUserInfo(SysUserVO userVO) {
        boolean success = true;
        String message = "修改成功";
        Long userId = ContextUtil.getUserId();
        SysUser sysUser = sysUserService.findEntityById(SysUser.class, userId);
        BeanUtilsEx.copyNotNullProperties(sysUser,userVO);
        sysUserService.update(sysUser);
        return jsonResult(success,message);
    }
}
