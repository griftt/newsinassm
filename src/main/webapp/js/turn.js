window.onload=function(){
	layui.use([ 'table', 'element' ], function() {
		var table = layui.table;
		//转换静态表格
		//table.init('demo', {
		//height: 400 //设置高度
		//,limit: 3 //注意：请务必确保 limit 参数（默认：10）是与你服务端限定的数据条数一致
		////支持所有基础参数
		//,page:'true'
		//}); turn.js
		//user表格
		tableins= table.render({
			elem : '#demo',
			height : 500,
			url : '/sinassm/user/loadUser.action' ,//数据接口
			page : true ,//开启分页
			cols : [ [ //表头
			{
				checkbox : true,
				fixed : true,
				align : 'center',
				width : 40
			}, {
				field : 'id',
				type : 'numbers',
				title : 'ID',
				width : 80,
				sort : true,
				align : 'center'
			}, {
				field : 'account',
				title : '账号',
				width : 80,
				align : 'center'
			}, {
				field : 'pwd',
				title : '密码',
				width : 80,
				align : 'center'
			}, {
				field : 'name',
				title : '用户名',
				width : 90,
				align : 'center'
			}, {
				field : 'gender',
				title : '性别',
				width : 80,
				align : 'center'
			}, {
				field : 'province',
				title : '省份',
				width : 80,
				align : 'center'
			}, {
				field : 'city',
				title : '城市',
				width : 80,
				align : 'center'
			}, {
				field : 'birthday',
				title : '生日',
				width : 80,
				align : 'center'
			}, {
				field : 'pic',
				title : '头像',
				width : 100,
				align : 'center'
			}, {
				field : 'statement',
				title : '签名',
				width : 80,
				align : 'center'
			},  {
				field : 'createtime',
				title : '注册时间',
				width : 120,
				align : 'center',
				templet: "#usercreatetime"
			},{
				field : 'dao',
				title : '操作',
				width : 260,
				toolbar : "#barDemo",
				align : 'center'
			}

			] ],
			id : 'first',
			request : {
				pageName : 'page' //页码的参数名称，默认：page
			   ,limitName : 'limit' //每页数据量的参数名，默认：limit
			}

		});
		table.on('checkbox(test)', function(obj) {
			console.log(obj.checked); //当前是否选中状态
			console.log(obj.data); //选中行的相关数据
			console.log(obj.type);
			alert(obj.data.id)//如果触发的是全选，则为：all，如果触发的是单选，则为：one
		});
		var flag=false;
		var num=0;
	table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
		  var data = obj.data; //获得当前行数据
		  var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		  var tr = obj.tr; //获得当前行 tr 的DOM对象
		  
		  if(layEvent == 'editUser'){ //查看
		    alert(data)
		  } 
		  else if(layEvent == 'weiboUser'){ //查看用户weibo
			  reloadWeibo(data.id);
		    //$(".page").css({"opacity":"0","z-index":"1",});
			//$(".showWeibo").css({"opacity": "1" , "z-index":"1000",});
		  } 
		  else if(layEvent == 'delUser'){ //删除
			    layer.confirm('真的删除行么', function(index){
			      obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
			      layer.close(index);
			      //向服务端发送删除指令
			    });
			  }
		  else if(layEvent == 'userFriend'){
			  reloadFriend(data.id)
			  //$(".page").css({"opacity":"0","z-index":"1",});
			  //$(".showUserFriend").css({"opacity": "1" , "z-index":"1000"});
		  }
		  
		  
		  else if(layEvent == 'delAdmin'){
			  layer.confirm('真的想要删除吗？', function(index){
				  deleteSome(data.id,"/sinassm/adminLogin/deleteAdmin.action");
				  if(num==0){
					  flag=true;
					  num++;
				  }
				  if(flag==true){
			    	  obj.del();
			    	  flag=false;//删除对应行（tr）的DOM结构，并更新缓存
			      }
				  layer.close(index);
				  
			   });
		  }else if(layEvent == 'editAdmin'){
			  alert(data.id)
		  }
		  //weibo页的功能条
		  else if(layEvent == 'weiboContent'){ //删除
			   reloadComment(data.id)
			  }
	})
		function deleteSome(id,url){
			$.ajax({
				url:url,
				type:"get",
				data:"id="+id,
				dataType:"json",
				success:function(obj){
					flag=obj;
				}
			})
		}
		//user表格重載
		var $userAdmin2=$("#userAdmin2")
		$userAdmin2.click(function(){
			$("#userAdmin").trigger("click")
			tableins.reload({
				elem : '#demo',
				height : 500,
				url : '/sinassm/user/loadUser.action' //数据接口
				,page : true //开启分页
				
				,cols : [ [ //表头
				{
					checkbox : true,
					fixed : true,
					align : 'center',
					width : 40
				}, {
					field : 'id',
					type : 'numbers',
					title : 'ID',
					fixed: 'left',
					width : 80,
					sort : true,
					align : 'center'
				}, {
					field : 'account',
					title : '账号',
					width : 80,
					align : 'center'
				}, {
					field : 'pwd',
					title : '密码',
					width : 80,
					align : 'center'
				}, {
					field : 'name',
					title : '用户名',
					width : 90,
					align : 'center'
				}, {
					field : 'gender',
					title : '性别',
					width : 80,
					align : 'center'
				}, {
					field : 'province',
					title : '省份',
					width : 80,
					align : 'center'
				}, {
					field : 'city',
					title : '城市',
					width : 80,
					align : 'center'
				}, {
					field : 'birthday',
					title : '生日',
					width : 80,
					align : 'center'
				}, {
					field : 'pic',
					title : '头像',
					width : 100,
					align : 'center'
				}, {
					field : 'statement',
					title : '签名',
					width : 120,
					align : 'center'
				}, {
					field : 'createtime',
					title : '注册时间',
					width : 120,
					align : 'center',
					sort:true,
					templet: "#usercreatetime"
						
				}
				, {
					field : 'dao',
					title : '操作',
					width : 300,
					toolbar : "#barDemo",
					align : 'center'
				}

				] ],
				

			});
		})
		//近三天注册的用户或当前在线用户
		function getUser(url){
			$("#userAdmin").trigger("click")
			
			tableins.reload({
				elem : '#demo',
				height : 500,
				url :url //数据接口
				,page : true //开启分页
				,where:{
					"day":3
				}
				,cols : [ [ //表头
				{
					checkbox : true,
					fixed : true,
					align : 'center',
					width : 40
				}, {
					field : 'id',
					type : 'numbers',
					title : 'ID',
					fixed: 'left',
					width : 80,
					sort : true,
					align : 'center'
				}, {
					field : 'account',
					title : '账号',
					width : 80,
					align : 'center'
				}, {
					field : 'pwd',
					title : '密码',
					width : 80,
					align : 'center'
				}, {
					field : 'name',
					title : '用户名',
					width : 90,
					align : 'center'
				}, {
					field : 'gender',
					title : '性别',
					width : 80,
					align : 'center'
				}, {
					field : 'province',
					title : '省份',
					width : 80,
					align : 'center'
				}, {
					field : 'city',
					title : '城市',
					width : 80,
					align : 'center'
				}, {
					field : 'birthday',
					title : '生日',
					width : 80,
					align : 'center'
				}, {
					field : 'pic',
					title : '头像',
					width : 100,
					align : 'center'
				},{
					field : 'createtime',
					title : '注册时间',
					width : 120,
					align : 'center',
					sort:true,
					templet: "#usercreatetime"
				},{
					field : 'statement',
					title : '签名',
					width : 80,
					align : 'center'
				}, {
					field : 'dao',
					title : '操作',
					width : 300,
					toolbar : "#barDemo",
					align : 'center'
				}

				]],
			});
			
			
			
			
		}
		
		
		
		var $search_in = $("#sea");
		$search_in.keyup(function() {
			var account = $(this).val();
			tableins.reload({
				elem : '#demo',
				height : 500,
				url : '/sinassm/user/findUser.action?account='+account //数据接口
				,
				page : true //开启分页
				,
				cols : [ [ //表头
				{
					checkbox : true,
					fixed : true,
					align : 'center',
					width : 40
				}, {
					field : 'id',
					type : 'numbers',
					title : 'ID',
					width : 80,
					fixed: 'left',
					sort : true,
					align : 'center'
				}, {
					field : 'account',
					title : '账号',
					width : 200,
					align : 'center'
				}, {
					field : 'pwd',
					title : '密码',
					width : 200,
					align : 'center'
				}, {
					field : 'name',
					title : '用户名',
					width : 220,
					align : 'center'
				}, {
					field : 'gender',
					title : '性别',
					width : 80,
					align : 'center'
				}, {
					field : 'province',
					title : '省份',
					width : 80,
					align : 'center'
				}, {
					field : 'city',
					title : '城市',
					width : 80,
					align : 'center'
				}, {
					field : 'birthday',
					title : '生日',
					width : 80,
					align : 'center'
				}, {
					field : 'pic',
					title : '头像',
					width : 100,
					align : 'center'
				}, {
					field : 'statement',
					title : '签名',
					width : 300,
					align : 'center'
				}, {
					field : 'dao',
					title : '操作',
					width : 260,
					toolbar : "#barDemo",
					align : 'center'
				}

				] ]
				,page : {
					curr : 1
				}

			})
		})
	//管理员列表
	$("#empower").click(function(){	
		$("#userAdmin").trigger("click")
		tableins.reload({
			elem : '#demo',
			height : 500,			
			url : '/sinassm/adminLogin/adminPage.action' //数据接口
			,page : true //开启分页
			,cols : [ [ //表头
			{
				checkbox : true,
				fixed : true,
				align : 'center',
				width : 40
			}, {
				field : 'id',
				type : 'numbers',
				title : 'ID',
				width : 80,
				fixed: 'left',
				sort : true,
				align : 'center'
			}, {
				field : 'account',
				title : '账号',
				width : 200,
				align : 'center'
			}, {
				field : 'pwd',
				title : '密码',
				width : 200,
				align : 'center'
			}, {
				field : 'name',
				title : '用户名',
				width : 220,
				align : 'center'
			}, {
				field : 'roleId',
				title : '性别',
				width : 80,
				align : 'center'
			},{
				field : 'dao',
				title : '操作',
				width : 300,
				toolbar : "#adminBar",
				align : 'center'
			}
			] ]
			,page : {
				curr : 1
			}
			 })
		
		
		})

	//管理员创建时的密码确认
	var $in1=$("#in1");
	var $in2=$("#in2");
	$in2.blur(function(){
		if($in1.val()!=$in2.val()){
			alert("请确认密码");
			return false;
		}
	})
	$("#addAdmin").click(function(){
		$("input[name='account']").val("")
		$("input[name='pwd']").val("")
		$("select[name='roleId']").val(0)
		$("#adminName").val("")
		$.ajax({
			url:"/sina/adminLogin/addAdmin",
			type:"post",
			data:{"account":$("input[name='account']").val(),"pwd":$("input[name='pwd']").val(),"roleId":$("select[name='roleId']").val(),"adminName":$("#adminName").val()},
			dataType:"json",
			success:function(){
				alert("success"+$("#adminName").val())
					$("#empower").trigger("click")
					$("#userAdmin").trigger("click")
			},
			error:function(){
				alert("插入有誤")
			}
			
			
		})
		
	})
	function reloadWeibo(obj){
		//weibo表格
		tableins.reload({
				elem : '#demo',
				height : 500,
				url : '/sinassm/weibo/weiboPage.action' //数据接口
				,
				page : true //开启分页
				,where:{
					"id":obj
				}
				,
				cols : [ [ //表头
				{
					checkbox : true,
					fixed : true,
					align : 'center',
					width : 40
				}, {
					field : 'id',
					type : 'numbers',
					title : 'ID',
					width : 80,
					sort : true,
					fixed: 'left',
					align : 'center'
				}, {
					field : 'userId',
					title : '用户id',
					width : 80,
					align : 'center'
				}, {
					field : 'content',
					title : '内容',
					width : 270,
					align : 'center'
				}, {
					field : 'time',
					title : '发布时间',
					width : 193,
					align : 'center',
					 templet: "#mytime"
				}, {
					field : 'pic',
					title : '图片',
					width : 120,
					align : 'center'
				}, {
					toolbar : "#weiboBar",
					title : '操作',
					width : 300,
					align : 'center'
				}
	
				] ],
				
	
			});
			
	}

	table.on('checkbox(weibo)', function(obj) {
		console.log(obj.checked); //当前是否选中状态
		console.log(obj.data); //选中行的相关数据
		console.log(obj.type);
		alert(obj.data.id)//如果触发的是全选，则为：all，如果触发的是单选，则为：one
	});
	var flag1=false;
	var num1=0;
table.on('tool(weibo)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
	  var data = obj.data; //获得当前行数据
	  var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
	  var tr = obj.tr; //获得当前行 tr 的DOM对象

	  if(layEvent == 'weiboContent'){ //删除
		   reloadComment(data.id)
		  }
	  else if(layEvent == 'delWeibo'){
		  layer.confirm('真的想要删除吗？', function(index){
			  //deleteSome(data.id,"/sinassm/adminLogin/deleteAdmin.action");
			  if(num1==0){
				  flag1=true;
				  num1++;
			  }
			  if(flag1==true){
		    	  obj.del();
		    	  flag1=false;//删除对应行（tr）的DOM结构，并更新缓存
		      }
			  layer.close(index);
			  
		   });
	  } 
	  else if(layEvent == 'editWeibo'){
		  alert(data.id)
	  }
})
	function reloadComment(id){
	  tableins.reload({
		    elem: '#demo'
		    ,height: 500
		    ,where:{
		    	objectId:id
		    }
		    ,url: '/sinassm/comment/weiboComment.action' //数据接口
		    ,page: true //开启分页
		    ,cols: [[ //表头
		       {checkbox : true,fixed : true,align : 'center',width : 40  }   
		      ,{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left',align : 'center'}
		      ,{field: 'objectId', title: '微博', width:80,align : 'center'}
		      ,{field: 'userId', title: '评论者', width:80,align : 'center' }
		      ,{field: 'reviewId', title: '被回复者', width:80,align : 'center'} 
		      ,{field: 'content', title: '内容', width: 177,align : 'center'}
		      ,{field: 'time', title: '时间', width: 80,align : 'center',sort:true,
		    	  templet: "#mytime"}
		      , {toolbar : "#WeiboContentBar",	title : '操作',width : 300,align : 'center'}
		    ]]
		  });
	}
  //展示用户的好友
	function reloadFriend(id){
		  tableins.reload({
			    elem: '#demo'
			    ,height: 500
			    ,where:{
			    	id:id
			    }
			    ,url: '/sinassm/friend/userFriend.action' //数据接口
			    ,page: true //开启分页
			    ,cols: [[ //表头
			       {checkbox : true,fixed : true,align : 'center',width : 40  }   
			      ,{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left',align : 'center'}
			      ,{field: 'userId', title: '用户ID', width:80,align : 'center'}
			      ,{field: 'friendId', title: '好友id', width:80,align : 'center' }
			      ,{field: 'name', title: '好友昵称', width:80,align : 'center'} 
			      ,{field: 'focus', title: '你关注ta', width: 177,align : 'center'}
			      ,{field: 'beFocus', title: 'ta关注你', width: 177,align : 'center'}
			      , {toolbar : "#friendBar",	title : '操作',width : 300,align : 'center'}
			    ]]
			  });
		}
	
	//展示待发送信息
	$("#UserMessage").click(function(){
		//$(".page").css({"opacity":"0","z-index":"1",});
		//$(".showUserMesssage").css({"opacity": "1" , "z-index":"1000"});
		$("#userAdmin").trigger("click")
		var tableins_msg=tableins.reload({
			 		elem: '#demo'
					,height: 500
					,url: '/sinassm/message/userMessage.action' //数据接口
					,page: true //开启分页
					,cols: [[ //表头
					          {checkbox : true,fixed : true,align : 'center',width : 40  }   
					          ,{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left',align : 'center'}
					          ,{field: 'userId', title: '用户ID', width:80,align : 'center'}
					          ,{field: 'receiveId', title: '好友id', width:80,align : 'center' }
					          ,{field: 'content', title: '内容', width:270,align : 'center'} 
					          ,{field: 'pic', title: '图片', width: 120,align : 'center'}
					          ,{field: 'time', title: '时间', width: 193,align : 'center',sort:true,
						    	templet:"#mytime"}
					          ,{toolbar : "#messageBar",title : '操作',width : 100,align : 'center'}
					        ]]
						
			  });
		
		
		
	})
	
	function reloadWeiboByDate(myurl,day){
		tableins.reload({
			elem : '#demo',
			height : 500,
			url :myurl, //数据接口
			page : true, //开启分页
			where:{
				"day":day
			},
			cols : [ [ //表头
			{
				checkbox : true,
				fixed : true,
				align : 'center',
				width : 40
			}, {
				field : 'id',
				type : 'numbers',
				title : 'ID',
				width : 80,
				sort : true,
				fixed: 'left',
				align : 'center'
			}, {
				field : 'userId',
				title : '用户id',
				width : 80,
				align : 'center'
			}, {
				field : 'content',
				title : '内容',
				width : 270,
				align : 'center'
			}, {
				field : 'time',
				title : '发布时间',
				width : 193,
				sort : true,
				align : 'center',
				 templet: "#mytime"
			}, {
				field : 'pic',
				title : '图片',
				width : 120,
				align : 'center'
			}, {
				toolbar : "#weiboBar",
				title : '操作',
				width : 300,
				align : 'center'
			}

			]],
		});
		
		
	}
	//查询近三天的微博
	var url="/sinassm/weibo/selectWeiBoByDate.action"
	$("#weibo_today").click(function(){
		$("#userAdmin").trigger("click")
		reloadWeiboByDate(url,0);
		
	})
	$("#weibo_threeday").click(function(){
		$("#userAdmin").trigger("click")
		reloadWeiboByDate(url,3);
		
	})
	$("#weibo_week").click(function(){
		$("#userAdmin").trigger("click")
		reloadWeiboByDate(url,7);
		
	})
	
	//查询新注册用户
	$("#newuser_three").click(function(){
		getUser("/sinassm/user/getNewUser.action")
		$("#userAdmin").trigger("click");
		
		
	})
	//查询在线用户
	$("#user_online").click(function(){
		getUser("/sinassm/user/getUserOnline.action")
		$("#userAdmin").trigger("click");
		
		
	})
	
	
	
	
	
	
	//下面这个个花括号是layui的
	
});

	
	

	
	$(".page").css({"opacity":"0","z-index":"1",});
	$(".users").css({"opacity": "1" , "z-index":"1000",});
	$("#userAdmin").click(function() {
		$(".page").css({"opacity":"0","z-index":"1",});
		$(".users").css({"opacity": "1" , "z-index":"1000",});
	})
	$("#control").click(function(){
		$(".page").css({"opacity":"0","z-index":"1",});
		$(".userdetail").css({"opacity": "1" , "z-index":"1000",});
	})
	$("#wei").click(function() {
		$(".page").css({"opacity":"0","z-index":"1",});
		$(".weibo").css({"opacity": "1" , "z-index":"1000",});
	})
	$("#createAdmin").click(function() {
		$(".page").css({"opacity":"0","z-index":"1",});
		$(".adminpage").css({"opacity": "1" , "z-index":"1000",});
	})
	$("#userWeibo").click(function() {
		$(".page").css({"opacity":"0","z-index":"1",});
		$(".showWeibo").css({"opacity": "1" , "z-index":"1000",});
	})
	
	
	
}

