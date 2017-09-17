/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.controller;

import com.br.lp3.model.dao.PostDAO;
import com.br.lp3.model.dao.UserinfoDAO;
import com.br.lp3.model.dao.UsuarioDAO;
import com.br.lp3.model.dao.ComentarioDAO;
import com.br.lp3.model.javabeans.Comentario;
import com.br.lp3.model.javabeans.Post;
import com.br.lp3.model.javabeans.Userinfo;
import com.br.lp3.model.javabeans.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Long.parseLong;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static java.lang.Long.parseLong;

@WebServlet(name = "UsuarioBusiness", urlPatterns = {"/UsuarioBusiness"})
public class UsuarioBusiness extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //INICIANDO COMANDO E USUARIODAO----------------------------------------
            String command = request.getParameter("command");
            UsuarioDAO usuariodao = new UsuarioDAO();
            PostDAO postdao = new PostDAO();
            ComentarioDAO comentariodao = new ComentarioDAO();
            UserinfoDAO userinfodao = new UserinfoDAO();
            //CADASTRO--------------------------------------------------------------
            if (command.endsWith("cadastro")) {
                response.sendRedirect("index.jsp");
                command.endsWith("login");
                //CRIA USUARIO
                Usuario novoUsuario = new Usuario();
                novoUsuario.setNome_usuario(request.getParameter("username"));
                novoUsuario.setSenha(request.getParameter("password"));
                usuariodao.create(novoUsuario);
                //USERINFO
                Userinfo novoUserinfo = new Userinfo();
                novoUserinfo.setNome(request.getParameter("nome"));
                novoUserinfo.setEmail(request.getParameter("email"));
                novoUserinfo.setAnoNasc(request.getParameter("anoNasc"));
                novoUserinfo.setPais(request.getParameter("pais"));
                novoUserinfo.setId_usuario(usuariodao.readByName(novoUsuario.getNome_usuario()).getId_usuario());
                userinfodao.create(novoUserinfo);
            }
            //LOGIN----------------------------------------------------------------
            if (command.endsWith("login")) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                Usuario usuario = usuariodao.readByName(username);
                if (usuario == null) {
                    request.getSession().setAttribute("erromsg", "Usuário não encontrado :c");
                    response.sendRedirect("erro.jsp");

                } else if (password.equals(usuario.getSenha())) {
                    request.getSession().setAttribute("usuario", usuario);
                    response.sendRedirect("index.jsp");
                } else {
                    request.getSession().setAttribute("erromsg", "You shall not pass! (senha errada)");
                    response.sendRedirect("erro.jsp");
                }
            } else if (command.endsWith("logout")) {
                request.getSession().invalidate();
                response.sendRedirect("index.jsp");
            }

            //CRIAR POSTS------------------------------------------------------------
            if (command.endsWith("postar")) {
                String texto = request.getParameter("texto");
                //CRIAÇÃO DO POST E CONEXÃO POST = USUARIO
                Post novoPost = new Post();
                Usuario usuarioTeste = (Usuario) request.getSession().getAttribute("usuario");
                novoPost.setTexto(request.getParameter("texto"));
                novoPost.setPontos(0);
                novoPost.setId_usuario(usuarioTeste.getId_usuario());
                //ADICIONA NOVO POST NO BANCO DE DADOS
                System.out.println("id_usuario= " + usuarioTeste.getId_usuario());
                postdao.create(novoPost);
                request.getSession().setAttribute("ultimo", postdao.readUtimo().getTexto());
                response.sendRedirect("index.jsp");
                //MOSTRA TODOS OS POSTS DO USUÁRIO LOGADO
                ArrayList<String> textos = postdao.retornaTextos(usuarioTeste.getId_usuario());
                for (String p : textos) {
                    System.out.println(p);
                }
            }
            //PAINEL DE ADMINISTRADOR------------------------------------------------------------
            if (command.endsWith("admApagarUsuario")) {
                String nome = request.getParameter("username");
                if (nome == ((Usuario) request.getSession().getAttribute("usuario")).getNome_usuario()) {
                    //Erro, Tentou se apagar
                    request.getSession().setAttribute("erromsg", "Impossível apagar-se enquanto logado!");
                    response.sendRedirect("erro.jsp");
                } else {
                    //Delete seus posts
                    List<Post> posts = postdao.readByUserId(usuariodao.readByName(nome).getId_usuario());
                    for (Post p : posts) {
                        postdao.delete(p);
                    }
                    //Deleta Usuário
                    usuariodao.delete(usuariodao.readByName(nome));
                    response.sendRedirect("home.jsp");
                }
            }
            if (command.endsWith("admApagarPost")) {
                Long id_post = parseLong(request.getParameter("id_post"));
                //Deleta Post
                postdao.delete(postdao.readById(id_post));
                response.sendRedirect("home.jsp");
            }

            //AUTO REQUEST FROM JSP------------------------------------------------------
            if (command.endsWith("send")) {
                request.getSession().setAttribute("postsUsuario", postdao.readAll());
                request.getSession().setAttribute("usuariodao", usuariodao);
                request.getSession().setAttribute("postdao", postdao);
                request.getSession().setAttribute("ultimoTexto", postdao.readUtimo().getTexto());
                request.getSession().setAttribute("ultimoPontos", postdao.readUtimo().getPontos());
                request.getSession().setAttribute("ultimoPostador", usuariodao.readById(postdao.readUtimo().getId_usuario()).getNome_usuario());
                request.getSession().setAttribute("ultimoData", postdao.readUtimo().getData());
                request.getSession().setAttribute("primeiraVez", 1);

                response.sendRedirect("index.jsp");
            }

            //COMENTARIOS-------------------------------------------------------------------
            if (command.endsWith("comentar")) {
                String texto = request.getParameter("texto");
                System.out.println(texto);
                long id_post = Long.parseLong(request.getParameter("id_post"));
                System.out.println("id:" + id_post);

                Comentario c = new Comentario();
                c.setId_post(id_post);
                Usuario usuarioTeste = (Usuario) request.getSession().getAttribute("usuario");
                c.setId_usuario(usuarioTeste.getId_usuario());
                c.setTexto(texto);

                System.out.println(c.getId_usuario());
                System.out.println(c.getId_post());
                System.out.println(c.getTexto());

                comentariodao.create(c);
                response.sendRedirect("comentarios.jsp");

                request.getSession().setAttribute("id_post", request.getParameter("id_post"));
                request.getSession().setAttribute("texto", request.getParameter("texto"));
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
