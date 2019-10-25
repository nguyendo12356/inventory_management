<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/styles.css'/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/inventory.css'/>" />
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<p class="addNew">
	<button class="btn btn-primary">Thêm sản phẩm</button>
</p>
<table class="table table-bordered table_custom"
	style="text-align: center;">
	<thead>
		<tr class="table_header" align="left">
			<th>Tên sản phẩm</th>
			<th>Loại</th>
			<th>Số lượng</th>
			<th>Giá</th>
			<th>Giảm giá</th>
			<th>Hình ảnh</th>
		</tr>
	</thead>
	<tbody>
		<form:form>
			<tr>
				<td>
					<input />
				</td>
				<td>
					<select></select>
				</td>
				<td>
					<input />
				</td>
				<td>
					<input />
				</td>
				<td>
					<input type="number"/>
				</td>
				<td>
					<input type="file"/>
				</td>
			</tr>
		</form:form>
	</tbody>
</table>
<script src='<c:url value="/resources/bootstrap/js/jquery.min.js"/>'></script>
<script src='<c:url value="/resources/js/inventory.js"/>'></script>

