<%--
  Created by IntelliJ IDEA.
  User: Kazaf
  Date: 16/10/27
  Time: 下午2:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Question</title>
</head>
<body>
You just want to ask <%=request.getParameter("friendname")%>:<br>
<form action="/Nomainate" method="post">
    <input type="text" name="question">
    <select name="privacy">
        <option value="public">Public</option>
        <option value="private">Private</option>
    </select>
    <input type="hidden" name="friendID" value="<%=request.getParameter("friendID")%>">
    <input type="submit" value="Ask"></form>
<br>


</body>
</html>
