package com.hugo.controller.author;

import com.hugo.common.CommonConstants;
import com.hugo.common.page.Pager;
import com.hugo.common.util.BeanUtilsEx;
import com.hugo.common.util.Config;
import com.hugo.common.util.FileUtil;
import com.hugo.controller.base.BaseController;
import com.hugo.entity.SysUser;
import com.hugo.entity.TBTask;
import com.hugo.entity.TBUserTask;
import com.hugo.model.vo.SysUserVO;
import com.hugo.model.vo.TaskVO;
import com.hugo.service.ISysUserService;
import com.hugo.service.ITaskService;
import com.hugo.service.IUserTaskService;
import com.hugo.util.ContextUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/17.
 */
@Controller
@RequestMapping("/author")
public class AuthorController extends BaseController {
    @Autowired
    private ITaskService taskService;
    @Autowired
    private IUserTaskService userTaskService;
    @Autowired
    private ISysUserService sysUserService;
    @RequestMapping("/home")
     public String home(){
        return "/author/author_home";
    }

    /**
     * 投稿
     * @param taskVO
     * @return
     */
    @RequestMapping(value = "/submission",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> submission(TaskVO taskVO){
        try {
            String taskId = taskVO.getTaskId();
            Long userId = ContextUtil.getUserId();
            TBUserTask userTask = userTaskService.getUserTask(taskId, userId);
            if (userTask != null) {
                return jsonResult(false, "不能重复投稿");
            }
            SysUser sysUser = userTaskService.findEntityById(SysUser.class, userId);
            TBUserTask tbUserTask = new TBUserTask();
            tbUserTask.setMemo(taskVO.getBookIntroduction());
            tbUserTask.setSysUser(sysUser);
            tbUserTask.setCreateTime(new Date());
            tbUserTask.setUserTaskType(CommonConstants.IS_EDIT_3);
            tbUserTask.setStatus(CommonConstants.TASK_STATUS_1);
            TBTask task = taskService.findEntityById(TBTask.class, taskId);
            tbUserTask.setTbTask(task);
            userTaskService.save(tbUserTask);
        }catch (Exception e){
            return jsonResult(false,"投稿失败");
        }
        return jsonResult(true,"投稿成功");
    }
    @RequestMapping("/book_list")
    public String bookList(Model model,TaskVO taskVO){
        if(taskVO==null)taskVO = new TaskVO();
        taskVO.setTaskType(CommonConstants.IS_BOOK_2);
        Pager pager = new Pager();
        Integer pageNo = taskVO.getPageNo();
        Integer pageSize = taskVO.getPageSize();
        pageNo = pageNo==null?1:pageNo;
        if(pageSize!=null)pager.setPageSize(pageSize);
        SysUserVO sysUserVO = new SysUserVO();
        sysUserVO.setUserId(ContextUtil.getUserId());
        taskVO.setUser(sysUserVO);
        pager.setPageNo(pageNo);
        pager.setCondition(taskVO);
        taskService.getTaskPager(pager);
        model.addAttribute("tasks",pager);
        return "/author/book_list";
    }

    @RequestMapping("/add_book_list")
    public String userAddbookList(Model model,TaskVO taskVO){
        if(taskVO==null)taskVO = new TaskVO();
        taskVO.setTaskType(CommonConstants.IS_USER_ADD_4);
        Pager pager = new Pager();
        Integer pageNo = taskVO.getPageNo();
        Integer pageSize = taskVO.getPageSize();
        pageNo = pageNo==null?1:pageNo;
        if(pageSize!=null)pager.setPageSize(pageSize);
        SysUserVO sysUserVO = new SysUserVO();
        sysUserVO.setUserId(ContextUtil.getUserId());
        taskVO.setUser(sysUserVO);
        pager.setPageNo(pageNo);
        pager.setCondition(taskVO);
        taskService.getTaskPager(pager);
        model.addAttribute("tasks",pager);
        return "/author/book_list";
    }
    /**
     * 作者约稿列表
     * @param model
     * @param taskVO
     * @return
     */
    @RequestMapping("/task_list")
    public String taskList(Model model,TaskVO taskVO){
        Pager pager = new Pager();
        Integer pageNo = taskVO.getPageNo();
        Integer pageSize = taskVO.getPageSize();
        pageNo = pageNo==null?1:pageNo;
        if(pageSize!=null)pager.setPageSize(pageSize);
        SysUserVO userVO = new SysUserVO();
        userVO.setUserId(ContextUtil.getUserId());
        taskVO.setUser(userVO);
        taskVO.setTaskStatus(CommonConstants.TASK_STATUS_1);
        taskVO.setTaskType(CommonConstants.IS_EDIT_3);
        pager.setPageNo(pageNo);
        pager.setCondition(taskVO);
        userTaskService.getUserTaskPager(pager);
        model.addAttribute("tasks",pager);
        return "/author/task_list";
    }

    /**
     * 添加作品
     * @return
     */
    @RequestMapping("/addWork")
    public String addWork(){
        return "/author/add_work";
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
    /**
     * 添加翻译团队
     * @return
     */
    @RequestMapping("/addTransTeam")
    public String addTransTeam(Model model){
        SysUser sysUser = sysUserService.findEntityById(SysUser.class,ContextUtil.getUserId());
        model.addAttribute("user",sysUser);
        return "/author/add_trans_team";
    }
    @RequestMapping(value = "/saveTransTeam",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> saveTransTeam(SysUserVO userVO){
        boolean success = true;
        try{

            sysUserService.saveTransTeam(userVO);
        }catch (Exception e){
            success = false;
        }
        return jsonResult(success,"保存成功！");
    }
}
