layui.config({
    base: '/static/js/modules/' //配置 layui 第三方扩展组件存放的基础目录
}).extend({
    sliderVerify:'sliderVerify'
}).use(['form','jquery','layer','sliderVerify'],function() {
    let form = layui.form,
        $ = layui.jquery,
        sliderVerify = layui.sliderVerify,
        layer = layui.layer;
    form.verify({
       numb:[/^[0-9]*$/ ,"账号只能输入数字"]
    });
    var slider = sliderVerify.render({
        elem: '#slider',
        onOk: function(){//当验证通过回调
            layer.msg("滑块验证通过");
        }
    });
    form.on('submit(ftPwd)', function(data) {
        if(slider.isOk()){
            slider.reset();
            $.post("/user/retpwd",data.field,function (info) {
                document.getElementById("pwdform").reset();
                layer.alert(info.msg);
            });
        }else{
            layer.msg("请先通过滑块验证");
        }
        return false;
    });


});

