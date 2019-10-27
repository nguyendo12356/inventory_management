<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/styles.css'/>" />
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"></c:set>
<h2>Category</h2>
<p>
	<a style="cursor: pointer; text-align: right; margin-bottom: 10px;"
		data-toggle="modal" data-target="#myModal"> <span
		class="glyphicon glyphicon-plus"></span> Nhập loại sản phẩm mới
	</a>
</p>
<table class="table table-bordered table_custom"
	style="text-align: center;" id="tbl-category">
	<thead>
		<tr class="table_header" align="left">
			<th>STT</th>
			<th>Tên loại</th>
			<th>Mô tả</th>
			<th>Ngày tạo</th>
			<th>Action</th>
		</tr>
	</thead>
	<tbody>
		<%
			int i = 1;
		%>
		<c:forEach items="${category}" var="item">
			<tr>
				<td>
					<%
						out.print(i++);
					%>

				</td>
				<td>${item.name}</td>
				<td>${item.description}</td>
				<td>${item.createDate}</td>
				<td><a type="button" class="btn btn-primary"
					href="<c:url value='/product/category/update/${item.id}'/>">Sửa</a>
					<a type="button" class="btn btn-danger"
					href="<c:url value="/product/category/delete/${item.id}"/>"
					id="btnDel${item.id}">Xóa</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<script src='<c:url value="/resources/bootstrap/js/jquery.min.js"/>'></script>
<script src='<c:url value="/resources/js/general.js"/>'></script>

<!-- The Modal -->
<div class="modal" id="myModal">
	<div class="modal-dialog modal-sm common-modal">
		<div class="modal-content">
			<!-- Modal body -->
			<div class="modal-body">
				<div class="form-group">
					<label for="usr">Tên loại:</label> <input type="text"
						class="form-control" id="categoryName">
				</div>
				<div class="form-group">
					<label for="pwd">Mô tả ngắn:</label>
					 <textarea rows="4" cols="6"  type="password"
						class="form-control" id="categoryDescription">
						</textarea>
				</div>
				<div class="error" id="error">
					<label >Vui lòng nhập đầy đủ thông tin</label>
				</div>
			</div>

			<!-- Modal footer -->
			<div class="modal-footer">
				<a id="addCategory">Thêm Mới</a>
			</div>

		</div>
	</div>
</div>

<script type="text/javascript">
$('#addCategory').click(function(){
	var categoryName = $('#categoryName').val().trim();
	var categoryDescription = $('#categoryDescription').val().trim();
	$('#error').css('display', 'none');
	if(!isEmpty(categoryName) && !isEmpty(categoryDescription)){
		$.ajax({
			url: '${contextPath}/api/category',
			type: 'post',
			data: {
				"categoryName": categoryName,
				"categoryDescription": categoryDescription
			},success: function(data){
				if(data=="Success"){
					$('#categoryName').val('');
					$('#categoryDescription').val('');
					$("#myModal").modal('hide');
					$('#error').css('display', 'none');
					location.reload();
				}else if(data = "category_exists"){
					$('#error').html('Loại sản phẩm đã tồn tại');
					$('#error').css('display', 'block');
				}
			}
		});
	}else{
		$('#error').html('Vui lòng nhập đầy đủ thông tin');
		$('#error').css('display', 'block');	
	}
})
</script>


