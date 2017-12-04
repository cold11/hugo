<@layout.head title="翻译团队 - 雨果翻译">
<script src="${ctx}/js/team/invite.js"></script>
<script type="text/javascript">
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
        <h3 class="c-font-20 c-font-bold">${userType}——${user.name}</h3>
        <div class="team-cont c-margin-t-40">
            <div class="team-tit c-margin-b-20">
                <h2>${user.name} <small class="qualification"><i class="fa fa-vimeo-square"></i> 已认证</small></h2>
            </div>
            <div class="row">
                <div class="col-sm-10">
                    <div class="row">
                        <div class="col-sm-3 col-md-2">
                            <div class="team-list-logo">
                                <#--<a href="team-content.html">-->
                                    <img src="${ctx}/task/getImage?fileName=${(user.filePath)?replace("\\","/")}"/>
                                <#--</a>-->
                            </div>
                        </div>
                        <div class="col-sm-9 col-md-10">
                            <h3>公司全称：<span class="c-font-green-2">${user.name}</span></h3>
                            <p class="c-font-dark-2 c-margin-t-10 c-margin-b-10 clearfix">
                                <span>公司规模：${user.language}人</span>
                                <#--<span>译文平均分：8.5</span>-->
                            </p>
                            <p class="c-font-dark-2 c-margin-b-10">
                                主要语种：<span class="c-font-blue-3">${user.language}</span>
                            </p>
                            <p class="c-font-dark-2 c-margin-b-10">
                                擅长翻译类型：${user.translationType}
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="c-margin-t-20 c-margin-b-20 text-center">
                        <button class="btn btn-success btn-lg c-square" onclick="invite('${user.userId}')">邀请试译</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="team-tab" role="tabpanel">
            <ul class="nav nav-justified" role="tablist">
                <li role="presentation" class="active">
                    <a href="#tab-1" role="tab" data-toggle="tab">公司简介</a>
                </li>
                <li role="presentation">
                    <a href="#tab-2" role="tab" data-toggle="tab">作品</a>
                </li>
                <li role="presentation">
                    <a href="#tab-3" role="tab" data-toggle="tab">用户评价</a>
                </li>
            </ul>
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane fade in active" id="tab-1">
                    ${user.companyDesc}
                </div>
                <div role="tabpanel" class="tab-pane fade" id="tab-2">
                    <div class="waterfall waterfall-5">
                        <ul class="clearfix">
                            <#list tasks as t>
                                <li>
                                    <div class="thumbnail">
                                        <a href="#">
                                            <img src="${ctx}/task/getImage?fileName=${(t.coverPath)?replace("\\","/")}"/>
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
                <div role="tabpanel" class="tab-pane fade" id="tab-3">
                        <#--${user.companyDesc}-->
                </div>
            </div>
        </div>
    </div>
 </div>

<!-- modal
================================================================================-->
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