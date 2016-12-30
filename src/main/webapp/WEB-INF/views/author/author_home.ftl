<@layout.head title="个人中心 - 雨果翻译">
<script src="${ctx}/lib/pagination/jquery.twbsPagination.min.js"></script>
<script type="text/javascript">
    var task = {pageNo:1,taskStatus:0};
    function loadList(url) {
        beforeLoad();
        $.post(url, task)
                .done(function (data) {
                    $("#data_div").empty().html(data);
                    afterLoad();
                })
                .fail(function () {
                    $("#data_div").empty().html("<span style='color: red;'>加载失败</span>");
                    afterLoad();
                });
    }

    function filterList(status){
        var url = "${ctx}/author/book_list";
        if(status==2){
            task.taskStatus = status;
            url = "${ctx}/author/add_book_list";
        }else if(status==3){
            url = "${ctx}/author/addWork";
        }else if(status==4){
            url = "${ctx}/author/task_list";
        }else if(status==5){
            url = '${ctx}/task/mytrans/myTransTaskList';
            task.taskStatus = 1;
        }else if(status==6){
            url = '${ctx}/task/mytrans/myTransTaskList';
            task.taskStatus = 2;

        }else if(status==7){
            url = '${ctx}/user/mytransInviteList';
        }else if(status==8){
            url = '${ctx}/task/mytrans/addTransWork';
        }else if(status==9){
            url = '${ctx}/author/addTransTeam';
        }
        else {
            task.taskStatus = status;
        }
        loadList(url)
    }
    function removeActive(){
        $('ul.c-dropdown-menu>li.c-active').each(function(i,e){
            $(e).removeClass("c-active");
        });
    }
    $(function () {
        var url = "${ctx}/author/book_list";
        $('ul.c-dropdown-menu>li>a').bind('click',function(){
            removeActive();
            var attr = $(this).attr('id');
            $(this).parent().addClass("c-active");
            filterList(attr);
        });
        loadList(url);
    });
</script>
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
        <div class="row">
            <div class="col-sm-3 hidden-xs">
                <div class="c-layout-sidebar-menu c-theme c-font-14">
                    <ul class="c-sidebar-menu">
                        <li class="c-dropdown c-open">
                            <a href="javascript:;">
                                我的书稿
                            </a>
                            <ul class="c-dropdown-menu">
                                <li class="c-active">
                                    <a href="javascript:void(0)" id="0">已发布 <i class="fa fa-angle-right"></i></a>
                                </li>
                                <li>
                                    <a href="javascript:void(0)" id="2">已出版 <i class="fa fa-angle-right"></i></a>
                                </li>
                                <#--<li>-->
                                    <#--<a href="javascript:void(0)" id="3">已收藏 <i class="fa fa-angle-right"></i></a>-->
                                <#--</li>-->
                                <li>
                                    <a href="javascript:void(0)" id="3">添加作品 <i class="fa fa-angle-right"></i></a>
                                </li>
                                <li>
                                    <a href="javascript:void(0)" id="4">我的投稿 <i class="fa fa-angle-right"></i></a>
                                </li>
                            </ul>
                        </li>

                        <li class="c-dropdown c-open">
                            <a href="javascript:;">
                                翻译信息
                            </a>
                            <ul class="c-dropdown-menu">
                                <@shiro.hasRole name="ROLE_TRANS">
                                <li>
                                    <a href="javascript:void(0);" id="5">正在翻译 <i class="fa fa-angle-right"></i></a>
                                </li>
                                <li>
                                    <a href="javascript:void(0);" id="6">已完成翻译 <i class="fa fa-angle-right"></i></a>
                                </li>
                                <li>
                                    <a href="javascript:void(0);" id="7">我收到的邀请 <i class="fa fa-angle-right"></i></a>
                                </li>
                                    <li>
                                        <a href="javascript:void(0);" id="8">添加翻译作品 <i class="fa fa-angle-right"></i></a>
                                    </li>
                                </@shiro.hasRole>
                                <li>
                                    <a href="javascript:void(0);" id="9">添加/修改团队信息 <i class="fa fa-angle-right"></i></a>
                                </li>

                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-sm-9">
                <div class="area-rg" id="data_div">


                </div>
            </div>
        </div>
    </div>
</div>
</@layout.body>
