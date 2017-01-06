<script src="${ctx}/js/user/updateUserInfo.js" type="text/javascript"></script>
<div class="user-info">
    <form  name="userForm" id="userForm" class="form-horizontal" method="post" enctype="multipart/form-data">
        <input type="hidden" name="filePath" id="filePath" />
        <div class="form-group">
            <div class="row">
                <div class="col-sm-3 text-right hidden-xs"><b>姓名：</b></div>
                <div class="col-sm-8 col-md-6">
                    <input type="text" class="form-control" id="name" name="name" value="${user.name}" placeholder="姓名">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-sm-3 text-right hidden-xs"><b>邮 箱：</b></div>
                <div class="col-sm-8 col-md-6">
                    <input type="text" class="form-control" id="email" name="email" value="${user.email}" placeholder="邮箱">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-sm-3 text-right hidden-xs"><b>电 话：</b></div>
                <div class="col-sm-8 col-md-6">
                    <input type="text" class="form-control" id="phone" name="phone" placeholder="电话" value="${user.phone}">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-sm-3 text-right hidden-xs"><b>个人简介：</b></div>
                <div class="col-sm-8 col-md-6">
                    <textarea name="personalIntro" id="personalIntro" class="form-control" rows="5" placeholder="个人简介">${user.personalIntro}</textarea>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-sm-3 text-right hidden-xs"><b>公司介绍：</b></div>
                <div class="col-sm-8 col-md-6">
                    <textarea name="companyDesc" id="companyDesc" class="form-control" rows="5" placeholder="公司介绍">${user.companyDesc}</textarea>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-sm-3 text-right hidden-xs"><b>头 像：</b></div>
                <div class="col-sm-8 col-md-6">
                    <div class="file clearfix">
                        <div class="file-img head-img"><a class="file-del" href="javascript:;"><img id="prviewImg" src="${ctx}/task/getImage?fileName=${user.filePath}" alt="" /></a></div>
                        <div id="upload1"></div>
                        <#--<div class="file-img head-img"><a class="file-del" href="javascript:;"><img src="${ctx}/task/getImage?fileName=${filePath}" alt="" /></a></div>-->
                        <#--<a href="javascript:;" class="btn head-add">-->
                            <#--<input type="file" class="file-input" accept="image/*" />-->
                            <#--<i class="fa fa-pencil"></i> 更改头像-->
                        <#--</a>-->
                    </div>
                </div>
            </div>
        </div>
        <div class="text-center c-margin-t-50">
            <input type="submit" class="btn btn-lg btn-lg-width c-theme-btn c-btn-uppercase c-btn-login" value="确认修改"/>
        </div>
    </form>
</div>
<#--<script>-->
    <#--$('#file-0a').fileinput({-->
        <#--language: 'zh',-->
        <#--uploadUrl: 'uploadMultipleFile',-->
        <#--allowedPreviewTypes : ['image']-->

    <#--});-->

    <#--$('#file-0a').on('fileuploaderror', function(event, data, previewId, index) {-->
        <#--var form = data.form, files = data.files, extra = data.extra,-->
                <#--response = data.response, reader = data.reader;-->
        <#--console.log(data);-->
        <#--console.log('File upload error');-->
    <#--});-->

    <#--$('#file-0a').on('fileerror', function(event, data) {-->
        <#--console.log(data.id);-->
        <#--console.log(data.index);-->
        <#--console.log(data.file);-->
        <#--console.log(data.reader);-->
        <#--console.log(data.files);-->
    <#--});-->

    <#--$('#file-0a').on('fileuploaded', function(event, data, previewId, index) {-->
        <#--var form = data.form, files = data.files, extra = data.extra,-->
                <#--response = data.response, reader = data.reader;-->
        <#--console.log('File uploaded triggered');-->
    <#--});-->
<#--</script>-->