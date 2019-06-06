layui.config({
    base: '/static/js/modules/'
}).extend({
    regionSelect: 'autocomplete/autocomplete'
}).use(['form','jquery','table','layer','laydate','autocomplete'], function() {
    let form = layui.form,
        $ = layui.jquery,
        table = layui.table,
        laydate = layui.laydate,
        autocomplete = layui.autocomplete,
        layer = layui.layer;
    var user = JSON.parse(sessionStorage.getItem("user"));
    var username = user.userName;
    var uid = user.userId;
    var saveToken;
    $("input").each(function () {
       $(this).attr("class","layui-input")
            .attr("autocomplete","off")
    });
    $.ajax({
        type:"POST",
        async:false,
        url:"/item/saveToken/"+uid,
        success:function (info) {
            saveToken = info.data;
        }
    });


    $.post('/facul/facnames',function (result) {
        workData = result.data;
        var strs="<option value=''>请选择院系</option>";
        for(var x in workData){
           strs+="<option value ='" + workData[x]+ "'>" + workData[x]+ "</option>";
        }
        $("#faculty").html(strs);
        form.render();
    });
    form.on('select(faculty)', function(data) {
        var fid = $("#faculty").val();
        $.post('/major/mjNames/'+fid,function (result) {
            workData = result.data;
            var strs="<option value=''>请选择专业</option>";
            for(var x in workData){
                strs+="<option value ='"+ workData[x] +"'>" + workData[x]+ "</option>";
            }
            $("#major").html(strs);
            form.render();
        });
    });
    var ftnode = $("#tupNo");
    searchtup(ftnode);
    form.on("submit(btns)",function(data) {
        console.log("type : "+data.field.type);
        if(data.field.type == "" || data.field.type==undefined){
            layer.msg("未选择项目类型");
            return false;
        }
        layer.confirm('确定提交吗?', {icon: 3, title:'提示'}, function(index){
            $.post('/item/save/'+saveToken,data.field,function (info) {
                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                layer.msg(info.msg);
                setTimeout(function () {
                    window.parent.location.reload();//刷新父页面
                    parent.layer.close(index);
                },800);
            });
            layer.close(index);
        });

        return false;
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
    $(".adds").click(function () {     //增加项目成员 subs
        var $td = $(this).parent("td");
        var rs = parseInt($td.attr("rowspan"));
        var i = rs+1;
        if(rs < 4){
            rs++;
            $td.attr("rowspan",rs);
        }
        var tb = $(".tst");
        var text = "项目成员不能超过5人";
        var num = 6;
        var node = $("<tr>" +
            "<td><input name='stmb["+i+"].id' ></td>" +
            "<td><input name='stmb["+i+"].name'  readonly></td>" +
            "<td><input name='stmb["+i+"].grade'  readonly></td>" +
            "<td><input name='stmb["+i+"].phone'  lay-verify='phone' readonly></td>" +
            "<td><input name='stmb["+i+"].email'  lay-verify='email' readonly></td>" +
            "</tr>");
        $(node).find("input").attr("class","layui-input")
                             .attr("autocomplete","off");
        var sno = $(node).find(":input[name$='id']");
        $(sno).attr("placeholder","输入完整学号搜索");
        searchstu(sno);
        addp(tb,node,num,text);
        return false;
    })
    $(".subs").click(function () {
        var $td = $(this).parent("td");
        var rs = parseInt($td.attr("rowspan"));
        if(rs < 2){
            rs--;
            $td.attr("rowspan",rs);
        }
        var text = "项目成员不能少于3人";
        var num =4;
        var tb = $(".tst");
        subp(tb,num,text);
        return false;
    });
    $(".addt").click(function () {
        var abu = $(this);
        var tb = $(".ttu");
        var text = "指导教师不能超过2人";
        var num = 3;
        var node = $("<tr><td>第二导师</td>" +
            "<td hidden><input name='tumb[1].id' readonly></td>" +
            "<td><input name='tumb[1].name'  placeholder='搜索选择导师'></td>" +
            "<td><input name='tumb[1].faculty'  readonly></td>" +
            "<td><input name='tumb[1].jobTitle' readonly></td>" +
            "<td><input name='tumb[1].resDirect' readonly></td></tr>");
        $(node).find("input").attr("class","layui-input")
                             .attr("autocomplete","off")
        addp(tb,node,num,text);
        var tupn = $(node).find("input[name$='name']");
        searchtup(tupn);
        return false;
    });
    $(".subt").click(function () {
        var text = "指导教师不能少于1人";
        var num =2;
        var tb = $(".ttu");
        subp(tb,num,text);
        return false;
    });
    $(".tst :input[name$='id']").each(function () {
        $(this).attr("placeholder","输入完整学号搜索");
        searchstu($(this));
    });
    function layopen(text){
        layer.open({
            type: 1
            ,content: '<div style="padding: 20px 100px;">'+text+'</div>'
            ,btn: '关闭'
            ,btnAlign: 'c' //按钮居中
            ,shade: 0 //不显示遮罩
            ,yes: function(){
                layer.closeAll();
            }
        });
    }
    function addp(tb,node,num,text) {
        var $rtd = $(tb).find("tr:first-child td:first-child");
        var rrs = parseInt($rtd.attr("rowspan"));
        if(rrs == num){
            layopen(text);
            return false;
        }
        rrs++;
        $rtd.attr("rowspan",rrs);
        $(tb).find("tbody").append($(node));
        return false;
    }
    function subp(tb,num,text) {
        var $rtd = $(tb).find("tr:first-child td:first-child");
        var rrs = parseInt($rtd.attr("rowspan"));
        if(rrs == num){
            layopen(text);
            return false;
        }
        rrs--;
        $rtd.attr("rowspan",rrs);
        $(tb).find("tbody tr:last").remove();
        return false;
    }
    function searchtup(node) {      //按照name查询导师
        autocomplete.render({
            elem: $(node),
            url: '/tch/getTup',
            template_val: '{{d.name}}',
            template_txt: '{{d.id}}  {{d.name}} {{d.faculty}}',
            onselect: function (resp) {
                var prex = $(node).attr("name").slice(0,8);
                $("input[name='"+prex+"id']").val(resp.id);
                $("input[name='"+prex+"faculty']").val(resp.faculty);
                $("input[name='"+prex+"jobTitle']").val(resp.jobTitle);
                $("input[name='"+prex+"resDirect']").val(resp.resDirect);
            }
        });
    }
    function searchstu(node) {      //按照name查询学生
        autocomplete.render({
            elem: $(node),
            url: '/student/secs',
            template_val: '{{d.id}}',
            template_txt: '{{d.id}}  {{d.name}} {{d.grade}}',
            onselect: function (resp) {
                var prex = $(node).attr("name").slice(0,8);
                $(".tst input[name='"+prex+"id']").val(resp.id);
                $(".tst input[name='"+prex+"name']").val(resp.name);
                $(".tst input[name='"+prex+"grade']").val(resp.grade);
                $(".tst input[name='"+prex+"phone']").val(resp.phone);
                $(".tst input[name='"+prex+"email']").val(resp.email);
            }
        });
    }
})
