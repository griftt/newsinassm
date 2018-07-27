window.onload=function(){
	layui.use([ 'table', 'element' ], function() {
		var msgtable = layui.table;
		//展示待发送信息
		var tableins=$("#UserMessage").click(function(){
			$(".page").css({"opacity":"0","z-index":"1",});
			$(".showUserMesssage").css({"opacity": "1" , "z-index":"1000"});
			var tableins_msg=msgtable.render({
				 		elem: '#messagetable'
						,height: 500
						,url: '/sinassm/message/userMessage.action' //数据接口
						,page: true //开启分页
						,cols: [[ //表头
						          {checkbox : true,fixed : true,align : 'center',width : 40  }   
						          ,{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left',align : 'center'}
						          ,{field: 'userId', title: '用户ID', width:80,align : 'center'}
						          ,{field: 'receiveId', title: '好友id', width:80,align : 'center' }
						          ,{field: 'content', title: '内容', width:80,align : 'center'} 
						          ,{field: 'pic', title: '图片', width: 150,align : 'center'}
						          ,{field: 'time', title: '时间', width: 80,align : 'center',sort:true,
							    	templet:"#mytime"}
						          ,{toolbar : "#messageBar",title : '操作',width : 200,align : 'center'}
						        ]]
							
				  });
			
			
			
		})
		
		
	});
	
	
}