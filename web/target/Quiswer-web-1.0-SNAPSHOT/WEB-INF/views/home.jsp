<%@ page import="entities.User" %>
<%@ page import="java.util.List" %>
<%@ page import="entities.Question" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entities.Request" %>
<%@ page import="java.util.Map" %>
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
${userid}<br>

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
                  <th>
                      <form action="question" method="post">
                          <input type="hidden" name="friendname" value="<%=user.getUsername()%>"/>
                          <input type="hidden" name="friendID" value="<%=user.getUserid()%>"/>
                          <input type="submit" value="Ask"/>
                      </form>
                  </th>
                  <th>View</th>
              </tr>
        <%}%>
</table><br>

<%
    Map<Request,String> requestmap=(Map<Request,String>)session.getAttribute("requestmap");
    if(requestmap.keySet().isEmpty()!=true)
    {%>
<table>
    <tr>
        <th>RequestFriend</th>
        <th>RequestText</th>
        <th>RequestTime</th>
        <th>Choidce</th>
        <th>Decide</th>
    </tr>
    <%
        for(Request request1:requestmap.keySet()){
            %>
    <tr><form method="post" action="friendrequest">
        <th><%=requestmap.get(request1)%></th>
        <th><%=request1.getRequest_message()%></th>
        <th><%=request1.getRequest_time()%></th>
        <th><select name="answer">
            <option value="1">Yes</option>
            <option value="2">No</option>
        </select> </th>
        <th><input type="submit" value="OK"></th>
        <input type="hidden" name="request_id" value="<%=request1.getRequest_id()%>">
        <input type="hidden" name="request_from" value="<%=requestmap.get(request1)%>">
    </form>
    </tr>
    <%}%>
</table>
<%}%>

<%Map<Question,String> questionmap=(Map<Question,String>)session.getAttribute("questionmap");
    if(questionmap.keySet().isEmpty()!=true){%>
<table>
    <tr>
        <th>QuestionFrom</th>
        <th>QuestionText</th>
        <th>QuestionTime</th>
        <th>QuestionPrivacy</th>
        <th>Answer</th>
    </tr>
    <%
        for(Question question:questionmap.keySet()){
    %>
    <tr>
        <th><%=questionmap.get(question)%></th>
        <th><%=question.getQuestion_text()%></th>
        <th><%=question.getQuestion_time()%></th>
        <th><%=question.getQuestion_privacy()%></th>
        <th><form method="post" action="answer">
            <input type="hidden" name="questiontext" value="<%=question.getQuestion_text()%>">
            <input type="hidden" name="questionID" value="<%=question.getQuestion_id()%>">
            <input type="submit" value="Answer"/>
        </form>

        </th>
    </tr>
    <%}%>
</table>
<%}%>


<form method="post" action="searchfriend">
    <input type="text" name="friendname">
    <input type="submit" value="Search this FriendName">
</form>
</body>
</html>