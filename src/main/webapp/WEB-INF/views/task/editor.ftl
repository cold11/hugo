<@layout.head title="发布约稿">
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
                <h3 class="c-font-20 c-font-bold">发布约稿</h3>
                <p class="c-margin-t-20 c-margin-b-10">请填写以下约稿信息</p>
            </div>
        </div>
        <form class="form-horizontal" name="editForm" id="editForm" method="post">
            <div class="row">
                <div class="col-md-8 col-md-offset-2">
                    <div class="form-group">
                        <div class="col-xs-12">
                            <span class="text-danger">*</span>
                            <input type="text" class="form-control" required id="bookname" name="bookname" placeholder="约稿标题">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <span class="text-danger">*</span>
                            <textarea class="form-control" rows="10" id="bookIntroduction" name="bookIntroduction" required placeholder="约稿需求"></textarea>
                        </div>
                    </div>
                    <div class="text-center c-margin-b-50">
                        <input type="submit" class="btn btn-lg btn-lg-width c-theme-btn c-btn-uppercase c-btn-login" value="确认发布"/>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</@layout.body>
