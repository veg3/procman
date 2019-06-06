layui.use(['form','jquery','table','layer',], function() {
    let form = layui.form,
        $ = layui.jquery,
        table = layui.table,
        laydate = layui.laydate,
        layer = layui.layer;
    var tid = $("#tid").val();
    $("#tid").remove();
    var user = JSON.parse(sessionStorage.getItem("user"));
    var roleid = user.roleId;
    $.ajax({                //获取所有指导老师名字
        type:"POST",
        async:false,
        url:'/tuj/tusNames/'+tid,
        success:function (info) {
           var tnames = info.data;
           $("#tnames").html(tnames);
        }
    });
    $.ajax({               //获取是否带有附件
        type:"POST",
        async:false,
        url:'/fnrp/existatch/'+tid,
        success:function (info) {
            if(info.code == 1){      //文件路径
                $("#attachment").find("div").html("<span style='font-size:16px'>"+info.msg+"</span>");
            }
            else{
                $("#attachment").show();
            }
        }
    });
    if(roleid == 2){
        $("#tup").show();
        $("tbody .fac").remove();
        $("tbody .led").remove();
        $("#unp_btn").remove();
    }
    else if(roleid == 3){
        $("#tup").remove();
        $("tbody .fac").show();
        $("tbody .led").remove();
        $("#unp_btn").show();
    }
    else if(roleid == 4){
        $("#tup").remove();
        $("tbody .fac").remove();
        $("tbody .led").show();
        $("#unp_btn").show();
    }
    $("a").click(function () {
        var url = $(this).attr("href")+tid;
        if($(this).attr("class") == "readpdf"){
            url = "/static/pdfjs/web/viewer.html?file="+url;
        }
        window.open(url);
        return false;
    });
    table.render();
    form.on("submit(back_btn)",function(data){    //返回修改
        var url ="/fnrp/backstat/"+tid;
        var field = data.field;
        var text = '确定要退回修改吗?'
        conf(text,url,field);
        return false;
    });
    form.on("submit(pass_btn)",function(data){    //审核通过
        var field = data.field;
        var text = '确定通过吗';
        var url ="/fnrp/tubstat/"+tid;
        conf(text,url,field);
        return false;
    });
    form.on("submit(unp_btn)",function(data){    //审核通过
        var field = data.field;
        var url ="/fnrp/unp/"+tid;
        var text = "确定不通过吗?";
        conf(text,url,field);
        return false;
    });
    function conf(text,url,field) {
        layer.confirm(text, {icon: 3, anim: 4, skin: 'layui-layer-molv'}, function () {
            $.ajax({
                type:"POST",
                async:false,
                dataType:'json',
                contentType : "application/json",
                url:url,
                data: JSON.stringify(field),
                success:function (data) {
                    layer.msg(data.msg);
                    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                    setTimeout(function () {
                        window.parent.location.reload();//刷新父页面
                        parent.layer.close(index);
                    },1000);
                }
            });
        });

    }
})
