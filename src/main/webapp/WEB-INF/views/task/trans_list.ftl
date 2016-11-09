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
        var nowTime = new Date($('#nowTime').val()).getTime();
        var timer = setInterval(function(){
            $(".endtime").each(function(){
                var obj = $(this);
                var endTime = new Date(obj.attr('value'));
                var nMS=endTime.getTime() - nowTime;
                var myD=Math.floor(nMS/(1000 * 60 * 60 * 24)); //天
                var myH=Math.floor(nMS/(1000*60*60)) % 24; //小时
                var myM=Math.floor(nMS/(1000*60)) % 60; //分钟
                var myS=Math.floor(nMS/1000) % 60; //秒
                //var myMS=Math.floor(nMS/100) % 10; //拆分秒
                if(myD>= 0){
                    //var str = myD+"天"+myH+"小时"+myM+"分"+myS+"."+myMS+"秒";
                    var str = myD+"天"+myH+"小时"+myM+"分"+myS+"秒";
                }else{
                    var str = "已结束！";
                    var aEle = obj.parent().next();
                    $(aEle).addClass('disabled');
                    clearInterval(timer);
                }
                obj.html(str);
                nowTime+=1000;
            });
        }, 1000); //每个1秒执行一次
    });
</script>
</#if>
<div class="waterfall waterfall-4">
    <input type="hidden" id="nowTime" value="${.now?string("yyyy/MM/dd,HH:mm:ss")}" />
    <ul class="clearfix">
<#list tasks.result as t>
        <li>
            <div class="thumbnail">
                <a href="#">
                    <img src="${ctx}/task/getImage?fileName=${t.coverPath}"/>
                    <div class="desc">
                        <p>${t.bookname} - ${t.publisher}</p>
                        <p>${t.transContent}</p>
                    </div>
                </a>
                <div class="caption">
                    <p>书名：<span>${t.bookname}</span></p>
                    <p>作者：<span>${t.author}</span></p>
                    <p>需要方向：<span>direction</span></p>
                    <p>距离截止时间：<b class="c-font-green-4 endtime" value="${t.transExpirationDateStr}">7天4小时</b></p>
                    <a class="btn btn-sm btn-primary c-margin-t-5" href="translation-page.html">申请试译</a>
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