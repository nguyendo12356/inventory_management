<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
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

<body class="nav-md" style="background-color: white;">
	<div class="container body">
		<div class="main_container">
			<table style="width: 100%">
				<tr>
					<td width="12%">
			<div class="col-md-3 left_col">
				<div class="left_col scroll-view" style="margin-top: -104px;">
					<div class="navbar nav_title" style="border: 0; ">
						<a href="<c:url value='/home'/>" class="site_title"><i
							class="fa fa-paw"></i><span>&ensp;T2D Shop</span></a>
					</div>

					<div class="clearfix"></div>
					<!-- menu profile quick info -->
					<div class="profile clearfix">
						<div class="profile_pic">
							<img src="<c:url value='/resources/images/${session.imageName}'/>" alt="..."
								class="img-circle profile_img" style="padding: 1px;">
						</div>
						<div class="profile_info">
							<h2 style="margin-bottom: 3px;">Welcome ,</h2>
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
							<ul class="nav side-menu">
								<li><a><i class="fa fa-home"></i> Dashboard <span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="index.html">Dashboard</a></li>
										<li><a href="index2.html">Dashboard2</a></li>
										<li><a href="index3.html">Dashboard3</a></li>
									</ul></li>
								<li>
									<a href='<c:url value="/menu"/>'><i class="glyphicon glyphicon-menu-hamburger"></i>&ensp;&ensp; Quản lí Menu</a>
								</li>
								<li>
									<a href='<c:url value="/logout"/>'><i class="glyphicon glyphicon-log-out"></i>&ensp;&ensp; Đăng xuất</a>
								</li>
							</ul>
						</div>
					</div>
					<!-- /sidebar menu -->

					<!-- /menu footer buttons -->
					<!-- /menu footer buttons -->
				</div>
			</div>
			<!-- /Content -->
			</td>
			<td>
			<div>
				<table class="table">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">First</th>
							<th scope="col">Last</th>
							<th scope="col">Handle</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th scope="row">1</th>
							<td>Mark</td>
							<td>Otto</td>
							<td>@mdo</td>
						</tr>
						<tr>
							<th scope="row">2</th>
							<td>Jacob</td>
							<td>Thornton</td>
							<td>@fat</td>
						</tr>
						<tr>
							<th scope="row">3</th>
							<td>Larry</td>
							<td>the Bird</td>
							<td>@twitter</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- /Content -->
			<!-- /page content -->

			<!-- footer content -->
			<footer>
				<div class="pull-right">T2D Shop - Thạnh Mỹ Tây, Châu Phú, An
					Giang</div>
				<div class="clearfix"></div>
			</footer>
			<!-- /footer content -->
				</td>
			</tr>
		</table>
		</div>
	</div>
	<!-- jQuery -->
	<script src='<c:url value="/resources/bootstrap/js/jquery.min.js"/>'></script>
	<!-- Bootstrap -->
	<script src='<c:url value="/resources/common/bootstrap.min.js"/>'></script>
	<script src="../build/js/custom.min.js"></script>
	<script src="<c:url value='/resources/build/js/custom.min.js'/>"></script>
</body>
</html>