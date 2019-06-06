layui.use(['form','jquery','table','layer'],function() {
    let form = layui.form,
        $ = layui.jquery,
        table = layui.table,
        layer = layui.layer;
    var tabInstance;
    var user = JSON.parse(sessionStorage.getItem("user"));
    var roleid = user.roleId;
    var std = roleid*2;
    tabInstance = table.render({
        elem: '#applyList',
        url: '/apply/list', //数据接口
        page: true, //开启分页
        toolbar: true,
        defaultToolbar: ['filter', 'print', 'exports'],
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
            {title: '操作', width: 160, toolbar: "#apTool", align: 'center'}
        ]]
        ,done:function (res, curr, count) {
            var data = res.data;
            for (var item in data) {
                if(data[item].statId > std){
                    $("tr[data-index='"+item+"']").find("a").remove();
                }
            }
        }
    });

    table.on('tool(applyList)',function(obj){
        switch (obj.event){
            case 'upload_btn':
                var statId = obj.data.statId;
                var id = obj.data.id;
                if(statId > std){
                    layop();
                    return false;
                    break;
                } else {
                    layer.open({
                        type: 2,
                        skin: 'layui-layer-molv', //加上边框
                        area: ['550px', '400px'], //宽高
                        content: '/pages/apply/stu/upload.html',
                        value: id,
                        //将数据赋值给弹框元素对象
                        success: function (layero, index) {
                            var iframe = layer.getChildFrame('body', index);
                            iframe.find('#id').val(id);
                        },
                        end: function () {
                            tabInstance.reload();
                        }
                    });
                }
                break;

            case 'edit_btn':                  //编辑
                var statId = obj.data.statId;
                if(statId > std){
                    layop();
                    return false;
                    break;
                } else {
                    var data = obj.data;
                    layer.open({
                        type: 2,
                        skin: 'layui-layer-molv', //加上边框
                        area: ['1150px', '500px'], //宽高
                        shade: [0.5, '#000'],
                        moveOut : true,
                        title:"更新",
                        content: '/pages/apply/stu/update.html',
                        success: function (layero, index) {
                            var iframe = layer.getChildFrame('body', index);
                            iframe.find('#tid').val(obj.data.id);
                        },
                        end: function () {
                            tabInstance.reload();
                        }
                    });
                }
                break;

            case 'submit_btn':                       //提交
                var statId = obj.data.statId;
                var itemId = obj.data.id;
                if(statId > std){
                    layop();
                    return false;
                    break;
                }else{
                    var url = "/apply/stustat/"+itemId;
                    layer.confirm('确定要提交吗？', {icon: 3, anim: 4, skin: 'layui-layer-molv'}, function () {
                        $.post(url,function (info) {
                            var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                            layer.msg(info.msg);
                            if(info.code == 0){
                                setTimeout(function () {
                                    tabInstance.reload();
                                },600);
                            }
                            parent.layer.close(index);
                        });
                    });
                }
                break;
        }
    });
    function layop() {
        layer.alert('该项目已提交', {
            icon: 7,
            title: "提示"
        });
    }
})
