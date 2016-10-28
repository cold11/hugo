/**
 * Created by ohj on 2016/10/21.
 */
$(function(){
    function objInit(obj,html){
        return $(obj).html(html);
    }
    var arrData = {
        英语: "1", 简体中文: "2",繁体中文: "3", 德语: "4",日语: "5", 法语: "6",俄语: "7", 韩语: "8",西班牙语: "9", 其他: "10"
    };

    $.each(arrData,function(text,value) {
        $("#sourceLanguage").append("<option value='"+value+"'>"+text+"</option>");
    });
    $("#sourceLanguage").change(function(event) {
        var target = event.target;
        var srcValue = target.value;
        var targetLanguageS = '<option value="" disabled selected>目标语种</option>';
        objInit("#targetLanguage",targetLanguageS);
        $.each(arrData,function(text,value) {
            if(value!=srcValue){
                $("#targetLanguage").append("<option value='"+value+"'>"+text+"</option>");
            }
        });
    });

});