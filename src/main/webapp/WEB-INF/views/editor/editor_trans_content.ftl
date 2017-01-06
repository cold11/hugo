<ul>
<#if tasks?size gt 0 >
    <#list tasks as t>
    <li>
        <span>${t.sysUser.name}团队</span>
        <a class="btn btn-sm btn-primary" href="javascript:;" onclick="showTransInfo('${t.userTaskId}');">查看</a>
    </li>
    </#list>
<#else>暂无人试译
</#if>
</ul>
