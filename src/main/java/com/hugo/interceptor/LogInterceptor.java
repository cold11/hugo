package com.hugo.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogInterceptor extends HandlerInterceptorAdapter {

    private Log log = LogFactory.getLog(this.getClass());

	
	private Long userId = null;//当前登陆用户id
	private String name = null;//当前登陆用户姓名
	private String clientIp = null;//client ip地址
	private String serverIp = null;//server ip地址
	private Date startTime = null;//开始时间
	private Date endTime = null;//开始时间
	private String url = null;//操作url地址
	private String requestParm = null;//请求参数
	private String className = null;//操作类名称
	private String methodName = null;//操作方法名称
	private String jspName = null;//返回页面名称
	private String isSuccess = null;//是否成功
	private String exceptionName = null;//异常类名
	private String exceptionMsg = null;//异常信息
	private boolean isLogin = false;//是否登录
	
    /** 
     * 显示视图前执行 
     */  
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	//已经登录
    	if(isLogin) {
	    	//返回jsp页面的地址
	    	if(null != modelAndView) {
	    		this.jspName = modelAndView.getViewName()+".jsp";
	    	}else{
	    		this.jspName = "ajax请求";
	    	}
    	}
        super.postHandle(request, response, handler, modelAndView);  
    }  
  
    /** 
     * Controller之前执行 
     */  
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("LogInterceptor日志拦截器开始-------------------------------------");
		//开始时间
		this.startTime = new Date();
		return super.preHandle(request, response, handler);
	}

    /**
     * 最后执行，可用于释放资源
     */
    public void afterCompletion(HttpServletRequest request,
    		HttpServletResponse response, Object handler, Exception e) {
    	//已经登录
    	if(isLogin) {
    		if(null != e) { //有异常
    			this.isSuccess = "N";
    			this.exceptionName = e.getClass().getName();
    			this.exceptionMsg = e.getMessage();
    		}else{ //无异常
    			this.isSuccess = "Y";
    		}
    		//结束时间
    		this.endTime = new Date();
    		//保存日志
    		saveSysOperationLog();
    	}
    	log.info("LogInterceptor日志拦截器结束-------------------------------------");
    }
    
    /**
     * 记录操作日志
     */
    private void saveSysOperationLog() {
//    	SysOperationLog sysOperationLog = new SysOperationLog();
//    	sysOperationLog.setUserId(this.userId);
//    	sysOperationLog.setName(this.name);
//    	sysOperationLog.setStartTime(DateUtil.toString(this.startTime, "yyyy-MM-dd HH:mm:ss.SSS"));
//    	sysOperationLog.setEndTime(DateUtil.toString(this.endTime, "yyyy-MM-dd HH:mm:ss.SSS"));
//    	sysOperationLog.setUseTime(this.endTime.getTime()-this.startTime.getTime());
//    	sysOperationLog.setClientIp(this.clientIp);
//    	sysOperationLog.setServerIp(this.serverIp);
//    	sysOperationLog.setUrl(this.url);
//    	sysOperationLog.setRequestParm(this.requestParm);
//    	sysOperationLog.setClassName(this.className);
//    	sysOperationLog.setMethodName(this.methodName);
//    	sysOperationLog.setJspName(this.jspName);
//    	sysOperationLog.setIsSuccess(this.isSuccess);
//    	sysOperationLog.setExceptionName(this.exceptionName);
//    	sysOperationLog.setExceptionMsg(this.exceptionMsg);
//    	baseService.save(sysOperationLog);
//    	//记录到日志文件
//    	log.info(sysOperationLog.toString());
    }
    
    
    public static void main(String[] args) {
    	String url = "http://xx.xx.com/www.jsp";
    	Pattern p =Pattern.compile("[^//]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(url);
		matcher.find();
		System.out.println(matcher.group());
	}
}
