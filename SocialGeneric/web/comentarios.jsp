<%-- 
    Document   : comentarios
    Created on : 29/05/2016, 13:44:23
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Comentarios:</h>
        <form action='Controller' method='POST'>
            <input type='hidden' name='textoPost' value='<%=request.getParameter("texto")%>'/>
            <input type='hidden' name='id_post' value='<%=request.getParameter("id_post")%>'/>
            <textarea name="texto" placeholder="Comente!" required="required" COLS=40 ROWS=6></textarea>   
            <input type='hidden' name='command' value='usuario.comentar'/>
            <input type='submit' value='Comentar'/>
        </form>
</body>
</html>
