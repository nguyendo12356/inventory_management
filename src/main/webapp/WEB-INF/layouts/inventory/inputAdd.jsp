<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/styles.css'/>" />
	<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/inventory.css'/>" />

<form action="" class="form-inline custom-form">
	<fieldset class="custom-fieldset">
		<legend>Thông tin hóa đơn</legend>
		<div class="form-group">
			<label for="pwd">Mã hóa đơn:</label> <input type="text"
				class="form-control" id="pwd">
		</div>
		<div class="form-group">
			<label for="suplier">Nhà cung cấp:</label> <input type="text"
				class="form-control" id="suplier">
		</div>
		<button type="submit" class="btn btn-default float-right">Submit</button>
	</fieldset>
	<table class="table table-bordered table_custom"
	style="text-align: center;">
	<thead>
		<tr class="table_header" align="left">
			<th>STT</th>
			<th>Mã hóa đơn</th>
			<th>Nhà cung cấp</th>
			<th>Tổng giá trị</th>
			<th>Ngày nhập</th>
			<th>Action</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td></td>
		</tr>
	</tbody>
</table>
</form>

