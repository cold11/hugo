<@layout.head title="图书信息 - 雨果翻译">
<script type="text/javascript" src="${ctx}/lib/newsbox/jquery.bootstrap.newsbox.min.js"></script>
<style type="text/css">
    .glyphicon
    {
        margin-right:4px !important; /*override*/
    }

    .pagination .glyphicon
    {
        margin-right:0px !important; /*override*/
    }

    .pagination a
    {
        color:#555;
    }

    .panel ul
    {
        padding:0px;
        margin:0px;
        list-style:none;
    }

    .news-item
    {
        padding:4px 4px;
        margin:0px;
        border-bottom:1px dotted #555;
    }
</style>
<script type="text/javascript">
    $(function () {
        $("#hlist").bootstrapNews({
            newsPerPage: 5,
            autoplay: true,
            pauseOnHover:true,
            direction: 'up',
            newsTickerInterval: 4000,
            onToDo: function () {
                console.log(this);
            }
        });
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


        <div class="team-cont c-margin-t-10">
            <div class="team-tit c-margin-b-20">
                <h2>图书信息</h2>
            </div>
            <div class="row">
                <div class="col-sm-9">
                    <div class="row">
                        <div class="col-sm-3 col-md-2">
                            <div class="team-list-logo">
                                <#if task.coverPath??>
                                    <img src="${ctx}/task/getImage?fileName=${(task.coverPath)?replace("\\","/")}"/>
                                </#if>


                            </div>
                        </div>
                        <div class="col-sm-9 col-md-10">
                            <h3 class="c-margin-t-10 c-margin-b-20">书名：<span class="c-font-green-2">${task.bookname}</span></h3>
                            <p class="c-font-dark-2 c-margin-b-10">
                                发布人：<span>${task.sysUser.username}</span>
                            </p>
                        <#--<p class="c-font-dark-2 c-margin-b-10">-->
                        <#--电话：<span></span>-->
                        <#--</p>-->
                        </div>
                    </div>
                </div>
            <div class="col-sm-3">
                <#if history?size gt 0 >
                <!-- 浏览历史 -->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <span class="glyphicon glyphicon-list-alt"></span><b>浏览历史</b></div>
                    <div class="panel-body">
                        <div class="row">
                            <div>
                                <ul id="hlist" style="overflow-y: hidden; height: 150px;">
                                    <#list history as h>
                                        <li class="news-item">${h.username}在${h.dateStr}浏览过该图书</li>

                                    </#list>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="panel-footer">

                    </div>
                </div>
                </#if>

            </div>
            </div>
        </div>
        <div class="team-tab" role="tabpanel">
            <ul class="nav nav-justified" role="tablist">
                <li role="presentation" class="active">
                    <a href="#tab-1" role="tab" data-toggle="tab">中文简介</a>
                </li>
                <li role="presentation">
                    <a href="#tab-2" role="tab" data-toggle="tab">外文简介</a>
                </li>
            </ul>
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane fade in active" id="tab-1">
                ${task.bookIntroduction!'暂无中文简介'}
                </div>
                <div role="tabpanel" class="tab-pane fade" id="tab-2">
                ${task.bookFlIntroduction!'暂无外文简介'}
                </div>
            </div>
        </div>
    </div>
</div>
</@layout.body>