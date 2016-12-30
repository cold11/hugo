
<link rel="stylesheet" type="text/css" href="${ctx}/lib/Huploadify-html5/Huploadify.css" media="all">
<script type="text/javascript" src="${ctx}/lib/Huploadify-html5/jquery.Huploadify.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap-datetimepicker.min.css" media="all">
<script type="text/javascript" src="${ctx}/lib/bootstrap/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${ctx}/js/author/addTransTeam.js"></script>



<form class="form-horizontal" method="post" action="${ctx}/author/saveTransTeam" id="transForm">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="form-group">
                <div class="col-xs-12">
                    <span class="text-danger">*</span>
                    <input type="text" class="form-control" required id="language" name="language" value="${user.language}" placeholder="擅长语种">
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-12">
                    <input type="text" name="translationType" id="translationType" class="form-control" value="${user.translationType}" placeholder="擅长翻译类型：科技、财经、小说、传记、其他">
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-12">
                    <input type="number" name="translatorCount" id="translatorCount" value="${user.translatorCount}" class="form-control" placeholder="译员数">
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-12">
                    <textarea name="companyDesc" id="companyDesc" class="form-control" rows="5" placeholder="团队介绍">${user.companyDesc}</textarea>
                </div>
            </div>
            <div class="text-center">
                <input type="submit" class="btn btn-lg btn-lg-width c-theme-btn c-btn-uppercase c-btn-login" value="提交"/>
            </div>
        </div>
    </div>
</form>

