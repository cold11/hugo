<@layout.head title="发布作品">
<link rel="stylesheet" type="text/css" href="${ctx}/lib/Huploadify-html5/Huploadify.css" media="all">
<script type="text/javascript" src="${ctx}/lib/Huploadify-html5/jquery.Huploadify.js"></script>
<script type="text/javascript" src="${ctx}/lib/select2/select2.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/lib/select2/select2-bootstrap.css" media="all">
<link href="${ctx}/lib/select2/select2.css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/js/task/book.js"></script>
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
                        <a href="${ctx}/task/task_workflow">工作流程</a>
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
                <h3 class="c-font-20 c-font-bold">发布作品</h3>
                <p class="c-margin-t-20 c-margin-b-10">请填写以下图书信息</p>
            </div>
            <div class="col-sm-5 col-md-4">
                <p>发布翻译任务 <a class="c-font-blue-3" href="${ctx}/task/trans">点击这里</a></p>
            </div>
        </div>
        <form class="form-horizontal" name="bookForm" id="bookForm" method="post">
            <div class="row">
                <div class="col-md-8 col-md-offset-2">
                    <div class="form-group">
                        <div class="col-xs-12">
                            <span class="text-danger">*</span>
                            <select class="form-control" required id="publishType" name="publishType">
                                <option value="" disabled selected>出版类型选择</option>
                                <option value="1">纸质书出版</option>
                                <option value="2">电子书出版</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group none">
                        <div class="col-xs-12">
                            <span class="text-danger">*</span>

                            <select class="form-control" name="eBookType" id="eBookType" multiple="multiple">
                                <option value="" disabled selected>电子书类型选择</option>
                                <option value="1">亚马逊</option>
                                <option value="2">当当</option>
                                <option value="3">掌阅</option>
                                <option value="4">京东</option>
                                <option value="5">豆瓣</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <span class="text-danger">*</span>
                            <select class="form-control" required id="copyrightType" name="copyrightType">
                                <option value="" disabled selected>版权类型选择</option>
                                <option value="1">中文版版原著国内出版</option>
                                <option value="2">外版译著引进出版</option>
                                <option value="3">版权输出</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <span class="text-danger">*</span>
                            <select class="form-control" required id="classId" name="classId">
                                <option value="" disabled selected>作品分类选择</option>
                                <#list cList as c>
                                    <option value="${c.id}">${c.name}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <span class="text-danger">*</span>
                            <input type="text" class="form-control" required id="copyrightDescript" name="copyrightDescript" placeholder="版权及翻译情况说明">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <span class="text-danger">*</span>
                            <input type="text" class="form-control" required id="bookname" name="bookname" placeholder="书名">
                        </div>
                    </div>
                    <#--<div class="form-group">-->
                        <#--<div class="col-xs-12">-->
                            <#--<span class="text-danger">*</span>-->
                            <#--<input type="text" class="form-control" required id="author" name="author" placeholder="作者">-->
                        <#--</div>-->
                    <#--</div>-->
                    <#--<div class="form-group">-->
                        <#--<div class="col-xs-12">-->
                            <#--<span class="text-danger">*</span>-->
                            <#--<textarea class="form-control" rows="5" name="authorIntroduction" id="authorIntroduction" required placeholder="作者简介"></textarea>-->
                        <#--</div>-->
                    <#--</div>-->
                    <div class="form-group">
                        <div class="col-xs-12">
                            <textarea class="form-control" rows="5" name="bookIntroduction" id="bookIntroduction" placeholder="中文简介"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <textarea class="form-control" rows="5" name="bookFlIntroduction" id="bookFlIntroduction" placeholder="外文简介"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <div id="upload1"></div>
                            <#--<div class="file clearfix">-->
                                <#--<a href="javascript:;" class="btn photo-add">-->
                                    <#--<input type="file" name="coverFile" id="coverFile" class="file-input" />-->
                                    <#--<i class="fa fa-upload"></i> 上传图书封面-->
                                <#--</a>-->
                                <#--<div class="file-img"></div>-->
                            <#--</div>-->
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <div id="upload2"></div>
                            <#--<div class="file clearfix">-->
                                <#--<a href="javascript:;" class="btn doc-add">-->
                                    <#--<input type="file" class="file-input" accept=".xls,.doc,.docx,.txt,.pdf" />-->
                                    <#--<i class="fa fa-upload"></i> 上传图书试读word文档-->
                                <#--</a>-->
                                <#--<div class="file-value"></div>-->
                            <#--</div>-->
                        </div>
                    </div>
                    <div class="text-center">
                        <input type="submit" class="btn btn-lg btn-lg-width c-theme-btn c-btn-uppercase c-btn-login" value="确认发布"/>
                    </div>
                </div>
            </div>
            <input type="hidden" name="coverPath" id="coverPath" />
            <input type="hidden" name="docPath" id="docPath" />
        </form>
    </div>
    </div>
</@layout.body>
