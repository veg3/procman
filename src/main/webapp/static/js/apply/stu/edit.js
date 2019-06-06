layui.config({
    base: '/static/js/modules/' //配置 layui 第三方扩展组件存放的基础目录
}).extend({
    regionSelect: 'autocomplete/autocomplete'
}).use(['form','jquery','table','layer','laydate','autocomplete'], function() {
    let form = layui.form,
        $ = layui.jquery,
        table = layui.table,
        laydate = layui.laydate,
        layer = layui.layer;
    var tid = $("#tid").val();
    $("#tid").remove();
    var fac = $("#faculty").val();
    $.post('/facul/facnames',function (result) {
        workData = result.data;
        var strs="";
        for(var x in workData){
            if(workData[x] == fac){
                strs+="<option value ='" + workData[x]+ "' selected>" + workData[x]+ "</option>";
            }
            else{
                strs+="<option value ='" + workData[x]+ "'>" + workData[x]+ "</option>";
            }
        }
        $("#faculty").html(strs);
        form.render();
    });
    form.on('select(faculty)', function(data) {
        var fid = $("#faculty").val();
        $.post('/major/mjNames/'+fid,function (result) {
            workData = result.data;
            var strs="";
            for(var x in workData){
                strs+="<option value ='"+ workData[x] +"'>" + workData[x]+ "</option>";
            }
            $("#major").html(strs);
            form.render();
        });
    });

    var leadtab = table.render({
        elem: '#leader',
        width: 800,
        page:false,
        url: '/stup/leader/'+tid, //数据接口
        cols: [[ //表头
            {field: 'id', title: '学号', width:'18%', fixed: 'left', align: 'center'},
            {field: 'name', title: '姓名',  align: 'center'},
            {field: 'grade', title: '年级', align: 'center'},
            {field: 'phone', title: '电话',  align: 'center'},
            {field: 'email', title: '邮箱', align: 'center'},
        ]]
    });
    var ostutab = table.render({
        elem: '#ostu',
        width: 800,
        url: '/stup/ostu/'+tid, //数据接口
        cols: [[ //表头
            {field: 'id', title: '学号',width:'18%', fixed: 'left', align: 'center'},
            {field: 'name', title: '姓名', align: 'center'},
            {field: 'grade', title: '年级', align: 'center'},
            {field: 'phone', title: '电话',align: 'center'},
            {field: 'email', title: '邮箱', align: 'center'},
            {title: '操作', width: 130, toolbar: "#ostuTool", align: 'center'}
        ]]
    });


    var fttab = table.render({     //第一指导老师
        elem: '#ftor',
        width: 800,
        page:false,
        url: '/tuj/ftu/'+tid, //数据接口
        cols: [[ //表头
            {field: 'id',hide:true},
            {field: 'name', title: '姓名',align: 'center'},
            {field: 'faculty', title: '院系',  align: 'center'},
            {field: 'jobTitle', title: '职称',  align: 'center'},
            {field: 'resDirect', title: '研究方向',  align: 'center'},
        ]]
    });
    $.post("/tuj/tsize/"+tid,function(das) {
        if(das == 2){
            $("#stdiv").show();
            var node = $("<table id='stor' lay-filter='stor'></table>");
            $("#stdiv").append(node);
            var fttab = table.render({
                elem: '#stor',
                width: 800,
                page:false,
                url: '/tuj/sctu/'+tid, //数据接口
                cols: [[ //表头
                    {field: 'id', hide:true},
                    {field: 'name', title: '姓名', align: 'center'},
                    {field: 'faculty', title: '院系', align: 'center'},
                    {field: 'jobTitle', title: '职称',  align: 'center'},
                    {field: 'resDirect', title: '研究方向',  align: 'center'},
                    {title: '操作', width: 130, toolbar: "#storTool", align: 'center'}
                ]]
            });
        }
    },"json");

    form.on("submit(btnude)",function(data) {//更新按钮
        var url = '/item/update/'+tid;
        updatemessage(url,data.field);
        return false;
    });
    table.on('tool(leader)',function(obj) {
        switch (obj.event) {
            case 'lead_btn':
                var data = obj.data;
                break;
        }
    });
    table.on('tool(ostu)',function(obj) {
        switch (obj.event) {
            case 'ostu_edit':            //更新成员信息
                var data = obj.data;
                break;
            case 'ostu_del':             //删除成员
                var node = $(this).parent("div").parent("td").parent("tr");
                $.post("/stup/numb/"+tid,function(res){
                    var num = res.data;
                    if(num <= 3){
                        layer.msg("项目成员不能少于3人");
                        return false;
                    }
                    else{
                        var path = '/stup/del/'+tid;
                        var data = obj.data;
                        delmessage(path,data,node);
                }
                });
                break;
        }
    });
    table.on('tool(ftor)',function(obj) {
        switch (obj.event) {
            case 'ftor_btn':
                var data = obj.data;
                break;
        }
    });
    table.on('tool(stor)',function(obj) {
        switch (obj.event) {
            case 'stor_btn':
                var data = obj.data;
                break;
            case 'ostu_del':{
                var node = $(this).parent("div").parent("td").parent("tr");
                var path = '/tuj/del/'+tid;
                var data = obj.data;
                delmessage(path,data,node);
                $("#stdiv").hide();
                break;
            }
        }
    });
    laydate.render({
        elem: '#executeDate'
        ,type: 'datetime'
        ,range: '-'
        ,format: 'yyyy年M月d日'
    });
    laydate.render({
        elem: '#applyDate'
        ,type: 'date'
        ,format: 'yyyy-MM-dd'
    });


    function delmessage(path,mdata,node){
        layer.confirm('确定要删除吗？', {icon: 3, anim: 4, skin: 'layui-layer-molv'}, function () {
            $.ajax({
                type:"POST",
                async:false,
                dataType:'json',
                contentType : "application/json",
                url:path,
                data: JSON.stringify(mdata),
                success:function (data) {
                    layer.msg(data.msg);
                    $(node).remove();
                }
            });
        });
    }

})
