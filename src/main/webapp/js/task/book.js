/**
 * Created by ohj on 2016/10/21.
 */
var upload1;
var upload2;
$(function () {
    var eBookTypes = $("#eBookType");
    eBookTypes.select2({
        placeholder: "电子书类型选择",
        allowClear: true
    });
    $('#bookForm').bootstrapValidator({
        message: '验证失败!',
        excluded: [':disabled', ':hidden', ':not(:visible)'],
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        submitHandler: function (validator, form, submitButton) {
            //validator.defaultSubmit();
            submitForm(form);
        },
        fields: {
            publishType: {
                validators: {
                    notEmpty: {
                        message: '出版类型不能为空!'
                    }
                }
            },
            eBookType: {
                validators: {
                    notEmpty: {
                        message: '请选择电子书类型!'
                    }
                }
            },
            classId:{
                validators: {
                    notEmpty: {
                        message: '请选择分类!'
                    }
                }
            },
            copyrightType: {
                validators: {
                    notEmpty: {
                        message: '请选择版权类型!'
                    }
                }
            },
            copyrightDescript: {
                validators: {
                    notEmpty: {
                        message: '版权及翻译情况说明不能为空!'
                    }
                }
            },
            bookname:{
                validators: {
                    notEmpty: {
                        message: '书名不能为空!'
                    }
                }
            },
            //author:{
            //    validators: {
            //        notEmpty: {
            //            message: '作者不能为空!'
            //        }
            //
            //    }
            //},
            //authorIntroduction:{
            //    validators: {
            //        notEmpty: {
            //            message: '作者简介不能为空!'
            //        }
            //    }
            //},
            bookIntroduction:{
                validators: {
                    notEmpty: {
                        message: '中文简介不能为空!'
                    }
                }
            }
            //bookFlIntroduction:{
            //    validators: {
            //        notEmpty: {
            //            message: '外文简介不能为空!'
            //        }
            //    }
            //}
        }
    });
    upload1 = $('#upload1').Huploadify({
        auto:true,
        multi:false,
        removeTimeout:9999999,
        showUploadedPercent:true,
        showUploadedSize:true,
        buttonText:'上传图书封面',
        fileSizeLimit:10240,
        queueSizeLimit  : 1,
        fileTypeExts:'*.jpeg;*.jpg;*.png;*.bmp',
        uploader:APP_BASE+'/task/upload',
        onUploadSuccess: function (file, data, response) {
            data = $.parseJSON(data);
            if(data.success){
                $('#coverPath').val(data.msg);
            }else{
                layer.alert('上传文件出错！');
            }

        },
        onUploadError: function (file, errorCode, errorMsg, errorString) {
            layer.alert('上传文件出错！'+errorString+"====="+errorMsg);
        }
    });
    upload2 = $('#upload2').Huploadify({
        auto:true,
        multi:false,
        removeTimeout:9999999,
        showUploadedPercent:true,
        showUploadedSize:true,
        buttonText:'上传图书试读word文档',
        fileSizeLimit:10240,
        queueSizeLimit  : 1,
        fileTypeExts:'*.xls;*.doc;*.docx;*.txt;*.pdf',
        uploader:APP_BASE+'/task/upload',
        onUploadSuccess: function (file, data, response) {
            data = $.parseJSON(data);
            if(data.success){
                $('#docPath').val(data.msg);
            }else{
                layer.alert('上传文件出错！');
            }

        },
        onUploadError: function (file, errorCode, errorMsg, errorString) {
            layer.alert('上传文件出错！'+errorString+"====="+errorMsg);
        }
    });
});

function submitForm(form){
    $.post(APP_BASE+"/task/book_publish", form.serialize(), function(result) {
        if (result.success) {
            alert(result.msg);
            //location.href=APP_BASE+"/login";
        } else {
            layer.msg("发布失败");
            $('#'+form.attr('id')).bootstrapValidator('disableSubmitButtons', false);

        }
    }, 'json');
}