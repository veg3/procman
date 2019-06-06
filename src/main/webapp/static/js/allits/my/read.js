layui.use(['form','jquery','table','layer',],function() {
    let form = layui.form,
        $ = layui.jquery,
        table = layui.table,
        layer = layui.layer;
    var tid = $("#tid").val();
    $("#tid").remove();
    var status = $("#status").val();
    $("#status").remove();

    for(var i = 3;i>= 1;i--){
        if(i > status){
            $(".fnode").eq(i-1).remove();
        }
        else{
            console.log("i: "+i);
            var fnode = $(".fnode").eq(i-1);
            flexist(fnode);
        }
    }
    $(".spana a").click(function () {
        var url = $(this).attr("href")+tid;
        if($(this).attr("class") == "readpdf"){
            url = "/static/pdfjs/web/viewer.html?file="+url;
        }
        window.open(url);
        return false;
    })

    function flexist(node) {
        var preh = $(node).show().find('a').eq(1).attr("href").split("/")[1];
        var url1 = "/"+preh+"/existdoc/"+tid;
        var url2 = "/"+preh+"/existatch/"+tid;
        var node1 = $(fnode).find(".spana").eq(0);
        var node2 = $(fnode).find(".spana").eq(1);
        reqa(url1,node1);
        reqa(url2,node2);
    }
    function  reqa(url,node) {
        $.ajax({
            url:url,
            async:false,
            success:function (info) {
                if(info.code == 1){      //attach
                    $(node).html("<span style='font-size:16px'>"+info.msg+"</span>");
                }
            }
        });
    }
})
