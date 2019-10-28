<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/styles.css'/>" />
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<h2>Danh sách nhập kho</h2>
<p>
	<a style="cursor: pointer; text-align: right; margin-bottom: 10px;"
		href="<c:url value='/inventory/input/add'/>"> <span
		class="glyphicon glyphicon-plus"></span> Nhập Hóa Đơn
	</a>
</p>
<table class="table table-bordered table_custom"
	style="text-align: center;">
	<thead>
		<tr class="table_header" align="left">
			<th>STT</th>
			<th>Mã hóa đơn</th>
			<th>Nhà cung cấp</th>
			<th>Tổng giá trị</th>
			<th>Ngày nhập</th>
			<th>Người nhập</th>
			<th>Action</th>
		</tr>
	</thead>
	<tbody>
		<%
			int i = 1;
		%>
		<c:forEach items="${inputList}" var="item">
			<tr>
				<td>
					<%
						out.print(i++);
					%>

				</td>
				<td>${item.codeBill}</td>
				<td>${item.suplier}</td>
				<td><fmt:formatNumber type="currency" value="${item.price}"  /></td>
				<td>${item.createDate}</td>
				<td>${item.staffName}</td>
				<td>
				<a type="button" class="btn btn-info"
					href="<c:url value='/inventory/input/detail/${item.id}'/>">Chi tiết</a>
				<a type="button" class="btn btn-primary"
					href="<c:url value='/inventory/input/update/${item.id}'/>">Sửa</a>
					<a type="button" class="btn btn-danger" href="<c:url value="/inventory/input/delete/${item.id}"/>" id="btnDel${item.id}">Xóa</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<script src='<c:url value="/resources/bootstrap/js/jquery.min.js"/>'></script>

