window.onload=function(){
	//提交表单
	$(".publish").click(function(){
			alert(1)
		$("#weiboForm").submit()
      })
      var open=false;
      $(".show").on("click",".fun .pinglun",event,function(){
    	  if(open){
    		  $(this).parent().parent().find(".comment").css({"display":"none","opacity":"0"})
    		  open=false;
    		  return ;
    	  }
		$(this).parent().parent().find(".comment").css({"display":"block","opacity":"1"})
		open=true;
	})
      

}