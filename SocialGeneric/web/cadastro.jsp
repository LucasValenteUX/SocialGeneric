<%-- 
    Document   : home
    Created on : 26/04/2016, 09:22:14
    Author     : 1147106
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mood</title>
        <link href="css/estiloCadastro.css" rel="stylesheet" type="text/css"/>
     <!--Import Google Icon Font-->
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"> 
        <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/> 
        <link href="css/materialize.css" rel="stylesheet" type="text/css"/>
    </head>
    <body> 
        <%@include file="topmenu.jspf" %>  
        <%-- TELA DE CADASTRO --%>
        <h1 class="titleCadastro">Cadastro</h1>
        <form class="formCadastro" action="Controller" method="POST">                     
            <input type="text" name="nome" placeholder="Digite seu nome" required="required"/>
            <br>
            <br>
            <input type="text" name="email" placeholder="Digite seu email" required="required"/>
            <br>
            <br>
            <input type="date" name="anoNasc" placeholder="DIgite sua data de nascimento" required="required"/>
            <br>
            <br>
            <input type="text" name="pais" placeholder="Digite seu país de origem" required="required"/>
            <br>
            <br>
            <input type="text" name="username" placeholder="Digite seu nome de usuário (login)" required="required"/>
            <br>
            <br>
            <input type="password" name="password" placeholder="Digite seu senha" required="required"/>
            <br>
            <br>
            <input type="hidden" name="command" value="usuario.cadastro"/>
            <br>
            <br>
            <a class ="btn black" href="index.jsp">Voltar</a>
            <button class="btn black right" type="submit" value="CADASTRAR">Cadastrar-se
            </button>
        </form>

    </body>
</html>
