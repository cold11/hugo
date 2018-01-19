package com.hugo.controller.task.trans;

import com.hugo.common.CommonConstants;
import com.hugo.common.page.Pager;
import com.hugo.common.util.Config;
import com.hugo.common.util.FileUtil;
import com.hugo.controller.base.BaseController;
import com.hugo.entity.SysUser;
import com.hugo.entity.TBClassification;
import com.hugo.entity.TBTask;
import com.hugo.entity.TBUserTask;
import com.hugo.model.vo.SysUserVO;
import com.hugo.model.vo.TaskVO;
import com.hugo.service.ITaskService;
import com.hugo.service.IUserTaskService;
import com.hugo.util.ContextUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ohj on 2016/10/13.
 */
@Controller
@RequiresRoles("ROLE_TRANS")
@RequestMapping("/task/mytrans")
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
                    tbUserTask.setUserTaskType(CommonConstants.IS_TRANS_1);
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
                if(status==null)status = CommonConstants.TASK_STATUS_1;
                if(status==2)userTask.setStatus(CommonConstants.TASK_STATUS_2);//提交
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
        taskVO.setTaskType(CommonConstants.IS_TRANS_1);
        pager.setPageNo(pageNo);
        pager.setCondition(taskVO);
        userTaskService.getUserTaskPager(pager);
        //taskService.getTaskPager(pager);
        model.addAttribute("tasks",pager);
        return "/task/trans/mytrans_list";
    }
    @RequestMapping("/addTransWork")
    public String addTransWork(Model model){

        return "/task/trans/add_trans_works";
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> upload(HttpServletRequest request, String inputFileName) throws IOException {
//        String path = Config.getConfig("serverFile.path", "");
//        DefaultMultipartHttpServletRequest defaultRequest = (DefaultMultipartHttpServletRequest) request;
//        String username = ContextUtil.getUserName();
//        MultiValueMap<String, MultipartFile> fileMap = defaultRequest.getMultiFileMap();
//        if (fileMap.containsKey("file"))
//            inputFileName = "file";
//        List<MultipartFile> fileList = fileMap.get(inputFileName);
//        String filePath = "";
//        for(MultipartFile file : fileList){
//            String fileName = file.getOriginalFilename();
//            String storeName = FileUtil.getRandName(fileName);
//            String storePath = File.separator+username+FileUtil.getDatePath();
//            filePath = storePath+storeName;
//            String savePath = path+filePath;
//            FileUtil.mkDirs(path+storePath);
//            File saveFile = new File(savePath);
//            FileUtils.copyInputStreamToFile(file.getInputStream(), saveFile);
//
//        }
//        return jsonResult(true,filePath);
        return uploadFile(request,inputFileName);
    }

    @RequestMapping(value = "/saveWorks",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> saveWorks(TaskVO taskVO){
        boolean success = true;
        TBTask task = new TBTask();
        try {
            SysUser sysUser = taskService.findEntityById(SysUser.class,ContextUtil.getUserId());
            BeanUtils.copyProperties(task, taskVO);
            task.setCreateTime(new Date());
            task.setSysUser(sysUser);
            task.setTaskType(CommonConstants.IS_USER_ADD_4);
            task.setTaskStatus(CommonConstants.TASK_STATUS_2);


            TBUserTask tbUserTask = new TBUserTask();
            tbUserTask.setSysUser(sysUser);
            tbUserTask.setCreateTime(new Date());
            tbUserTask.setUserTaskType(CommonConstants.IS_TRANS_1);
            tbUserTask.setStatus(CommonConstants.TASK_STATUS_2);
            tbUserTask.setTbTask(task);
            userTaskService.save(tbUserTask);
            //taskService.save(task);
        } catch (Exception e) {
            success = false;
            e.printStackTrace();
        }
        return jsonResult(success,"添加成功！");
    }
}
