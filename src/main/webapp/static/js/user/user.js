layui.use(['form','jquery','table','layer'],function() {
    let form = layui.form,
        $ = layui.jquery,
        table = layui.table;
        layer = layui.layer;
    //弹出窗 个人资料
    $("#usermessage").click(function () {
        layer.open({
            type: 2,
            content: '/pages/user/usermessage.html',
            skin:'layui-layer-molv',
            title:"个人资料",
            area:["500px","400px"],
            shade: 0.3
        });
    });
    //弹出窗 修改密码
    $("#userPwd").click(function () {
        layer.open({
            type: 2,
            content: '/pages/user/userPwd.html',
            skin:'layui-layer-molv',
            title:"修改密码",
            area:["500px","400px"],
            shade: 0.3
        });
    });

});
