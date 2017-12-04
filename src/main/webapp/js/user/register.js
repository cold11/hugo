/**
 * Created by Administrator on 2016/10/4.
 */
$(function () {
    $("#email").mailAutoComplete();
    function randomNumber(min, max) {
        return Math.floor(Math.random() * (max - min + 1) + min);
    };
    $('#captchaOperation').html([randomNumber(1, 100), '+', randomNumber(1, 200), '='].join(''));
    $('#releaseForm').bootstrapValidator({
        message: '验证失败!',
        excluded: [':disabled', ':hidden', ':not(:visible)'],
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        submitHandler: function (validator, form, submitButton) {
            saveUser(form);
        },
        fields: {
            releaseRole: {
                validators: {
                    notEmpty: {
                        message: '请选择角色!'
                    }
                }
            },
            username: {
                validators: {
                    notEmpty: {
                        message: '用户名不能为空!'
                    },
                    stringLength: {
                        min: 4,
                        max: 15,
                        message: '用户名长度在4-15之间!'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: 'The username can only consist of alphabetical, number and underscore'
                    },
                    remote: {
                        url: './validateUser',
                        type: 'POST',
                        //data:{username:$('#inputUsername').val()},
                        delay: 2000
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
                        url: './validateUser',
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
                        url: './validateUser',
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
                    identical: {
                        field: 'equalToPassword',
                        message: '两次密码输入不一致!'
                    },
                    different: {
                        field: 'username',
                        message: '密码不能跟用户名相同!'
                    },
                    stringLength: {
                        min: 6,
                        max: 20,
                        message: '密码长度在6-20之间!'
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
                    },
                    different: {
                        field: 'username',
                        message: '密码不能跟用户名相同!'
                    }
                }
            },
            name:{
                validators: {
                    notEmpty: {
                        message: '姓名不能为空!'
                    }
                }
            },
            releaseAgency:{
                validators: {
                    notEmpty: {
                        message: '所在出版机构不能为空!'
                    }
                }
            },
            releaseCompany:{
                validators: {
                    notEmpty: {
                        message: '公司名称不能为空!'
                    }
                }
            },
            captcha: {
                validators: {
                    callback: {
                        message: '答案錯誤!',
                        callback: function(value, validator) {
                            var items = $('#captchaOperation').html().replace('=','').split('+'), sum = parseInt(items[0]) + parseInt(items[1]);
                            return value == sum;
                        }
                    }
                }
            }
        }
    });

    $('#translatorForm').bootstrapValidator({
        message: '验证失败!',
        excluded: [':disabled', ':hidden', ':not(:visible)'],
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        submitHandler: function (validator, form, submitButton) {
            validator.defaultSubmit();
        },
        fields: {
            translatorType: {
                validators: {
                    notEmpty: {
                        message: '请选择译员类型!'
                    }
                }
            },
            username: {
                validators: {
                    notEmpty: {
                        message: '用户名不能为空!'
                    },
                    stringLength: {
                        min: 4,
                        max: 15,
                        message: '用户名长度在4-15之间!'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: 'The username can only consist of alphabetical, number and underscore'
                    },
                    remote: {
                        url: './validateUser',
                        type: 'POST',
                        //data:{username:$('#inputUsername').val()},
                        delay: 2000
                    }
                }
            },
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
                        url: './validateUser',
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
                        url: './validateUser',
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
                    identical: {
                        field: 'equalToPassword',
                        message: '两次密码输入不一致!'
                    },
                    different: {
                        field: 'username',
                        message: '密码不能跟用户名相同!'
                    },
                    stringLength: {
                        min: 6,
                        max: 20,
                        message: '密码长度在6-20之间!'
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
                    },
                    different: {
                        field: 'username',
                        message: '密码不能跟用户名相同!'
                    }
                }
            },
            language:{
                validators: {
                    notEmpty: {
                        message: '擅长语种不能为空!'
                    }
                }
            },
            captcha: {
                validators: {
                    callback: {
                        message: '答案錯誤!',
                        callback: function(value, validator) {
                            var items = $('#captchaOperation').html().replace('=','').split('+'), sum = parseInt(items[0]) + parseInt(items[1]);
                            return value == sum;
                        }
                    }
                }
            }
        }
    });
})

function saveUser(form){
    $.post(form.attr('action'), form.serialize(), function(result) {
        if (result.success) {
            alert(result.msg);
            location.href=APP_BASE+"/login";
        } else {
            layer.msg(result.msg);
            $('#'+form.attr('id')).bootstrapValidator('disableSubmitButtons', false);

        }
    }, 'json');
}
