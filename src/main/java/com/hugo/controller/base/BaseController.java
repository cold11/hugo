package com.hugo.controller.base;

import com.hugo.common.util.json.JsonUtil;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class BaseController {

    //��¼��־
    public static Logger log = LogManager.getLogger(BaseController.class);


    /**
     * �����ַ���
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
            log.error("json�����ַ�������", e);
        }
    }

    /**
     * ���ؼ���
     * @param response
     * @param jsons
     */
    public void outJsonList(HttpServletResponse response, List<?> jsons) {
        response.setContentType("text/javascript;charset=UTF-8");
        response.setHeader("Cache-Control","no-store max-age=0 no-cache must-revalidate");
        response.addHeader("Cache-Control","post-check=0 pre-check=0");
        response.setHeader("Pragma","no-cache");

        try {
            PrintWriter out = response.getWriter();
            out.print(JsonUtil.objectToJson(jsons));
            out.flush();
            out.close();
        } catch (IOException e) {
            log.error("json���ؼ��ϴ���", e);
        }
    }

    /**
     * ���ؼ���
     * @param response
     * @param jsons
     */
    public void outJsonObject(HttpServletResponse response, Object jsons) {
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control","no-store max-age=0 no-cache must-revalidate");
        response.addHeader("Cache-Control", "post-check=0 pre-check=0");
        response.setHeader("Pragma", "no-cache");

        try {
            PrintWriter out = response.getWriter();
            out.print(JsonUtil.objectToJson(jsons));
            out.flush();
            out.close();
        } catch (IOException e) {
            log.error("json���ؼ��ϴ���", e);
        }
    }

    /**
     * ����json������
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
     * ����json������
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
     * �ļ�Ԥ��
     * @param response
     * @param file
     * @param fileName
     */
    public void previewFile(HttpServletResponse response, File file, String fileName) {
        InputStream in = null;
        OutputStream out = null;
        try {
            response.reset(); //���response
            response.setContentType("application/octet-stream");
            response.setHeader("Connection", "close");   // ��ʾ�����������ֱ�Ӵ�
            response.setHeader("Accept-Ranges", "bytes");// ���߿ͻ�������ϵ��������߳���������
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

    /**
     * �ļ�����
     * @param response
     * @param file
     * @param fileName
     */
    public void downloadFile(HttpServletResponse response, File file, String fileName) {
        InputStream in = null;
        OutputStream out = null;
        try {
            response.reset(); //���response
            response.setContentType("application/x-msdownload");
            response.setHeader("Connection", "close");   // ��ʾ�����������ֱ�Ӵ�
            response.setHeader("Accept-Ranges", "bytes");// ���߿ͻ�������ϵ��������߳���������
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("GB2312"), "ISO8859-1"));
            response.setHeader("Content-Length", "" + file.length());
            response.setCharacterEncoding("UTF-8");
            in = new FileInputStream(file);
            out = response.getOutputStream();
            IOUtils.copy(in, out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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

    public void showImage(HttpServletResponse response, String filePath){
        InputStream in = null;
        OutputStream out = null;
        try {
            response.reset(); //���response
            response.setContentType("image/jpeg");
            in = new FileInputStream(filePath);
            out = response.getOutputStream();
            IOUtils.copy(in, out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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

    /**
     * �ļ�����
     * @param response
     * @param in
     * @param fileName
     */
    public void downloadFile(HttpServletResponse response, InputStream in, String fileName) {
        OutputStream out = null;
        try {
            response.reset(); //���response
            response.setContentType("application/x-msdownload");
            response.setHeader("Connection", "close");   // ��ʾ�����������ֱ�Ӵ�
            response.setHeader("Accept-Ranges", "bytes");// ���߿ͻ�������ϵ��������߳���������
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("GB2312"), "ISO8859-1"));
            response.setCharacterEncoding("UTF-8");
            out = response.getOutputStream();
            IOUtils.copy(in, out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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


    /**
     * ��ҳ���ӡ��Ϣ
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
}
