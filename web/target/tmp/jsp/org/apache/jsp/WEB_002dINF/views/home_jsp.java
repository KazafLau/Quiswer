/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: jetty/9.3.9.v20160517
 * Generated at: 2016-11-04 08:19:21 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import entities.User;
import java.util.List;
import entities.Question;
import java.util.ArrayList;
import entities.Request;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("entities.User");
    _jspx_imports_classes.add("entities.Request");
    _jspx_imports_classes.add("entities.Question");
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("java.util.ArrayList");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <title>Quiswer</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("the homepage<br>\n");
      out.write("\n");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${username}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("<br>\n");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${questionqueue}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("<br>\n");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.getUserid()}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("<br>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<table >\n");
      out.write("    <tr>\n");
      out.write("        <th>UserID</th>\n");
      out.write("        <th>UserName</th>\n");
      out.write("        <th>Ask</th>\n");
      out.write("        <th>View</th>\n");
      out.write("    </tr>\n");
 for(User user:(List<User>)session.getAttribute("friendslist"))
           {
      out.write(" <tr><th>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.getUserid()}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</th>\n");
      out.write("                  <th>");
      out.print(user.getUsername());
      out.write("</th>\n");
      out.write("<th><input type=\"button\" value=\"Ask\" onclick=\"window.location.href='question?friendID=");
      out.print(user.getUserid());
      out.write("';\"/></th>\n");
      out.write("<th></th></tr>\n");
      out.write("        ");
}
      out.write("\n");
      out.write("</table><br>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
List<Request> RequestList=(List<Request>)session.getAttribute("requestlist");
    if(RequestList!=null){
      out.write("\n");
      out.write("<table>\n");
      out.write("    <tr>\n");
      out.write("        <th>RequestID</th>\n");
      out.write("        <th>RequestFriend</th>\n");
      out.write("        <th>RequestText</th>\n");
      out.write("        <th>Yes</th>\n");
      out.write("        <th>No</th>\n");
      out.write("    </tr>\n");
      out.write("    ");

        for(Request request1:RequestList){
            
      out.write("\n");
      out.write("    <tr>\n");
      out.write("        <th>");
      out.print(request1.getRequest_id());
      out.write("</th>\n");
      out.write("        <th>");
      out.print(request1.getRequest_from());
      out.write("</th>\n");
      out.write("        <th>");
      out.print(request1.getRequest_message());
      out.write("</th>\n");
      out.write("        <th><button name=\"yes\" value=\"yes\"/> </th>\n");
      out.write("        <th><button name=\"No\" value=\"No\"/> </th>\n");
      out.write("    </tr>\n");
      out.write("    ");
}
      out.write("\n");
      out.write("</table>\n");
      out.write("    ");
}
      out.write('\n');
      out.write('\n');
List<Question> QuestionList=(List<Question>)session.getAttribute("questionlist");
    if(QuestionList!=null){
      out.write("\n");
      out.write("<table>\n");
      out.write("    <tr>\n");
      out.write("        <th>QuestionFrom</th>\n");
      out.write("        <th>QuestionText</th>\n");
      out.write("        <th>Answer</th>\n");
      out.write("    </tr>\n");
      out.write("    ");

        for(Question question:QuestionList){
    
      out.write("\n");
      out.write("    <tr>\n");
      out.write("        <th>");
      out.print(question.getQuestion_from());
      out.write("</th>\n");
      out.write("        <th>");
      out.print(question.getQuestion_text());
      out.write("</th>\n");
      out.write("        <th>Answer</th>\n");
      out.write("    </tr>\n");
      out.write("    ");
}
      out.write("\n");
      out.write("</table>\n");
}
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<table>\n");
      out.write("\n");
      out.write("</table>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
