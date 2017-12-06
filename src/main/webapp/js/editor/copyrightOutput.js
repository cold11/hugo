/**
 * Created by ohj on 2017/12/6.
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
            author:{
               validators: {
                   notEmpty: {
                       message: '作者不能为空!'
                   }

               }
            },
            authorIntroduction:{
               validators: {
                   notEmpty: {
                       message: '作者简介不能为空!'
                   }
               }
            },
            bookIntroduction:{
                validators: {
                    notEmpty: {
                        message: '中文简介不能为空!'
                    }
                }
            }
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
});

function submitForm(form){
    $.post(APP_BASE+"/task/editor/pubCopyRightOutput", form.serialize(), function(result) {
        if (result.success) {
            layer.alert(result.msg, {
                skin: 'layui-layer-molv', //样式类名
                closeBtn: 1
                ,anim: 4 //动画类型
            }, function(){
                location.href=APP_BASE;
            });
        } else {
            layer.msg("发布失败");
            $('#'+form.attr('id')).bootstrapValidator('disableSubmitButtons', false);

        }
    }, 'json');
}