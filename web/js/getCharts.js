
// Load the Visualization API and the piechart package.
google.load('visualization', '1', {
    'packages':['corechart','table']
});
//          $.getJSON('result.htm',function(js){
//       
//    var dt = eval("(" + js.json + ")"); 
//    var alldisks=new Array();
//    for(var i=0;i<dt.length;i++){
//        var singledisk=new Array();
//             singledisk[0]=dt[i].device;
//             singledisk[1]=dt[i].rps;
//             singledisk[2]=dt[i].wps;
//             alldisks[i]=singledisk;
//    }
//       
//    });

var json=new Array();
$.ajax({
    url: "result.htm",
    async:false,
    dataType:"json",
    success: function(js){
        var dt = eval("(" + js.json + ")"); 
        var alldisks=new Array();
        for(var i=0;i<dt.length;i++){
            var singledisk=new Array();
            singledisk[0]=dt[i].device.toString();
            singledisk[1]=parseFloat(dt[i].rps);
            singledisk[2]=parseFloat(dt[i].wps);
            singledisk[3]=parseFloat(dt[i].krps);
            singledisk[4]=parseFloat(dt[i].kwps);
            singledisk[5]=parseFloat(dt[i].wait);
            singledisk[6]=parseFloat(dt[i].actv);
            singledisk[7]=parseFloat(dt[i].svc_t);
            singledisk[8]=parseFloat(dt[i].w);
            singledisk[9]=parseFloat(dt[i].b);
            alldisks[i]=singledisk;
        }

        json=alldisks;

    }
  
});
//alert(json);

//var json=[
//['sda0', 66.66 ,67.04],
//['sda1', 63.98,100.23],
//['sda2', 100.00,103.50]
//];
// Set a callback to run when the Google Visualization API is loaded.
google.setOnLoadCallback(drawBarChart);
      
// Callback that creates and populates a data table, 
// instantiates the pie chart, passes in the data and
// draws it.


function initData(){
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Hard Disk Name');
    data.addColumn('number', 'Reads per second');
    data.addColumn('number','Writes per second');
    data.addColumn('number','Kb read per second');
    data.addColumn('number','Kb written per second');
    data.addColumn('number','Time spent by a process while waiting for block I/O to complete.');
    data.addColumn('number','Number of active requests in the hardware queue');
    data.addColumn('number','Service time (ms). Includes everything: wait time, active queue time, seek rotation, transfer time');
    data.addColumn('number','Occupancy of the wait queue(%w)');
    data.addColumn('number','Occupancy of the active queue with the device busy(%b)');
    data.addRows(json);
    return data;
}


function drawBarChart() {
    var data=initData();
    var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
    chart.draw(data, {
        width: 1024, 
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
        width: 1024, 
        height: 600
    });
}

function drawColumnChart(){
    var data=initData();
    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
    chart.draw(data, {
        width: 1024, 
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
        width: 1024, 
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
        width: 1024, 
        height: 600,
        showRowNumber:true
    });
}


