<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/styles.css'/>" />
<h2>Quản lí người dùng</h2>
<p>
	<a style="cursor: pointer; text-align: right; margin-bottom: 10px;"
		href="<c:url value='/signupForm'/>"> <span
		class="glyphicon glyphicon-plus"></span> Thêm người dùng
	</a>
</p>
<table class="table table-bordered table_custom"
	style="text-align: center;">
	<thead>
		<tr class="table_header" align="left">
			<th>STT</th>
			<th>Tên người dùng</th>
			<th>Email</th>
			<th>Họ tên</th>
			<th>Giới tính</th>
			<th>Ảnh đại diện</th>
			<th>Kích hoạt</th>
			<th>Action</th>
		</tr>
	</thead>
	<tbody>
		<%
			int i = 1;
		%>
		<c:forEach items="${userList}" var="item">
			<tr>
				<td>
					<%
						out.print(i++);
					%>

				</td>
				<td>${item.username}</td>
				<td>${item.email}</td>
				<td>${item.name}</td>
				<td><c:choose>
						<c:when test="${item.gender}">
							Nam
						</c:when>
						<c:otherwise>Nữ</c:otherwise>
					</c:choose></td>
				<td><img
					src="<c:url value = '/resources/images/${item.imageName}'/>"
					class="user_image" /></td>
				<td><c:choose>
						<c:when test="${item.active}">
							<a id="btn${item.id}"
								onclick="" style="display: block;" data-toggle="modal" ><span
								id="span${item.id}" class="glyphicon glyphicon-pause" ></span></a>
							<a id="btnhidden${item.id}"
								onclick="changeState(${item.id},true)" style="display: none;" data-toggle="modal" ><span
								id="span${item.id}" class="glyphicon glyphicon-play"></span></a>
						</c:when>
						<c:otherwise>
							<a id="btn${item.id}"
								onclick="changeState(${item.id},false)" style="display: none;" data-toggle="modal" ><span
								id="span${item.id}" class="glyphicon glyphicon-pause"></span></a>
							<a id="btnhidden${item.id}"
								onclick="changeState(${item.id},true)" style="display: block;" data-toggle="modal" ><span
								id="span${item.id}" class="glyphicon glyphicon-play"></span></a>
						</c:otherwise>
					</c:choose></td>
				<td><a type="button" class="btn btn-info"
					href="<c:url value="/user/update/${item.id}"/>">Sửa</a> <a
					type="button" class="btn btn-danger"
					href="<c:url value="/user/delete/${item.id}"/>">Xóa</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<script src='<c:url value="/resources/bootstrap/js/jquery.min.js"/>'></script>

<div id="popup" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Kích hoạt tài khoản</h4>
      </div>
      <div class="modal-body">
        <p>Bạn có muốn kích hoạt tài khoản ?</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" id="modal_yes">Yes</button>
        <button type="button" class="btn btn-default" id="modal_no">No</button>
      </div>
    </div>

  </div>
</div>



