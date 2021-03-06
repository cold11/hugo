package com.hugo.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/3.
 */
public class CommonConstants {
    /**
     * 用户 session key
     */
    public static final String USER_SESSION_KEY = "userSessionKey";
    /**
     * token的key的前缀
     */
    public static final String SPRING_TOKEN_NAME = "springMVC.token";
    /**
     * 删除标示
     */
    public static final Integer IS_DELETE_0 = 0;//未删除
    public static final Integer IS_DELETE_1 = 1;//已删除

    /**
     * 禁用标示
     */
    public static final Integer IS_DISABLE_0 = 0;//未禁用
    public static final Integer IS_DISABLE_1 = 1;//已禁用

    /**
     * 是否激活
     */
    public static final Integer IS_ACTIVATE_0 = 0;//未激活
    public static final Integer IS_ACTIVATE_1 = 1;//已激活

    /**
     * 是否审核通过
     */
    public static final Integer IS_CHECK_0 = 0;//未审核通过
    public static final Integer IS_CHECK_1 = 1;//已审核通过

    /**
     * role
     */
    public static final String ROLE_TRANS = "ROLE_TRANS";//翻译团队/翻译员
    public static final String ROLE_EDITOR = "ROLE_EDITOR";//编辑
    public static final String ROLE_AUTHOR = "ROLE_AUTHOR";//作者/译者
    public static final String ROLE_ANGET = "ROLE_ANGET";//版权代理

    public static final  long ROLE_TRANS_ID = 4L;
    public static final  long ROLE_EDITOR_ID = 1L;
    public static final  long ROLE_AUTHOR_ID = 2L;
    public static final  long ROLE_ANGET_ID = 3L;

    /**
     * 翻译员/翻译团队 译员类型
     */
    public static final Integer PERSONAL= 1;//个人
    public static final Integer TEAM = 2;//团队
    public static final Integer COMPANY = 3;//公司
    /**
     * taskType
     */

    public static final Integer IS_TRANS_1= 1;//翻译任务
    public static final Integer IS_BOOK_2 = 2;//图书任务
    public static final Integer IS_EDIT_3 = 3;//编辑约稿
    public static final Integer IS_USER_ADD_4 = 4;//用户添加
    public static final Integer IS_COPYRIGHT_OUTPUT_5 = 5;//版权输出

    public static final Integer COPYRIGHTTYPE_1 = 1;//中文版版原著国内出版
    public static final Integer COPYRIGHTTYPE_2 = 2;//外版译著引进出版
    public static final Integer COPYRIGHTTYPE_3 = 3;//版权输出

    public static final Integer TASK_STATUS_0= 0;//初始状态
    public static final Integer TASK_STATUS_1= 1;//已领取
    public static final Integer TASK_STATUS_2 = 2;//已完成
    /**
     * 默认cookie key
     */
    public static final String COOKIE_KEY = "SESSIONID";

    /**
     * 各个系统对应contentPath值
     */
    public static final Map<String, String> CONTENTPATHS = new HashMap<String, String>();
    public static final String UPLOAD_ROOT ="upload" ;

    static{
        CONTENTPATHS.put("/spring", "SPRING");
    }
}
