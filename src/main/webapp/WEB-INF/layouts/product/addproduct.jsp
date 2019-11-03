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
	<div class="div-error" id="error-div">
		<label class="label_error" id="error-text">*Vui lòng nhập đầy
			đủ thông tin</label>
	</div>
	<form:form modelAttribute="product" method="post"
		action="${contextPath}/product/add" enctype="multipart/form-data">
		<div class="form-group">
			<label>Mã sản phẩm:</label>
			<form:input path="code" class="form-control" id="codeBill" />
		</div>
		<div class="form-group">
			<label>Tên sản phẩm:</label>
			<form:input path="name" class="form-control" id="name"/>
		</div>
		<div class="form-group">
			<label>Loại sản phẩm:</label>
			<form:select class="form-control" path="category">
				<c:forEach items="${category}" var="item">
					<option value="${item.id}">${item.name}</option>
				</c:forEach>
			</form:select>
		</div>
		<div class="form-group">
			<label>Giá: </label>
			<form:input path="price" type="number" id="price"
				class="form-control" />
		</div>
<%-- 		<div class="form-group">
			<label>Số lượng:</label>
			<form:input path="quantity" type="number" id="quantity" class="form-control" />
		</div> --%>
		<div class="form-group">
			<label>Discount(%):</label>
			<form:input path="discount" type="number" id="discount" class="form-control" />
		</div>
		<div class="form-group">
			<label>Hình ảnh:</label>
			<form:input type="file" class="form-control" path="img_url" />
		</div>
		<div class="form-group">
			<label for="pwd">Mô tả ngắn:</label>
			<form:textarea path="description" cols="3" rows="2"
				cssClass="form-control" />
		</div>
		<div class="button-align">
			<form:button class="btn" id="btnAddProduct"
				onclick="return validate();">Thêm sản phẩm</form:button>
		</div>
	</form:form>
</div>

<script src='<c:url value="/resources/js/general.js"/>'></script>
<script src='<c:url value="/resources/bootstrap/js/jquery.min.js"/>'></script>
<!-- Bootstrap -->
<script src='<c:url value="/resources/common/bootstrap.min.js"/>'></script>
<script src="<c:url value='/resources/build/js/custom.min.js'/>"></script>
<script type="text/javascript">
	function validate(){
		let codeBill = $('#codeBill');
		let name = $('#name');
		let quantity = $('#quantity');
		let price = $('#price');
		let discount = $('#discount');
		$('#error-div').css('display','none');
		if(codeBill.val().length == 0){
			$('#error-div').css('display','block');
			$('#error-text').html('Vui lòng nhập mã sản phẩm');
			codeBill.css('borderColor','red');
			codeBill.focus();
			return false;
		}else{
			codeBill.css('borderColor','#ccc');
		}
		
		if(name.val().length == 0){
			$('#error-div').css('display','block');
			$('#error-text').html('Vui lòng nhập tên sản phẩm');
			name.css('borderColor','red');
			name.focus();
			return false;
		}else{
			name.css('borderColor','#ccc');
		}
		
		if(price.val() == 0){
			$('#error-div').css('display','block');
			$('#error-text').html('Vui lòng nhập giá sản phẩm');
			price.css('borderColor','red');
			price.focus();
			return false;
		}else{
			price.css('borderColor','#ccc');
		}
		
/* 		if(quantity.val().length == 0){
			$('#error-div').css('display','block');
			$('#error-text').html('Vui lòng nhập số lượng sản phẩm');
			quantity.css('borderColor','red');
			quantity.focus();
			return false;
		}else{
			quantity.css('borderColor','#ccc');
		} */
		
		if(discount.val() < 0 || discount.val() > 90){
			$('#error-div').css('display','block');
			$('#error-text').html('Giảm giá từ 0 đến 90');
			discount.css('borderColor','red');
			discount.focus();
			return false;
		}else{
			name.css('borderColor','#ccc');
		}
		
	}
</script>