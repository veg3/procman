layui.config({
    base: '/static/js/modules/' //配置 layui 第三方扩展组件存放的基础目录
}).extend({
    pdfobject: 'pdfobject'
}).use(['form','jquery','table'], function() {
    let form = layui.form,
        $ = layui.jquery,
        table = layui.table;

    $("#hepdf").click(function () {
        var url1 = "/apply/nima";
        var url2 = "/apply/dndoc/1"
        window.open("/static/pdfjs/web/viewer.html?file="+url2);
        return false;
    })
});