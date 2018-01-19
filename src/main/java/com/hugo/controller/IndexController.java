package com.hugo.controller;

import com.hugo.controller.base.BaseController;
import org.springframework.beans.propertyeditors.LocaleEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created by ohj on 2015/4/23.
 */
@Controller
public class IndexController extends BaseController {
    @RequestMapping("/")
    public String index(){
        return "/index";
    }
    @RequestMapping("/error")
    public String error(){
        return "/error.jsp";
    }
    @RequestMapping(value = "/changelanguage")
    public ModelAndView changeLanguage(HttpServletRequest request, HttpServletResponse response, String language){
        String viewName = "/index";
        try
        {
            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
            if (localeResolver == null) {
                throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
            }
            if(language.equals("en")){
                viewName = "/index_en";
                localeResolver.setLocale(request, response, Locale.ENGLISH);
            }else{
                localeResolver.setLocale(request, response, Locale.CHINA);
            }
//            LocaleEditor localeEditor = new LocaleEditor();
//            localeEditor.setAsText(language);
//            localeResolver.setLocale(request, response, (Locale)localeEditor.getValue());


        }
        catch(Exception ex)
        {
            viewName = "/500";
            ex.printStackTrace();
        }
        return new ModelAndView(viewName);
    }
}
