<%@ page import="entities.Question" %><%--
  Created by IntelliJ IDEA.
  User: kazaf
  Date: 16-11-5
  Time: 下午5:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Answer</title>
</head>
<body>

<form action="/Answer" method="post">
    The question is:   <%=request.getParameter("questiontext")%><br/>
    <input type="text" name="questionanswer">
    <input type="hidden" name="questionID" value="<%=request.getParameter("questionID")%>">
    <input type="submit" value="Answer"></form>

</form>

</body>
</html>
