layui.use(['form','jquery','laytpl','table','laydate'],function() {
    let form = layui.form,
        $ = layui.jquery,
        table = layui.table,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        layer = layui.layer;
    var user = JSON.parse(sessionStorage.getItem("user"));
    var roleid = user.roleId;
    let std = roleid*2;
    var tabis = table.render({
        elem: '#fnrpList',
        url: '/fnrp/list', //数据接口
        page: true, //开启分页
        limit: 5, //每页显示的条数，默认10
        limits: [5, 10, 15],
        request: {
            pageName: 'current',
            limitName: 'size',
        },
        cols: [[ //表头
            {field: 'id', title: '项目编号', width: 80, sort: true, fixed: 'left', align: 'center'},
            {field: 'name', title: '项目名称', align: 'center'},
            {field: 'tpName', title: '项目类型', align: 'center'},
            {field: 'stuName', title: '项目负责人', align: 'center'},
            {field: 'tuName', title: '第一指导教师', align: 'center'},
            {field: 'faculty', title: '院系', align: 'center'},
            {field: 'major', title: '专业', align: 'center'},
            {field: 'statName', title: '状态', align: 'center'},
            {title: '操作', width: 120, toolbar: "#frTool", align: 'center'}
        ]],
        done:function (res, curr,count) {
            var data = res.data;
            for (var item in data) {
                var statId = data[item].statId;
                if( statId < std-1 || statId > std){
                    var node = $(".layui-table tbody tr").eq(item).find("a");
                    $(node).css("background-color","grey");
                }
            }
        }
    });
    table.on('tool(fnrpList)',function(obj){
        switch (obj.event){
            case 'submit_btn':
                var statId = obj.data.statId;
                if( statId < std-1 || statId > std){
                    layop();
                    return false;
                    break;
                }else{
                    var data = obj.data;
                    layer.open({
                        type: 2,
                        title: "结题审核",
                        shade: [0.5, '#000'],
                        skin: 'layui-layer-molv', //加上边框
                        area: ['850px', '500px'], //宽高
                        content: '/pages/final/tec/audit.html',
                        value: data,
                        //将数据赋值给弹框元素对象
                        success: function (layero, index) {
                            var iframe = layer.getChildFrame('body', index);
                            iframe.find('#tid').val(data.id);
                            iframe.find('#header h1').html(data.name);
                            iframe.find('#roleid').val(roleid);
                            iframe.find('#stuName').html(data.stuName);
                            iframe.find('#applyDate').html(data.applyDate);
                        },
                        end: function () {
                            tabInstance.reload();
                        }
                    });
                }
                break;
        }
    });
    function layop() {
        layer.alert('该项目暂时不能审核', {
            icon: 7,
            title: "提示"
        });
    };
    laydate.render({
        elem: '#apdate'
        ,type: 'date'
        ,range: '~'
        ,format: 'yyyy-MM-dd'
    });
    form.on("submit(query)",function (data) {
        tabis.reload({
            where : data.field,
            page : {curr: 1}
        });
    });
})
