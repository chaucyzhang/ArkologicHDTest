<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Arkologic Test</title>
        <link rel="stylesheet" href="css/index.css"/>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
        <script type="text/javascript" src="js/jQuery.fileinput.js"></script>
        <script type="text/javascript" src="js/index.js"></script>
    </head>

    <body>
        <div id="wrap">
 
<div id="top"></div>

<div id="content">
<spring:message code="locale.language" text="default text" />
  <a href="?language=en">English</a>|<a href="?language=zh_CN">Chinese</a>
<div class="header">
 <h1><a href="javascript:void(0);"><spring:message code="locale.title" text="default text" /></a></h1> 
</div>


<div class="middle">
			
<h2><spring:message code="locale.upload" text="default text" /></h2>
		 <div id="fileInput">
        <form action="form.htm" method="post" enctype="multipart/form-data">
            <fieldset>
			<legend><spring:message code="locale.uploadMessage" text="default text" /></legend>
			<label for="file"><spring:message code="locale.chooseUpload" text="default text" /></label>
                <input name="file" id="file" type="file"/>
                <input type="submit" id="upload" value="<spring:message code="locale.submit" text="default text" />"/>
            </fieldset>
        </form>
                </div>	


		 <div id="findRecord">
<h2><a href="record.htm"><spring:message code="locale.findRecordFromPast" text="default text" /></a></h2>
                </div>	
<br /><br />
 		
</div>
		

<div id="clear"></div>

</div>

<div id="bottom"></div>

</div>

<div id="footer">
</div>
    </body>
</html>
