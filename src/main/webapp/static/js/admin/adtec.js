layui.use(['form','jquery','table','layer'], function() {
    let form = layui.form,
        $ = layui.jquery,
        table = layui.table,
        layer = layui.layer;
    var tabis;
    $.post('/facul/facnames',function (result) {
        workData = result.data;
        var strs="<option value=''>请选择院系</option>";
        for(var x in workData){
            strs+="<option value ='" + workData[x]+ "'>" + workData[x]+ "</option>";
        }
        $("#faculty").html(strs);
        form.render();
    });

    tabis = table.render({
        elem: '#slist',
        page: true, //开启分页
        limit: 5, //每页显示的条数，默认10
        limits: [10, 15, 20],
        request: {
            pageName: 'current',
            limitName: 'size',
        },
        toolbar:true,
        defaultToolbar:['filter', 'exports'],       //开启工具栏
        cols: [[ //表头
            {field: 'id', title: '教工号', width: 120, sort: true, fixed: 'left', align: 'center'},
            {field: 'name', title: '姓名', align: 'center'},
            {field: 'faculty', title: '院系', align: 'center'},
            {field: 'jobTitle', title: '职称', align: 'center'},
            {title: '操作', width: 130, toolbar: "#stool", align: 'center'}
        ]]
    });


    form.on('submit(btns)',function (data) {
        $("#slist").parent().show();
        tabis.config.url = "/tch/atlist";
        tabis.reload({
            where : data.field,
            page : {curr: 1}
        });
        return false;
    });
    table.on('tool(slist)',function(obj){
        switch (obj.event) {
            case 'udp_btn':
                var data = obj.data;
                layer.open({
                    type: 2,
                    skin: 'layui-layer-molv', //加上边框
                    area: ['650px', '470px'], //宽高
                    shade: [0.5, '#000'],
                    moveOut: true,
                    content: '/pages/admin/udp/udptec.html',
                    value: data,
                    success: function (layero, index) {
                        var iframe = layer.getChildFrame('body', index);
                        iframe.find('#id').val(data.id);
                        iframe.find('#oldid').val(data.id);
                        iframe.find("#name").val(data.name);
                        iframe.find("#jobTitle").val(data.jobTitle);
                        iframe.find("#resDirect").val(data.resDirect);
                        iframe.find('#faculty option').html(data.faculty);
                        iframe.find(":radio[value='"+data.roleid+"']").attr("checked","checked");
                    },
                    end : function () {
                        tabis.reload();
                    }
                });
                break;
            case 'del_btn':
                var id = obj.data.id;
                layer.confirm('确定要删除吗？', {icon: 3, anim: 4, skin: 'layui-layer-molv'}, function () {
                    $.post('/tch/del/'+id,function (info) {
                        layer.msg(info.msg);
                        setTimeout(function () {
                            tabis.reload();
                        },500);
                    });
                });
                break;
        }
    });

})
