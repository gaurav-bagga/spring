<%-- 
    Document   : all person listing page
    Created on : Aug 25, 2013, 5:28:13 PM
    Author     : gaurav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Person Listing</title>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
    </head>
    <body>
        <c:if test="${not empty sucMsg}">${sucMsg}</c:if>
        <c:if test="${not empty errMsg}">${errMsg}</c:if>
        <br />
        <br />
        
        <table>
            <tr>
                <th>Sr. No.</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Age</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="person" items="${personList}" varStatus="i">
                <tr>
                    <td>${i.count}</td>
                    <td>${person.firstName}</td>
                    <td>${person.lastName}</td>
                    <td>${person.age}</td>
                    <td>
                        <a class="edit" href="${pageContext.request.contextPath}/edit/${person.id}">Edit</a> 
                        &nbsp; 
                        <a class="delete" href="#" data-id="${person.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
         <a href="${pageContext.request.contextPath}/">New</a>
         <form id="postDelete" method="post" action=""></form>
         <script>
             var partAction = "${pageContext.request.contextPath}/delete/";
             $(".delete").click(function(){
                if(!confirm("Are you sure you want to delete the entry?")) return;
                $("#postDelete").attr("action",partAction + $(this).attr("data-id"));
                $("#postDelete").submit();
             });
         </script>
    </body>
</html>
