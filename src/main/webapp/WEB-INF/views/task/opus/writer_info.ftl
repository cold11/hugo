<@layout.head title="翻译团队 - 雨果翻译">
<script type="text/javascript">
    function invite(userId){
        <@shiro.hasRole name="ROLE_EDITOR">
            $.post("${ctx}/task/editor/invitation",{userId:userId}).done(function(data){
                layer.alert(data.msg);
            }).fail(function(){
                layer.alert("出现错误");
            }).always(function(){
                $('#'+userId).removeAttr("disabled");
            });
        </@shiro.hasRole>
        <@shiro.lacksRole name="ROLE_EDITOR">
            layer.alert('权限不够,不能发出邀请!',{icon:8});
        </@shiro.lacksRole>
    }
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
                <h2>作者信息 <small class="qualification no"><i class="fa fa-vimeo-square"></i> 未认证</small></h2>
            </div>
            <div class="row">
                <div class="col-sm-10">
                    <div class="row">
                        <div class="col-sm-3 col-md-2">
                            <div class="team-list-logo">

                                <img src="${ctx}/task/getImage?fileName=${user.filePath}"/>

                            </div>
                        </div>
                        <div class="col-sm-9 col-md-10">
                            <h3 class="c-margin-t-10 c-margin-b-20">姓名：<span class="c-font-green-2">${user.name}</span></h3>
                            <p class="c-font-dark-2 c-margin-b-10">
                                邮箱：<span>${user.email}</span>
                            </p>
                            <p class="c-font-dark-2 c-margin-b-10">
                                电话：<span>${user.phone}</span>
                            </p>
                        </div>
                    </div>
                </div>
                <#--<div class="col-sm-2">-->
                    <#--<div class="c-margin-t-20 c-margin-b-20 text-center">-->
                        <#--<a class="btn btn-success btn-lg c-square" href="javascript:;">联系作者</a>-->
                    <#--</div>-->
                <#--</div>-->
            </div>
        </div>
        <div class="team-tab" role="tabpanel">
            <ul class="nav nav-justified" role="tablist">
                <li role="presentation" class="active">
                    <a href="#tab-1" role="tab" data-toggle="tab">作者简介</a>
                </li>
                <li role="presentation">
                    <a href="#tab-2" role="tab" data-toggle="tab">作品</a>
                </li>
            </ul>
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane fade in active" id="tab-1">
                    ${user.personalIntro}
                </div>
                <div role="tabpanel" class="tab-pane fade" id="tab-2">
                    <div class="waterfall waterfall-5">
                        <ul class="clearfix">
                            <#list tasks as t>
                                <li>
                                    <div class="thumbnail">
                                        <a href="#">
                                            <img src="${ctx}/task/getImage?fileName=${t.coverPath}"/>
                                            <div class="desc">
                                                <p>${t.bookname} - ${t.publisher}</p>
                                                <p>${t.transContent}</p>
                                            </div>
                                        </a>
                                        <div class="caption">
                                            <p>书名：<span>${t.bookname}</span></p>
                                            <p>作者：<span>${t.author}</span></p>
                                        </div>
                                    </div>
                                </li>
                            </#list>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</@layout.body>