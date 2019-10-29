<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/styles.css'/>" />
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}"></c:set>
<h2>Quản lí sản phẩm</h2>
<p class="form-inline div-addnew">
	<a class="button-addnew" href="${contextPath}/product/form"> <span
		class="glyphicon glyphicon-plus"></span> Thêm sản phẩm mới
	</a>
	<select class="form-control float-right" id="slPageSize">
		<option value="4">4</option>
		<option value="8">8</option>
		<option value="10">10</option>
		<option value="-1">Tất cả</option>
	</select>
</p>

<table class="table table-bordered table_custom"
	style="text-align: center;" id="tbl-category">
	<thead>
		<tr class="table_header" align="left">
			<th>STT</th>
			<th>Mã sản phẩm</th>
			<th>Tên sản phẩm</th>
			<th class="w-12">Loại sản phẩm</th>
			<th>Hình ảnh</th>
			<th class="w-6">Giá (VNĐ)</th>
			<th class="w-6">Số lượng</th>
			<th class="w-10">Giảm giá (%)</th>
			<th>Mô tả</th>
			<th>Action</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${products}" var="item" varStatus="loop">
			<tr>
				<td>
					${loop.index + pageSize*(pageNumber - 1) + 1}
				</td>
				<td class="w-10">${item.code}</td>
				<td>${item.name}</td>
				<td>${item.category.name}</td>
				<td>
					<img class="common-img" alt="No image" src='<c:url value="/resources/images/${item.img_url}"/>'>
				</td>
				<td><fmt:formatNumber value="${item.price}" /> đ</td>
				<td>${item.quantity}</td>
				<td>${item.discount}</td>
				<td>${item.description}</td>
				<td><a type="button" class="btn btn-primary"
					href="<c:url value='/product/update/${item.id}'/>">Sửa</a>
					<a type="button" class="btn btn-danger"
					href="<c:url value="/product/delete/${item.id}"/>"
					id="btnDel${item.id}">Xóa</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div class="footer-product">
	<div class="total-product">Tổng số sản phẩm : ${totalProduct}</div>
	<div class="common-pagination">
		<ul class="pagination pagination-md">
			<li class="page-item"><a class="page-link" onclick="paginationEvent(false, '${contextPath}')" id="previousButton">Previous</a></li>
			<c:choose>
				<c:when test="${totalProduct/pageSize == 0}">
					<c:set var="num" value="${totalProduct/pageSize}" />
				</c:when>
				<c:otherwise>
					<c:set var="num" value="${totalProduct/pageSize + 1}" />
				</c:otherwise>
			</c:choose>
			<c:forEach begin="1" end="${num}" varStatus="loop">
				<li class="page-item" id="1"><a class="page-link" href="${contextPath}/product/list/${loop.index}/${pageSize}" id = "page-${loop.index}" >${loop.index}</a></li>
			</c:forEach>
			<li class="page-item"><a class="page-link" onclick="paginationEvent(true,'${contextPath}')" id="nextButton">Next</a></li>
		</ul>
	</div>
</div>
<script src='<c:url value="/resources/bootstrap/js/jquery.min.js"/>'></script>
<script src='<c:url value="/resources/js/general.js"/>'></script>

<script type="text/javascript">
	$('#addCategory').click(function() {
		var categoryName = $('#categoryName').val().trim();
		var categoryDescription = $('#categoryDescription').val().trim();
		$('#error').css('display', 'none');
		if (!isEmpty(categoryName) && !isEmpty(categoryDescription)) {
			$.ajax({
				url : '${contextPath}/api/category',
				type : 'post',
				data : {
					"categoryName" : categoryName,
					"categoryDescription" : categoryDescription
				},
				success : function(data) {
					if (data == "Success") {
						$('#categoryName').val('');
						$('#categoryDescription').val('');
						$("#myModal").modal('hide');
						$('#error').css('display', 'none');
						location.reload();
					} else if (data = "category_exists") {
						$('#error').html('Loại sản phẩm đã tồn tại');
						$('#error').css('display', 'block');
					}
				}
			});
		} else {
			$('#error').html('Vui lòng nhập đầy đủ thông tin');
			$('#error').css('display', 'block');
		}
	})
	
	disabledNextAndPreviousButton('${num}');
	
	$('#slPageSize').on("change",function(){
		let pageSplit = document.URL.split("/");
		let url = '${contextPath}'+"/"+pageSplit[4]+"/"+pageSplit[5]+"/"+pageSplit[6]+"/"+$(this).val();
		window.location.href = url;
	})
	
	let slValue = $('#slPageSize').children();
	let pageSplit = document.URL.split("/");
	
	for(let i = 0; i < slValue.length; i++){
		if(slValue[i].value === pageSplit[7]){
			slValue[i].selected = 'selected';
		}
	}

</script>


