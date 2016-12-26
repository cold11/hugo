<@layout.head title="翻译团队 - 雨果翻译">
<script src="${ctx}/lib/pagination/jquery.twbsPagination.min.js"></script>
<script src="${ctx}/js/team/invite.js"></script>
<script type="text/javascript">
    var task = {pageNo:1};
    function loadList() {
        beforeLoad();
        $.post("${ctx}/user/transteamlist", task)
                .done(function (data) {
                    $("#data_div").empty().html(data);
                    $('.inviteCSS').bind('click',function(){
                        //$(this).attr('disabled',"true");
                        var attr = $(this).attr('id');
                        invite(attr);
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

    function invite(userId){
    <@shiro.hasRole name="ROLE_EDITOR">
        postinvite(userId);
    </@shiro.hasRole>
    <@shiro.lacksRole name="ROLE_EDITOR">
        layer.alert('权限不够,不能发出邀请!',{icon:8});
     </@shiro.lacksRole>
    }
</script>
</@layout.head>
<@layout.body class6="c-active">
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
    <div class="container" id="data_div">



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
                <h3 class="c-font-20 c-font-sbold">邀请试译</h3>
                <form name="inviteForm" id="inviteForm" method="post">
                    <input type="hidden" name="userId" id="userId" />
                    <div class="form-group">
                        <span class="text-danger">*</span>
                        <textarea id="transContent" name="transContent" class="form-control" rows="5" maxlength="2000"  required placeholder="试译内容"></textarea>
                    </div>
                    <div class="form-group">
                        <span class="text-danger">*</span>
                        <textarea id="memo" name="memo" class="form-control" rows="5" maxlength="1000" required placeholder="试译要求"></textarea>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block">邀请</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</@layout.body>
