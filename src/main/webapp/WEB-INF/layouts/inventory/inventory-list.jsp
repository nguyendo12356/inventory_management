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
			<option value="ALL">Tất cả trạng thái</option>
			<option value="low">Sắp hết hàng</option>
			<option value="high">Tồn kho quá lâu</option>
		</select>
	</div>
	<div class="col-sm-1">
		<button class="form-control btn btn-danger" id="btnSearch">&gt;&gt;</button>
	</div>
</div>

<div id="show-products"></div>
<script src='<c:url value="/resources/bootstrap/js/jquery.min.js"/>'></script>
<script>
	
	$('#btnSearch').on("click",function(){
		var id = $('#slCategory option:selected').val();
		var status = $('#slStatus option:selected').val();
		getTableProduct(id,status);
	})

	getTableProduct(-1, "ALL");

	function getTableProduct(id, status) {
		$.ajax({
			type : 'get',
			url : '${contextPath}/inventory/ajax/list',
			data : {
				'id' : id,
				'status': status
			},
			success : function(data) {
				$('#show-products').html(data);
			}
		})
	}
</script>