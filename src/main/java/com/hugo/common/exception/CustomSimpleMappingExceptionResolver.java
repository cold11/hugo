package com.hugo.common.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2016/10/3.
 */
public class CustomSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {

    private static Logger log = LogManager.getLogger(CustomSimpleMappingExceptionResolver.class);

    protected ModelAndView doResolveException(HttpServletRequest request,
                                              HttpServletResponse response, Object handler, Exception ex) {
        try {
            //错误日记记录
            log.error("请求过程中发生错误：", ex);

            String viewName = determineViewName(ex, request);
            if (null != viewName && !"".equals(viewName)) {
                // JSP格式返回
                if (!(request.getHeader("accept").indexOf("application/json") > -1
                        || (null != request.getHeader("X-Requested-With") && request.getHeader(
                        "X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
                    Integer statusCode = determineStatusCode(request, viewName);
                    if (null != statusCode) {
                        applyStatusCodeIfPossible(request, response, statusCode);
                    }
                    return getModelAndView(viewName, ex, request);
                } else {
                    // JSON格式返回
                    PrintWriter writer = response.getWriter();
                    writer.write(ex.getMessage());
                    writer.flush();
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

