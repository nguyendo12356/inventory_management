<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/styles.css'/>" />
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}"></c:set>
<div>
	<span>Mã hóa đơn: ${invoiceDetail.codeBill }</span><br /> <span>Nhân
		viên: ${invoiceDetail.staffName}</span><br /> <span>Ngày nhập: <fmt:formatDate
			value="${invoiceDetail.createDate}" pattern="dd-MM-yyyy" />
	</span><br /> <span>Tổng giá trị: <fmt:formatNumber type="Number"
			value="${invoiceDetail.price}" /> đ
	</span> <a class="btn btn-primary float-right"
		href="${contextPath}/export?invoice=${invoiceDetail.id}"
		target="_blank">In báo cáo</a>
</div>

<table class="table table-bordered table_custom"
	style="text-align: center;" id="tbl-input">
	<thead>
		<tr class="table_header" align="left">
			<th>Mã sản phẩm</th>
			<th>Tên sản phẩm</th>
			<th>Loại</th>
			<th>Số lượng</th>
			<th>Giá</th>
			<th>Giảm giá</th>
			<th>Hình ảnh</th>
		</tr>
	</thead>
	<tbody>
		<%
			int i = 1;
		%>
		<c:forEach items="${invoiceDetail.ips}" var="item">
			<tr>
				<td>
					<%
						out.print(i++);
					%>

				</td>
				<td>${item.product.name}</td>
				<td>${item.product.category.name}</td>
				<td>${item.quantity}</td>
				<td><fmt:formatNumber type="Number"
						value="${item.product.price}" /> đ</td>
				<td>${item.product.discount}</td>
				<td><img alt="No image"
					src="${contextPath}/resources/images/${item.product.img_url}"
					class="common-img-50" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div class="back">
	<i class="glyphicon glyphicon-chevron-left"></i>&ensp;<a
		href="${contextPath}/inventory/input"> Danh sách hóa đơn</a>
</div>
<script src='<c:url value="/resources/bootstrap/js/jquery.min.js"/>'></script>

