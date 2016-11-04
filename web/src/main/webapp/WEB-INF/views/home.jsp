<%@ page import="entities.User" %>
<%@ page import="java.util.List" %>
<%@ page import="entities.Question" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entities.Request" %>
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
           {%> <tr><th>${user.getUserid()}</th>
                  <th><%=user.getUsername()%></th>
<th><input type="button" value="Ask" onclick="window.location.href='question?friendID=<%=user.getUserid()%>';"/></th>
<th></th></tr>
        <%}%>
</table><br>





<%List<Request> RequestList=(List<Request>)session.getAttribute("requestlist");
    if(RequestList!=null){%>
<table>
    <tr>
        <th>RequestID</th>
        <th>RequestFriend</th>
        <th>RequestText</th>
        <th>Yes</th>
        <th>No</th>
    </tr>
    <%
        for(Request request1:RequestList){
            %>
    <tr>
        <th><%=request1.getRequest_id()%></th>
        <th><%=request1.getRequest_from()%></th>
        <th><%=request1.getRequest_message()%></th>
        <th><button name="yes" value="yes"/> </th>
        <th><button name="No" value="No"/> </th>
    </tr>
    <%}%>
</table>
    <%}%>

<%List<Question> QuestionList=(List<Question>)session.getAttribute("questionlist");
    if(QuestionList!=null){%>
<table>
    <tr>
        <th>QuestionFrom</th>
        <th>QuestionText</th>
        <th>Answer</th>
    </tr>
    <%
        for(Question question:QuestionList){
    %>
    <tr>
        <th><%=question.getQuestion_from()%></th>
        <th><%=question.getQuestion_text()%></th>
        <th>Answer</th>
    </tr>
    <%}%>
</table>
<%}%>




<table>

</table>

</body>
</html>