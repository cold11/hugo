<@layout.head title="个人中心 - 雨果翻译">
<script src="${ctx}/lib/pagination/jquery.twbsPagination.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/lib/Huploadify-html5/Huploadify.css" media="all">
<script type="text/javascript" src="${ctx}/lib/Huploadify-html5/jquery.Huploadify.js"></script>
<script src="${ctx}/js/editor/editorHome.js"></script>
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
    function filterList(type){
        var url = '${ctx}/editor/trans_list';
        if(type==4){
            url='${ctx}/editor/myInviteWorkList';
        }else  if(type==5){
            url = '${ctx}/editor/transInviteList';
        }else if(type==10){
            url = '${ctx}/user/userInfo';
        }else if(type==11){
            url = '${ctx}/user/updatePsd';
        }
        loadList(url)
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
           // console.log(attr);
            filterList(attr);
        });
        var url = '${ctx}/editor/trans_list';
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
                                我的图书
                            </a>
                            <ul class="c-dropdown-menu">
                                <#--<li class="c-active">-->
                                    <#--<a href="#" id="0">正在翻译 <i class="fa fa-angle-right"></i></a>-->
                                <#--</li>-->
                                <#--<li>-->
                                    <#--<a href="#" id="2">已完成翻译 <i class="fa fa-angle-right"></i></a>-->
                                <#--</li>-->
                                <#--<li>-->
                                    <#--<a href="#">已发布 <i class="fa fa-angle-right"></i></a>-->
                                <#--</li>-->
                                <li class="c-active">
                                    <a href="#" id="3">试译结果 <i class="fa fa-angle-right"></i></a>
                                </li>
                                <li>
                                    <a href="#" id="4">我的约稿<i class="fa fa-angle-right"></i></a>
                                </li>
                                <li>
                                    <a href="#" id="5">我的邀请<i class="fa fa-angle-right"></i></a>
                                </li>
                                <#--<li>-->
                                    <#--<a href="#">已完成 <i class="fa fa-angle-right"></i></a>-->
                                <#--</li>-->
                            </ul>
                        </li>
                        <li class="c-dropdown c-open">
                            <a href="javascript:;">
                                个人资料
                            </a>
                            <ul class="c-dropdown-menu">
                                <li>
                                    <a href="javascript:void(0);" id="10">个人资料维护 <i class="fa fa-angle-right"></i></a>
                                </li>
                                <li>
                                    <a href="javascript:void(0);" id="11">修改密码 <i class="fa fa-angle-right"></i></a>
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


<!-- translation
================================================================================-->
<!-- translation-list -->
<div class="modal fade" id="translation-list" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content c-square">
            <div class="modal-header c-no-border">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <div class="translation-list" id="translation-content">
                    暂无人试译

                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="translation-result" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content c-square">
            <div class="modal-header c-no-border">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body" id="translation-info">

            </div>
        </div>
    </div>
</div>
</@layout.body>
