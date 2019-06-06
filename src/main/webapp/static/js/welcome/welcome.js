layui.use(['jquery'],function(){
    var $ = layui.$;
   $(".dfa").click(function () {
      window.open($(this).attr("href"));
      return false;
   });
})