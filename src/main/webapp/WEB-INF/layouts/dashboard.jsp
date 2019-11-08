<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}" />
<html>
<head>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script src='<c:url value="/resources/bootstrap/js/jquery.min.js"/>'></script>
<script type="text/javascript">
	var pieChart = [];
	function getData() {
		$.ajax({
			url : '${contextPath}/api/category/quantity',
			async : false,
			type : 'get',
			success : function(data) {
				pieChart.push([ 'Task', 'Hours per Day' ]);
				for (let i = 0; i < data.length; i++) {
					pieChart.push(data[i]);
				}
			}
		});
	}
	getData();
	google.charts.load('current', {
		'packages' : [ 'corechart' ]
	});
	google.charts.setOnLoadCallback(drawChart);

	function drawChart() {

		var data = google.visualization.arrayToDataTable(pieChart)

		var options = {
			title : 'Sản phẩm theo loại',
			backgroundColor : 'transparent',
			is3D : true
		};

		var chart = new google.visualization.PieChart(document
				.getElementById('piechart'));

		chart.draw(data, options);
	}

	google.charts.setOnLoadCallback(drawChart1);

	function drawChart1() {
		var data1 = google.visualization.arrayToDataTable([
				[ 'Số lượng', 'Sản phẩm đã bán' ], [ '2004', 1000 ],
				[ '2005', 1170 ], [ '2006', 660 ], [ '2007', 1030 ] ]);

		data1.addRows([ [ '2008', 900 ], [ '2009', 950 ] ]);

		var options1 = {
			title : 'Company Performance',
			curveType : 'function',
			backgroundColor : 'transparent',
			legend : {
				position : 'bottom'
			}
		};

		var chart1 = new google.visualization.LineChart(document
				.getElementById('curve_chart'));

		chart1.draw(data1, options1);
	}
</script>

</head>
<body>

	<table>
		<tr>
			<td width="75%">
				<div id="curve_chart" style="width: 100%; height: 500px"></div>
			</td>
			<td>
				<div id="piechart" style="width: 100%; height: 300px"></div>
			</td>
		</tr>
	</table>

	<table class="table table-bordered table_custom"
		style="text-align: center;">
		<thead>
			<tr class="table_header" align="left">
				<th>Doanh thu trong tháng</th>
				<th>Doanh Thu trung bình</th>
				<th>Doanh Thu trong năm</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>1</td>
				<td>2</td>
				<td>3</td>
			</tr>
		</tbody>
	</table>
</body>
</html>