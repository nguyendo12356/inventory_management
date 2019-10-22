<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
body {
	font-weight: 500 !important;
}
</style>
</head>
<body class="login">
	<div class="login_wrapper">
		<div id="register" class="animate form registration_form"
			style="opacity: 1;">
			<section class="login_content">
				<c:if test="${not empty success}">
					<div class="alert alert-success alert-dismissible" style="text-align: left;" id="success" >
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						${success}
					</div>
				</c:if>
				<c:remove var="success"/>
				<form:form modelAttribute="user" method="post" action="${pageContext.request.contextPath}/addUser" enctype="multipart/form-data">
					<h1>
						<c:choose>
							<c:when test="${id > 0}">Cập nhật thông tin</c:when>
							<c:otherwise>Tạo tài khoản mới</c:otherwise>
						</c:choose>
					</h1>
						<div id="div_username">
							<form:input path="username" id="username" class="form-control"
								placeholder="Username" />
							<label class="label_error" id="error_username">${error}</label>
						</div>
						<div>
							<form:password class="form-control" id="password" path="password"
								placeholder="Password" />
							<div><input type="hidden" value="${user.password}" id="passwordHidden"/></div>
							<label class="label_error" id="error_password">${errorPassword}</label>
						</div>

					<div>
						<form:input path="email" id="email" class="form-control"
							placeholder="Email" />
						<label class="label_error" id="error_email">${errorEmail}</label>
					</div>
					<div>
						<form:input path="name" id="name" class="form-control"
							placeholder="Your name" />
						<label class="label_error" id="error_name"></label>
					</div>
					<div>
						<form:select path="gender" class="form-control">
							<form:option value="1">Nam</form:option>
							<form:option value="0">Nữ</form:option>
						</form:select>
					</div>
					<div style="margin-top: 30px;margin-bottom: 10px;">
						<form:input type="file" id="image" class="form-control"
							path="image" />
						<label class="label_error" id="error_image">${errorImage}</label>
					</div>
					
					<div style="margin-bottom: 30px;" id="slRole">
						<form:select path="roleId" class="form-control">
							<c:forEach items="${roles}" var="role">
								<form:option value="${role.id}">${role.roleName}</form:option>
							</c:forEach>
						</form:select>
					</div>
					<div>
						<form:hidden path="id" id="idUser"/>
						<form:hidden path="active"/>
						<form:hidden path="imageName" id="idImage"/>
					</div>
					<div>
						<form:button class="btn btn-primary submit form-control"
							onclick="return validate();" style="background-color: #64b733;">
							<c:choose>
								<c:when test="${id > 0}">Cập Nhật</c:when>
								<c:otherwise>Đăng Ký</c:otherwise>
							</c:choose>
							</form:button>
					</div>
					<div class="clearfix"></div>

					<div class="separator">
						<p class="change_link">
							Already a member ? <a href="<c:url value='/loginForm'/>"
								class="to_register"> Log in </a>
						</p>
						<div class="clearfix"></div>
						<br />

						<div>
							<h1>
								<i class="fa fa-paw"></i> T2D Shop!
							</h1>
							<p>106/10 - Xã Thạnh Mỹ Tây - Huyện Châu Phú - Tỉnh An Giang</p>
						</div>
					</div>
				</form:form>
			</section>
		</div>
	</div>
	<script src='<c:url value="/resources/bootstrap/js/jquery.min.js"/>'></script>
	<!-- Bootstrap -->
	<script src='<c:url value="/resources/common/bootstrap.min.js"/>'></script>
	<script src="<c:url value='/resources/build/js/custom.min.js'/>"></script>
	<script type="text/javascript">
		
		$('#success').fadeOut(8000);
		if ($.trim($('#idUser').val()) > 0){
			$('#div_username').css("display", "none");
			$('#slRole').css('display','none');
		}
	
		function validate() {
			var username = $.trim($('#username').val());
			var password = $.trim($('#password').val());
			var passwordHidden = $.trim($('#passwordHidden').val());
			var email = $.trim($('#email').val());
			var name = $.trim($('#name').val());
			var image = $.trim($('#image').val());
			var idUser = $.trim($('#idUser').val());
			var idImage = $('#idImage').val();

			$('#error').html('');
			//Username
			if (username.length < 1) {
				$('#error_username').html('Tên tài khoản không được rỗng');
				return false;
			} else {
				$('#error_username').html('');
			}

			//password
			if (passwordHidden.length > 0){
				if (password.length > 0 && password.length < 6){
					$('#error_password').html('Mật khẩu phải từ 6 ký tự');
					return false;
				}else if (password.length == 0){
					$('#password').val(passwordHidden);
				}
			}else if (password.length < 6) {
				$('#error_password').html('Mật khẩu phải từ 6 ký tự');
				return false;
			} else {
				$('#error_password').html('');
			}

			//email
			if (email.length < 1) {
				
				$('#error_email').html('Email không được rỗng');
				return false;
			} else {
				$('#error_email').html('');
			}

			//name
			if (name.length < 1) {
				$('#error_name').html('Tên không được rỗng');
				return false;
			} else {
				$('#error_name').html('');
			}

			//image
			if (image.length < 1) {
				if (idImage.length < 1){
					$('#error_image').html('Image không được rỗng');
				}else{
					$('#image').val(idImage);
					alert('hello');
				}
				return false;
			} else {
				$('#error_image').html('');
			}

		}
	</script>
</body>
</html>