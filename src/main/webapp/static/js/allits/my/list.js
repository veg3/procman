layui.use(['form','jquery','table','layer','laydate'],function() {
    let form = layui.form,
        $ = layui.jquery,
        table = layui.table,
        laydate = layui.laydate,
        layer = layui.layer;
    var tabis;
    var user = JSON.parse(sessionStorage.getItem("user"));
    var roleid = user.roleId;
    tabis = table.render({
        elem: '#allList',
        url: '/item/list', //数据接口
        page: true, //开启分页
        toolbar:true,
        defaultToolbar:['filter', 'exports'],       //开启工具栏
        limit: 5, //每页显示的条数，默认10
        limits: [5,10,15],
        request: {
            pageName: 'current',
            limitName: 'size',
        },
        cols: [[ //表头
            {field: 'id', title: '项目编号', width: 80, sort: true, fixed: 'left', align: 'center'},
            {field: 'name', title: '项目名称', align: 'center'},
            {field: 'tpName', title: '项目类型', align: 'center'},
            {field: 'stuName', title: '项目负责人', align: 'center'},
            {field: 'tuName', title: '指导教师', align: 'center'},
            {field: 'faculty', title: '院系', align: 'center'},
            {field: 'major', title: '专业', align: 'center'},
            {field: 'statName', title: '状态', align: 'center'},
            {title: '操作', width: 160, toolbar: "#alTool", align: 'center'}
        ]]
    });

    var exnode = $(".layui-table-tool").find("div[title='导出']");
    $(exnode).attr('lay-event', 'NO_LAYTABLE_EXPORT');
    $(exnode).click(function () {
        var len = tabis.config.cols[0].length;
        var param = new Array();
        for(var i = 0;i<len-1;i++){
           param[i] = tabis.config.cols[0][i].field;
        }
        $("<form action='/item/export' method='post'>" +
            "<input  name='param' value='"+param+"'/>" +
            "</form>")
            .appendTo('body').submit().remove();
    });
    table.on('tool(allList)',function(obj){
        switch (obj.event) {
            case 'read_btn':                  //编辑
                var data = obj.data;
                    layer.open({
                        type: 2,
                        skin: 'layui-layer-molv', //加上边框
                        area: ['750px', '500px'], //宽高
                        shade: [0.5, '#000'],
                        moveOut: true,
                        content: '/pages/allits/read/read.html',
                        value: data,
                        //将数据赋值给弹框元素对象
                        success: function (layero, index) {
                            var iframe = layer.getChildFrame('body', index);
                            iframe.find('#tid').val(data.id);
                            iframe.find("#head").html(data.name);
                            iframe.find('#stuName').html(data.stuName);
                            iframe.find("#tnames").html(data.tuName);
                            iframe.find("#status").val(data.status);
                            iframe.find('#applyDate').html(data.applyDate);
                        }
                    });
                break;
            case 'del_btn':
                var id = obj.data.id;
                layer.confirm('确定要删除吗？', {icon: 3, anim: 4, skin: 'layui-layer-molv'}, function () {
                    $.post('/item/del/'+id,function (info) {
                        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                        layer.msg(info.msg);
                        setTimeout(function () {
                            tabis.reload();
                            parent.layer.close(index);
                        },500);
                    });
                });
                break;
        }
    });
    function layop() {
        layer.alert('该项目已提交,不能删除', {
            icon: 7,
            title: "提示"
        });
    }


    ///////  tec //////////////
    laydate.render({
        elem: '#apdate'
        ,type: 'date'
        ,range: '~'
        ,format: 'yyyy-MM-dd'
    });


    form.on("submit(query)",function (data) {
        // var str = JSON.stringify(data.field);
        // var obj = JSON.parse(str);
        // var s = $.trim(obj.name+obj.faculty+obj.executeDate);
        tabis.reload({
            where : data.field,
            page : {curr: 1}
        });
    });
})
