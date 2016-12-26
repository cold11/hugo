package com.hugo.controller.task.editor;

import com.hugo.common.CommonConstants;
import com.hugo.common.page.Pager;
import com.hugo.common.util.DateUtil;
import com.hugo.controller.base.BaseController;
import com.hugo.entity.SysUser;
import com.hugo.entity.TBTask;
import com.hugo.entity.TBTransMessage;
import com.hugo.model.vo.TaskVO;
import com.hugo.model.vo.TransMessageVO;
import com.hugo.service.ITaskService;
import com.hugo.service.ITransMessageService;
import com.hugo.util.ContextUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

/**
 * Created by ohj on 2016/11/10.
 */
@Controller
@RequestMapping("/task/editor")
public class EditorController extends BaseController {
    @Autowired
    private ITaskService taskService;
    @Autowired
    private ITransMessageService transMessageService;
    @RequestMapping("")
    public String editor(){
        return "/task/editor";
    }

    @RequestMapping(value = "/publish",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String,Object> publish(TaskVO taskVO){
        boolean success = true;
        TBTask task = new TBTask();
        try {
            SysUser sysUser = taskService.findEntityById(SysUser.class, ContextUtil.getUserId());
            BeanUtils.copyProperties(task, taskVO);
            task.setCreateTime(new Date());
            task.setSysUser(sysUser);
            task.setTaskType(CommonConstants.IS_EDIT_3);
            task.setTaskStatus(CommonConstants.TASK_STATUS_0);
            taskService.save(task);
        } catch (Exception e) {
            success = false;
            e.printStackTrace();
        }
        return jsonResult(success,"发布成功！");
    }

    @RequestMapping("/todo")
    public String todo(){
        return "/task/edit_todo";
    }
    @RequestMapping("/todo_list")
    public String todo_list(Model model,TaskVO taskVO){
        Pager pager = new Pager();
        Integer pageNo = taskVO.getPageNo();
        Integer pageSize = taskVO.getPageSize();
        pageNo = pageNo==null?1:pageNo;
        if(pageSize!=null)pager.setPageSize(pageSize);
        //taskVO.setUserId(ContextUtil.getUserId());
        taskVO.setTaskType(CommonConstants.IS_EDIT_3);
        pager.setPageNo(pageNo);
        pager.setCondition(taskVO);
        taskService.getTaskPager(pager);
        model.addAttribute("tasks",pager);
        return "/task/edit_list";
    }

    @RequestMapping(value = "/invitation",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> invitation(TransMessageVO transMessageVO){
        boolean success = true;
        String message = "邀请成功";
        Long inviteId = ContextUtil.getUserId();
        Long userId = transMessageVO.getUserId();
        try {
            transMessageVO.setInviteId(inviteId);
             transMessageService.save(transMessageVO);
            //transMessageService.findTransMessage(userId,inviteId);

        }catch (Exception e){
            log.error("邀请试译出错["+userId+","+inviteId+"]",e);
            success = false;
            message = "出现错误";
        }
        return jsonResult(success,message);
    }

}
