<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人微博首页</title>
<script type="text/javascript" src="/sinassm/js/jquery-3.3.1.min.js"></script>
<link id="skin_style" href="//img.t.sinajs.cn/t6/skin/skin053/skin.css?version=54261fee8278383e" type="text/css" rel="stylesheet" charset="utf-8" /> 
<link rel="stylesheet" href="../css/sinamain.css" />
<script type="text/javascript" src="/sinassm/js/sinamain.js"></script>
<style>
	.WB_miniblog{
		height: 1000px;
	}
	
	
</style>
</head>
<body>
<div class="top">
	<div class="pic">
		<img src="../img/weibo_03.gif" />
	</div>
	<input type="text" name="search" id="search" value="" placeholder="请输入" />
	<ul class="barli1">
		<li>首页</li>
		<li>视频</li>
		<li>发现</li>
		<li>游戏</li>
		<li>${sessionScope.user.name}</li>
	</ul>
	<ul class="barli2">
		<li>信息</li>
		<li>设置</li>
	</ul>
</div>
<div class="WB_miniblog">
	<div class="main">
		<div class="one"></div>
		<div class="two">
		<%-- ${pageContext.request.contextPath } --%>
			<form id="weiboForm" action="<%=request.getContextPath() %>/weibo/weiboPublish.action" method="post" enctype="multipart/form-data">
				<div class="new">
					<p class="some">推送热点信息</p>
					<input type="text"name="userId" value="${sessionScope.user.id}">
					<textarea name="content"></textarea>
					<div class="nav">
						<ul>
							<li>表情</li>
							<li>视频</li>
							<li><span >图片</span>
							<input type="file" name="userpic" accept="image/gif,image/jpeg,image/jpg,image/png" class="uploadpic"/> </li>
							<li>话题</li>
							<li>头条文章</li>
							<li>...</li>
						</ul>
						<p class="publish">发布</p>
					</div>
				</div>
				
			</form>
		<c:forEach items="${weibos}" var="weibo">
			<div class="show">
				<div class="weibo">
					<div class="userbar">
						<img src="/pic/${sessionScope.user.pic}"/>
						<ul>
							<li>昵称${sessionScope.user.name}</li>
							<li>时间</li>
						</ul>
					</div>
					<div class="userWord">${weibo.content}</div>
					<div class="userPic"><img  src="/pic/${weibo.pic}"></div>
					<ul class="fun">
							<li>推广</li>
							<li>转发</li>
							<li class="pinglun">评论</li>
							<li>赞</li>
					</ul>
					<div class="comment">
						<div class="officail">
							sdfsdfasdfasdfasdf
						</div>
						<div class="input">
							<img  src="/pic/${user.pic}"/>
							<input type="text" placeholder="尽情毒舌吧" />
						</div>
						<div class="publishWord">
							<ul >
								<li><img src="../img/mypublish_03.gif"></li>
								<li> <img src="../img/mypublish_05.gif"/></li>
								<li><img src="../img/mypublish_07.gif"/></li>
								<li> <input type="checkbox"><span>同时转发到我的微博</span> </li>
							</ul>
							<p id="weibocritical">评论</p>
						</div>
						
					</div>
				</div>
			</div>
	</c:forEach>
		</div>
		<div class="three"></div>
	</div>
</div>

</body>
</html>