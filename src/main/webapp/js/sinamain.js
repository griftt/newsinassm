window.onload=function(){
	//提交表单
	$(".publish").click(function(){
		$("#weiboForm").submit()
      })
      $(".picIcon").click(function(){
    	  $(".uploadpic").trigger("click");
      })
      //评论框的显示
      var open=false;
      $(".show").on(
    		  "click",".fun .pinglun",function(){
    	  if(open){
    		  $(this).parent().parent().find(".comment").css({"display":"none","opacity":"0"})
    		  open=false;
    		  return ;
    	  }
    	  else{
    		  var $com=$(this).parent().parent().find(".comment")
    		  .css({"display":"block","opacity":"1"})
    		    $.ajax({
    			  url:"/sinassm/userRequest/weiboComment.action",
    			  type:"post",
    			  data:"userId=1&page=1&limit=10",
    			  dataType:"json",
    			  success:function(obj){
    				  alert(obj)
    				// var $com = $(this).parent().parent().find(".comment")
    				 var $commentOne=$("<div class='userconmment'><div class='userbar2'><img src='a.jpg' /><ul><li><p><span>昵称：</span></p></li><li><span>时间</span><p><a>删除</a><a>回复</a><a>赞</a></p></li></ul></div></div>")
    			
    				 // var $commentOne=$('<div style="background:red;height:20px;width:20px;">haha</div>')
    				 $com.append($commentOne);
    				 	
    				 
    			  }
    		  })
    		  open=true;
    	  }
    	 })
    	 //user信息弹框的显示
    	 $(".userIcon").hover(
    	 function(){
    		 
    		 var $wb_user= $(this).parent().parent();
    		 var id=$wb_user.find("#userId").val();
    		 var objectId=$wb_user.find("#objectId").val()
    		 var a=Math.random();
    		 $.ajax({
    			 url:"/sinassm/userRequest/userMessage.action",
    			 type:"post",
    			 data:"id="+id+"&objectId="+objectId,
    			 dataType:"json",
    			 success:function(obj){
    				 $wb_user.find("#focusCount").text(obj.focusCount)
    				 $wb_user.find("#befocusCount").text(obj.befocusCount)
    				 $wb_user.find("#weiboCount").text(obj.weiboCount);
    				   $wb_user.find(".province").text(obj.province)
    			 }
    			 
    		 })
    		 
    		 $(this).parent().parent().find("#personBox").css({"opacity":1,"z-index":10});
    		 $(this).parent().parent().find("#personBox").hover(
    				 function(){
    					/* if( $("#personBox").css("opacity")==0){
    		    			 return ;
    		    		 }*/
    					 $(this).css({"opacity":1,"z-index":10});
    				 			}
			    	, function(){
			       			$(this).parent().parent().find("#personBox").css({"opacity":0,"z-index":-10})
			    	})
    	 },
    	 function(){
    			$(this).parent().parent().find("#personBox").css({"opacity":0,"z-index":-10})
    	 });
  //取消关注
     var main_userId =$("#main_userId").val();
  $("#canleFocus").click(function(){
	  var ObjectId_focus=$(this).parent().parent().parent().parent().find("#userId").val();
	  $.ajax({ 
		  url:"/sinassm/userRequest/canleFocus.action",
		  type:"post",
		  data:"objectId="+ObjectId_focus+"&userId="+main_userId,
		  success:function(obj){
			 alert("ok"+obj) ;
			 
			location.href="/sinassm/userRequest/weibos2.action"
		  }
	  })
  })    
    	 
    	 
}