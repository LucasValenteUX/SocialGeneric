<%@page import="com.br.lp2.model.dao.UsuarioDAO"%>
<%@page import="com.br.lp2.model.javabeans.Post"%>
<%@page import="com.br.lp2.model.dao.PostDAO"%>

<p class="titleIndex">Posts mais votados:</p>
<c:forEach var="p" items="${postsUsuario}">
    <section class="card">
        <p class="card-title">${p.texto} </p>
        <p class="textCard3"><i class="material-icons">equalizer</i> ${p.pontos} pontos</p>
        <p class="textCard2">Postado por ${usuariodao.readById(p.id_usuario).getNome_usuario()} em ${postdao.readById(p.id_post).getData()}</p>
        <p class="textCard2">Logue-se para votar</p>   
    </section>
    <br>
</c:forEach>
