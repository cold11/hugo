/**
 * Created by ohj on 2016/12/14.
 * 试译邀请
 */
$(function () {
    $('#submission-form').on('show.bs.modal', function() {
        $('#inviteForm').bootstrapValidator('resetForm', true);
    });
    $('#inviteForm').bootstrapValidator({
        message: '验证失败!',
        excluded: [':disabled', ':hidden', ':not(:visible)'],
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        submitHandler: function (validator, form, submitButton) {
            //validator.defaultSubmit();
            var userId = $('#userId').val();
            $.post(APP_BASE+"/task/editor/invitation",form.serialize()).done(function(data){
                layer.alert(data.msg);
                if(data.success){
                    $('#submission-form').modal('hide');
                }
            }).fail(function(){
                layer.alert("出现错误");
            }).always(function(){
                $('#'+userId).removeAttr("disabled");
            });
        },
        fields: {
            transContent: {
                validators: {
                    notEmpty: {
                        message: '试译内容不能为空!'
                    },
                    stringLength: {
                        min: 5,
                        max: 2000,
                        message: '试译内容长度在5-2000之间!'
                    }
                }
            },
            memo: {
                validators: {
                    notEmpty: {
                        message: '试译要求不能为空!'
                    },
                    stringLength: {
                        min: 5,
                        max: 1000,
                        message: '试译要求长度在5-1000之间!'
                    }
                }
            }
        }
    });
});

function postinvite(userId){
    $('#submission-form').modal('show');
    $('#userId').val(userId);
}