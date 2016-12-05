package com.hugo.controller.task.trans;

import com.hugo.common.CommonConstants;
import com.hugo.common.page.Pager;
import com.hugo.controller.base.BaseController;
import com.hugo.entity.SysUser;
import com.hugo.entity.TBTask;
import com.hugo.entity.TBUserTask;
import com.hugo.model.vo.SysUserVO;
import com.hugo.model.vo.TaskVO;
import com.hugo.service.ITaskService;
import com.hugo.service.IUserTaskService;
import com.hugo.util.ContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

/**
 * Created by ohj on 2016/10/13.
 */
@Controller
@RequiresRoles("ROLE_TRANS")
@RequestMapping("/task/trans")
public class TransController extends BaseController {

    @Autowired
    private ITaskService taskService;
    @Autowired
    private IUserTaskService userTaskService;

    /**
     * 试译
     * @param model
     * @param taskId
     * @return
     */
    @RequestMapping("/trialTranslation/{taskId}")
    public String trialTranslation(Model model,@PathVariable("taskId") String taskId){
        TaskVO taskVO = new TaskVO();
        String trans = "";
        boolean isSubmit = false;
        if(StringUtils.isNotBlank(taskId)) {
            TBTask task = taskService.findEntityById(TBTask.class, taskId);
            if(task!=null) {
                taskVO.setBookIntroduction(task.getBookIntroduction());
                taskVO.setTransContent(task.getTransContent());
                taskVO.setBookname(task.getBookname());
                TBUserTask userTask = userTaskService.getUserTask(taskId, ContextUtil.getUserId());
                if (userTask != null) {
                    taskVO.setTaskId(userTask.getUserTaskId());
                    trans = userTask.getTrans();
                    Integer status = userTask.getStatus();
                    if (status == null) status = CommonConstants.TASK_STATUS_1;
                    taskVO.setTaskStatus(status);
                    if(status==CommonConstants.TASK_STATUS_2){
                        isSubmit = true;
                    }
                }else{
                    Long userId = ContextUtil.getUserId();
                    SysUser sysUser = userTaskService.findEntityById(SysUser.class,userId);
                    TBUserTask tbUserTask = new TBUserTask();
                    tbUserTask.setSysUser(sysUser);
                    tbUserTask.setCreateTime(new Date());
                    tbUserTask.setStatus(CommonConstants.TASK_STATUS_1);
                    tbUserTask.setTbTask(task);
                    userTaskService.save(tbUserTask);
                }
            }else{
                model.addAttribute("message", "非法操作!");
                return "/common/illegal";
            }
        }
        log.info(isSubmit);
        model.addAttribute("task",taskVO);
        model.addAttribute("trans",trans);
        model.addAttribute("isSubmit",isSubmit);
        return "/task/trial_translation";
    }

    @RequestMapping(value = "/taskTrialTranslation/saveTrialTranslation",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String,Object> taskTrialTranslation(TaskVO taskVO){
        boolean success = true;
        String message = "成功!";
        if(taskVO!=null&&StringUtils.isNotBlank(taskVO.getTaskId())) {
            try {
                TBUserTask userTask = userTaskService.findEntityById(TBUserTask.class,taskVO.getTaskId());
                if (userTask == null) {
                    success = false;
                    message = "非法操作";
                }
                Integer status = taskVO.getTaskStatus();
                if(status==null)status = CommonConstants.TASK_STATUS_0;
                if(status==1)userTask.setStatus(CommonConstants.TASK_STATUS_2);//提交
                userTask.setTrans(taskVO.getTransContent());
                userTaskService.update(userTask);
            } catch (Exception e) {
                e.printStackTrace();
                success = false;
                message = "操作失败!";
            }
        }
        return jsonResult(success,message);
    }

    @RequestMapping("/myTransTask")
    public String myTransTask(Model model){
        return "task/trans/mytrans_task";
    }

    @RequestMapping("/myTransTaskList")
    public String myTransTaskList(Model model,TaskVO taskVO){
        Pager pager = new Pager();
        Integer pageNo = taskVO.getPageNo();
        Integer pageSize = taskVO.getPageSize();
        pageNo = pageNo==null?1:pageNo;
        if(pageSize!=null)pager.setPageSize(pageSize);
        SysUserVO userVO = new SysUserVO();
        userVO.setUserId(ContextUtil.getUserId());
        taskVO.setUser(userVO);
        pager.setPageNo(pageNo);
        pager.setCondition(taskVO);
        userTaskService.getUserTaskPager(pager);
        //taskService.getTaskPager(pager);
        model.addAttribute("tasks",pager);
        return "task/trans/mytrans_list";
    }
}
