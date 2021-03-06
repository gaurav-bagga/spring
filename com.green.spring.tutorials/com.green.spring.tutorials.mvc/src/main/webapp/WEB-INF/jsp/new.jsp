<%-- 
    Document   : new person creation page
    Created on : Aug 25, 2013, 5:11:44 PM
    Author     : gaurav
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Person</title>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
    </head>
    <body>
        <h1>Create Person</h1>
        <sf:form class="form-signin" action="${pageContext.request.contextPath}/create" method="post" modelAttribute="person">
            <table>
                <tr>
                    <td colspan="2">
                        <sf:errors path="*" />
                    </td>
                </tr>
                <tr>
                    <td>First Name:</td>
                    <td><sf:input path="firstName" id="firstName" /></td>
                </tr>
                <tr>
                    <td>Last Name:</td>
                    <td><sf:input path="lastName" id="lastName" /></td>
                </tr>
                <tr>
                    <td>Age:</td>
                    <td><sf:input path="age" id="age" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><button type="submit">Create</button></td>
                </tr>
            </table>
        </sf:form>
        <a href="${pageContext.request.contextPath}/list">List All</a>
    </body>
</html>
