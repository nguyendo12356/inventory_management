<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>
<html>
  <head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script src='<c:url value="/resources/bootstrap/js/jquery.min.js"/>'></script>
    <script type="text/javascript">
    
    var dataPieChart = [];
    $.ajax({
		url: '${contextPath}/api/category/quantity',
		type: 'get',
		success: function(data){
			dataPieChart = data;
			dataPieChart.push(5);
			dataPieChart.push(6);
		}
	});
    console.log(dataPieChart);
    var test = [];
    test.push(['Task', 'Hours per Day']);
    for(let i = 0; i < 5; i++){
    	let a = [];
    	a.push("t"+i);
    	a.push(i+1);
    	test.push(a);
    }
    
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);
      google.charts.setOnLoadCallback(drawChart1);

      function drawChart() {

        /* var data = google.visualization.arrayToDataTable([
            ['Task', 'Hours per Day'],
            ['Work',     11],
            ['Eat',      2],
            ['Commute',  2],
            ['Watch TV', 2],
            ['Sleep',    7]
          ]) */

          var data = google.visualization.arrayToDataTable(test);
        var options = {
          title: 'My Daily Activities',
          backgroundColor: 'transparent',
          is3D: true
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
      
      
      function drawChart1() {
          var data1 = google.visualization.arrayToDataTable([
            ['Year', 'Sales', 'Expenses'],
            ['2004',  1000,      400],
            ['2005',  1170,      460],
            ['2006',  660,       1120],
            ['2007',  1030,      540]
          ]);

          var options1 = {
            title: 'Company Performance',
            curveType: 'function',
            backgroundColor: 'transparent',
            legend: { position: 'bottom' }
          };

          var chart1 = new google.visualization.LineChart(document.getElementById('curve_chart'));

          chart1.draw(data1, options1);
        }
    </script>
    <style type="text/css">
    	#piechart{
    		background: none;
    		position: absolute;
    		top: 20%;
    		left: 75%;
    	}
    </style>
  </head>
  <body>
    <div id="piechart"></div>
    <div id="curve_chart" style="width: 900px; height: 500px"></div>
  </body>
</html>