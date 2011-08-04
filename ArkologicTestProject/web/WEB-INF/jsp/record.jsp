<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test Record</title>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
        <script type="text/javascript" src="js/record.js"></script>
        <link rel="stylesheet" href="css/index.css"/>
        <link rel="stylesheet" href="css/record.css"/>
    </head>
    <body>
        <div id="wrap">

            <div id="top"></div>

            <div id="content">
                <div id="navi">
                <span id="navigation" style="float: left;"><a href="index.htm">Home</a></span>
<span><spring:message code="locale.language" text="default text" />
  <a href="?language=en">English</a>|<a href="?language=zh_CN">Chinese</a></span>
  </div>
                <div class="header">
                    <h1><a href="javascript:void(0);"><spring:message code="locale.title" text="default text" /></a></h1> 
                </div>


                <div class="middle">
                    <div id="record">
                        <table style="width:100%;">
                            <thead>
                                <tr>
                                    <th>Device</th>
                                    <th>r/s</th>
                                    <th>w/s</th>
                                    <th>kr/s</th>
                                    <th>kw/s</th>
                                    <th>wait</th>
                                    <th>actv</th>
                                    <th>svc_t</th>
                                    <th>%w</th>
                                    <th>%b</th>
                                    <th>Create Time</th>
                                </tr>
                            </thead>
                            <tbody id="dataTbody">
                                <c:forEach items="${record}" var="record">
                                    <tr>
                                        <td><c:out value="${record.device}"/></td>
                                        <td><c:out value="${record.rps}"/></td>
                                        <td><c:out value="${record.wps}"/></td>
                                        <td><c:out value="${record.krps}"/></td>
                                        <td><c:out value="${record.kwps}"/></td>
                                        <td><c:out value="${record.wait}"/></td>
                                        <td><c:out value="${record.actv}"/></td>
                                        <td><c:out value="${record.svct}"/></td>
                                        <td><c:out value="${record.w}"/></td>
                                        <td><c:out value="${record.b}"/></td>
                                        <td><c:out value="${record.createtime}"/></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <div id="seemoreBtn">
                          <p><spring:message code="locale.moreRecord" text="default text" /></p>
                        </div>
                        <div id="page">
                            <spring:message code="locale.pageNum" text="default text" /><span id="currentPageNum"><c:out value="${page}"/></span>
                        </div>
                    </div>	


                </div>


                <div id="clear"></div>

            </div>

            <div id="bottom"></div>

        </div>

        <div id="footer">
        </div>

    </body>
</html>
