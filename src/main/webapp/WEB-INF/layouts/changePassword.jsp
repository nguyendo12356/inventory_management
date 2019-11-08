<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>
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
<style type="text/css">
.div-changePassword {
	width: 30%;
	position: absolute;
	top: 50%;
	left: 50%;
	height: 240px;
	transform: translate(-50%, -50%);
	background: radial-gradient(lightblue, transparent);
	transform: translate(-50%, -50%);
	display: table;
	padding: 0 20px;
	border-radius: 20px;
	box-shadow: 1px 1px 10px;
	color: white;
}

.div-changePassword form {
	display: table-cell;
	vertical-align: middle;
}

.div-changePassword form button{
	float: right;
}

body{
	height: 100vh;
	background: url("${contextPath}/resources/images/bg1.jpg") no-repeat center center;
	background-size: cover;
}
</style>
</head>
<body class="container">
	<div class="div-changePassword">
		<form class="" action="${contextPath}/updatePassword" method="post">
			<div class="form-group">
				<label for="email">Nhập mật khẩu mới:</label> <input type="password"
					class="form-control" id="email" name="password">
			</div>
			<div class="form-group">
				<label for="pwd">Xác nhận mật khẩu:</label> <input type="password"
					class="form-control" id="pwd">
			</div>
			<input type="hidden" name="username" value="${username}"/>
			<button type="submit" class="btn btn-danger">Đổi mật khẩu</button>
		</form>
	</div>
</body>
</html>