package com.br.lp3.model.dao;

import com.br.lp3.model.SingletonConnection;
import com.br.lp3.model.javabeans.Comentario;
import com.br.lp3.model.javabeans.Userinfo;
import com.br.lp3.model.javabeans.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComentarioDAO {

    private Connection connection;

    public ComentarioDAO() {
        //PASSO 1 - Estabelecer a conexão
        connection = SingletonConnection.getInstance().getConnection();
    }

    public boolean create(Comentario e) {
        boolean resposta = false;
        PreparedStatement pst;
        try {
            //PASSO 2 - Criar o Statement
            String sql = "INSERT INTO comentario(texto,id_usuario,id_post) VALUES(?,?,?)";
            pst = connection.prepareStatement(sql);
            pst.setString(1, e.getTexto());
            pst.setLong(2, e.getId_usuario());
            pst.setLong(3, e.getId_post());
            //PASSO 3 - executar a consulta
            int resultado = pst.executeUpdate();

            //PASSO 4 - tratar o resultado
            if (resultado > 0) {
                resposta = true;
            }
            System.out.println("CRIOU");
            //PASSO 5 - Fechar tudo
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resposta;
    }

    public List<Comentario> read() {
        List<Comentario> comentarios = new ArrayList<>();

        try {
            //PASSO 2
            String sql = "SELECT * FROM comentario";
            PreparedStatement pst = connection.prepareStatement(sql);

            //PASSO 3
            ResultSet rs = pst.executeQuery();

            //PASSO 4
            while (rs != null && rs.next()) {
                Comentario u = new Comentario();

                u.setId_comentario(rs.getLong("id_comentario"));
                u.setTexto(rs.getString("texto"));
                u.setId_usuario(rs.getLong("id_usuario"));
                u.setId_post(rs.getLong("id_post"));

                comentarios.add(u);
            }

            //PASSO 5
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return comentarios;
    }

    public List<Comentario> readAllById_Post(Long id_post) {
        List<Comentario> comentarios = new ArrayList<>();
        try {
            //PASSO 2
            String sql = "SELECT * FROM comentario WHERE id_post=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setLong(1, id_post);
            //PASSO 3
            ResultSet rs = pst.executeQuery();
            //PASSO 4
            while (rs != null && rs.next()) {
                Comentario u = new Comentario();
                u.setId_comentario(rs.getLong("id_comentario"));
                u.setTexto(rs.getString("texto"));
                u.setId_usuario(rs.getLong("id_usuario"));
                u.setId_post(rs.getLong("id_post"));

                comentarios.add(u);
            }

            //PASSO 5
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return comentarios;
    }

    public boolean update(Comentario e) {
        boolean resposta = false;
        try {
            //PASSO 2
            String sql = "UPDATE comentario SET "
                    + "texto=?, id_usuario=?, id_post=? WHERE id_comentario=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, e.getTexto());
            pst.setLong(2, e.getId_usuario());
            pst.setLong(3, e.getId_post());
            pst.setLong(4, e.getId_comentario());

            //PASSO 3 
            int resultado = pst.executeUpdate();

            //PASSO 4
            if (resultado > 0) {
                resposta = true;
            }

            //PASSO 5
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return resposta;
    }

    public boolean delete(Comentario e) {
        boolean resposta = false;
        try {
            //PASSO 2
            String sql = "DELETE FROM comentario WHERE id_comentario=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setLong(1, e.getId_comentario());

            //PASSO 3
            int resultado = pst.executeUpdate();

            //PASSO 4
            if (resultado > 0) {
                resposta = true;
            }

            //PASSO 5
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return resposta;
    }

}
