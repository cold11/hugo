<@layout.head title="个人中心 - 雨果翻译">
<script src="${ctx}/lib/pagination/jquery.twbsPagination.min.js"></script>
<script type="text/javascript">
    <#--var task = {pageNo:1,taskStatus:0};-->
    <#--function loadList() {-->
        <#--beforeLoad();-->
        <#--$.post("${ctx}/task/trans/todo_list", task)-->
                <#--.done(function (data) {-->
                    <#--$("#data_div").empty().html(data);-->
                    <#--afterLoad();-->
                <#--})-->
                <#--.fail(function () {-->
                    <#--$("#data_div").empty().html("<span style='color: red;'>加载失败</span>");-->
                    <#--afterLoad();-->
                <#--});-->
    <#--}-->
    <#--function filterList(lan){-->
        <#--task.sourceLanguage=lan;-->
        <#--loadList()-->
    <#--}-->
    <#--function removeActive(){-->
        <#--$('ul.c-dropdown-menu>li.c-active').each(function(i,e){-->
            <#--$(e).removeClass("c-active");-->
        <#--});-->
    <#--}-->
    <#--$(function () {-->
        <#--$('ul.c-dropdown-menu>li>a').bind('click',function(){-->
            <#--removeActive();-->
            <#--var attr = $(this).attr('id');-->
            <#--$(this).parent().addClass("c-active");-->
            <#--filterList(attr);-->
        <#--});-->
        <#--loadList();-->
    <#--});-->
</script>
</@layout.head>
<@layout.body>
<div class="c-layout-breadcrumbs-1 c-subtitle c-fonts-uppercase c-fonts-bold c-bordered c-bordered-both">
    <div class="container">
        <div class="row">
            <div class="col-sm-6 clearfix">
                <ul class="c-page-breadcrumbs c-theme-nav c-fonts-regular">
                    <li>
                        <a href="task-workflow.html">工作流程</a>
                    </li>
                    <li>/</li>
                    <li>
                        <a href="release-translation-task.html">发布翻译任务</a>
                    </li>
                    <li>/</li>
                    <li>
                        <a href="release-book-topic.html">发布图书</a>
                    </li>
                </ul>
            </div>
            <div class="col-sm-6 clearfix">
                <div class="c-search-box">
                    <div class="input-group">
                        <input type="text" class="form-control">
					      	<span class="input-group-btn">
					        	<button class="btn btn-search" type="button"><i class="fa fa-search"></i></button>
					      	</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- area
================================================================================-->
<div class="area">
    <div class="container">
        <div class="row">
            <div class="col-sm-3 hidden-xs">
                <div class="c-layout-sidebar-menu c-theme c-font-14">
                    <ul class="c-sidebar-menu">
                        <li class="c-dropdown c-open">
                            <a href="javascript:;">
                                我的任务
                            </a>
                            <ul class="c-dropdown-menu">
                                <li class="c-active">
                                    <a href="#">正在翻译 <i class="fa fa-angle-right"></i></a>
                                </li>
                                <li>
                                    <a href="#">已完成翻译 <i class="fa fa-angle-right"></i></a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-sm-9">
                <div class="area-rg">
                    <div class="waterfall waterfall-4">
                        <ul class="clearfix">
                            <li>
                                <div class="thumbnail">
                                    <a href="#">
                                        <img src="images/img/pic04.jpg"/>
                                        <div class="desc">
                                            <p>fsdfsfsfs - fdsfaaaafdsf</p>
                                            <p>退隐秘密特工卷入超自然案件，退隐秘密特工卷入退隐秘密特工卷入超自然案件超自然案件，退隐秘密退隐秘密特工卷入超自然案件特工卷入超自然案件，退隐秘密特工卷入超自然案件，退隐秘密特工卷入超自然案件，退隐秘密工卷入退特工卷入超自然案件，</p>
                                        </div>
                                        <div class="desc">
                                            <p>fsdfsfsfs - fdsfaaaafdsf</p>
                                            <p>退隐秘密特工卷入超自然案件，退隐秘密特工卷入退隐秘密特工卷入超自然案件超自然案件，退隐秘密退隐秘密特工卷入超自然案件特工卷入超自然案件，退隐秘密特工卷入超自然案件，退隐秘密特工卷入超自然案件，退隐秘密工卷入退特工卷入超自然案件，</p>
                                        </div>
                                    </a>
                                    <div class="caption">
                                        <p>书名：<span>name</span></p>
                                        <p>作者：<span>writer</span></p>
                                        <a class="btn btn-sm btn-primary c-margin-t-5" href="translation-page.html" target="_blank">继续翻译</a>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="thumbnail">
                                    <a href="#">
                                        <img src="images/img/pic01.jpg"/>
                                        <div class="desc">
                                            <p>fsdfsfsfs - fdsfaaaafdsf</p>
                                            <p>退隐秘密特工卷入超自然案件，退隐秘密特工卷入退隐秘密特工卷入超自然案件超自然案件，退隐秘密退隐秘密特工卷入超自然案件特工卷入超自然案件，退隐秘密特工卷入超自然案件，退隐秘密特工卷入超自然案件，退隐秘密工卷入退特工卷入超自然案件，</p>
                                        </div>
                                    </a>
                                    <div class="caption">
                                        <p>书名：<span>name</span></p>
                                        <p>作者：<span>writer</span></p>
                                        <a class="btn btn-sm btn-primary c-margin-t-5" href="translation-page.html" target="_blank">继续翻译</a>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="thumbnail">
                                    <a href="#">
                                        <img src="images/img/pic02.jpg"/>
                                        <div class="desc">
                                            <p>fsdfsfsfs - fdsfaaaafdsf</p>
                                            <p>退隐秘密特工卷入超自然案件，退隐秘密特工卷入退隐秘密特工卷入超自然案件超自然案件，退隐秘密退隐秘密特工卷入超自然案件特工卷入超自然案件，退隐秘密特工卷入超自然案件，退隐秘密特工卷入超自然案件，退隐秘密工卷入退特工卷入超自然案件，</p>
                                        </div>
                                    </a>
                                    <div class="caption">
                                        <p>书名：<span>name</span></p>
                                        <p>作者：<span>writer</span></p>
                                        <a class="btn btn-sm btn-primary c-margin-t-5" href="translation-page.html" target="_blank">继续翻译</a>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="thumbnail">
                                    <a href="#">
                                        <img src="images/img/pic03.jpg"/>
                                        <div class="desc">
                                            <p>fsdfsfsfs - fdsfaaaafdsf</p>
                                            <p>退隐秘密特工卷入超自然案件，退隐秘密特工卷入退隐秘密特工卷入超自然案件超自然案件，退隐秘密退隐秘密特工卷入超自然案件特工卷入超自然案件，退隐秘密特工卷入超自然案件，退隐秘密特工卷入超自然案件，退隐秘密工卷入退特工卷入超自然案件，</p>
                                        </div>
                                    </a>
                                    <div class="caption">
                                        <p>书名：<span>name</span></p>
                                        <p>作者：<span>writer</span></p>
                                        <a class="btn btn-sm btn-primary c-margin-t-5" href="translation-page.html" target="_blank">继续翻译</a>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="thumbnail">
                                    <a href="#">
                                        <img src="images/img/pic04.jpg"/>
                                        <div class="desc">
                                            <p>fsdfsfsfs - fdsfaaaafdsf</p>
                                            <p>退隐秘密特工卷入超自然案件，退隐秘密特工卷入退隐秘密特工卷入超自然案件超自然案件，退隐秘密退隐秘密特工卷入超自然案件特工卷入超自然案件，退隐秘密特工卷入超自然案件，退隐秘密特工卷入超自然案件，退隐秘密工卷入退特工卷入超自然案件，</p>
                                        </div>
                                    </a>
                                    <div class="caption">
                                        <p>书名：<span>name</span></p>
                                        <p>作者：<span>writer</span></p>
                                        <a class="btn btn-sm btn-primary c-margin-t-5" href="translation-page.html" target="_blank">继续翻译</a>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="thumbnail">
                                    <a href="#">
                                        <img src="images/img/pic05.jpg"/>
                                        <div class="desc">
                                            <p>fsdfsfsfs - fdsfaaaafdsf</p>
                                            <p>退隐秘密特工卷入超自然案件，退隐秘密特工卷入退隐秘密特工卷入超自然案件超自然案件，退隐秘密退隐秘密特工卷入超自然案件特工卷入超自然案件，退隐秘密特工卷入超自然案件，退隐秘密特工卷入超自然案件，退隐秘密工卷入退特工卷入超自然案件，</p>
                                        </div>
                                    </a>
                                    <div class="caption">
                                        <p>书名：<span>name</span></p>
                                        <p>作者：<span>writer</span></p>
                                        <a class="btn btn-sm btn-primary c-margin-t-5" href="translation-page.html" target="_blank">继续翻译</a>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="thumbnail">
                                    <a href="#">
                                        <img src="images/img/pic01.jpg"/>
                                        <div class="desc">
                                            <p>fsdfsfsfs - fdsfaaaafdsf</p>
                                            <p>退隐秘密特工卷入超自然案件，退隐秘密特工卷入退隐秘密特工卷入超自然案件超自然案件，退隐秘密退隐秘密特工卷入超自然案件特工卷入超自然案件，退隐秘密特工卷入超自然案件，退隐秘密特工卷入超自然案件，退隐秘密工卷入退特工卷入超自然案件，</p>
                                        </div>
                                    </a>
                                    <div class="caption">
                                        <p>书名：<span>name</span></p>
                                        <p>作者：<span>writer</span></p>
                                        <a class="btn btn-sm btn-primary c-margin-t-5" href="translation-page.html" target="_blank">继续翻译</a>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="thumbnail">
                                    <a href="#">
                                        <img src="images/img/pic02.jpg"/>
                                        <div class="desc">
                                            <p>fsdfsfsfs - fdsfaaaafdsf</p>
                                            <p>退隐秘密特工卷入超自然案件，退隐秘密特工卷入退隐秘密特工卷入超自然案件超自然案件，退隐秘密退隐秘密特工卷入超自然案件特工卷入超自然案件，退隐秘密特工卷入超自然案件，退隐秘密特工卷入超自然案件，退隐秘密工卷入退特工卷入超自然案件，</p>
                                        </div>
                                    </a>
                                    <div class="caption">
                                        <p>书名：<span>name</span></p>
                                        <p>作者：<span>writer</span></p>
                                        <a class="btn btn-sm btn-primary c-margin-t-5" href="translation-page.html" target="_blank">继续翻译</a>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <!-- pages -->
                    <div class="c-body text-center c-margin-t-30">
                        <ul class="pagination">
                            <li>
                                <a href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <li>
                                <a href="#">1</a>
                            </li>
                            <li class="active">
                                <a href="#">2</a>
                            </li>
                            <li>
                                <a href="#">3</a>
                            </li>
                            <li>
                                <a href="#">4</a>
                            </li>
                            <li>
                                <a href="#">5</a>
                            </li>
                            <li>
                                <a href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</@layout.body>
