<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>Quản lí danh mục</h2>
<p><a style="cursor: pointer; text-align: right; margin-bottom: 10px;" href="/addMenu">
	 <span class="glyphicon glyphicon-plus"></span>
	 	 Thêm mới
	 </a></p>
<table class="table table-bordered table_custom" style="text-align: center;">
	<thead>
		<tr class="table_header" align="left">
			<th>STT</th>
			<th>Tên Chức năng</th>
			<th>Chức năng Lớn</th>
			<th>URL</th>
			<th>Thứ tự</th>
			<th>Kích hoạt</th>
			<th>Action</th>
		</tr>
	</thead>
	<tbody>
		<% int i = 1; %>
		<c:forEach items="${menu}" var="item">
			<tr>
				<td>
					<% out.print(i++); %>
				</td>
				<td>${item.name}</td>
				<td>
					<select class="form-control">
						<option>Chọn chức năng lớn</option>
						<option>1</option>
					</select>
				</td>
				<td>${item.url}</td>
				<td>${item.index}</td>
				<td>${item.active}</td>
				<td>
					<button class="btn btn-info">Sửa</button>
					<button class="btn btn-danger">Xóa</button>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
