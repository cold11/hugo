/**
 * Created by ohj on 2016/11/10.
 */
$(function () {
    $('#editForm').bootstrapValidator({
        message: '验证失败!',
        excluded: [':disabled', ':hidden', ':not(:visible)'],
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        submitHandler: function (validator, form, submitButton) {
            $.post(APP_BASE+"/task/editor/publish", form.serialize(), function(result) {
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
        },
        fields: {
            bookname:{
                validators: {
                    notEmpty: {
                        message: '约稿标题不能为空!'
                    }
                }
            },

            bookIntroduction:{
                validators: {
                    notEmpty: {
                        message: '约稿需求不能为空!'
                    }
                }
            }
        }
    });
});
