layui.use(['form','layer','jquery',"table"],function(){
    var form = layui.form,
        layer = layui.layer,
        table = layui.table,
        $ = layui.jquery;
    form.verify({
        numb:[/^[0-9]*$/ ,"账号只能输入数字"]
    });
    //登录按钮
    form.on("submit(sub)",function(data){
        $.post('/user/login',data.field,function (info) {
            layer.msg(info.msg,{offset:['58%','45%']});
            if(info.code==0) {
                setTimeout(function () {
                    window.location.href = "/pages/index.html";
                },500);
            }
            else {
                $("input[name='pwd']").val("");
            }
        });
        return false;
    });

    $("#fot").click(function () {
        layer.open({
            type: 2
            , skin: 'layui-layer-molv'
            ,content:"/pages/user/fotPwd.html"
            , area: ['500px','400px']

        });

        return false;
    });
})
