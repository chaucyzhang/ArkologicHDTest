// Load the Visualization API and the piechart package.
google.load('visualization', '1', {
    'packages':['corechart','table']
});
var json=[
['sda0', 66.66 ,67.04],
['sda1', 63.98,100.23],
['sda2', 100.00,103.50]
];
// Set a callback to run when the Google Visualization API is loaded.
google.setOnLoadCallback(drawBarChart);
      
// Callback that creates and populates a data table, 
// instantiates the pie chart, passes in the data and
// draws it.


function initData(){
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Hard Disk Name');
    data.addColumn('number', 'Read Speed (M/s)');
    data.addColumn('number','Write Speed (M/s)')
    data.addRows(json);
    return data;
}


function drawBarChart() {
    var data=initData();
    var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
    chart.draw(data, {
        width: 800, 
        height: 600,
        title: 'Hard Disk W/R Speed Test Result',
        vAxis: {
            title: 'Hard Disk Name'
        },
        hAxis: {
            title: 'M/s'
        }
        
    });
}

function drawPieChart(){
    var data=initData();
    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
    chart.draw(data, {
        width: 800, 
        height: 600
    });
}

function drawColumnChart(){
    var data=initData();
    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
    chart.draw(data, {
        width: 800, 
        height: 600,
                title: 'Hard Disk W/R Speed Test Result',
        hAxis: {
            title: 'Hard Disk Name'
        },
        vAxis: {
            title: 'M/s'
        }
    });
}

function drawLineChart(){
    var data=initData();
    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
    chart.draw(data, {
        width: 800, 
        height: 600,
                title: 'Hard Disk W/R Speed Test Result',
        hAxis: {
            title: 'Hard Disk Name'
        },
        vAxis: {
            title: 'M/s'
        }
    });
}

function drawTableChart(){
    var data=initData();
    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.Table(document.getElementById('chart_div'));
    chart.draw(data, {
        width: 800, 
        height: 600,
        showRowNumber:true
    });
}




 