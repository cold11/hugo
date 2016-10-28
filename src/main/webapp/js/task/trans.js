/**
 * Created by ohj on 2016/10/21.
 */
$(function () {
    $('#transForm').bootstrapValidator({
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
            bookname: {
                validators: {
                    notEmpty: {
                        message: '书名不能为空!'
                    }
                }
            },
            sourceLanguage: {
                validators: {
                    notEmpty: {
                        message: '原文语种不能为空!'
                    }
                }
            },
            targetLanguage: {
                validators: {
                    notEmpty: {
                        message: '目标语种不能为空!'
                    }
                }
            },
            words: {
                validators: {
                    digits: {
                        message: '字数只能输入数字!'
                    }
                }
            },
            transContent:{
                validators: {
                    notEmpty: {
                        message: '试译内容不能为空!'
                    }
                }
            },
            transExpirationDate:{
                validators: {
                    //notEmpty: {
                    //    message: '试译截止时间不能为空!'
                    //},
                    data: {
                        message: '试译截止时间输入错误!'
                    }
                }
            },
            'fileselect[]':{
                validators: {
                    notEmpty: {
                        message: '请上传封面!'
                    },
                    file: {
                        extension: 'jpeg,jpg,png,bmp',
                        type: 'image/jpeg,image/png,image/x-ms-bmp',
                        maxSize: 1024 * 1024 * 10,   // 10 MB
                        message: '请上传大小不超过10MB的jpeg,jpg,png,bmp文件!'
                    }
                }
            }
        }
    });
    $('#upload').Huploadify({
        auto:true,
        multi:false,
        removeTimeout:9999999,
        showUploadedPercent:true,
        showUploadedSize:true,
        buttonText:'上传图书封面',
        fileSizeLimit:10240,
        queueSizeLimit  : 1,
        //btnClass : 'btn photo-add',
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

});

function submitForm(form){
    $.post(APP_BASE+"/task/trans_publish", form.serialize(), function(result) {
        if (result.success) {
            alert(result.msg);
            //location.href=APP_BASE+"/login";
        } else {
            layer.msg("发布失败");
            $('#'+form.attr('id')).bootstrapValidator('disableSubmitButtons', false);

        }
    }, 'json');
}
