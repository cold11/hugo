<@layout.head title="历史翻译任务 - 雨果翻译">
<script src="${ctx}/lib/pagination/jquery.twbsPagination.min.js"></script>
<script type="text/javascript">
    var task = {pageNo:1,taskStatus:0};
    function loadList() {
        beforeLoad();
        $.post("${ctx}/task/trans/history_list", task)
                .done(function (data) {
                    $("#data_div").empty().html(data);
                    afterLoad();
                })
                .fail(function () {
                    $("#data_div").empty().html("<span style='color: red;'>加载失败</span>");
                    afterLoad();
                });
    }
    $(function () {
        loadList();
    });
</script>
</@layout.head>
<@layout.body class5="c-active">
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
        <div class="c-layout-breadcrumbs-1 c-bg-white">
            <ul class="c-page-breadcrumbs c-theme-nav c-fonts-regular">
                <li> <a href="${ctx}/task/trans/todo">待领取翻译任务</a></li>
                <li>|</li>
                <li class="c-state_active">
                    历史翻译任务
                </li>
                <li>|</li>
                <li>
                    <a href="${ctx}/task/task_workflow">译员工作流程</a>
                </li>
            </ul>
        </div>
        <div class="area-rg" id="data_div">

        </div>
    </div>
</div>
</@layout.body>
