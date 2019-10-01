<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ul class="nav side-menu">
	<li><a href='<c:url value="/home"/>'><i
			class="fa fa-home"></i> Dashboard</a></li>
	<c:forEach items="${menus}" var="menu">
		<li><a><i class="glyphicon glyphicon-fire"
				id="${menu.idMenu}"></i>&ensp;&ensp; ${menu.name} <c:if
					test="${menu.childMenu.size() != 0}">
					<span class="fa fa-chevron-down"></span>
				</c:if> </a>
			<ul class="nav child_menu">
				<c:forEach items="${menu.childMenu}" var="child">
					<li><a href="<c:url value='${child.url}'/>">${child.name}</a></li>
				</c:forEach>
			</ul></li>
		<ul class="nav child_menu">
			<c:forEach items="${menu.childMenu}" var="child">
				<li><a>${child.name}</a></li>
			</c:forEach>
		</ul>
	</c:forEach>
	<li><a href='<c:url value="/logout"/>'><i
			class="glyphicon glyphicon-log-out"></i>&ensp;&ensp; Đăng xuất</a></li>
</ul>

