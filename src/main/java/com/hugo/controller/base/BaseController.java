package com.hugo.controller.base;

import com.hugo.common.util.Config;
import com.hugo.common.util.FileUtil;
import com.hugo.common.util.json.JsonUtil;
import com.hugo.util.ContextUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 所有Controller类必须继续此类
 */
public class BaseController {

    //记录日志
    public Log log = LogFactory.getLog(this.getClass());


    /**
     * 返回json
     * @param response
     * @param json
     */
    public void outJsonString(HttpServletResponse response, Object json) {
        response.setContentType("text/javascript;charset=UTF-8");
        response.setHeader("Cache-Control","no-store max-age=0 no-cache must-revalidate");
        response.addHeader("Cache-Control","post-check=0 pre-check=0");
        response.setHeader("Pragma","no-cache");

        try {
            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();
            out.close();
        } catch (IOException e) {
            log.error("json返回字符串错误", e);
        }
    }

    /**
     * 返回json的数据
     * @param success
     * @param msg
     * @return
     */
    public Map<String, Object> jsonResult(boolean success, Object msg) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("success", success);
        data.put("msg", msg);
        return data;
    }

    /**
     * 返回json的数据
     * @param success
     * @param msg
     * @return
     */
    public Map<String, Object> jsonResult(boolean success, Object msg, Object text) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("success", success);
        data.put("msg", msg);
        data.put("text", text);
        return data;
    }

    /**
     * 文件预览
     * @param response
     * @param file
     * @param fileName
     */
    public void previewFile(HttpServletResponse response, File file, String fileName) {
        InputStream in = null;
        OutputStream out = null;
        try {
            response.reset(); //清空response
            response.setContentType("application/octet-stream");
            response.setHeader("Connection", "close");   // 表示不能用浏览器直接打开
            response.setHeader("Accept-Ranges", "bytes");// 告诉客户端允许断点续传多线程连接下载
            response.setHeader("Content-Disposition", "inline;filename=" + new String(fileName.getBytes("GB2312"), "ISO8859-1"));
            response.setHeader("Content-Length", "" + file.length());
            response.setCharacterEncoding("UTF-8");
            in = new FileInputStream(file);
            out = response.getOutputStream();
            IOUtils.copy(in, out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(out,in);
        }
    }

    /**
     * 文件下载
     * @param response
     * @param file
     * @param fileName
     */
    public void downloadFile(HttpServletResponse response, File file, String fileName) {
        InputStream in = null;
        OutputStream out = null;
        try {
            response.reset(); //清空response
            response.setContentType("multipart/form-data");
            //response.setContentType("application/x-msdownload");
            response.setHeader("Connection", "close");   // 表示不能用浏览器直接打开
            response.setHeader("Accept-Ranges", "bytes");// 告诉客户端允许断点续传多线程连接下载
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
            //response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "UTF-8"));
            response.setHeader("Content-Length", "" + file.length());
            response.setCharacterEncoding("UTF-8");
            in = new FileInputStream(file);
            out = response.getOutputStream();
            IOUtils.copy(in, out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(out,in);
        }
    }

    public void showImage(HttpServletResponse response, String filePath){
        InputStream in = null;
        OutputStream out = null;
        try {

            response.reset(); //清空response
            response.setContentType(FileUtil.getContentType(filePath));
            in = new FileInputStream(filePath);
            out = response.getOutputStream();
            IOUtils.copy(in, out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(out,in);
        }
    }

    /**
     * 文件上传
     * @param request
     * @param inputFileName
     * @return
     * @throws IOException
     */
    protected Map<String,Object> uploadFile(HttpServletRequest request, String inputFileName) throws IOException{
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
            String storePath = File.separator+username+FileUtil.getDatePath();
            filePath = storePath+storeName;
            String savePath = path+filePath;
            FileUtil.mkDirs(path+storePath);
            File saveFile = new File(savePath);
            FileUtils.copyInputStreamToFile(file.getInputStream(), saveFile);

        }
        return jsonResult(true,filePath);
    }
    /**
     * 文件下载
     * @param response
     * @param in
     * @param fileName
     */
    public void downloadFile(HttpServletResponse response, InputStream in, String fileName) {
        OutputStream out = null;
        try {
            response.reset(); //清空response
            response.setContentType("application/x-msdownload");
            response.setHeader("Connection", "close");   // 表示不能用浏览器直接打开
            response.setHeader("Accept-Ranges", "bytes");// 告诉客户端允许断点续传多线程连接下载
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("GB2312"), "ISO8859-1"));
            response.setCharacterEncoding("UTF-8");
            out = response.getOutputStream();
            IOUtils.copy(in, out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(out,in);
        }
    }

    /**
     * 向页面打印信息
     */
    public void printMes(HttpServletResponse response,String messsage){

        try {
            response.getWriter().print(messsage);
            response.setCharacterEncoding("UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    private void close(OutputStream out,InputStream in){
        try {
            if(null != out) {
                out.close();
            }
            if(null != in) {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
