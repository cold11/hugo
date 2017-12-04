<@layout.head title="注册 - 翻译团队">
<script type="text/javascript" src="${ctx}/lib/email/jquery.mailAutoComplete-4.0.js"></script>
<script type="text/javascript" src="${ctx}/js/user/register.js"></script>
<style type="text/css">
.emailist{border:1px solid #bdbdbd; border-radius: 4px; background-color:#fff; color:#666; font-size:14px; list-style-type:0; padding:0; margin:0; overflow:hidden;}
.emailist li{padding:2px 11px; cursor:pointer;}
.emailist .on, .emailist li:hover{background-color:#eee;}
</style>
</@layout.head>
<@layout.body>
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
                <h3 class="c-font-20 c-font-bold">作者/译者/翻译团队/翻译员 快速注册</h3>
                <p class="c-margin-t-20 c-margin-b-10">请填写以下表格进行注册</p>
            </div>
            <div class="col-sm-5 col-md-4">
                <p>编辑/版权代理 注册 <a class="c-font-blue-3" href="${ctx}/signUp/regRelease">点击这里</a></p>
            </div>
        </div>
        <form class="form-horizontal" action="${ctx}/signUp/saveTransUser" method="post" id="translatorForm"enctype="multipart/form-data">
            <div class="row">
                <div class="col-md-8 col-md-offset-2">

                    <div class="form-group">
                        <div class="col-xs-12">
                            <span class="text-danger">*</span>
                            <input type="text" class="form-control" required id="username" name="username" placeholder="用户名">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <span class="text-danger">*</span>
                            <input type="text" class="form-control" required id="name" name="name" placeholder="名称">
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
                            <textarea class="form-control" name="personalIntro" rows="5" placeholder="个人简介（很棒的个人简介可以吸引优秀的翻译团队，领取翻译任务）"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <input name="isTranslator" id="isTranslator" type="checkbox">是否注册翻译团队
                        </div>
                    </div>
                    <div id="transInfoDiv"  class="none">
                    <div class="form-group">
                        <div class="col-xs-12">
                            <span class="text-danger">*</span>
                            <select class="form-control" required id="translatorType" name="translatorType">
                                <option value="" disabled selected>译员类型选择</option>
                                <option value="1">个人</option>
                                <option value="2">团队</option>
                                <option value="3">公司</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <span class="text-danger">*</span>
                            <input type="text" class="form-control" required id="language" name="language" placeholder="擅长语种">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <input type="text" name="translationType" id="translationType" class="form-control" placeholder="擅长翻译类型：科技、财经、小说、传记、其他">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <input type="number" name="translatorCount" id="translatorCount" class="form-control" placeholder="译员数">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <textarea name="companyDesc" id="companyDesc" class="form-control" rows="5" placeholder="公司介绍"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <div class="file clearfix">
                                <a href="javascript:;" class="btn photo-add">
                                    <input type="file" name="upload" id="upload" class="file-input" accept="image/*" multiple="true" />
                                    <i class="fa fa-upload"></i> 上传公司证明文件
                                </a>
                                <div class="file-img"></div>
                            </div>
                        </div>
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
