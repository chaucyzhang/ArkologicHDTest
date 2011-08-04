<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Disk Statistics</title>
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
        <script type="text/javascript" src="js/getCharts.js"></script>
        <link rel="stylesheet" href="css/resultPage.css"/>
    </head>
    <body>
          <div id="chart_div"></div> 
          <div id="changeChartType">
       <input type="button" id="changeBarChart" onclick="drawBarChart();" value="Bar Chart"/>
       <input type="button" id="changePieChart" onclick="drawPieChart();" value="Pie Chart"/>
       <input type="button" id="changeColumnChart" onclick="drawColumnChart();" value="Column Chart"/>
       <input type="button" id="changeLineChart" onclick="drawLineChart();" value="Line Chart"/>
       <input type="button" id="changeTableChart" onclick="drawTableChart();" value="Table Chart"/>
          </div>
    </body>
</html>
