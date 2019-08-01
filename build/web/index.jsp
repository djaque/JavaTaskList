<%-- 
    Document   : index
    Created on : 25-jul-2019, 22:27:33
    Author     : dany
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="cl.task.Task"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Mis tareas!</h1>
        
        <table width="80%">
            <tbody>
                <tr>
                    <td>
                        <form action="handler" method="post">
                        <input type="text" name="text"><input type="submit" value="Agregar"> 
                        </form>
                    </td>
                </tr>
                
                <%  
                    List<Task> taskList = (List<Task>)request.getAttribute("tasklist");
                    if (null == taskList) {
                        taskList = new ArrayList<>();
                    }
                    for (int i = taskList.size() -1 ; i >= 0; i--) {
                        Task t = taskList.get(i);
                        if (!t.getIsDone()) {
                            %>
                                <tr>
                                    <td>
                                        <form action="handler" method="get"> 
                                            <input type="hidden" name="action" value="complete">
                                            <input type="checkbox" 
                                                   name="id" 
                                                   value="<%=t.getId()%>"
                                                   onclick="this.form.submit()"
                                                   > 
                                            <%=t.getId()%> - <%=t.getText()%>
                                        </form>
                                    </td>
                                </tr>
                            <%
                        }
                    }
                %>
                
                 
                <tr><td><hr></td></tr>
                <%
                    for (int i = taskList.size() -1 ; i >= 0; i--) {
                        Task t = taskList.get(i);
                        if (t.getIsDone()) {
                            %>
                                <tr>
                                    <td>
                                        <input type="checkbox" name="id" value="<%=t.getId()%>" checked="checked"> 
                                        <strike>
                                            <%=t.getId()%> - <%=t.getText()%>
                                        </strike>
                                    </td>
                                </tr>
                            <%
                        }
                    }
                %>

            </tbody>
        </table>
    </body>
</html>
