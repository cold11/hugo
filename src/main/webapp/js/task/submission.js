/**
 * Created by Administrator on 2016/12/17.
 */
$(function () {
    $('#submission-form').on('show.bs.modal', function() {
        $('#submissionForm').bootstrapValidator('resetForm', true);
    });
    $('#submissionForm').bootstrapValidator({
        message: '验证失败!',
        excluded: [':disabled', ':hidden', ':not(:visible)'],
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        submitHandler: function (validator, form, submitButton) {
            $.post(APP_BASE+"/author/submission", form.serialize(), function(result) {
                layer.alert(result.msg);
                if(result.success){
                    $('#submission-form').modal('hide');
                }else {
                    //layer.msg("投稿失败");
                    $('#'+form.attr('id')).bootstrapValidator('disableSubmitButtons', false);

                }
            }, 'json');
        },
        fields: {
            bookIntroduction:{
                validators: {
                    notEmpty: {
                        message: '投稿需求不能为空!'
                    }
                }
            }
        }
    });
});
function showWindow(taskId){
    $('#taskId').val(taskId);
    $('#submission-form').modal('show');
}

