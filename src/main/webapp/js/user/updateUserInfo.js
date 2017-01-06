/**
 * Created by ohj on 2017/1/5.
 */
var uploadLimit = 1;
var uploadify;
$(function () {
    $('#passwordForm').bootstrapValidator({
        message: '验证失败!',
        excluded: [':disabled', ':hidden', ':not(:visible)'],
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        submitHandler: function (validator, form, submitButton) {
            $.post(APP_BASE+'/user/updatePassword', form.serialize()).done(function(result) {
                if (result.success) {
                    layer.alert(result.msg);
                } else {
                    layer.alert(result.msg);
                    $('#'+form.attr('id')).bootstrapValidator('disableSubmitButtons', false);

                }
            }).fail(function(){
                layer.alert('出现错误');
            });
        },
        fields: {
            oldPassword: {
                validators: {
                    notEmpty: {
                        message: '原密码不能为空!'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '新密码不能为空!'
                    },
                    stringLength: {
                        min: 6,
                        max: 20,
                        message: '密码长度在6-20之间!'
                    },
                    identical: {
                        field: 'equalToPassword',
                        message: '两次密码输入不一致!'
                    }
                }
            },
            equalToPassword: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空!'
                    },
                    identical: {
                        field: 'password',
                        message: '两次密码输入不一致!'
                    }
                }
            }
        }
    });

    $('#userForm').bootstrapValidator({
        message: '验证失败!',
        excluded: [':disabled', ':hidden', ':not(:visible)'],
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        submitHandler: function (validator, form, submitButton) {
            $.post(APP_BASE+'/user/updateUserInfo', form.serialize()).done(function(result) {
                if (result.success) {
                    layer.alert(result.msg);
                } else {
                    layer.alert(result.msg);
                    $('#'+form.attr('id')).bootstrapValidator('disableSubmitButtons', false);

                }
            }).fail(function(){
                layer.alert('出现错误');
            });
        },
        fields: {
            name: {
                validators: {
                    notEmpty: {
                        message: '姓名不能为空!'
                    }
                }
            },
            email: {
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空!'
                    },
                    emailAddress: {
                        message: '邮箱格式错误!'
                    },
                    remote: {
                        url: APP_BASE+'/user/validateUserInfo',
                        type: 'POST',
                        delay: 2000
                    }
                }
            },
            phone: {
                //threshold: 7,
                validators: {
                    notEmpty: {
                        message: '电话号码不能为空!'
                    },
                    regexp: {
                        regexp: /1(3|4|5|7|8)\d{9}$/,
                        message: '电话号码格式错误!'
                    },
                    remote: {
                        url: APP_BASE+'/user/validateUserInfo',
                        type: 'POST',
                        delay: 2000
                    }
                }
            }
        }
    });

    uploadify = $('#upload1').Huploadify({
        auto:true,
        multi:false,
        removeTimeout:9999999,
        showUploadedPercent:true,
        showUploadedSize:true,
        buttonText:'上传头像',
        fileSizeLimit:10240,
        queueSizeLimit  : uploadLimit,
        fileTypeExts:'*.jpeg;*.jpg;*.png;*.bmp',
        uploader:APP_BASE+'/user/upload',
        onUploadSuccess: function (file, data, response) {
            data = $.parseJSON(data);
            if(data.success){
                $('#filePath').val(data.msg);
                $('#prviewImg').attr('src',APP_BASE+'/task/getImage?fileName='+data.msg);
            }else{
                layer.alert('上传文件出错！');
            }

        },
        onUploadError: function (file, errorCode, errorMsg, errorString) {
            layer.alert('上传文件出错！'+errorString+"====="+errorMsg);
        },
         onCancel:function(){
             uploadify.Huploadify('settings','uploadLimit', ++uploadLimit);
         }
    });
});