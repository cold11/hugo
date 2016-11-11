package com.hugo.controller.task.trans;

import com.hugo.controller.base.BaseController;
import com.hugo.entity.TBTask;
import com.hugo.entity.TBUserTask;
import com.hugo.model.vo.TaskVO;
import com.hugo.service.ITaskService;
import com.hugo.service.IUserTaskService;
import com.hugo.util.ContextUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.InvocationTargetException;

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
    @RequestMapping("/trialTranslation/{taskId}")
    public String trialTranslation(Model model,@PathVariable("taskId") String taskId){
        TaskVO taskVO = new TaskVO();
        String trans = "";
        if(StringUtils.isNotBlank(taskId)) {
            TBTask task = taskService.findEntityById(TBTask.class, taskId);
            if(task!=null){
                try {
                    TBUserTask userTask = userTaskService.getUserTask(taskId, ContextUtil.getUserId());
                    if(userTask!=null){
                        Integer status = userTask.getStatus();
                    }
                    BeanUtils.copyProperties(taskVO,task);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        model.addAttribute("task",taskVO);
        model.addAttribute("trans",trans);
        return "/task/trial_translation";
    }

    @RequestMapping("/taskTrialTranslation/{taskId}")
    public String taskTrialTranslation(Model model,@PathVariable("taskId") String taskId){
        if(StringUtils.isNotBlank(taskId)) {
            try {
                TBUserTask userTask = userTaskService.getUserTask(taskId, ContextUtil.getUserId());
                if (userTask == null) {
                    model.addAttribute("message", "非法操作!");
                    return "/common/illegal";
                }
                model.addAttribute("task", userTask);
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("message", "非法操作!");
                return "/common/illegal";
            }
        }
        return "/task/trial_translation";
    }

}
