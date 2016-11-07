<#macro head title="雨果翻译">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="keywords" content="雨果翻译">
    <meta name="description" content="雨果翻译">

    <title>${title}</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css" media="all">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/font-awesome.min.css" media="all">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/default.css" media="all">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/components.css" media="all">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/base.css" media="all">
    <!-- js -->
    <script src="${ctx}/lib/jquery/jquery-2.1.min.js"></script>
    <script src="${ctx}/lib/bootstrap/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/lib/bootstrapvalidator/css/bootstrapValidator.min.css" media="all">
    <script type="text/javascript" src="${ctx}/lib/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
    <script src="${ctx}/js/components.js"></script>
    <script src="${ctx}/js/common.js"></script>
    <script src="${ctx}/lib/layer/layer.js"></script>

    <script src="${ctx}/js/user/login.js"></script>
    <script language="javascript">
        var APP_BASE = '${ctx}';
        function showLogin(){
            $("#login-form").modal('show');
        }
    <#if openLogin?if_exists>

            $(window).load(function(){
                $("#login-form").modal('show');
            });
    </#if>
    </script>
    <#nested>
</head>
</#macro>

<#macro body class1="" class2="" class3="" class4="" class5="" class6="" class7="" class8="">
<body class="c-layout-header-fixed c-layout-header-mobile-fixed c-layout-header-topbar">
    <@header class1=class1 class2=class2 class3=class3 class4=class4 class5=class5 class6=class6 class7=class7 class8=class8></@header>

<!-- content -->
<div class="c-layout-page">
    <#nested>
</div>
<@footer></@footer>
</body>
</html>
</#macro>

<#macro header class1="" class2="" class3="" class4="" class5="" class6="" class7="" class8="">
<!-- header -->
<!-- top
================================================================================-->
<header class="c-layout-header c-layout-header-4 c-layout-header-default-mobile">
    <div class="c-navbar">
        <div class="container">
            <div class="login-box clearfix">
                <@shiro.user>
                    <a href="javascript:void(0)"><@shiro.principal></@shiro.principal></a>
                    <span>|</span>
                    <a href="${ctx}/logout" >退出</a>
                </@shiro.user>
                <@shiro.guest>
                <a href="javascript:void(0);" data-toggle="modal" onclick="showLogin();">登录</a>
                <span>|</span>
                <a href="javascript:;" data-toggle="modal" data-target="#signup-form">注册</a>
                </@shiro.guest>
            </div>
            <div class="c-navbar-wrapper clearfix">
                <div class="c-brand c-pull-left">
                    <a href="${ctx}" class="c-logo">
                        <img src="${ctx}/images/logo.png" alt="首页" class="c-desktop-logo" />
                        <img src="${ctx}/images/logo.png" alt="首页" class="c-desktop-logo-inverse" />
                        <img src="${ctx}/images/logo.png" alt="首页" class="c-mobile-logo" />
                    </a>
                    <button class="c-hor-nav-toggler" type="button" data-target=".c-mega-menu">
                        <span class="c-line"></span>
                        <span class="c-line"></span>
                        <span class="c-line"></span>
                    </button>
                </div>
                <nav class="c-mega-menu c-pull-right c-mega-menu-dark c-mega-menu-dark-mobile c-fonts-uppercase c-fonts-bold">
                    <ul class="nav navbar-nav c-theme-nav">
                        <li class="${class1}">
                            <a href="${ctx}" class="c-link">首页</a>
                        </li>
                        <li class="${class2}">
                            <a href="edition-this.html" class="c-link">中文版选题</a>
                        </li>
                        <li class="${class3}">
                            <a href="edition-foreign.html" class="c-link">外版选题</a>
                        </li>
                        <li class="${class4}">
                            <a href="appointment-modify.html" class="c-link">编辑约稿</a>
                        </li>
                        <li class="${class5}">
                            <a href="${ctx}/task/trans/todo" class="c-link">翻译任务</a>
                        </li>
                        <li class="${class6}">
                            <a href="team-list.html" class="c-link">翻译团队</a>
                        </li>
                        <li class="${class7}">
                            <a href="publisher.html" class="c-link">出版商</a>
                        </li>
                        <li class="${class8}">
                            <a href="javascript:;" class="c-link">联系我们</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</header>
<!-- / header -->

</#macro>



<#macro footer>
<footer>
    <!-- footer
    ================================================================================-->
    <div id="copyright">
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    <p>
                        © 2016 北京言美科技有限公司
                        <a href="#">常见问题</a>
                    </p>
                </div>
                <div class="col-sm-6 clearfix">
                    <a class="wechat" href="javascript:;"><i class="fa fa-wechat"></i></a>
                </div>
            </div>
        </div>
    </div>

    <div class="c-layout-go2top">
        <i class="fa fa-angle-up"></i>
    </div>

    <!-- login-form
    ================================================================================-->
    <!-- register -->
    <div class="modal fade c-content-login-form" id="signup-form" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content c-square">
                <div class="modal-header c-no-border">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                </div>
                <div class="modal-body">
                    <h3 class="c-font-24 c-font-sbold">请选择您的身份</h3>
                    <div class="row c-margin-t-30">
                        <div class="col-xs-6">
                            <a class="btn btn-success c-square btn-block btn-lg c-font-14 c-line-height-24 c-padding-20" href="${ctx}/signUp/regRelease">编辑<br />作者/译者<br />版权代理</a>
                        </div>
                        <div class="col-xs-6">
                            <a class="btn btn-primary c-square btn-block btn-lg c-font-14 c-line-height-24 c-padding-20" href="${ctx}/signUp/regTranslator">&nbsp;<br />翻译团队/翻译员<br />&nbsp;</a>
                        </div>
                    </div>
                    <div class="c-margin-t-30 clearfix">
                        <p class="c-btn-forgot">
                            已有账户？
                            <a href="javascript:;" data-toggle="modal" data-target="#login-form" data-dismiss="modal">登录</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- forgot -->
    <div class="modal fade c-content-login-form" id="forget-password-form" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content c-square">
                <div class="modal-header c-no-border">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                </div>
                <div class="modal-body">
                    <h3 class="c-font-24 c-font-sbold">密码恢复</h3>
                    <p>请填写您的邮件地址恢复密码</p>
                    <form>
                        <div class="form-group">
                            <label for="forget-email" class="hide">邮箱</label>
                            <input type="email" class="form-control c-square" id="forget-email" placeholder="邮箱">
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn c-theme-btn btn-md c-btn-uppercase c-btn-bold c-btn-square c-btn-login">提交</button>
                            <a href="javascript:;" class="c-btn-forgot" data-toggle="modal" data-target="#login-form" data-dismiss="modal">返回登录</a>
                        </div>
                    </form>
                </div>
                <div class="modal-footer c-no-border">
                    <span class="c-text-account">还没有账号？</span>
                    <a href="javascript:;" data-toggle="modal" data-target="#signup-form" data-dismiss="modal" class="btn btn-default c-btn-signup">免费注册</a>
                </div>
            </div>
        </div>
    </div>
    <!-- login -->
    <div class="modal fade c-content-login-form" id="login-form" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content c-square">
                <div class="modal-header c-no-border">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                </div>
                <div class="modal-body">
                    <h3 class="c-font-24 c-font-sbold">你好！</h3>
                    <form method="post" id="loginForm" action="" name="loginForm">
                        <div class="form-group">
                            <label for="login-email" class="hide">邮箱/用户名/手机号</label>
                            <input type="text" class="form-control c-square" id="username" name="username" placeholder="邮箱/用户名/手机号">
                        </div>
                        <div class="form-group">
                            <label for="login-password" class="hide">密码</label>
                            <input type="password" class="form-control c-square" name="password" id="password" placeholder="密码">
                        </div>

                        <div class="form-group">
                            <div class="c-checkbox">
                            <#--<input type="checkbox" id="login-rememberme" class="c-check">-->
                            <#--<label for="login-rememberme" class="c-font-thin c-font-14">-->
                            <#--<span></span>-->
                            <#--<span class="check"></span>-->
                            <#--<span class="box"></span>-->
                            <#--记住密码-->
                            <#--</label>-->
                                <a href="javascript:;" data-toggle="modal" data-target="#forget-password-form" data-dismiss="modal" class="c-btn-forgot c-font-14">忘记密码？</a>
                            </div>
                        </div>
                        <div class="form-group">
                            <button type="submit" id="btnLogin" class="btn btn-primary btn-block">登录</button>
                        </div>
                    </form>
                </div>
                <div class="modal-footer c-no-border">
                    <span class="c-text-account">还没有账号？</span>
                    <a href="javascript:;" data-toggle="modal" data-target="#signup-form" data-dismiss="modal" class="btn btn-default c-btn-signup">免费注册</a>
                </div>
            </div>
        </div>
    </div>
</footer>
</#macro>

<#macro pager pager>
<#-- 定义局部变量pageCount保存总页数 -->
    <#assign pageNo=pager.pageNo?int>
    <#assign pageSize=pager.pageSize?int>
    <#assign recordCount=pager.totalRows?int>

    <#assign pageCount=pager.pageCount?int>
    <#if pager.totalRows==0><#return/></#if>
<#-- 输出分页样式 -->

<#-- 页号越界处理 -->
    <#if (pageNo > pageCount)>
        <#assign pageNo=pageCount>
    </#if>
    <#if (pageNo < 1)>
        <#assign pageNo=1>
    </#if>
<#-- 输出分页表单 -->
<#--共${recordCount}条数据,分${((recordCount+pageSize -1)/pageSize)?int}页,每页显示${pageSize}条-->
<div class="pages">
    总记录：${recordCount}　页码：<font color="#0782e0">${pageNo}</font>/${pageCount}　每页：${pageSize}　　　
    <#if (pageNo == 1)>
        <a href="javascript:;">首页</a>&nbsp;
        <a href="javascript:;">上一页</a>&nbsp;
    <#else>
        <a href="javascript:void(0);" onclick="turnOverPage(1)">首页</a>&nbsp;
        <a href="javascript:void(0);" onclick="turnOverPage(${pageNo - 1})">上一页</a>&nbsp;
    </#if>　
    <#if (pageNo == pageCount)>
        <a href="javascript:;">下一页</a>&nbsp;
        <a href="javascript:;">尾页</a>
    <#else>
        <a href="javascript:void(0);" onclick="turnOverPage(${pageNo + 1})">下一页</a>&nbsp;
        <a href="javascript:void(0);" onclick="turnOverPage(${((recordCount+pageSize -1)/pageSize)?int})">末页</a>
    </#if>
    <input type="text" id="pageNo1" class="text wid50"/>
    <input type="button" value="GO" onclick="goPage()"/>
    <script language="javascript">
        function goPage() {
            turnOverPage($("#pageNo1").val());
        }
        function turnOverPage(no) {
            var queryForm = document.formSearch;
            if (no >${pageCount}) {
                no =${pageCount};
            }
            if (no < 1) {
                no = 1;
            }
            queryForm.pageNo.value = no;
            queryForm.submit();
        }
    </script>
</div>
</#macro>