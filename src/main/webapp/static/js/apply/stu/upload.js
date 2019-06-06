layui.use(['form','jquery','layer','upload'],function() {
    let form = layui.form,
        $ = layui.jquery,
        upload = layui.upload,
        layer = layui.layer;
    var tid =$("#id").val();
    console.log("id: "+tid);
    var loadzip = upload.render({ //允许上传的文件后缀
        elem: '#test4'
        ,url: '/apply/upzip/'+tid
        ,accept: 'file' //普通文件
        ,exts: 'zip|rar|7z' //只允许上传压缩文件
        ,size: 20480
        ,choose: function(obj){
            //读取本地文件
            obj.preview(function(index, file, result){
                var size = file.size/1024;
                $("#fileinfo2").html(file.name+" "+size.toFixed(1)+"KB");
            });
        }
        ,done: function(res){
            if(res.code > 0){
                return layer.msg("上传失败");
                var demoText = $('#demoText2');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    loadzip.upload();
                });
            }
            if(res.code == 0){
                var demoText = $('#demoText2');
                demoText.html('');
                return layer.msg("上传成功");
            }
        }
        ,error: function () {
            var demoText = $('#demoText2');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                loadzip.upload();
            });
        }
    });

    var loadpdf = upload.render({ //允许上传的文件后缀
        elem: '#test3'
        ,url: '/apply/updoc/'+tid
        ,size: 2048
        ,accept: 'file' //普通文件
        ,exts: 'pdf' //只允许上传pdf文件
        ,choose: function(obj){
            //读取本地文件
            obj.preview(function(index, file, result){
                var size = file.size/1024;
                $("#fileinfo").html(file.name+" "+size.toFixed(1)+"KB");
            });
        }
        ,done: function(res){
            if(res.code > 0){
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    loadpdf.upload();
                });
                return layer.msg("上传失败");
            }
            if(res.code == 0){
                var demoText = $('#demoText');
                demoText.html('');
                return layer.msg("上传成功");
            }
        }
        ,error: function () {
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                loadpdf.upload();
            });
        }
    });
})