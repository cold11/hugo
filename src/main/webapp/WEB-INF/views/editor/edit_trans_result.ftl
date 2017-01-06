<h3 class="c-font-16 c-font-bold">译员团队信息</h3>
<div class="c-margin-t-10 c-margin-b-20">
    <p>用 户 名：<span>${t.sysUser.name}</span></p>
    <p>联系方式：<span>${t.sysUser.phone}</span></p>
    <p>译员数量：<span>${t.sysUser.translatorCount}</span></p>
    <p>公司名称：<span>${t.sysUser.releaseCompany}</span></p>
    <#--<p>学校名称：<span>${t.sysUser.name}</span></p>-->
    <p>公司介绍：<span>${t.sysUser.companyDesc}</span></p>
</div>
<h3 class="c-font-16 c-font-bold">试译结果</h3>
<div class="c-margin-t-10 c-margin-b-20">
    <div class="form-control translation-txt">
        <p>${t.trans}</p>

    </div>
</div>