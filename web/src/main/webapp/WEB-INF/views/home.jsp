<%@ page import="entities.User" %>
<%@ page import="java.util.List" %>
<%@ page import="entities.Question" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quiswer</title>
</head>
<body>
the homepage<br>

${username}<br>
${questionqueue}<br>
${user.getUserid()}<br>



<table >
    <tr>
        <th>UserID</th>
        <th>UserName</th>
        <th>Ask</th>
        <th>View</th>
    </tr>
<% for(User user:(List<User>)session.getAttribute("friendslist"))
           {%> <tr><th><%=user.getUserid()%></th>
                  <th><%=user.getUsername()%></th>
<th><input type="button" value="Ask" onclick="window.location.href='question?friendID=<%=user.getUserid()%>';"/></th>
<th></th></tr>
        <%}%>
</table><br>
<%User temp=(User)session.getAttribute("user");
    if(temp.getUserid()==3){%>

<table >
    <tr>
        <th>From</th>
        <th>Question</th>
        <th>TIme</th>
        <th>privacy</th>
    </tr>
    <% for(Question question:(ArrayList<Question>)session.getAttribute("userquestionList"))
    {%> <tr><th><%=question.getQuestion_from()%></th>
    <th><%=question.getQuestion_text()%></th>
    <th><%=question.getQuestion_time()%></th>
    <th><%=question.getQuestion_privacy()%></th></tr>
    <%}%>
</table><br>

<%}%>
</body>
</html>