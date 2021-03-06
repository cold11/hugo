package com.hugo.controller;

import com.google.common.collect.Lists;
import com.hugo.common.CommonConstants;
import com.hugo.common.page.Pager;
import com.hugo.controller.base.BaseController;
import com.hugo.entity.EditorViewHistory;
import com.hugo.entity.SysUser;
import com.hugo.entity.TBClassification;
import com.hugo.entity.TBTask;
import com.hugo.model.vo.SysUserVO;
import com.hugo.model.vo.TaskVO;
import com.hugo.model.vo.ViewHistoryVo;
import com.hugo.service.IClassificationService;
import com.hugo.service.ISysUserService;
import com.hugo.service.ITaskService;
import com.hugo.util.ContextUtil;
import com.hugo.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by ohj on 2016/12/8.
 */
@Controller
@RequestMapping("/opus")
public class OpusController extends BaseController {

    @Autowired
    private ITaskService taskService;
    @Autowired
    private IClassificationService classificationService;
    @Autowired
    private ISysUserService sysUserService;
    @RequestMapping("/{copyrightType}")
    public String opus(Model model,@PathVariable("copyrightType") Integer copyrightType){
        if(copyrightType==null){
            return "/common/illegal";
        }
        String title = "";
        if(copyrightType==CommonConstants.COPYRIGHTTYPE_1){
            title = "中文版选题";
        }else if(copyrightType==CommonConstants.COPYRIGHTTYPE_2){
            title = "外版选题";
        }else  copyrightType = CommonConstants.COPYRIGHTTYPE_1;
        List<TBClassification> list = classificationService.getList();
        model.addAttribute("title",title);
        model.addAttribute("cList",list);
        model.addAttribute("copyrightType",copyrightType);
        return "/task/opus/opus";
    }
    @RequestMapping("/opus_list")
    public String opusList(Model model,TaskVO taskVO){
        Pager pager = new Pager();
        Integer pageNo = taskVO.getPageNo();
        Integer pageSize = taskVO.getPageSize();
        pageNo = pageNo==null?1:pageNo;
        if(pageSize!=null)pager.setPageSize(pageSize);
        taskVO.setTaskType(CommonConstants.IS_BOOK_2);
        pager.setPageNo(pageNo);
        pager.setCondition(taskVO);
        taskService.getTaskPager(pager);
        model.addAttribute("tasks",pager);
        return "/task/opus/opus_list";
    }

    @RequestMapping("/bookInfo/{taskId}")
    public ModelAndView bookInfo(@PathVariable("taskId") String taskId){
        TBTask task = taskService.findEntityById(TBTask.class,taskId);
        ModelAndView modelAndView = new ModelAndView("/task/opus/book_info");
        modelAndView.addObject("task",task);
        Long loginUserId = ContextUtil.getUserId();
        if(taskId!=null) {
            TaskVO taskVO = new TaskVO();
            taskVO.setTaskId(taskId);
            SysUserVO sysUserVO = new SysUserVO();
            sysUserVO.setUserId(loginUserId);
            taskVO.setUser(sysUserVO);
            taskService.saveEditorHistory(taskVO);
        }
        List<ViewHistoryVo> historyVos = Lists.newArrayList();
        List<EditorViewHistory> list = Lists.newArrayList(task.getEditorViewHistories());
        for (EditorViewHistory editorViewHistory : list){
            ViewHistoryVo viewHistoryVo = new ViewHistoryVo();
            viewHistoryVo.setDateStr(DateUtil.getShortTime(editorViewHistory.getCreateTime()));
            viewHistoryVo.setUsername(editorViewHistory.getSysUser().getUsername());
            historyVos.add(viewHistoryVo);
        }
        modelAndView.addObject("history",historyVos);
        return modelAndView;
    }
    /**
     * 作者信息
     * @param model
     * @param userId
     * @return
     */
    @RequestMapping("/writerInfo")
    public String writerInfo(Model model,Long userId){
        if(userId==null){
            return "/common/illegal";
        }
        SysUser sysUser = sysUserService.findEntityById(SysUser.class,userId);
        if(sysUser!=null){
            List<TBTask> tbTaskList = Lists.newArrayList(sysUser.getTbTasks());
            model.addAttribute("user",sysUser);
            model.addAttribute("tasks",tbTaskList);
            return "/task/opus/writer_info";
        }
        return null;
    }

}
