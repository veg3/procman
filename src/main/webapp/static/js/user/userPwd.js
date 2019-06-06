layui.use(['form','jquery','table','layer'],function() {
    let form = layui.form,
        $ = layui.jquery,
        table = layui.table,
        layer = layui.layer;

    form.verify({
        pass: [
            /^[\S]{6,12}$/,'密码必须6到12位，且不能出现空格'
        ],
        repass: function(value) {
            var repassvalue = $('#newPwd').val();
            if (value != repassvalue) {
                return '两次输入的密码不一致!';
            }
        }
    });
    form.on('submit(userPwd)',function (data) {
        console.log(data.field);
        $.ajax({
            cache : true,
            type : "POST",
            url : "/user/updatePwd",
            data : data.field, // 你的formid
            async : false,
            error : function(request) {
                parent.layer.alert("网络超时");
            },
            success : function(data) {
                //判断当前密码输入是否正确 0正确
                if (data.code ==0){
                    parent.layer.msg(data.msg);
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                    $.post('/user/logout',function (info) {
                        setTimeout(function () {
                            parent.window.location.reload();
                        },900);
                    });
                }else {
                    parent.layer.msg(data.msg);
                }
            }
        });
    });
});

