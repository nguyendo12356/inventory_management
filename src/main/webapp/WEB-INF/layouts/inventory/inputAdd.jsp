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

<form class="form-inline custom-form" enctype="multipart/form-data">
	<%-- <fieldset class="custom-fieldset">
		<legend>Thông tin hóa đơn</legend>
		<div class="form-group">
			<label for="pwd">Mã hóa đơn:</label>
			<form:input class="form-control" path="codeBill" id="codeBill" />
		</div>
		<div class="form-group">
			<label for="suplier">Nhà cung cấp:</label>
			<form:input class="form-control" id="suplier" path="suplier" />
		</div>
		<div class="form-group">
			<label for="suplier">Tổng giá trị:</label>
			<form:input class="form-control" id="totalPrice" path="totalPrice" />
		</div>
	</fieldset> --%>
	<fieldset class="custom-fieldset">
		<legend>Thông tin hóa đơn</legend>
		<div class="form-group">
			<label for="pwd">Mã hóa đơn:</label> <input class="form-control"
				id="codeBill" />
		</div>
		<div class="form-group">
			<label for="suplier">Nhà cung cấp:</label> <input
				class="form-control" id="suplier" />
		</div>
		<div class="form-group">
			<label for="suplier">Tổng giá trị:</label> <input
				class="form-control" id="totalPrice" />
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
			<%-- <c:forEach begin="0" end="${fn:length(model.products) - 1}"
				varStatus="loop">
				<tr>
					<td class="m-width-120">
						<div class="left" style="background-color: #bbb;">
							<form:input cssClass="form-control w-100"
								path="products[${loop.index}].code"
								onkeyup="showHideChangePopUp(event)" />
							<ul id="myMenu" class="hiddenPopup">
							</ul>
						</div>
					</td>
					<td class="m-width-sl"><form:input cssClass="form-control"
							path="products[${loop.index}].name" /></td>
					<td><form:select class="form-control w-100"
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
			</c:forEach> --%>
			<c:forEach begin="0" end="2"
				varStatus="loop">
				<tr>
					<td class="m-width-120">
						<div class="left" style="background-color: #bbb;">
							<input class="form-control w-100"
								onkeyup="showHideChangePopUp(event)" />
							<ul id="myMenu" class="hiddenPopup">
							</ul>
						</div>
					</td>
					<td class="m-width-sl"><input class="form-control" /></td>
					<td><select class="form-control w-100"
						onchange="findProductByCategoryId(event)">
							<option>Chọn loại sản phẩm</option>
							<c:forEach var="categoryItem" items="${category}">
								<option value="${categoryItem.id}">${categoryItem.name}</option>
							</c:forEach>
					</select></td>
					<td class="m-width"><input class="form-control w-100" /></td>
					<td class="m-width"><input class="form-control w-100" /></td>
					<td class="m-width"><input class="form-control w-100" /></td>
					<td class="m-width-120"><img alt="No image" src=""
						class="common-img-50" style="display: none;" /> <input
						type="file" class="form-control w-100" /></td>

					<td><a type="button" class="btn btn-danger glyphicon glyphicon-remove" onclick="deleteLineTable(event)"></a></td>
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
			<button class="btn btn-primary" onclick="saveInputInvoice()">
				Lưu hóa đơn</button>
		</div>
	</div>

</form>
<button class="btn btn-primary" onclick="saveInputInvoice()">Test</button>
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
		var code = '<input class="form-control w-100" onkeyup="showHideChangePopUp(event)" />'
				+ '<ul id="myMenu" class="hiddenPopup"></ul>';
		var action = '<a type="button" class="btn btn-danger  glyphicon glyphicon-remove" onclick="deleteLineTable(event)" ></a>';
		var name = '<input class="form-control" />';
		var category = '<select class="form-control w-100" onchange="findProductByCategoryId(event)">'
				+ '<option>Chọn loại sản phẩm</option>'
				+ '<c:forEach var="categoryItem" items="${category}">'
				+ '<option value="${categoryItem.id}">${categoryItem.name}</option>'
				+ '</c:forEach>' + '</select>';
		var quantity = '<input class="form-control w-100" />';
		var price = '<input class="form-control w-100" />';
		var discount = '<input class="form-control w-100" />';
		var imageFile = '<img alt="No image" src="" class="common-img-50" style="display: none;" />'
				+ '<input type="file" class="form-control w-100" />';
		var newLine = "<tr>" + "<td class='m-width-120'>" + code + "</td>"
				+ "<td class='m-width-sl'>" + name + "</td>" + "<td>"
				+ category + "</td>" + "<td class='m-width'>" + quantity
				+ "</td>" + "<td class='m-width'>" + price + "</td>"
				+ "<td class='m-width'>" + discount + "</td>"
				+ "<td class='m-width-120'>" + imageFile + "</td>" + "<td>"
				+ action + "</td>" + "</tr>";
		var id = "#tbl-input";
		$(id + " tbody").append(newLine);
	}

	function fillProduct(data, row) {
		$(row[0]).find('input').val(data.code);
		$(row[1]).find('input').val(data.name);
		let slCategory = $(row[2]).find('select').find('option');
		for (let j = 0; j < slCategory.length; j++) {
			if (slCategory[j].value == data.cate.id) {
				$(slCategory[j]).attr('selected', 'selected');
			}
		}
		$(row[4]).find('input').val(data.price);
		$(row[5]).find('input').val(data.discount);
		$($(row[6]).children()[0]).attr('src',
				'${contextPath}/resources/images/' + data.imageName);
		$($(row[6]).children()[1]).css('display', 'none');
		$($(row[6]).children()[0]).css('display', 'initial');

	}

	function findProductByCode(event, code) {
		let row = $(event.target).parent().parent().parent().parent().parent()
				.find('td');
		$.ajax({
			url : '${contextPath}/api/productbycode',
			type : 'get',
			data : {
				"code" : code
			},
			success : function(data) {
				fillProduct(data, row);
			}
		})
	}

	function showHideChangePopUp(event) {
		let ul = $(event.target);
		ul.unbind('keyup');
		ul.next().css('display', 'block');
		ul.next().empty();
		$.ajax({
			url : '${contextPath}/api/productbyletter',
			type : 'get',
			data : {
				"code" : $(event.target).val()
			},
			success : function(data) {
				if (ul.next().find('option').length == 0) {
					for (let i = 0; i < data.length; i++) {
						let value = '\'' + data[i] + '\'';
						ul.next()
								.append(
										'<li><a onclick="findProductByCode(event,'
												+ value + ')">' + data[i]
												+ '</a></li>');
					}

				}
			}
		})
	}

	function findProductByCategoryId(event) {
		let slCategory = $(event.target);
		let slProduct = $(slCategory.parent().parent().find('td')[1])
				.children()[1];
		$.ajax({
			url : '${contextPath}/api/categoryType',
			type : 'get',
			data : {
				"cateId" : slCategory.val()
			},
			success : function(data) {
				console.log($(slProduct).find('option').length);
				for (let j = 0; j < $(slProduct).find('option').length; j++) {
					if (j > 1) {
						$(slProduct).find('option')[j].remove();
					}
				}
				for (let i = 0; i < data.length; i++) {
					slProduct.append(new Option(data[i].name, data[i].id));
				}
			}
		})
	}

	function saveInputInvoice() {
		let product = {
				"codeBill" : "AAAA",
				"type" : 1,
				"staffName" : "THanh Do",
				"suplier" : "AAAAA",
				"totalPrice" : 10,
				"products":[]
			};
		$('#tbl-input tr:not(".table_header")').each(function(){
			let row = $(this).find('td');
			product.products.push(getTableValue(row));
		})
		$.ajax({
			url : '${contextPath}/api/test',
			type : 'post',
			headers : {
				'Accept' : 'application/json',
				'Content-Type' : 'application/json'
			},
			data : JSON.stringify(product),
			success : function(data) {
				console.log(data);
			}
		})
	}
	
	
	function getTableValue(data){
		$(data[0]).find('input').val('7');
		$(data[1]).find('input').val('8');
		$(data[3]).find('input').val('3');
		$(data[4]).find('input').val('4');
		$(data[5]).find('input').val('5');
		$(data[6]).find('input').val();
		let imageName = ($(data[6]).find('input')[0]).files[0];
		let product = {
				"name": "thanh do",
				"code": "sp15",
				"img_url.originalFilename": imageName.name
		}; 
		return product;
	}
</script>