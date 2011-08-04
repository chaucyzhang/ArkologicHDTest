$(document).ready(function(){
    $("#seemoreBtn").click(function(){
        var currentPageNum = $("#currentPageNum").text();
        $.getJSON("page.htm",{
            currentPage:currentPageNum
        },function(js){
            var dt=eval('(' + js.json + ')');
            if(dt!=""){
           
                for(var i=0;i<dt.length;i++){
                    var newline="<tr class='newPost'><td>"+dt[i].device+"</td><td>"+dt[i].rps+"</td><td>"+dt[i].wps+"</td><td>"+dt[i].krps+"</td><td>"+dt[i].kwps+"</td><td>"+dt[i].wait+"</td><td>"+dt[i].actv+"</td><td>"+dt[i].svct+"</td><td>"+dt[i].w+"</td><td>"+dt[i].b+"</td><td>"+dt[i].createtime+"</td></tr>"
                    $("#dataTbody").append(newline);
              $(".newPost").hide();
                    $(".newPost").fadeIn(1500);
                    $(".newPost").removeClass("newPost");
                }   
                $("#currentPageNum").text(parseInt($("#currentPageNum").text())+1);
            }
            else{
                alert("No More Record");
            
            }
        });
    });
});

