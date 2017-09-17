<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.br.lp2.model.dao.UsuarioDAO"%>
<%@page import="com.br.lp2.model.javabeans.Post"%>
<%@page import="com.br.lp2.model.dao.PostDAO"%>

    <c:choose>    
        <c:when test="${primeiraVez==null}">
            <jsp:forward page="Controller">  
                <jsp:param name="command" value="auto.send"/>
            </jsp:forward>
        </c:when>
    </c:choose>
<section class="right">
        <p class="titleIndex">Último Post:</p>
        <div class="card">
            <p class="card-title">${ultimoTexto}</p>
            <p class="textCard3"><i class="material-icons">equalizer</i> ${ultimoPontos} pontos</p>
            <p class="textCard2">Postado por ${ultimoPostador} em ${ultimoData}</p>
        </div>
    </section>    
    <section class="left">        
        <c:forEach var="p" items="${postsUsuario}">
            <br>
            <hr>            
            <section class="card">
                <p class="card-title">${p.texto} </p>
                <p class="textCard3"><i class="material-icons">equalizer</i> ${p.pontos} pontos</p>
                <p class="textCard2">Postado por ${usuariodao.readById(p.id_usuario).getNome_usuario()} em ${postdao.readById(p.id_post).getData()}</p>

                <form action='Controller' method='POST'>
                    <input type='hidden' name='command' value='post.upvote'/>
                    <input type='hidden' name='id_post' value='${p.id_post}'/>
                    <button class="btn waves-effect left blue" type='submit' value='BOTÃO UPVOTE'>
                        <i class="material-icons">thumb_up</i><span></span>
                    </button>
                </form>

                <form action='Controller' method='POST'>
                    <input type='hidden' name='command' value='post.downvote'/>
                    <input type='hidden' name='id_post' value='${p.id_post}'/>
                    <button class="btn waves-effect right red" type='submit' value='BOTÃO DOWNVOTE'>
                        <span></span><i class="material-icons right">thumb_down</i>
                    </button>
                </form>
            </section>
          <br>          
    </c:forEach>
    </section>


<%-- Passar id_post para arquivo comentarios.jsp --%>

<%--  <form action='comentarios.jsp' method='POST'>
    <input type='hidden' name='id_post' value='${p.id_post}'/>
    <input type='hidden' name='texto' value='${p.texto}'/>
    <a href="comentarios.jsp}"><input type='submit' value='Comentarios'/></a>
</form>--%>




