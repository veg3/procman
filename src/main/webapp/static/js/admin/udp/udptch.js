layui.use(['form','jquery','table','layer'], function() {
    let form = layui.form,
        $ = layui.jquery,
        table = layui.table,
        layer = layui.layer;
    form.render();

    var fac = $("#faculty option").html();
    sec('/facul/facnames',"faculty",fac);

    form.on("submit(udp_btn)",function (data) {
        console.log("ubtn");
        layer.confirm('确定要提交吗？', {icon: 3, anim: 4, skin: 'layui-layer-molv'}, function () {
            $.post("/tch/udp",data.field,function (info) {
                layer.msg(info.msg);
                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                setTimeout(function () {
                    parent.layer.close(index);
                },500);
            });
        });
        return false;
    });
    function sec(url,nodeId,initV) {
        $.post(url,function (result) {
            workData = result.data;
            var strs="";
            for(var x in workData){
                if(workData[x] == initV){
                    strs+="<option value ='" + workData[x]+ "' selected>" + workData[x]+ "</option>";
                }
                else{
                    strs+="<option value ='" + workData[x]+ "'>" + workData[x]+ "</option>";
                }
            }
            $("#"+nodeId).html(strs);
            form.render();
        });
    }
})
