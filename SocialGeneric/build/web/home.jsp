<%-- 
    Document   : home
    Created on : 26/04/2016, 09:22:14
    Author     : 1147106
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=iso-8859-1" %>
<!DOCTYPE html>
<html>
   <%@include file="head.jsp" %>  
    <body>        
        <%@include file="topmenu.jspf" %>     
        <section class="container">
            <c:choose>    
                <c:when test="${usuario==null}">  
                    <b><p>Logue-se!</p></b>
                </c:when>
                <c:otherwise>
                    <div class="formHome">
                        <h1 class="titleHome1">Bem vindo ${usuario.nome_usuario}</h1>
                        <p class="titleHome2">Escreva um post:</p>              
                        <form  action="Controller" method="POST">
                            <textarea name="texto" placeholder="No que está pensado?" required="required" COLS=40 ROWS=6></textarea>   
                            <input type="hidden" name="command" value="usuario.postar"/>
                            <a href="home.jsp"><input type="submit" value="Postar"/></a>
                        </form>
                        <c:choose>    
                            <c:when test="${usuario.adm==true}">      
                                <%--PAINEL ADM--%>
                                <b><p>Painel do Administrador</p></b>
                                <%--Excluir Usuario por nome (e seus posts juntos)--%>
                                <p>Apagar Usuário:</p>
                                <form action="Controller" method="POST">
                                    <input type="text" name="username" placeholder="nome de usuário" required="required"/>
                                    <input type="hidden" name="command" value="usuario.admApagarUsuario"/>
                                    <input type="submit" value="Excluir Usuário"/>
                                </form>
                                <p>Apagar Post</p>
                                <%--Excluir Post por ID--%>
                                <form action="Controller" method="POST">
                                    <input type="text" name="id_post" placeholder="id do post" required="required"/>
                                    <input type="hidden" name="command" value="usuario.admApagarPost"/>
                                    <input type="submit" value="Excluir Post"/>
                                </form>
                            </div>
                        </c:when>
                    </c:choose> 
                </c:otherwise>
            </c:choose>            
        </section>

    </body>
</html>
