<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}" />
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
	<li role="presentation" class="dropdown"><a href="javascript:;"
		class="dropdown-toggle info-number" data-toggle="dropdown"
		aria-expanded="false" id="btnMessage"> <i class="fa fa-envelope-o"></i>
			<span class="badge bg-green" id="message"></span>
	</a>
		<ul id="menu1" class="dropdown-menu list-unstyled msg_list"
			role="menu">
			<li><a> <span class="image">Cảnh báo</span> <span
					class="time">2019-12-03</span> <span class="message">Sản
						phẩm SP1 sắp hết hàng</span>
			</a></li>
			<li>
				<div class="text-center">
					<a> <strong>See All Alerts</strong> <i
						class="fa fa-angle-right"></i>
					</a>
				</div>
			</li>
		</ul></li>

	<!-- jQuery -->
	<script src='<c:url value="/resources/bootstrap/js/jquery.min.js"/>'></script>
	<!-- Bootstrap -->
	<script src='<c:url value="/resources/common/bootstrap.min.js"/>'></script>
	<script src="<c:url value='/resources/build/js/custom.min.js'/>"></script>
	<script src="<c:url value='/resources/build/js/customjs.js'/>"></script>
	<script type="text/javascript">
		$('.right_col').css('min-height', '800px !important');

		$('#message').html(4);

		$('#btnMessage').on("click", function() {

			$.ajax({
				url : '${contextPath}/api/notification',
				type : 'get',
				success : function(data) {
					//$('#menu1').empty()
				}
			})
		})
	</script>
</body>
</html>