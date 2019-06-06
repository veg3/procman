layui.config({
    base: '/static/js/modules/' //配置 layui 第三方扩展组件存放的基础目录
}).extend({
    echarts:'echarts',
    formSelects: 'formSelects-v4'
}).use(['form','jquery','table','laydate','formSelects','echarts'], function() {
    let form = layui.form,
        $ = layui.jquery,
        table = layui.table,
        laydate = layui.laydate,
        echarts = layui.echarts,
        formSelects = layui.formSelects;

    $.post('/facul/facnames',function (result) {
        workData = result.data;
        var strs="<option value=''></option>";
        for(var x in workData){
            strs+="<option value ='" + workData[x]+ "'>" + workData[x]+ "</option>";
        }
        $("#sfa").html(strs);
        formSelects.render('select1');
    });


    laydate.render({
        elem: '#test8'
        ,range: '~'
    });

    form.on("submit(btns)",function (data) {
        var str = JSON.stringify(data.field);
        var obj = JSON.parse(str);
        console.log("date: "+obj.executeDate);
        if($.trim(obj.faculty) == ""){
            layer.alert('院系不能为空', {
                icon: 7,
                title: "提示"
            });
            return false;
        }
        else {
            $.ajax({
                type: "POST",
                async: false,
                dataType: 'json',
                contentType: "application/json",
                url: '/item/chartbar',
                data: str,
                success: function (info) {
                    var num = info.data.option.length;
                    var cidName = new Array("tda","sda");
                    for(var m = num-1;m>=0;m--) {
                        var resultmap = info.data.option[m];
                        var option = {};
                        $.extend(true, option, opt);
                        option.title.text = resultmap.title;
                        option.legend.data = resultmap.legend;
                        option.xAxis.data = resultmap.xaxis;
                        var len = resultmap.legend.length;
                        var pie = {data: []};
                        for (var i = 0; i < len; i++) {
                            var rda = resultmap.series[i].data;
                            var sum = eval(rda.join("+"));
                            pie.data[i] = {name: resultmap.series[i].name, value: sum};
                            resultmap.series[i].markPoint = {data: [{type: 'max', name: '最大值'}]};
                            option.series[i] = resultmap.series[i];
                        }
                        option.series[len] = {
                            type: 'pie',
                            name: '总计 :',
                            radius: [0, '21%'],
                            center: ['88%', '30%'],
                            tooltip: {
                                trigger: 'item',
                                formatter: "{a} <br/>{b} : {c} ({d}%)"
                            },
                            data: pie.data
                        };
                        var chart;
                        if(m==1){
                           chart = echarts.init(document.getElementById('sda'));
                        }
                        else{
                            chart = echarts.init(document.getElementById('tda'));
                        }
                        $("#"+cidName[m]).height('560px');
                        $("#"+cidName[m]).width('100%');
                        chart.resize();
                        chart.setOption(option, true);
                    }
                }
            });
            return false;
        }
    });
    var opt = {
        title : {
            text: '',
        },
        tooltip : {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            },
            textStyle:{
                align:'left'
            }
        },
        grid: {
            top: 75,
            width: '75%',
            bottom: '45%',
            left: 2,
            containLabel: true
        },
        legend: {
            data:[]
        },
        toolbox: {
            show : true,
            feature : {
                dataView : {
                    show: true,
                    readOnly: false
                },
                magicType : {show: true,type: ['line', 'bar','stack', 'tiled']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        xAxis : {
                type: 'category',
                axisTick: {
                    alignWithLabel: true
                },
                name : '学院',
                data : []
            }
        ,
        yAxis : {
                name : '数量',
                type : 'value'
        },
        series : []
    };

});