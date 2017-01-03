/**
 * Created by ohj on 2016/12/30.
 */
$(function () {
    $('#translatorForm').bootstrapValidator({
        message: '验证失败!',
        excluded: [':disabled', ':hidden', ':not(:visible)'],
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        submitHandler: function (validator, form, submitButton) {
            submit(form);
        },
        fields: {
            language:{
                validators: {
                    notEmpty: {
                        message: '擅长语种不能为空!'
                    }
                }
            }
        }
    });
});
function submit(form){
    $.post(form.attr('action'), form.serialize(), function(result) {
        if (result.success) {
            layer.alert(result.msg);

        } else {
            layer.alert(result.msg);
            $('#'+form.attr('id')).bootstrapValidator('disableSubmitButtons', false);

        }
    }, 'json');
}