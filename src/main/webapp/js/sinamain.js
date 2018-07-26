window.onload=function(){
	//提交表单
	$(".publish").click(function(){
			alert(1)
		$("#weiboForm").submit()
      })
      var open=false;
      $(".show").on("click",".fun .pinglun",function(){
    	  if(open){
    		  $(this).parent().parent().find(".comment").css({"display":"none","opacity":"0"})
    		  open=false;
    		  return ;
    	  }
    	  else{
    		  var $com=$(this).parent().parent().find(".comment")
    		  .css({"display":"block","opacity":"1"})
    		    $.ajax({
    			  url:"/sinassm/comment/weiboComment.action",
    			  type:"post",
    			  data:"userId=1&page=1&limit=10",
    			  dataType:"json",
    			  success:function(obj){
    				  alert(obj)
    				// var $com = $(this).parent().parent().find(".comment")
    				 var $commentOne=$("<div class='userconmment'><div class='userbar2'><img src='a.jpg' /><ul><li><p><span>昵称：</span></p></li><li><span>时间</span><p><a>删除</a><a>回复</a><a>赞</a></p></li></ul></div></div>")
    			
    				 // var $commentOne=$('<div style="background:red;height:20px;width:20px;">haha</div>')
    				 $com.append($commentOne)
    				 	alert($com.append($commentOne))
    				 
    			  }
    		  })
    		  open=true;
    		  
    	  }
    	 })
      

}