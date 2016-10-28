<@layout.head title="发布翻译任务">
<link rel="stylesheet" type="text/css" href="${ctx}/lib/Huploadify-html5/Huploadify.css" media="all">
<script type="text/javascript" src="${ctx}/lib/Huploadify-html5/jquery.Huploadify.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap-datetimepicker.min.css" media="all">
<script type="text/javascript" src="${ctx}/lib/bootstrap/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${ctx}/js/language.js"></script>
<script type="text/javascript" src="${ctx}/js/task/trans.js"></script>
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
                <h3 class="c-font-20 c-font-bold">发布翻译任务</h3>
                <p class="c-margin-t-20 c-margin-b-10">请填写以下翻译任务信息</p>
            </div>
            <div class="col-sm-5 col-md-4">
                <p>发布作品 <a class="c-font-blue-3" href="${ctx}/task/book">点击这里</a></p>
            </div>
        </div>
        <form class="form-horizontal" method="post" id="transForm">
            <input type="hidden" name="coverPath" id="coverPath"/>
            <div class="row">
                <div class="col-md-8 col-md-offset-2">
                    <div class="form-group">
                        <div class="col-xs-12">
                            <input type="text" name="bookname" id="bookname" class="form-control" placeholder="书名">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-6">
                            <select class="form-control" name="sourceLanguage" id="sourceLanguage">
                                <option value="" disabled selected>原文语种</option>
                            </select>
                        </div>
                        <div class="col-xs-6">
                            <select class="form-control" name="targetLanguage" id="targetLanguage">
                                <option value="" disabled selected>目标语种</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-4">
                            <input type="text" name="words" id="words" class="form-control" placeholder="字数">
                        </div>
                        <div class="col-xs-8">
                            <input type="text" name="publisher" id="publisher" class="form-control" placeholder="原出版商">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <textarea name="authorIntroduction" id="authorIntroduction" class="form-control" rows="5" placeholder="作者简介"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <textarea name="bookIntroduction" id="bookIntroduction" class="form-control" rows="5" placeholder="图书简介"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <textarea name="transContent" id="transContent" class="form-control" rows="5" placeholder="试译内容"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <div class="input-group date date-hours">
                                <input class="form-control" type="text" name="transExpirationDate" id="transExpirationDate" value="" readonly="readonly" placeholder="试译截止时间" />
                                <span class="input-group-addon"><span class="fa fa-remove"></span></span>
                                <span class="input-group-addon"><span class="fa fa-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <div id="upload"></div>
                            <#--<div class="file clearfix">-->
                                <#--<a href="javascript:;" class="btn photo-add">-->
                                    <#--<input type="file" name="coverFile" id="coverFile" class="file-input" />-->
                                    <#--<i class="fa fa-upload"></i> 上传图书封面-->
                                <#--</a>-->
                                <#--<div class="file-img"></div>-->
                            <#--</div>-->
                        </div>
                    </div>
                    <div class="text-center">
                        <input type="submit" class="btn btn-lg btn-lg-width c-theme-btn c-btn-uppercase c-btn-login" value="确认发布"/>
                    </div>
                </div>
            </div>
        </form>
    </div>
</@layout.body>
