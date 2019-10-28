<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/styles.css'/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/inventory.css'/>" />
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form:form class="form-inline custom-form"
	modelAttribute="model" method="post" action="${pageContext.request.contextPath}/inventory/input/save" enctype="multipart/form-data">
	<fieldset class="custom-fieldset">
		<legend>Thông tin hóa đơn</legend>
		<div class="form-group">
			<label for="pwd">Mã hóa đơn:</label>
			<form:input class="form-control" path="codeBill" />
		</div>
		<div class="form-group">
			<label for="suplier">Nhà cung cấp:</label>
			<form:input class="form-control" id="suplier" path="suplier" />
		</div>
		<div class="form-group">
			<label for="suplier">Tổng giá trị:</label>
			<form:input class="form-control" id="totalPrice" path="totalPrice" />
		</div>
	</fieldset>
	<table class="table table-bordered table_custom"
		style="text-align: center;" id="tbl-input">
		<thead>
			<tr class="table_header" align="left">
				<th>Tên sản phẩm</th>
				<th>Loại</th>
				<th>Số lượng</th>
				<th>Giá</th>
				<th>Giảm giá</th>
				<th>Hình ảnh</th>
				<th>Active</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach begin="0" end="${fn:length(model.products) - 1}" varStatus="loop">
			<tr>
				<td><form:select class="form-control w-100" id="slProduct" path="products[${loop.index}].category">
						<c:forEach var="categoryItem" items="${category}">
							<option value="${categoryItem.id}">${categoryItem.name}</option>
						</c:forEach>
				</form:select></td>
				<td class="m-width-sl"><form:input id="txtName" style="display: none;" path="products[${loop.index}].name"/>
					<form:select class="form-control w-100" id="slProduct" path="products[${loop.index}].id">
						<option>Chọn sản phẩm</option>
						<option value="new">Sản phẩm mới</option>
						<c:forEach var="product" items="${productlist}">
							<option value="${product.id}">${product.name}</option>
						</c:forEach>
				</form:select></td>
				<td class="m-width"><form:input path="products[${loop.index}].quantity" cssClass="form-control w-100"/></td>
				<td class="m-width"><form:input path="products[${loop.index}].price" cssClass="form-control w-100"/></td>
				<td class="m-width"><form:input path="products[${loop.index}].discount" cssClass="form-control w-100"/></td>
				<td class="m-width-120"><form:input type="file" path="products[${loop.index}].img_url" cssClass="form-control w-100" /></td>
				
				<td>
					<a type="button" class="btn btn-danger"
					href="<c:url value="/inventory/input/delete/${item.id}"/>"
					id="btnDel${item.id}">Xóa</a></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="addNew">
	<button type="button" class="btn btn-default btn-sm"
		onclick="addNewLineTable()">
		<span class="glyphicon glyphicon-plus"></span> Thêm
	</button>
	<div class="custom-button"><form:button class="btn btn-primary">Thêm hóa đơn</form:button></div>
</div>
	
</form:form>
<script src='<c:url value="/resources/bootstrap/js/jquery.min.js"/>'></script>
<script src='<c:url value="/resources/js/general.js"/>'></script>

<script>
	$('#slProduct').on('change', function() {
		if ($(this).val() === "new") {
			$('#txtName').css('display', 'inherit');
			$('#slProduct').css('display', 'none');
		}
	})

	function addNewLineTable() {
		var action = '<a type="button" class="btn btn-danger" onclick="deleteLineTable(event)" id="ccc">Xóa</a>'
		var category = '<select  class="form-control" id="slProduct" >'
				+ '<c:forEach var="categoryItem" items="${category}">'
				+ '<option value="${categoryItem.id}">${categoryItem.name}</option>'
				+ '</c:forEach>' + '</select>';
		var newLine = "<tr><td>" + category + "</td>" + "<td><input /></td>"
				+ "<td>3</td>" + "<td>4</td>" + "<td>5</td>" + "<td>6</td>"
				+ "<td>" + action + "</td>" + "</tr>";
		var id = "#tbl-input";
		$(id + " tbody").append(newLine);
	}
</script>