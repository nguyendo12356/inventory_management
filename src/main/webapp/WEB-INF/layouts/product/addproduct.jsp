<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/styles.css'/>" />
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}"></c:set>

<div class="w-60">
	<h2>Thêm sản phẩm</h2>
	<div>
		<label class="label_error">*Vui lòng nhập đầy đủ thông tin</label>
	</div>
	<form:form modelAttribute="product" method="post" action="${contextPath}/product/add"  enctype="multipart/form-data" >
		<div class="form-group">
			<label>Mã sản phẩm:</label> <form:input path="code" class="form-control" />
		</div>
		<div class="form-group">
			<label>Tên sản phẩm:</label> <form:input path="name" class="form-control" />
		</div>
		<div class="form-group">
			<label>Loại sản phẩm:</label> <form:select class="form-control" path="category">
				<c:forEach items="${category}" var="item">
					<option value="${item.id}">${item.name}</option>
				</c:forEach>
			</form:select>
		</div>
		<div class="form-group">
			<label>Giá: </label> <form:input path="price" type="number" id="price" class="form-control"/>
		</div>
		<div class="form-group">
			<label>Số lượng:</label> <form:input path="quantity" type="number" class="form-control" />
		</div>
		<div class="form-group">
			<label>Discount(%):</label> <form:input path="discount" type="number" class="form-control" />
		</div>
		<div class="form-group">
			<label>Hình ảnh:</label> <form:input type="file" class="form-control" path="img_url"/>
		</div>
		<div class="form-group">
			<label for="pwd">Mô tả ngắn:</label>
 			<form:textarea path="description" cols="3" rows="2" cssClass="form-control"/>
		</div>
		<div class="button-align">
			<form:button class="btn">Thêm mới</form:button>
		</div>
	</form:form>
</div>

<script src='<c:url value="/resources/js/general.js"/>'></script>