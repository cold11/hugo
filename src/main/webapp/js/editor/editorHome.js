/**
 * Created by Administrator on 2016/12/20.
 */

function showTrans(taskId){
    $.post(APP_BASE+'/editor/userTaskList',{taskId:taskId}).done(function(data){
        $('#translation-content').empty().html(data);

        $('#translation-list').modal('show');
    }).fail(function(){
        $('#translation-list').modal('show');
    });

}
function showTransInfo(userTaskId){
    $.post(APP_BASE+'/editor/userTask',{userTaskId:userTaskId}).done(function(data){
        $('#translation-info').empty().html(data);

        $('#translation-result').modal('show');
    }).fail(function(){
        $('#translation-result').modal('show');
    });
}

function viewTG(taskId){
    $.post(APP_BASE+'/editor/userTGList',{taskId:taskId}).done(function(data){
        $('#translation-content').empty().html(data);

        $('#translation-list').modal('show');
    }).fail(function(){
        $('#translation-list').modal('show');
    });
}

function showUserInfo(userTaskId){
    $.post(APP_BASE+'/editor/tgInfo',{userTaskId:userTaskId}).done(function(data){
        console.log(data);
        $('#translation-info').empty().html(data);

        $('#translation-result').modal('show');
    }).fail(function(){
        $('#translation-result').modal('show');
    });
}