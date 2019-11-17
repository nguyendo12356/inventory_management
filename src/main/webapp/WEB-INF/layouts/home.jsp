<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}" />
<c:set var="numMessage" value="${numMessage}" />
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>T2D Shop</title>
<!-- Bootstrap -->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/vendors/bootstrap/dist/css/bootstrap.min.css'/>" />
<!-- Font Awesome -->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/vendors/font-awesome/css/font-awesome.min.css'/>" />
<!-- Custom Theme Style -->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/build/css/custom.min.css'/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/styles.css'/>" />
</head>

<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<div class="col-md-3 left_col">
				<div class="left_col scroll-view">
					<div class="navbar nav_title" style="border: 0;">
						<a href="index.html" class="site_title"><i class="fa fa-paw"></i>
							<span>T2D Shop</span></a>
					</div>

					<div class="clearfix"></div>

					<!-- menu profile quick info -->
					<div class="profile clearfix">
						<div class="profile_pic">
							<img
								src="<c:url value='/resources/images/${session.imageName}'/>"
								alt="..." class="img-circle profile_img img_profile">
						</div>
						<div class="profile_info" style="padding-top: 20px;">
							<span>Xin chào,</span>
							<h2>${session.name}</h2>
						</div>
					</div>
					<!-- /menu profile quick info -->

					<br />

					<!-- sidebar menu -->
					<div id="sidebar-menu"
						class="main_menu_side hidden-print main_menu">
						<div class="menu_section">
							<h3>General</h3>
							<!-- Menu -->
							<tiles:insertAttribute name="menu" />

						</div>
					</div>
					<!-- /sidebar menu -->

					<!-- /menu footer buttons -->
					<!-- /menu footer buttons -->
				</div>
			</div>

			<!-- top navigation -->
			<div class="top_nav">
				<div class="nav_menu">
					<nav>
						<div class="nav toggle">
							<a id="menu_toggle"><i class="fa fa-bars"></i></a>
						</div>

						<ul class="nav navbar-nav navbar-right">
							<li class=""><a href="javascript:;"
								class="user-profile dropdown-toggle" data-toggle="dropdown"
								aria-expanded="false"> <img
									src="<c:url value='/resources/images/${session.imageName}'/>"
									alt="Loi"> ${session.name} <span
									class=" fa fa-angle-down"></span>
							</a>
								<ul class="dropdown-menu dropdown-usermenu pull-right">
									<li><a href=""> Profile</a></li>
									<li><a href="javascript:;"> <span
											class="badge bg-red pull-right">50%</span> <span>Settings</span>
									</a></li>
									<li><a href="javascript:;">Help</a></li>
									<li><a
										href="${pageContext.servletContext.contextPath}/logout"><i
											class="fa fa-sign-out pull-right"></i> Đăng Xuất</a></li>
								</ul></li>

							<li role="presentation" class="dropdown"><a
								href="javascript:;" class="dropdown-toggle info-number"
								data-toggle="dropdown" aria-expanded="false" id="btnMessage">
									<i class="fa fa-envelope-o"></i> <span class="badge bg-green"
									id="message1">${numMessage}</span>
							</a>
								<ul id="menu1" style="height: 400px; overflow-y: scroll;"
									class="dropdown-menu list-unstyled msg_list" role="menu">
									<!-- 									<li>
										<a>
										 	<span class="image">Cảnh báo</span>
										 	<span class="time">2019-12-03</span>
										<span class="message">Sản phẩm SP1 sắp hết hàng</span>
										</a>
									</li> -->
									<!-- <li>
										<div class="text-center">
											<a> <strong>See All Alerts</strong> <i
												class="fa fa-angle-right"></i>
											</a>
										</div>
									</li> -->
								</ul></li>
						</ul>
					</nav>
				</div>
			</div>
			<!-- /top navigation -->

			<!-- page content -->
			<div class="right_col" role="main">
				<tiles:insertAttribute name="content" />

			</div>
			<!-- /page content -->

			<!-- footer content -->
			<!-- 			<footer style="margin-top: -19px;">
				<div class="pull-right"><p>&copy; 2019 Inventory Management<p></div>
				<div class="clearfix"></div>
			</footer> -->
			<!-- /footer content -->
		</div>
	</div>

	<!-- jQuery -->
	<script src='<c:url value="/resources/bootstrap/js/jquery.min.js"/>'></script>
	<!-- Bootstrap -->
	<script src='<c:url value="/resources/common/bootstrap.min.js"/>'></script>
	<script src="<c:url value='/resources/build/js/custom.min.js'/>"></script>
	<script src="<c:url value='/resources/build/js/customjs.js'/>"></script>
	<script type="text/javascript">
		$('.right_col').css('min-height', '800px !important');
		if(${numMessage} != 0){
			$('#message1').html(${numMessage});
		}else{
			$('#message1').html('');
		}
		
		$.ajax({
			url: '${contextPath}/api/notificationUnread',
			async: false,
			success: function(data){
				$('#message').html(data);
			}
		})
		
		$('#btnMessage').on("click",function(){
			$.ajax({
				url: '${contextPath}/api/notification',
				type: 'get',
				success : function(data){
					$('#menu1').empty()
					$.each(data, function(){
						var mDate = new Date(this.createDate);
						var day = mDate.getDate();
						var month = mDate.getMonth() + 1;
						var year = mDate.getFullYear();
						var date = day+"/"+month+"/"+year;
						var id = this.id;
						var newLi = '<li id = '+id+'><a href="${contextPath}/inventory/'+this.code+'"><span class="image">'+this.title+'</span>'+
						'<span class="time">'+date+'</span>'+
						'<span class="message">'+this.message+'</span></a></li>';
						$('#menu1').append(newLi);
						if(this.status == 0){
							$('#'+id).css('background-color', 'lightgray');
						}
					})
					$('#message1').html('');
				}
			})
		})
	</script>
</body>
</html>