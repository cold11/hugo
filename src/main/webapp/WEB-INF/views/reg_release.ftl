<@layout.head title="注册 - 发布人">
<script type="text/javascript" src="${ctx}/js/user/register.js"></script>
</@layout.head>
<@layout.body>
<!-- banner
	======================================-->
<!-- breadcrumbs
	================================================================================-->
<div class="c-layout-breadcrumbs-1 c-subtitle c-fonts-uppercase c-fonts-bold c-bordered c-bordered-both">
    <div class="container">
        <div class="row">
            <div class="col-sm-6 clearfix">
                <ul class="c-page-breadcrumbs c-theme-nav c-fonts-regular">
                    <li>
                        <a href="task-workflow.html">工作流程</a>
                    </li>
                </ul>
            </div>
            <div class="col-sm-6 clearfix">
                <div class="c-search-box">
                    <div class="input-group">
                        <input type="text" class="form-control">
					      	<span class="input-group-btn">
					        	<button class="btn btn-search" type="button"><i class="fa fa-search"></i></button>
					      	</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- area
	================================================================================-->
<div class="area">
    <div class="container">
        <div class="row c-font-16 c-margin-b-30">
            <div class="col-sm-7 col-md-5 col-md-offset-2">
                <h3 class="c-font-20 c-font-bold">编辑/作者/译者/版权代理 快速注册</h3>
                <p class="c-margin-t-20 c-margin-b-10">请填写以下表格进行注册</p>
            </div>
            <div class="col-sm-5 col-md-4">
                <p>翻译团队/翻译员 注册 <a class="c-font-blue-3" href="${ctx}/signUp/regTranslator">点击这里</a></p>
            </div>
        </div>
        <form class="form-horizontal" action="${ctx}/signUp/saveReleaseUser" method="post" id="releaseForm">
            <div class="row">
                <div class="col-md-8 col-md-offset-2">
                    <div class="form-group">
                        <div class="col-xs-6">
                            <span class="text-danger">*</span>
                            <select class="form-control" required id="releaseRole" name="releaseRole">
                                <option value="" disabled selected>发布人角色</option>
                                <option value="1">编辑</option>
                                <#--<option value="2">作者/译者</option>-->
                                <option value="3">版权代理</option>
                            </select>
                        </div>
                        <div class="col-xs-6">
                            <input type="text" name="postscript" id="postscript" class="form-control" placeholder="补充说明">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <span class="text-danger">*</span>
                            <input type="text" class="form-control" required id="username" name="username" placeholder="用户名">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <span class="text-danger">*</span>
                            <input type="email" class="form-control" required id="email" name="email" placeholder="邮箱">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <span class="text-danger">*</span>
                            <input type="tel" class="form-control" required id="phone" name="phone" placeholder="手机号">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <span class="text-danger">*</span>
                            <input type="password" class="form-control" required id="password" name="password" placeholder="密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <span class="text-danger">*</span>
                            <input type="password" class="form-control" required id="equalToPassword" name="equalToPassword" placeholder="再次输入密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <span class="text-danger">*</span>
                            <input type="text" class="form-control" required id="name" name="name" placeholder="姓名">
                        </div>
                    </div>
                    <div class="form-group none">
                        <div class="col-xs-12">
                            <span class="text-danger">*</span>
                            <input type="text" class="form-control" id="releaseAgency" name="releaseAgency" placeholder="所在出版机构">
                        </div>
                    </div>
                    <div class="form-group none">
                        <div class="col-xs-12">
                            <span class="text-danger">*</span>
                            <input type="text" class="form-control" id="releaseCompany" name="releaseCompany" placeholder="公司名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <textarea class="form-control" name="personalIntro" rows="5" placeholder="个人简介（很棒的个人简介可以吸引优秀的翻译团队，领取翻译任务）"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-6 control-label" id="captchaOperation"></label>
                        <div class="col-xs-2">
                            <input type="text" class="form-control" name="captcha" />
                        </div>
                    </div>
                    <div class="text-center">
                        <input type="submit" class="btn btn-lg btn-lg-width c-theme-btn c-btn-uppercase c-btn-login" value="确认注册"/>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</@layout.body>
