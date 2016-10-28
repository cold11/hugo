package com.hugo.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hugo.common.CommonConstants;
import com.hugo.model.vo.SysUserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * 从Cache中获取登陆用户信息
 */
public class ContextUtil {
	
	/**
	 * 获取SessionUser
	 * @return
	 */
	public static SysUserVO getSessionUser() {
		HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		if(null != session) {
			return (SysUserVO) session.getAttribute(CommonConstants.USER_SESSION_KEY);
		}else{
			return null;
		}
	}
	
	/**
	 * 获取用户id
	 * @return
	 */
	public static Long getUserId(){
		SysUserVO sessionUser = getSessionUser();
		if(null != sessionUser) {
			return sessionUser.getUserId();
		}else{
			return null;
		}
	}
	
	/**
	 * 获取用户名
	 * @return
	 */
	public static String getUserName(){
		SysUserVO sessionUser = getSessionUser();
		if(null != sessionUser) {
			return sessionUser.getUsername();
		}else{
			return null;
		}
	}
	
	/**
	 * 获取姓名
	 * @return
	 */
	public static String getName(){
		SysUserVO sessionUser = getSessionUser();
		if(null != sessionUser) {
			return sessionUser.getName();
		}else{
			return null;
		}
	}

	
	
	/**
	 * 获取sessionId值
	 * @param loginUser
	 * @return
	 */
	public static String getSessionId(SysUserVO loginUser) {
		if(null != loginUser) { 
			return MD5Util.MD5(loginUser.getUserId()+"#"+loginUser.getUsername()+"#"+System.currentTimeMillis());
		}else{
			return null;
		}
	}
	
	/**
	 * 获取系统上下文名称
	 * @return
	 */
	public static String getContentPath() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		if(null != request) {
			if(StringUtils.isNoneBlank(CommonConstants.CONTENTPATHS.get(request.getContextPath()))) {
				return CommonConstants.CONTENTPATHS.get(request.getContextPath());
			}else{
				return CommonConstants.COOKIE_KEY;
			}
		}
		return null;
	}

	/**
	 * 获取各个系统cookie的name
	 * @param request  系统上下文
	 * @return
	 */
	public static String getCookieName(HttpServletRequest request) {
		if(StringUtils.isNoneBlank(CommonConstants.CONTENTPATHS.get(request.getContextPath()))) {
			return CommonConstants.CONTENTPATHS.get(request.getContextPath());
		}else{
			return CommonConstants.COOKIE_KEY;
		}
	}
	
	/**
	 * 创建cookie
	 * @param request
	 * @param response
	 * @param value
	 * @param maxAge
	 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String value, Integer maxAge) {
		//获取各个系统cookie的name
		String name = getCookieName(request);
		//删除cookie
		delCookie(request, response, name);
		//创建cookie
		Cookie cookie = new Cookie(name, value);
		if(null != maxAge) {
			//设置cookie时间
			cookie.setMaxAge(maxAge);
		}
		//设置cookie路径
		cookie.setPath(getPath(request));
		response.addCookie(cookie);
	}
	
	/**
	 * 获取cookie路径
	 * @param request
	 * @return
	 */
	private static String getPath(HttpServletRequest request) {
		String path = request.getContextPath();
		return StringUtils.isNoneBlank(path) ? "/" : path;
	}
	
	/**
	 * 根据name 获取cookie
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {		
		Cookie[] cookies = request.getCookies();
		if(null != cookies && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(name)) {
					return cookie;
				}
			}
		}
		return null;
	}

	/**
	 * 根据name 获取cookie value
	 * @param name
	 * @return
	 */
	public static String getCookieValue(String name) {	
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Cookie[] cookies = request.getCookies();
		if(null != cookies && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(name)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
	
	/**
	 * 获取cookie value
	 * @return
	 */
	public static String getCookieValue() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Cookie[] cookies = request.getCookies();
		if(null != cookies && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(getCookieName(request))) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
	
	/**
	 * 删除cookie
	 * @param request
	 * @param response
	 * @param name
	 */
	public static void delCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		//给cookie从新赋值
		Cookie cookie = new Cookie(name, null);
		//设置cookie时间为0
		cookie.setMaxAge(0);
		//设置cookie路径
		cookie.setPath(getPath(request));
		response.addCookie(cookie);
	}
	
	/**
	 * 删除cookie
	 * @param request
	 * @param response
	 */
	public static void delCookie(HttpServletRequest request, HttpServletResponse response) {
		//给cookie从新赋值
		Cookie cookie = new Cookie(getCookieName(request), null);
		//设置cookie时间为0
		cookie.setMaxAge(0);
		//设置cookie路径
		cookie.setPath(getPath(request));
		response.addCookie(cookie);
	}
	
	/**
	 * 跳转到最外层url
	 * @param request
	 * @param response
	 * @param url
	 * @throws IOException
	 */
	public static void printUrl(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
		//判断是普通请求还是ajax请求
        if (null != request.getHeader("x-requested-with") && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) { 
        	response.setHeader("page", url);
    	}else{ 
    		response.setContentType("text/html; charset=UTF-8");
    		response.setHeader("Cache-control", "no-cache");
    		response.getWriter().print("<script>top.location.replace('"+url+"');</script>");
    	}
	}

	/***
	 * 获取客户端IP地址;这里通过了Nginx获取;X-Real-IP,
	 * @param request
	 * @return
	 */
	public static String getClientIP(HttpServletRequest request) {
		String fromSource = "X-Real-IP";
//        Enumeration<String> headers =  request.getHeaderNames();
//        while (headers.hasMoreElements()) {
//            String header = headers.nextElement();
//            System.out.println(header+">>>>"+request.getHeader(header));
//        }
		String ip = request.getHeader("X-Real-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
			fromSource = "X-Forwarded-For";
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			fromSource = "Proxy-Client-IP";
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			fromSource = "WL-Proxy-Client-IP";
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			fromSource = "request.getRemoteAddr";
		}
		System.out.println("App Client IP: "+ip+", fromSource: "+fromSource);
		return ip;
	}
}
