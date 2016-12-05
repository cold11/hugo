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
<div class="waterfall waterfall-4">
    <ul class="clearfix">
        <#list tasks.result as t>
            <li>
                <div class="thumbnail">
                    <a href="#">
                        <img src="${ctx}/task/getImage?fileName=${t.tbTask.coverPath}"/>
                        <div class="desc">
                            <p>${t.tbTask.bookname} - ${t.tbTask.publisher}</p>
                            <p>${t.tbTask.bookIntroduction}</p>
                        </div>
                    </a>
                    <div class="caption">
                        <p>书名：<span>${t.tbTask.bookname}</span></p>
                        <p>作者：<span>${t.tbTask.author}</span></p>
                        <a class="btn btn-sm btn-primary c-margin-t-5" href="${ctx}/task/trans/trialTranslation/${t.tbTask.taskId}">继续翻译</a>
                    </div>
                </div>
            </li>


        </#list>

    </ul>
</div>
<!-- pages -->
<div class="c-body text-center c-margin-t-30">
    <ul class="pagination" id="pagination">
    </ul>
</div>
<#else>
<div class="caption" style="height: 425px;">暂无记录</div>
</#if>


