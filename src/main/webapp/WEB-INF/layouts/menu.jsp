<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h2>Quản lí danh mục</h2>
<p>
	<a style="cursor: pointer; text-align: right; margin-bottom: 10px;"
		data-toggle="modal" data-target="#myModal"> <span
		class="glyphicon glyphicon-plus"></span> Thêm mới
	</a>
</p>
<table class="table table-bordered table_custom"
	style="text-align: center;">
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
		<%
			int i = 1;
		%>
		<c:forEach items="${menu}" var="item">
			<tr>
				<td>
					<%
						out.print(i++);
					%>
				</td>
				<td>${item.name}</td>
				<td><select class="form-control">
						<option>Chọn chức năng lớn</option>
						<option>1</option>
				</select></td>
				<td>${item.url}</td>
				<td>${item.index}</td>
				<td>
					<c:choose>
						<c:when test="${item.active}">
							<span class="glyphicon glyphicon-pause"></span>
						</c:when>
						<c:otherwise>
							<span class="glyphicon glyphicon-play"></span>
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					<a type="button" class="btn btn-info" href="">Sửa</a>
					<a type="button" class="btn btn-danger" href="<c:url value="deleteMenu/${item.id}"/>">Xóa</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
	<div class="modal-dialog modal-md">

		<!-- Modal content-->
		<form method="post" action="<c:url value = '/addMenu'/>">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Thêm chức năng</h4>
				</div>
				<div class="modal-body form-group">
					<div class="form-group">
						<label>Tên chức năng : </label> <input class="form-control"
							name="txtname" id="name" placeholder="...." />
					</div>
					<div class="form-group">
						<label>Thuộc chức năng :</label> <select class="form-control"
							name="slchucnang" id="name">
							<option value=0>Chọn chức năng</option>
							<c:forEach items="${menu}" var="item">
								<c:if test="${item.parent_id == 0}">
									<option value="${item.id}">${item.name}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label>Nhập vào đường dẫn :</label> <input class="form-control"
							name="txturl" id="url" placeholder="...." />
					</div>
					<div class="form-group">
						<label>Nhập ví trí xuất hiện :</label> <input class="form-control"
							name="txtindex" id="index" placeholder="...." />
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary">Thêm</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
				</div>
			</div>
		</form>
	</div>
</div>
