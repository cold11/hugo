package com.hugo.controller.task;

import com.hugo.common.CommonConstants;
import com.hugo.common.page.Pager;
import com.hugo.common.util.Config;
import com.hugo.common.util.DateUtil;
import com.hugo.common.util.FileUtil;
import com.hugo.controller.base.BaseController;
import com.hugo.entity.SysUser;
import com.hugo.entity.TBTask;
import com.hugo.model.vo.TaskVO;
import com.hugo.service.ITaskService;
import com.hugo.util.ContextUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
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
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ohj on 2016/10/13.
 */
@Controller
@RequiresRoles("ROLE_TRANS,ROLE_EDITOR,ROLE_AUTHOR")
@RequestMapping("/task")
public class TaskController extends BaseController {
    @Autowired
    private ITaskService taskService;
    @RequestMapping("/task_workflow")
    public String taskWorkFlow(){
        return "/task/task_workflow";
    }
    @RequestMapping("/trans")
    public String trans(){
        return "/task/trans";
    }

    @RequestMapping(value = "/trans_publish",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String,Object> transPublish(Model model,HttpServletRequest request,TaskVO taskVO){
        boolean success = true;
        TBTask task = new TBTask();
        try {
            SysUser sysUser = taskService.findEntityById(SysUser.class,ContextUtil.getUserId());
            BeanUtils.copyProperties(task,taskVO);
            task.setCreateTime(new Date());
            task.setSysUser(sysUser);
            task.setTaskType(CommonConstants.IS_TRANS_1);
            task.setTaskStatus(CommonConstants.TASK_STATUS_0);
            taskService.save(task);
        } catch (Exception e) {
            success = false;
            e.printStackTrace();
        }
        return jsonResult(success,"发布成功！");
    }

    @RequestMapping("/trans/todo")
    public String trans_todo(){
        return "/task/trans_todo";
    }

    @RequestMapping("/trans/todo_list")
    public String todo_list(Model model,TaskVO taskVO){
        Pager pager = new Pager();
        Integer pageNo = taskVO.getPageNo();
        Integer pageSize = taskVO.getPageSize();
        pageNo = pageNo==null?1:pageNo;
        if(pageSize!=null)pager.setPageSize(pageSize);
        //taskVO.setUserId(ContextUtil.getUserId());
        pager.setPageNo(pageNo);
        pager.setCondition(taskVO);
        taskService.getTaskPager(pager);
        String nowDateStr = DateUtil.today("yyyy/MM/dd,HH:mm:ss");
        model.addAttribute("nowDateStr",nowDateStr);
        model.addAttribute("tasks",pager);
        return "/task/trans_list";
    }

    @RequestMapping("/getImage")
    public void getImage(HttpServletResponse response,String fileName){
        String path = Config.getConfig("serverFile.path","");
        File file = new File(path,fileName);
        if(file.exists()&&file.isFile()){
            showImage(response, file.getAbsolutePath());
        }
    }

    @RequestMapping("/book")
    public String book(){
        return "/task/book";
    }
    @RequestMapping(value = "/book_publish",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String,Object> bookPublish(Model model,HttpServletRequest request,TaskVO taskVO){
        boolean success = true;
        TBTask task = new TBTask();
        try {
            SysUser sysUser = taskService.findEntityById(SysUser.class,ContextUtil.getUserId());
            BeanUtils.copyProperties(task,taskVO);
            task.setCreateTime(new Date());
            task.setSysUser(sysUser);
            task.setTaskType(CommonConstants.IS_BOOK_2);
            task.setTaskStatus(CommonConstants.TASK_STATUS_0);
            taskService.save(task);
        } catch (Exception e) {
            success = false;
            e.printStackTrace();
        }
        return jsonResult(success,"发布成功！");
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> upload(HttpServletRequest request, String inputFileName) throws IOException {
        String path = Config.getConfig("serverFile.path","");
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
            String storePath = File.separator+username+FileUtil.getDatePath();
            filePath = storePath+storeName;
            String savePath = path+filePath;
            FileUtil.mkDirs(storePath);
            File saveFile = new File(savePath);
            FileUtils.copyInputStreamToFile(file.getInputStream(), saveFile);

        }
        return jsonResult(true,filePath);
    }
}
