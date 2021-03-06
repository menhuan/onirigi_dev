$(function() {
	// 初始化分页组件
	initPage();

})

function initPage() {
	laypage({
	    cont: 'footer',
	    pages: '${result.pages}', //可以叫服务端把总页数放在某一个隐藏域，再获取。假设我们获取到的是18
	    curr: function(){ //通过url获取当前页，也可以同上（pages）方式获取
	        var page = location.search.match(/page=(\d+)/);
	        return page ? page[1] : 1;
	    }(), 
	    jump: function(e, first){ //触发分页后的回调
	        if(!first){ //一定要加此判断，否则初始时会无限刷新
				var url = location.href.toString()
				location.href= changeURLArg(url, 'page',e.curr);
	        }
	    }
	});
}