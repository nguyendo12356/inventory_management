<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/styles.css'/>" />
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>
<table class="table table-bordered table_custom"
	style="text-align: center;">
	<thead>
		<tr class="table_header" align="left">
			<th>STT</th>
			<th>Mã sản phẩm</th>
			<th>Tên sản phẩm</th>
			<th>Loại sản phẩm</th>
			<th>Số lượng</th>
			<th>Tình trạng</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach varStatus="loop" items="${productList}" var="item">
			<tr>
				<td>${loop.index + 1}</td>
				<td>${item.code}</td>
				<td>${item.name}</td>
				<td>${item.category.name}</td>
				<td>${item.quantity}</td>
				<td>
	
					<c:choose>
						<c:when test="${item.quantity < item.lowestQuantity }">
							<img class="status-image-inventory" alt="No image" src="${contextPath}/resources/images/lowstock.png"/>
						</c:when>
						<c:when test="">
						
						</c:when>
						<c:otherwise>
							
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<script src='<c:url value="/resources/bootstrap/js/jquery.min.js"/>'></script>

