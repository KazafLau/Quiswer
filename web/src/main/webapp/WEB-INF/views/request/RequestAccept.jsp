<%@ page import="entities.Request" %><%--
  Created by IntelliJ IDEA.
  User: kazaf
  Date: 16-11-16
  Time: 下午8:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
＜meta http-equiv="refresh" content="3; url=home.jsp"＞
<head>
    <title>RequestAccept</title>
</head>
<body>

<%=request.getAttribute("sentence")%>

<%request.getRequestDispatcher("/home.jsp").forward(request, response);%>
</body>
</html>
