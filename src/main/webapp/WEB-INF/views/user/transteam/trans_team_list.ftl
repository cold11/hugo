<#if users.totalPages gt 0 >
<script type="text/javascript">
    $(function() {
        $("#pagination").twbsPagination({
            first: "首页",
            prev: "上一页",
            next: "下一页",
            last: "尾页",
            startPage:${users.pageNo},
            totalPages: ${users.totalPages},
            visiblePages: 10,
            onPageClick: function (event, page) {
                task.pageNo = page;
                loadList();
            }
        });

    });
</script>

        <#list users.result as t>
        <div class="team-list">
            <div class="row">
                <div class="col-sm-10">
                    <div class="team-list-left row">
                        <div class="col-sm-3 col-md-2">
                            <div class="team-list-logo">
                                <a href="${ctx}/user/transteamcontent/${t.userId}">
                                    <img src="${ctx}/task/getImage?fileName=${t.filePath}"/>
                                </a>
                            </div>
                        </div>
                        <div class="col-sm-9 col-md-10">
                            <a href="${ctx}/user/transteamcontent/${t.userId}"><h3><span class="c-font-blue-1">${t.name}</span> <small>编号：${t.userNo}</small></h3></a>
                            <p class="c-font-dark-2 c-margin-t-5 c-margin-b-10 clearfix">
                                <span class="c-font-blue-3">${t.language}</span>
                                <#--<span>评价：15</span>-->
                                <span>作品：${t.tbTasks?size}</span>
                                <span>公司规模：<b class="c-font-blue-3">${t.translatorCount}人</b></span>
                            </p>
                            <p class="c-font-grey-3 c-margin-b-10">${t.companyDesc}</p>
                        </div>
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="c-margin-t-20 c-margin-b-20 text-center">
                        <button class="btn btn-success btn-lg c-square inviteCSS" id="${t.userId}">邀请试译</button>
                    </div>
                </div>
            </div>
        </div>
        </#list>

<!-- pages -->
<div class="c-body text-center c-margin-t-30">
    <ul class="pagination" id="pagination">
    </ul>
</div>
<#else>
<div class="caption" style="height: 425px;">暂无记录</div>
</#if>


