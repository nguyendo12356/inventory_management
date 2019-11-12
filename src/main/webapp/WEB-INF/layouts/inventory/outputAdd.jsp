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
 <div class="alert alert-danger alert-dismissible" id="alert-error" style="display: none">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Error!</strong>&ensp;<span id="tbl-error">Vui lòng nhập đầy đủ thông tin</span>
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
				<th>Active</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach begin="0" end="1"
				varStatus="loop">
				<tr>
					<td class="m-width-120">
						<div class="left" style="background-color: #bbb;">
							<input class="form-control w-100"
								onkeyup="showHideChangePopUp(event)" onchange="findProductByCode(event,this.value)"/>
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
		var code = '<div class="left" style="background-color: #bbb;">'+
			'<input class="form-control w-100" onkeyup="showHideChangePopUp(event)" onchange="findProductByCode(event,this.value)"/>'+
			'<ul id="myMenu" class="hiddenPopup"></ul></div>';
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
		let row = $(event.target).closest('tr').find('td');
		$.ajax({
			url : '${contextPath}/api/productbycode',
			type : 'get',
			data : {
				"code" : code
			},
			success : function(data) {
				if (data.category != 0)
					fillProduct(data, row);
			}
		})
	}

	function showHideChangePopUp(event) {
		let ul = $(event.target);
		ul.unbind();
		ul.next().css('display', 'block');
		$.ajax({
			url : '${contextPath}/api/productbyletter',
			type : 'get',
			data : {
				"code" : $(event.target).val()
			},
			success : function(data) {
				ul.next().empty();
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

	function validate(codeBill, suplier){
		$('#error-invoice').css('display','none');
		if(codeBill.val().length == 0){
			$('#error-invoice').css('display','block');
			codeBill.css('borderColor','red');
			$('#error-invoice-text').html('Vui lòng nhập mã hóa đơn');
			return false;
		}else if(suplier.val().length == 0){
			$('#error-invoice').css('display','block');
			suplier.css('borderColor','red');
			$('#error-invoice-text').html('Vui lòng nhập nhà cung cấp');
			return false;
		}
		return true;
	}
	
	function validateTable(data){
		$('#alert-error').css('display','none');
		let code = $(data[0]).find('input');
		let name = $(data[1]).find('input');
		let quantity = $(data[3]).find('input');
		let price = $(data[4]).find('input');
		let discount = $(data[5]).find('input');
		if (code.val().length == 0){
			code.css('borderColor','red');
			$('#alert-error').css('display','block');
			$('#tbl-error').html('Vui lòng nhập đầy đủ thông tin');
			return false;
		}else if (name.val().length == 0){
			name.css('borderColor','red');
			$('#alert-error').css('display','block');
			$('#tbl-error').html('Vui lòng nhập đầy đủ thông tin');
			return false;
		}
		else if (quantity.val().length == 0){
			quantity.css('borderColor','red');
			$('#alert-error').css('display','block');
			$('#tbl-error').html('Vui lòng nhập đầy đủ thông tin');
			return false;
		}
		else if (price.val().length == 0){
			price.css('borderColor','red');
			$('#alert-error').css('display','block');
			$('#tbl-error').html('Vui lòng nhập đầy đủ thông tin');
			return false;
		}
		else if (discount.val().length == 0){
			discount.css('borderColor','red');
			$('#alert-error').css('display','block');
			$('#tbl-error').html('Vui lòng nhập đầy đủ thông tin');
			return false;
		}
		return true;
	}
	
	
	$('body').find('input').on("change", function(){
		$(this).css('borderColor', '#ccc');
	})
	
	function checkString(content){
		let regex = /^[0-9a-zA-Z-_]+$/;
		return regex.test(content);
	}
	
	function saveInputInvoice() {
		totalPriceNumber = 0;
		let invoice = {
				"products":[]
			};
		let isErrorTable = false;
		if(true){
			let rows = $('#tbl-input tr:not(".table_header")');
			for (let m = 0; m < rows.length; m++){
				let row = $(rows[m]).find('td');
				if(validateTable(row)){
					invoice.products.push(getTableValue(row));
					isErrorTable = false;
				}else{
					isErrorTable = true;
					break;
				}
			};
			invoice["totalPrice"] = totalPriceNumber;
			if(!isErrorTable){
				$.ajax({
					url : '${contextPath}/api/addInvoiceOutput',
					type : 'post',
					headers : {
						'Accept' : 'application/json',
						'Content-Type' : 'application/json'
					},
					data : JSON.stringify(invoice),
					success : function(data) {
						console.log(data);
						window.location.replace("${contextPath}/inventory/output");
					}
				})
			}
		}
	}
		
	var totalPriceNumber = 0;
	
	function getTableValue(data){
		let code = $(data[0]).find('input').val();
		let name = $(data[1]).find('input').val();
		let category = $(data[2]).find('select').val();
		let quantity = $(data[3]).find('input').val();
		let price = $(data[4]).find('input').val();
		let discount = $(data[5]).find('input').val();
		totalPriceNumber = totalPriceNumber + quantity*price*(100 - discount)/100;
		let product = {
				"name": name,
				"code": code,
				"quantity": quantity,
				"price": price,
				"category": category,
				"discount": discount
		}; 
		return product;
	}
</script>