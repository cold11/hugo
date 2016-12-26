<@layout.head title="${title} - 雨果翻译">
<script src="${ctx}/lib/pagination/jquery.twbsPagination.min.js"></script>
<script type="text/javascript">
    var task = {pageNo:1,copyrightType:${copyrightType}};
    function loadList() {
        beforeLoad();
        $.post("${ctx}/opus/opus_list", task)
                .done(function (data) {
                    $("#data_div").empty().html(data);
                    afterLoad();
                })
                .fail(function () {
                    $("#data_div").empty().html("<span style='color: red;'>加载失败</span>");
                    afterLoad();
                });
    }
    function filterList(classId){
        task.classId=classId;
        loadList()
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
<#assign class2_css=""/>
<#assign class3_css=""/>
<#if title=="中文版选题">
    <#assign class2_css="c-active"/>
<#else><#assign class3_css="c-active"/>
</#if>
<@layout.body class2=class2_css class3=class3_css>
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
    <p class="offcanvas-btn visible-xs">
        <button type="button" class="btn btn-warning btn-xs" data-toggle="offcanvas">Toggle menu</button>
    </p>
    <div class="container">
        <div class="row row-offcanvas row-offcanvas-left">
            <div class="col-sm-3 sidebar-offcanvas">
                <div class="c-layout-sidebar-menu c-theme c-font-14">
                    <ul class="c-sidebar-menu">
                        <li class="c-dropdown c-open">
                            <a href="javascript:;">
                                全部图书<span class="c-arrow"></span>
                            </a>
                            <ul class="c-dropdown-menu">
                                <#list cList as c>
                                    <li>
                                        <a href="javascript:void(0)" id="${c.id}">${c.name} <i class="fa fa-angle-right"></i></a>
                                    </li>
                                </#list>

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
