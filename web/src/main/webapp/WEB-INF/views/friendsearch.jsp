<%@ page import="entities.User" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: kazaf
  Date: 16-11-16
  Time: 下午10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Searching Friends</title>
</head>
<body>


<%Map<User,String> friendsmap=(Map<User,String>)request.getAttribute("friendswithname");
    if(friendsmap.keySet().isEmpty()!=true){%>
<table>
    <tr>
        <th>UserName</th>
        <th>UserID</th>
        <th>UserEmail</th>
        <th>Friends?</th>
        <th>Message</th>
        <th>Add Friend</th>
    </tr>
    <%
        for(User user:friendsmap.keySet()){
    %>
    <tr>
        <th><%=user.getUsername()%></th>
        <th><%=friendsmap.get(user)%></th>
        <th><%=user.getUserid()%></th>
        <th><%=user.getUsermail()%></th>
        <form method="post" action="addfriend">
            <th><input type="text" name="message"> </th>
        <th>
            <input type="hidden" name="friendID" value="<%=user.getUserid()%>">
            <input type="submit" value="Add!"/>
        </th></form>
    </tr>
    <%}%>
</table>
<%}%>

</body>
</html>
