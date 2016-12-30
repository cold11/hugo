<#if tasks.totalPages gt 0 >
<script type="text/javascript">
    $(function() {
        $("#pagination").twbsPagination({
            first: "首页",
            prev: "上一页",
            next: "下一页",
            last: "尾页",
            startPage:${tasks.pageNo},
            totalPages: ${tasks.totalPages},
            visiblePages: 10,
            onPageClick: function (event, page) {
                task.pageNo = page;
                loadList();
            }
        });
    });
</script>
<#else>
<div class="col-sm-10 col-sm-offset-1 c-margin-b-20">
    <div class="appoint-list img-thumbnail">
        <div class="c-font-15 appoint-tit">
            <p>暂无结果</p>
        </div></div></div>
</#if>

<#list tasks.result as t>
<div class="col-sm-10 col-sm-offset-1 c-margin-b-20">
    <div class="appoint-list img-thumbnail"style="width:100%">
        <div class="c-font-15 appoint-tit">
            <p>
                <b>约稿标题：</b>
                <span class="c-font-blue-1">${t.tbTask.bookname}</span>
            </p>
            <p>
                <b>约稿需求：</b>
                <span>${t.tbTask.bookIntroduction}</span>
            </p>
            <p>
                <b>投稿说明：</b>
                <span>${t.memo}</span>
            </p>
        </div>
        <div class="row c-margin-t-20">
            <div class="col-sm-6">
                <div class="c-font-grey-3">
                    <p>${t.tbTask.sysUser.releaseAgency}</p>
                    <p>${t.tbTask.sysUser.name}</p>
                </div>
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