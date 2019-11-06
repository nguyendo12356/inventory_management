<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>
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
<body class="login">
    <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper">
        <div class="animate form login_form">
          <section class="login_content">
            <form:form modelAttribute="user" action="loginUser" method="post">
              <h1>Login Form</h1>
              <form:label path=""  id="error" cssClass="label_error">${error}</form:label>
              <div>
                <form:input class="form-control" id="username" placeholder="Username" path="username"/>
              	<form:label path="" id="error_username" cssClass="label_error"></form:label>
              </div>
              <div>
                <form:input type="password" id="password" class="form-control" placeholder="Password" path="password" />
              	<form:label path="" id="error_password" cssClass="label_error"></form:label>
              </div>
              <div>
                <form:button class="btn btn-default submit" onclick="return validate()">Log in</form:button>
                <a class="reset_pass" data-target="#myModal" data-toggle="modal">Quên mật khẩu?</a>
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <p class="change_link">New to site?
                  <a href='<c:url value="/signupForm"/>' class="to_register"> Create Account </a>
                </p>

                <div class="clearfix"></div>
                <br />

                <div>
                  <h1><i class="fa fa-paw"></i> T2D Shop</h1>
                  <p>106/10 - Xã Thạnh Mỹ Tây - Huyện Châu Phú - Tỉnh An Giang</p>
                </div>
              </div>
            </form:form>
          </section>
        </div>
      </div>
    </div>
    
    
    <div class="modal" id="myModal">
	<div class="modal-dialog common-modal">
		<div class="modal-content">
			<!-- Modal body -->
			<div class="modal-body">
					<div class="div-error" id="error-invoice"><span class="label_error" id="error-invoice-text">Tài khoản không tồn tài</span></div>
				<div class="form-group">
					<label for="usr">Tên tài khoản:</label> <input type="text"
						class="form-control" id="txtEmail">
				</div>
			</div>

			<!-- Modal footer -->
			<div class="modal-footer">
				<a id="addCategory" onclick="sendEmail()">Xác nhận</a>
			</div>

		</div>
	</div>
</div>
    <script src='<c:url value="/resources/bootstrap/js/jquery.min.js"/>'></script>
	<!-- Bootstrap -->
	<script src='<c:url value="/resources/common/bootstrap.min.js"/>'></script>
	<script src="<c:url value='/resources/build/js/custom.min.js'/>"></script>
    <script type="text/javascript">
    	
    	function validate(){
    		$('#error').html('');
	    	if($.trim($('#username').val()).length < 1){
	    		$('#error_username').html('Tên tài khoản không được rỗng');
	    		return false;
	    	}else{
	    		$('#error_username').html('');
	    	}
	    	
	    	if($.trim($('#password').val()).length < 1){
	    		$('#error_password').html('Mật khẩu không được rỗng');
	    		return false;
	    	}else{
	    		$('#error_password').html('');
	    	}
    	}
    </script>
    <script type="text/javascript">
    	function sendEmail(){
    		$.ajax({
    			url : '${contextPath}/api/sendEmail',
    			data:{
    				username : $('#txtEmail').val()
    			},success: function(data){
    				if(data == "success"){
    					$("#myModal").modal('hide');
    					alert('Truy cập email để tiền hành thay đổi mật khẩu');
    				}else if(data == "user_not_exits"){
    					$('#error-invoice').css('display','block');
    				}
    			}
    		})
    	}
    </script>
  </body>
</html>