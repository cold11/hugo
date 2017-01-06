<script src="${ctx}/js/user/updateUserInfo.js" type="text/javascript"></script>
<div class="user-info">
    <form name="passwordForm" id="passwordForm" class="form-horizontal" method="post">
        <div class="form-group">
            <div class="row">
                <div class="col-sm-3 text-right hidden-xs"><b>原密码：</b></div>
                <div class="col-sm-8 col-md-6">
                    <input type="password" class="form-control" id="oldPassword" required name="oldPassword" placeholder="原密码">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-sm-3 text-right hidden-xs"><b>新密码：</b></div>
                <div class="col-sm-8 col-md-6">
                    <input type="password" class="form-control" id="password" name="password" required placeholder="新密码">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-sm-3 text-right hidden-xs"><b>确认密码：</b></div>
                <div class="col-sm-8 col-md-6">
                    <input type="password" class="form-control" id="equalToPassword" name="equalToPassword" placeholder="确认密码">
                </div>
            </div>
        </div>
        <div class="text-center c-margin-t-50">
            <input type="submit" class="btn btn-lg btn-lg-width c-theme-btn c-btn-uppercase c-btn-login" value="确认修改"/>
        </div>
    </form>
</div>