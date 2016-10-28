package com.hugo.interceptor;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hugo.common.CommonConstants;

/**
 * 防止表单重复提交
 *
 * @author：熊焱
 * @date：2014-11-4 下午01:53:29
 */
public class TokenHandlerInterceptor extends HandlerInterceptorAdapter {
	
	private static final Log log = LogFactory.getLog(TokenHandlerInterceptor.class);
	
	//生成token的url
	private static Set<String> tokenUrls = new HashSet<String>();
	static {
		tokenUrls.add("/user/register.do");
	}
	
	//验证token的url
	private static Set<String> validTokenUrls = new HashSet<String>();
	static {
		validTokenUrls.add("/user/saveUser.do");
	}


	/** 
     * 显示视图前执行 
     */  
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		String uri = request.getRequestURI();
    	String path = request.getContextPath();
    	
    	if(tokenUrls.contains(uri.substring(path.length()))) {
    		//创建token
    		String token = generateGUID(request);
    		modelAndView.addObject("token", token);
    		super.postHandle(request, response, handler, modelAndView);
    	}
	}
	
	/** 
     * 进入方法之前执行
     */  
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
    	String path = request.getContextPath();
    	
    	if(validTokenUrls.contains(uri.substring(path.length()))) {
			//验证token
			boolean success = validToken(request);
			if(!success) {
				response.getWriter().print("<script>alert('no repeat submit!');</script>");//跳转到登陆页面
				return false;
			}
    	}
    	return super.preHandle(request, response, handler);
	}
	
	
	/**
	 * 生成唯一值的token
	 * @param request
	 * @return
	 */
	public synchronized static String generateGUID(HttpServletRequest request) {
		try {
			String token = new BigInteger(165, new Random()).toString(36).toUpperCase();
			//把token放入session中
			request.getSession().setAttribute(CommonConstants.SPRING_TOKEN_NAME + "." + token, token);
			return token;
		} catch (IllegalStateException e) {
			log.error("generateGUID() mothod find bug,by token session...");
		}
		return null;
	}
	
	/**
	 * 验证token
	 * @param request
	 * @return
	 */
	public static boolean validToken(HttpServletRequest request) {
		String token = request.getParameter(CommonConstants.SPRING_TOKEN_NAME);
        if (token == null) {
            log.error("token is not valid!inputToken is NULL");
            return false;
        }
 
        HttpSession session = request.getSession();
        String sessionToken = (String) session.getAttribute(CommonConstants.SPRING_TOKEN_NAME + "." + token);
        if(null == sessionToken || "".equals(sessionToken)) {
        	log.error("sessionToken is null");
            return false;
        }

        if (!token.equals(sessionToken)) {
            log.error("token is not valid!inputToken='" + token + "',sessionToken = '" + sessionToken + "'");
            return false;
        }
        //清除session中的token
        session.removeAttribute(CommonConstants.SPRING_TOKEN_NAME + "." + token);
        return true;
    }
	
}
