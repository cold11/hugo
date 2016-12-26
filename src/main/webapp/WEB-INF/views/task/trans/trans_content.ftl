<@layout.head title="翻译 - 雨果翻译 - 试译">
<script type="text/javascript">
    function submit(type){
        var trans = $('#trans').val();
        if($.trim(trans)==''){
            layer.alert("译文不能为空!")
            return;
        }
        console.log('11111');
        $.post("${ctx}/user/mytransInvite/saveTrialTranslation",{transMessageId:'${t.transMessageId}',transResult:trans,status:type}).done(function(data){
            console.log(data);
            if(data.success){
                if(type==2){
                    alert(data.msg);
                    location.href = '${ctx}/task/trans/todo';
                }else{
                    layer.alert(data.msg);
                }

            }else layer.alert(data.msg);
        }).fail(function(){
            layer.alert('操作失败!');
        });
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
        <div class="translation-box">
            <div class="c-margin-b-40">
                <p class="c-font-18 c-margin-t-10 c-margin-b-10">邀请人：<b class="c-font-16">${t.sysUser.name}</b></p>
                <p class="c-font-18 c-margin-t-10 c-margin-b-10">电话：<b class="c-font-16">${t.sysUser.phone}</b></p>
                <p class="c-font-18">简介：<span class="c-font-16">${t.sysUser.personalIntro}</span></p>
            </div>
            <div class="row">
                <div class="col-sm-6">
                    <h3 class="c-margin-t-10 c-margin-b-10">原文</h3>
                    <div class="form-control translation-txt">${t.transContent}
                    </div>
                </div>
                <div class="col-sm-6">
                    <h3 class="c-margin-t-10 c-margin-b-10">译文</h3>
                    <textarea class="form-control" rows="20" name="trans" id="trans">${t.transResult}</textarea>
                </div>
            </div>
            <#if !isSubmit>
                <div class="text-right c-margin-t-20">
                    <input type="button" class="btn btn-primary btn-lg btn-lg-width" value="保 存" onclick="submit(1)" />
                    <input type="button" class="btn btn-success btn-lg btn-lg-width" value="提 交" onclick="submit(2)" />
                </div>
            </#if>
        </div>
    </div>
</div>

</@layout.body>
