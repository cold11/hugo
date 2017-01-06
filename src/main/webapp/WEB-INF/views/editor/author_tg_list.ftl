<ul>
<#if tasks?size gt 0 >
    <#list tasks as t>
        <li>
            <span>${t.sysUser.name}</span>
            <a class="btn btn-sm btn-primary" href="javascript:;" onclick="showUserInfo('${t.userTaskId}');">查看</a>
        </li>
    </#list>
<#else>暂无人投稿
</#if>
</ul>
