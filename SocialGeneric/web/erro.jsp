<%-- 
    Document   : erro
    Created on : 26/04/2016, 09:22:14
    Author     : 1147106
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="head.jsp" %>       
    <body>  
        <%@include file="topmenu.jspf" %>  
        <section class="container center ">
            <h1>ERRO:  ${erromsg}</h1>
            <c:set var="erromsg" scope="session" value=""></c:set>
            <img src="img/erro.jpg"/>
        </section>
    </body>
</html>
