
<link rel="stylesheet" type="text/css" href="${ctx}/lib/Huploadify-html5/Huploadify.css" media="all">
<script type="text/javascript" src="${ctx}/lib/Huploadify-html5/jquery.Huploadify.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap-datetimepicker.min.css" media="all">
<script type="text/javascript" src="${ctx}/lib/bootstrap/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${ctx}/js/language.js"></script>
<script type="text/javascript" src="${ctx}/js/task/addworks.js"></script>



        <form class="form-horizontal" method="post" id="transForm">
            <input type="hidden" name="coverPath" id="coverPath"/>
            <div class="row">
                <div class="col-md-8 col-md-offset-2">
                    <div class="form-group">
                        <div class="col-xs-12">
                            <input type="text" name="bookname" id="bookname" class="form-control" placeholder="书名">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <input type="text" class="form-control" required id="author" name="author" placeholder="作者">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-6">
                            <select class="form-control" name="sourceLanguage" id="sourceLanguage">
                                <option value="" disabled selected>原文语种</option>
                            </select>
                        </div>
                        <div class="col-xs-6">
                            <select class="form-control" name="targetLanguage" id="targetLanguage">
                                <option value="" disabled selected>目标语种</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-4">
                            <input type="text" name="words" id="words" class="form-control" placeholder="字数">
                        </div>
                        <div class="col-xs-8">
                            <input type="text" name="publisher" id="publisher" class="form-control" placeholder="原出版商">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <textarea name="authorIntroduction" id="authorIntroduction" class="form-control" rows="5" placeholder="作者简介"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <textarea name="bookIntroduction" id="bookIntroduction" class="form-control" rows="5" placeholder="图书简介"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-12">
                            <div id="upload"></div>
                        <#--<div class="file clearfix">-->
                        <#--<a href="javascript:;" class="btn photo-add">-->
                        <#--<input type="file" name="coverFile" id="coverFile" class="file-input" />-->
                        <#--<i class="fa fa-upload"></i> 上传图书封面-->
                        <#--</a>-->
                        <#--<div class="file-img"></div>-->
                        <#--</div>-->
                        </div>
                    </div>
                    <div class="text-center">
                        <input type="submit" class="btn btn-lg btn-lg-width c-theme-btn c-btn-uppercase c-btn-login" value="确认发布"/>
                    </div>
                </div>
            </div>
        </form>

