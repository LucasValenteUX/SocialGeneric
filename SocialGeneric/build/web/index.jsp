<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.br.lp2.model.dao.UsuarioDAO"%>
<%@page import="com.br.lp2.model.javabeans.Post"%>
<%@page import="com.br.lp2.model.dao.PostDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
    <%@include file="head.jsp" %>       
    <body>       
       
        <%@include file="topmenu.jspf" %>        
        
        <section class="welcome">            
                <div class="card hoverable big">
                  <div class="card-image">
                    <img src="img/capa1.jpg">                    
                  </div>                               
                </div>            
        </section>
        <section class="container"> 
            <h1 class="text-darken-1 center-align">MooD FeeD</h1> 
            <c:choose>    
                <c:when test="${usuario==null}"> 
                    <c:choose>    
                        <c:when test="${primeiraVez==null}">
                            <jsp:forward page="Controller">  
                                <jsp:param name="command" value="auto.send"/>
                            </jsp:forward>
                        </c:when>
                    </c:choose>
                    <%-- POSTS MODO VISITANTE --%>                  
                    <%@include file="mostrarPostsVisitante.jsp" %>
                </c:when>
                <c:otherwise>
                    <%--POSTS MODO LOGADO--%>   
                    <%@include file="mostrarPostsLogado.jsp" %>
                </c:otherwise>
            </c:choose> 
        </section>
    </body>
</html>


