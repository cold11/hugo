/**
 * Created by Administrator on 2016/10/4.
 */
$(function () {
    $('#login-form').bootstrapValidator({
        message: '验证失败!',
        excluded: [':disabled', ':hidden', ':not(:visible)'],
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        submitHandler: function (validator, form, submitButton) {
            loginUser(form);
        },
        fields: {
            username: {
                validators: {
                    notEmpty: {
                        message: '用户名不能为空!'
                    },
                    remote: {
                        url: APP_BASE+'/validateUser4all',
                        type: 'POST',
                        delay: 2000
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空!'
                    },
                    stringLength: {
                        min: 6,
                        max: 20,
                        message: '密码长度在6-20之间!'
                    }
                }
            }
        }
    });
})
function loginUser(form){

    var params={
        username:$('#loginForm #username').val(),
        password:$('#loginForm #password').val()
    };
    $.ajax({
        type: 'post',
        cache: false,
        dataType: 'json',
        url: APP_BASE+"/doLogin",
        data: params,
        success: function (data) {
            console.log(data);
            if(data.success) {
                window.location.href=APP_BASE+"/"+data.msg;
            } else {
                layer.msg(data.msg);
                $("#btnLogin").html("登录");
                $("#btnLogin").removeAttr("disabled");
                $("#username").focus();
            }
        },
        error : function(data){
            layer.msg(data.msg);
            $("#btnLogin").html("登录");
            $("#btnLogin").removeAttr("disabled");
        },
        beforeSend: function () {
            $("#btnLogin").html("登录中...");
            $("#btnLogin").attr('disabled',true);
        }
    });
}
