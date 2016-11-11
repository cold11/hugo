<@layout.head title="翻译 - 雨果翻译">
<link rel="stylesheet" type="text/css" href="${ctx}/lib/Huploadify-html5/Huploadify.css" media="all">
<script type="text/javascript" src="${ctx}/lib/Huploadify-html5/jquery.Huploadify.js"></script>
<script type="text/javascript" src="${ctx}/lib/select2/select2.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/lib/select2/select2-bootstrap.css" media="all">
<link href="${ctx}/lib/select2/select2.css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/js/task/editor.js"></script>
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
                    <li>/</li>
                    <li>
                        <a href="${ctx}/task/trans">发布翻译任务</a>
                    </li>
                    <li>/</li>
                    <li>
                        <a href="${ctx}/task/book">发布图书</a>
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
        <div class="translation-box">
            <div class="c-margin-b-40">
                <p class="c-font-18 c-margin-t-10 c-margin-b-10">书名：<b class="c-font-16">${task.bookname}</b></p>
                <p class="c-font-18">简介：<span class="c-font-16">${task.bookIntroduction}</span></p>
            </div>
            <div class="row">
                <div class="col-sm-6">
                    <h3 class="c-margin-t-10 c-margin-b-10">原文</h3>
                    <div class="form-control translation-txt">
                    ${task.tbTask.transContent}
                    </div>
                </div>
                <div class="col-sm-6">
                    <h3 class="c-margin-t-10 c-margin-b-10">译文</h3>
                    <textarea class="form-control" rows="20" name="trans" id="trans">${task.trans}</textarea>
                </div>
            </div>
            <div class="text-right c-margin-t-20">
                <input type="submit" class="btn btn-primary btn-lg btn-lg-width" value="保 存" />
                <input type="submit" class="btn btn-success btn-lg btn-lg-width" value="提 交" />
            </div>
        </div>
    </div>
</div>

</@layout.body>
