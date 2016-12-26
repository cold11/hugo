package com.hugo.controller;

import com.google.common.collect.Lists;
import com.hugo.common.CommonConstants;
import com.hugo.common.page.Pager;
import com.hugo.controller.base.BaseController;
import com.hugo.entity.SysUser;
import com.hugo.entity.TBTask;
import com.hugo.entity.TBTransMessage;
import com.hugo.model.vo.SysUserVO;
import com.hugo.model.vo.TransMessageVO;
import com.hugo.service.ISysUserService;
import com.hugo.service.ITransMessageService;
import com.hugo.util.ContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by ohj on 2016/12/6.
 */
@Controller
@RequestMapping("/user")
public class TranslationTeamController extends BaseController {
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ITransMessageService transMessageService;
    @RequestMapping("/transteam")
    public String transteam(){

        return "/user/transteam/trans_team";
    }
    @RequestMapping("/transteamlist")
    public String transteamlist(Model model,SysUserVO sysUserVO){
        List<Long> roles = Arrays.asList(CommonConstants.ROLE_TRANS_ID);
        sysUserVO.setInputRoles(roles);
        Pager pager = new Pager();
        Integer pageNo = sysUserVO.getPageNo();
        Integer pageSize = sysUserVO.getPageSize();
        pageNo = pageNo==null?1:pageNo;
        if(pageSize!=null)pager.setPageSize(pageSize);
        pager.setPageNo(pageNo);
        pager.setCondition(sysUserVO);
        sysUserService.getUserPager(pager);
        model.addAttribute("users",pager);
        return "/user/transteam/trans_team_list";
    }

    @RequestMapping("/transteamcontent/{userId}")
    public String transteamcontent(Model model,@PathVariable("userId") Long userId){
        if(userId!=null){
            boolean hasRole = sysUserService.checkUserRole(userId,CommonConstants.ROLE_TRANS_ID);
            if(!hasRole){
                return "/common/illegal";
            }
            SysUser sysUser = sysUserService.findEntityById(SysUser.class, userId);
            String userType = "";
            Integer type = sysUser.getTranslatorType();
            List<TBTask> tbTaskList = Lists.newArrayList(sysUser.getTbTasks());
            if(type==null||type==CommonConstants.PERSONAL){
                userType = "个人";
            }else if(type==CommonConstants.COMPANY){
                userType = "翻译公司";
            }else  userType = "翻译团队";
            model.addAttribute("tasks",tbTaskList);
            model.addAttribute("user",sysUser);
            model.addAttribute("userType",userType);
        }else{
            return "/common/illegal";
        }

        return "/user/transteam/trans_team_content";
    }
//    @RequestMapping("/mytransInvite")
//    public void mytransInvite(Model model){
//
//    }
    @RequestMapping("/mytransInviteList")
    public String mytransInviteList(Model model,TransMessageVO transMessageVO){
        Pager pager = new Pager();
        transMessageVO.setUserId(ContextUtil.getUserId());
        pager.setCondition(transMessageVO);
        Integer pageNo = transMessageVO.getPageNo();
        Integer pageSize = transMessageVO.getPageSize();
        pageNo = pageNo==null?1:pageNo;
        pager.setPageNo(pageNo);
        if(pageSize!=null)pager.setPageSize(pageSize);
        transMessageService.getTransMessagePager(pager);
        model.addAttribute("tasks",pager);
        return "/task/trans/mytrans_invite_list";
    }
    @RequestMapping("/mytrialTranslation/{taskId}")
    public String trialTranslation(Model model,@PathVariable("taskId") String taskId){
        boolean isSubmit = false;
        TBTransMessage transMessage = transMessageService.findEntityById(TBTransMessage.class,taskId);
        Integer status = transMessage.getStatus();
        if (status == null) status = CommonConstants.TASK_STATUS_1;
        if(status==CommonConstants.TASK_STATUS_2){
            isSubmit = true;
        }
        model.addAttribute("isSubmit",isSubmit);
        model.addAttribute("t",transMessage);
        return "/task/trans/trans_content";
    }

    @RequestMapping(value = "/mytransInvite/saveTrialTranslation",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> saveTrialTranslation(TransMessageVO messageVO){
        if(StringUtils.isNotBlank(messageVO.getTransMessageId())) {
            TBTransMessage transMessage = transMessageService.findEntityById(TBTransMessage.class, messageVO.getTransMessageId());
            transMessage.setTransResult(messageVO.getTransResult());
            if (messageVO.getStatus() != null) {
                if (messageVO.getStatus() == CommonConstants.TASK_STATUS_1) {
                    transMessage.setStatus(CommonConstants.TASK_STATUS_1);
                } else if (messageVO.getStatus() == CommonConstants.TASK_STATUS_2) {
                    transMessage.setStatus(CommonConstants.TASK_STATUS_2);
                }
                transMessageService.update(transMessage);
            }
        }
        return jsonResult(true,"成功");
    }
}
