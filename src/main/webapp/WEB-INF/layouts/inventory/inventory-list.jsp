<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/styles.css'/>" />
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<h2>Sản phẩm trong kho</h2>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}" />
<div class="row" style="margin-bottom: 1.5em;">
	<div class="col-sm-3">
		<select class="form-control" id="slCategory">
			<option value="-1 ">Tất cả sản phẩm</option>
			<c:forEach var="category" items="${categorys}">
				<option value="${category.id}">${category.name}</option>
			</c:forEach>
		</select>
	</div>
	<div class="col-sm-3">
		<select class="form-control" id="slStatus">
			<option value="ALl">Tất cả trạng thái</option>
			<option value="low">Sắp hết hàng</option>
			<option value="high">Tồn kho quá lâu</option>
		</select>
	</div>
	<div class="col-sm-1">
		<button class="form-control btn btn-danger">&gt;&gt;</button>
	</div>
</div>

<div id="show-products"></div>
<script src='<c:url value="/resources/bootstrap/js/jquery.min.js"/>'></script>
<script>
	$('#slCategory').on("change", function() {
		getTableProduct($('#slCategory option:selected').val());
	})

	getTableProduct(-1);

	function getTableProduct(id) {
		$.ajax({
			type : 'get',
			url : '${contextPath}/inventory/ajax/list',
			data : {
				'id' : id
			},
			success : function(data) {
				$('#show-products').html(data);
			}
		})
	}
</script>