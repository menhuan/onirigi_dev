$(function() {
	// 初始化分页组件
	initPage();

})

function initPage() {
	layui.use(['laypage', 'layer'], function(){
		 var laypage = layui.laypage
		  ,layer = layui.layer;
		//完整功能
		  laypage.render({
		    elem: 'footer'    // div id
		    ,count: '${result.pages}'
		    , curr: location.hash.replace('#!fenye=', '') //获取起始页	
		    ,layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
		    ,jump: function(obj){
		      console.log(obj)
		    }
		  });
	}
}