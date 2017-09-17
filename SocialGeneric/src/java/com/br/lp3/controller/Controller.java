package com.br.lp3.controller;

import com.br.lp3.model.dao.PostDAO;
import com.br.lp3.model.dao.UsuarioDAO;
import com.br.lp3.model.javabeans.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 1147106
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            PostDAO postdao = new PostDAO();
            //Comando que veio do formulário
            String command = request.getParameter("command");
            System.out.println(command);

            if (command.startsWith("usuario")) {
                //Todo comando para usuário
                RequestDispatcher rd = request.getRequestDispatcher("UsuarioBusiness");
                rd.forward(request, response);
            } else if (command.startsWith("post")) {
                //Todo comando para post
                RequestDispatcher rd = request.getRequestDispatcher("PostBusiness");
                rd.forward(request, response);
            } else if (command.startsWith("auto")) {
                //ir para o controller automaticamente
                RequestDispatcher rd = request.getRequestDispatcher("UsuarioBusiness");
                rd.forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
