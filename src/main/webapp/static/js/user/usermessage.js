layui.use(['jquery'],function() {
    let $ = layui.$;
    $.post('/user/login-info',function(info){
        $('#userId').val(info.data.userId);
        $('#userName').val(info.data.userName);
        $('#roleName').val(info.data.roleName);
        $('#faculty').val(info.data.faculty);
        if($('#roleName').val() == "学生"){
            $('#labId').html("学号");
        }
        else{
            $('#labId').html("教工号");
        }
    });
});

