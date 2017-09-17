/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.controller;

import com.br.lp3.model.dao.PostDAO;
import com.br.lp3.model.dao.UsuarioDAO;
import com.br.lp3.model.javabeans.Post;
import com.br.lp3.model.javabeans.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PostBusiness", urlPatterns = {"/PostBusiness"})
public class PostBusiness extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String command = request.getParameter("command");
            PostDAO postdao = new PostDAO();
            UsuarioDAO usuariodao = new UsuarioDAO();
           
            request.getSession().setAttribute("postsUsuario", postdao.readAll());
            request.getSession().setAttribute("usuariodao", usuariodao);
            request.getSession().setAttribute("postdao", postdao);
            
            request.getSession().setAttribute("ultimo", postdao.readUtimo().getTexto());
            
            if (command.endsWith("upvote")) {
                long id_post = Long.parseLong(request.getParameter("id_post"));
                Post p = postdao.readById(id_post);
                if (p != null) {
                    p.upvote();
                    postdao.update(p);
                    response.sendRedirect("index.jsp");
                }
            }
            if (command.endsWith("downvote")) {
                long id_post = Long.parseLong(request.getParameter("id_post"));
                Post p = postdao.readById(id_post);
                if (p != null) {
                    p.downvote();
                    postdao.update(p);
                    response.sendRedirect("index.jsp");
                }
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
