<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

<style type="text/css">
	body {
		font-weight: 500 !important;
	}
</style>
</head>
 <body class="login">
        <div class="login_wrapper">
            <div id="register" class="animate form registration_form" style="opacity: 1;">
            <section class="login_content">
                <form:form modelAttribute="user" method="post" action="addUser">
                <h1>Create Account</h1>
                <div>
                	<form:input path="username" class="form-control" placeholder="Username"/>
                </div>
                <div>
                    <form:input path="email" class="form-control" placeholder="Email"/>
                </div>
               
                <div>
                    <form:password class="form-control" path="password" placeholder="Password"  />
                </div>
                <div>
                    <form:input path="name" class="form-control" placeholder="Your name"/>
                </div>
                <div>
                    <form:select path="gender" class="form-control">
                    	<form:option value="1">Nam</form:option>
                    	<form:option value="0">Nữ</form:option>
                    </form:select>
                </div>
                <div  style="margin: 22px 0;">
                    <form:input type="file" class="form-control" path="image" />
                </div>
                <div>
                    <form:button class="btn btn-default submit">Submit</form:button>
                </div>

                <div class="clearfix"></div>

                <div class="separator">
                    <p class="change_link">Already a member ?
                    <a href="<c:url value='/login'/>" class="to_register"> Log in </a>
                    </p>
                    <div class="clearfix"></div>
                    <br />

                    <div>
                    <h1><i class="fa fa-paw"></i> T2D Shop!</h1>
                    <p>106/10 - Xã Thạnh Mỹ Tây - Huyện Châu Phú - Tỉnh An Giang</p>
                    </div>
                </div>
                </form:form>
            </section>
            </div>
        </div>
  </body>
</html>