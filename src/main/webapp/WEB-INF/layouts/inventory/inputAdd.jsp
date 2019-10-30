<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/styles.css'/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/inventory.css'/>" />
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page buffer="8192kb"%>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}"></c:set>

<form:form class="form-inline custom-form" modelAttribute="model"
	method="post"
	action="${pageContext.request.contextPath}/inventory/input/save"
	enctype="multipart/form-data">
	<fieldset class="custom-fieldset">
		<legend>Thông tin hóa đơn</legend>
		<div class="form-group">
			<label for="pwd">Mã hóa đơn:</label>
			<form:input class="form-control" path="codeBill" />
		</div>
		<div class="form-group">
			<label for="suplier">Nhà cung cấp:</label>
			<form:input class="form-control" id="suplier" path="suplier" />
		</div>
		<div class="form-group">
			<label for="suplier">Tổng giá trị:</label>
			<form:input class="form-control" id="totalPrice" path="totalPrice" />
		</div>
	</fieldset>
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
				<th>Active</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach begin="0" end="${fn:length(model.products) - 1}"
				varStatus="loop">
				<tr>
					<td class="m-width-180">
						<div class="left" style="background-color: #bbb;">
							<form:input
							cssClass="form-control w-100" path="products[${loop.index}].code"
							onkeyup="showHideChangePopUp(event)"/>
							<ul id="myMenu" class="hiddenPopup">
								<li value="5"><a onclick="test('')">HTML</a></li>
								<li><a>CSS</a></li>
								<li><a>JavaScript</a></li>

							</ul>
						</div></td>
					<td class="m-width-sl"><form:input cssClass="form-control"
							path="products[${loop.index}].name" /></td>
					<td><form:select class="form-control w-100" id="slProduct"
							path="products[${loop.index}].category"
							onchange="findProductByCategoryId(event)">
							<option>Chọn loại sản phẩm</option>
							<c:forEach var="categoryItem" items="${category}">
								<option value="${categoryItem.id}">${categoryItem.name}</option>
							</c:forEach>
						</form:select></td>
					<td class="m-width"><form:input
							path="products[${loop.index}].quantity"
							cssClass="form-control w-100" /></td>
					<td class="m-width"><form:input
							path="products[${loop.index}].price"
							cssClass="form-control w-100" /></td>
					<td class="m-width"><form:input
							path="products[${loop.index}].discount"
							cssClass="form-control w-100" /></td>
					<td class="m-width-120"><img alt="No image" src=""
						class="common-img-50" style="display: none;" /> <form:input
							type="file" path="products[${loop.index}].img_url"
							cssClass="form-control w-100" /></td>

					<td><a type="button" class="btn btn-danger"
						href="<c:url value="/inventory/input/delete/${item.id}"/>"
						id="btnDel${item.id}">Xóa</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="addNew">
		<button type="button" class="btn btn-default btn-sm"
			onclick="addNewLineTable()">
			<span class="glyphicon glyphicon-plus"></span> Thêm
		</button>
		<div class="custom-button">
			<form:button class="btn btn-primary">Thêm hóa đơn</form:button>
		</div>
	</div>

</form:form>

<!-- popup below input -->

<!-- popup below input -->
<script src='<c:url value="/resources/bootstrap/js/jquery.min.js"/>'></script>
<script src='<c:url value="/resources/js/general.js"/>'></script>

<script>
	$('#slProduct').on('change', function() {
		if ($(this).val() === "new") {
			$('#txtName').css('display', 'inherit');
			$('#slProduct').css('display', 'none');
		}
	})

	function addNewLineTable() {
		var action = '<a type="button" class="btn btn-danger" onclick="deleteLineTable(event)" id="ccc">Xóa</a>'
		var category = '<select  class="form-control" id="slProduct" >'
				+ '<c:forEach var="categoryItem" items="${category}">'
				+ '<option value="${categoryItem.id}">${categoryItem.name}</option>'
				+ '</c:forEach>' + '</select>';
		var newLine = "<tr><td>" + category + "</td>" + "<td><input /></td>"
				+ "<td>3</td>" + "<td>4</td>" + "<td>5</td>" + "<td>6</td>"
				+ "<td>" + action + "</td>" + "</tr>";
		var id = "#tbl-input";
		$(id + " tbody").append(newLine);
	}

	function fillProduct(product, productForm) {
		$(productForm[3]).children().val(product.price);
		$(productForm[4]).children().val(product.discount);
		$($(productForm[5]).children()[0]).attr('src',
				'${contextPath}/resources/images/' + product.imageName);
		$($(productForm[5]).children()[0]).css('display', 'initial');
		$($(productForm[5]).children()[1]).css('display', 'none');
	}

	function findProductByCode(event,code) {
		console.log($(event.target).parent().parent().parent().parent());
		$.ajax({
			url : '${contextPath}/api/productbycode',
			type : 'get',
			data : {
				"code" : code
			},
			success : function(data) {
				console.log(event);
				//fillProduct(data, rowTarget);
			}
		})
	}
	

	function showHideChangePopUp(event){
		let ul = $(event.target);
		//ul.next().focus();
		ul.next().css('display','block');
		ul.next().empty();
		$.ajax({
			url : '${contextPath}/api/productbyletter',
			type : 'get',
			data : {
				"code" : $(event.target).val()
			},
			success : function(data) {
				if (data.length !== 0){
					for(let i = 0; i < data.length; i++){
						let value = '\''+data[i]+'\'';
						ul.next().append('<li><a onclick="findProductByCode(event,'+value+')">'+data[i]+'</a></li>');
					}
					
				}
			}
		})
	}
	

	
	function findProductByCategoryId(event) {
		let slCategory = $(event.target);
		let slProduct = $(slCategory.parent().parent().find('td')[1])
				.children()[1];
		$
				.ajax({
					url : '${contextPath}/api/categoryType',
					type : 'get',
					data : {
						"cateId" : slCategory.val()
					},
					success : function(data) {
						console.log($(slProduct).find('option').length);
						for (let j = 0; j < $(slProduct).find('option').length; j++) {
							console.log(j + " - "
									+ $(slProduct).find('option').length);
							if (j > 1) {
								$(slProduct).find('option')[j].remove();
								//console.log(j+" - "+$(slProduct).find('option')[j]);
							}
						}
						for (let i = 0; i < data.length; i++) {
							slProduct.append(new Option(data[i].name,
									data[i].id));
						}
					}
				})
	}
</script>