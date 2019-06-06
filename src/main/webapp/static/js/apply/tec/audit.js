layui.use(['form','jquery','table','layer'], function() {
    let form = layui.form,
        $ = layui.jquery,
        table = layui.table,
        laytpl = layui.laytpl,
        layer = layui.layer;
    var tid = $("#tid").val();
    $("#tid").remove();
    var user = JSON.parse(sessionStorage.getItem("user"));
    var roleid = user.roleId;

    $.ajax({                //获取所有指导老师名字和项目简介
        type:"POST",
        async:false,
        url:'/item/tui/'+tid,
        success:function (info) {
           $("#tnames").html(info.data.tnames);
           $("#intro").html(info.data.intro);
        }
    });

    $.ajax({               //获取是否带有附件
        type:"POST",
        async:false,
        url:'/apply/existatch/'+tid,
        success:function (info) {
            if(info.code == 1){      //文件路径
                $("#attachment").find("div").html("<span style='font-size:16px'>"+info.msg+"</span>");
            }
            $("#attachment").show();
        }
    });
    if(roleid == 2){
         $("#tup").show();
         $("tbody .fac").remove();
         $("#unp_btn").remove();
    }
    else if(roleid == 3){          //禁止编辑
        $("#tup").remove();
        $("tbody .fac").show();
        $("#unp_btn").show();
    }
    $("a").click(function () {
        var url = $(this).attr("href")+tid;
        if($(this).attr("class") == "readpdf"){
            url = "/static/pdfjs/web/viewer.html?file="+url;
        }
        window.open(url);
        return false;
    })
    table.render();
    form.on("submit(back_btn)",function(data){    //返回修改
        var url ="/apply/backstat/"+tid;
        var field = data.field;
        var text = '确定要退回修改吗?';
        conf(text,url,field);
        return false;
    });
    form.on("submit(pass_btn)",function(data){    //审核通过
        var field = data.field;
        var url ="/apply/tubstat/"+tid;
        var text = "确定通过吗?";
        conf(text,url,field);
        return false;
    });

    form.on("submit(unp_btn)",function(data){    //审核通过
        var field = data.field;
        var url ="/apply/unp/"+tid;
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
