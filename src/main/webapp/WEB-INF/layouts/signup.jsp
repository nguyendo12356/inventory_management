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
				<c:remove var="user"/>
				<form:form modelAttribute="user" method="post" action="addUser">
					<h1>Create Account</h1>
					<form:label path="" id="error" cssClass="label_error">${errorEmailOrPassword}</form:label>
					<div>
						<form:input path="username" id="username" class="form-control"
							placeholder="Username" />
						<label class="label_error" id="error_username">${error}</label>
					</div>
					<div>
						<form:input path="email" id="email" class="form-control"
							placeholder="Email" />
						<label class="label_error" id="error_email"></label>
					</div>

					<div>
						<form:password class="form-control" id="password" path="password"
							placeholder="Password" />
						<label class="label_error" id="error_password"></label>
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
					<div style="margin: 22px 0;">
						<form:input type="file" id="image" class="form-control"
							path="image" />
						<label class="label_error" id="error_image"></label>
					</div>
					<div>
						<form:button class="btn btn-default submit"
							onclick="return validate();">Submit</form:button>
					</div>

					<div class="clearfix"></div>

					<div class="separator">
						<p class="change_link">
							Already a member ? <a href="<c:url value='/login'/>"
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
	
		function validate() {
			var username = $.trim($('#username').val());
			var password = $.trim($('#password').val());
			var email = $.trim($('#email').val());
			var name = $.trim($('#name').val());
			var image = $.trim($('#image').val());

			$('#error').html('');
			//Username
			if (username.length < 1) {
				$('#error_username').html('Tên tài khoản không được rỗng');
				return false;
			} else {
				$('#error_username').html('');
			}

			//email
			if (email.length < 1) {
				$('#error_email').html('Email không được rỗng');
				return false;
			} else {
				$('#error_email').html('');
			}

			//password
			if (password.length < 6) {
				$('#error_password').html('Mật khẩu phải từ 6 ký tự');
				return false;
			} else {
				$('#error_password').html('');
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
				$('#error_image').html('Image không được rỗng');
				return false;
			} else {
				$('#error_image').html('');
			}

		}
	</script>
</body>
</html>