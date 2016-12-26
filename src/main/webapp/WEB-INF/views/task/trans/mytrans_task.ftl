<@layout.head title="个人中心 - 雨果翻译">
<script src="${ctx}/lib/pagination/jquery.twbsPagination.min.js"></script>
<script type="text/javascript">
    var task = {pageNo:1,taskStatus:1};
    function loadList() {
        beforeLoad();
        $.post("${ctx}/task/mytrans/myTransTaskList", task)
                .done(function (data) {
                    $("#data_div").empty().html(data);
                    afterLoad();
                })
                .fail(function () {
                    $("#data_div").empty().html("<span style='color: red;'>加载失败</span>");
                    afterLoad();
                });
    }
    function loadList2(url) {
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
        if(status==4){
            var url = '${ctx}/task/mytrans/addTransWork';
            loadList2(url);
        }else if(status==3){
            var url = '${ctx}/user/mytransInviteList';
            loadList2(url);
        }else {
            task.taskStatus = status;
            loadList();
        }
    }
    function removeActive(){
        $('ul.c-dropdown-menu>li.c-active').each(function(i,e){
            $(e).removeClass("c-active");
        });
    }
    $(function () {
        $('ul.c-dropdown-menu>li>a').bind('click',function(){
            removeActive();
            var attr = $(this).attr('id');
            $(this).parent().addClass("c-active");
            filterList(attr);
        });
        loadList();
    });
</script>
</@layout.head>
<@layout.body>
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
                                我的任务
                            </a>
                            <ul class="c-dropdown-menu">
                                <li class="c-active">
                                    <a href="javascript:void(0);" id="1">正在翻译 <i class="fa fa-angle-right"></i></a>
                                </li>
                                <li>
                                    <a href="javascript:void(0);" id="2">已完成翻译 <i class="fa fa-angle-right"></i></a>
                                </li>
                                <li>
                                    <a href="javascript:void(0);" id="3">我收到的邀请 <i class="fa fa-angle-right"></i></a>
                                </li>
                                <li>
                                    <a href="javascript:void(0);" id="4">添加作品 <i class="fa fa-angle-right"></i></a>
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
