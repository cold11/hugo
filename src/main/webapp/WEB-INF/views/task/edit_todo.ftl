<@layout.head title="编辑约稿 - 雨果翻译">
<script src="${ctx}/lib/pagination/jquery.twbsPagination.min.js"></script>
<script type="text/javascript" src="${ctx}/js/task/submission.js"></script>
<script type="text/javascript">
    var task = {pageNo:1,taskStatus:0};
    function loadList() {
        beforeLoad();
        $.post("${ctx}/task/editor/todo_list", task)
                .done(function (data) {
                    $("#data_div").empty().html(data);
                    $('.inviteCSS').bind('click',function(){
                        //$(this).attr('disabled',"true");
                        var attr = $(this).attr('id');
                        subTask(attr);
                    });
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
    function subTask(taskId){
        <@shiro.hasRole name="ROLE_AUTHOR">
            showWindow(taskId);
        </@shiro.hasRole>
        <@shiro.lacksRole name="ROLE_AUTHOR">
            layer.alert('权限不够,不能投稿!',{icon:8});
        </@shiro.lacksRole>
    }
</script>
</@layout.head>
<@layout.body class4="c-active">
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
        <div class="row" id="data_div">

        </div>
    </div>
</div>

<!-- modal
================================================================================-->
<div class="modal fade c-content-login-form" id="submission-form" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content c-square">
            <div class="modal-header c-no-border">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <h3 class="c-font-20 c-font-sbold">我要投稿</h3>
                <form method="post" name="submissionForm" id="submissionForm">
                    <input type="hidden" name="taskId" id="taskId" />
                    <div class="form-group">
                        <span class="text-danger">*</span>
                        <textarea class="form-control" name="bookIntroduction" id="bookIntroduction" rows="5" required placeholder="投稿说明"></textarea>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block">确定</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</@layout.body>
