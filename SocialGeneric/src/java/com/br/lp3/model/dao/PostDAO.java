package com.br.lp3.model.dao;

import com.br.lp3.model.SingletonConnection;
import com.br.lp3.model.javabeans.Post;
import com.br.lp3.model.javabeans.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostDAO {

    private Connection connection;

    public PostDAO() {
        //PASSO 1 - Estabelecer a conexão
        connection = SingletonConnection.getInstance().getConnection();
    }

    public boolean create(Post e) {
        boolean resposta = false;
        PreparedStatement pst;
        try {
            //PASSO 2 - Criar o Statement
            String sql = "INSERT INTO post(texto,pontos,id_usuario,data) VALUES(?,?,?,?)";
            pst = connection.prepareStatement(sql);
            pst.setString(1, e.getTexto());
            pst.setInt(2, e.getPontos());
            pst.setLong(3, e.getId_usuario());
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            pst.setDate(4, date);

            //PASSO 3 - executar a consulta
            int resultado = pst.executeUpdate();

            //PASSO 4 - tratar o resultado
            if (resultado > 0) {
                resposta = true;
            }

            //PASSO 5 - Fechar tudo
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resposta;
    }

    public List<Post> read() {
        List<Post> posts = new ArrayList<>();

        try {
            //PASSO 2
            String sql = "SELECT * FROM post";
            PreparedStatement pst = connection.prepareStatement(sql);

            //PASSO 3
            ResultSet rs = pst.executeQuery();

            //PASSO 4
            while (rs != null && rs.next()) {
                Post u = new Post();
                u.setTexto(rs.getString("texto"));
                u.setPontos(rs.getInt("pontos"));
                posts.add(u);
            }

            //PASSO 5
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return posts;
    }

    public Post readById(long id) {
        Post u = null;
        try {
            //PASSO 2
            String sql = "SELECT * FROM post WHERE id_post=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setLong(1, id);

            //PASSO 3
            ResultSet rs = pst.executeQuery();

            //PASSO 4
            while (rs != null && rs.next()) {
                u = new Post();
                u.setTexto(rs.getString("texto"));
                u.setPontos(rs.getInt("pontos"));
                u.setId_post(id);
                u.setData(rs.getDate("data"));
            }

            //PASSO 5
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public boolean update(Post e) {
        boolean resposta = false;
        try {
            //PASSO 2
            String sql = "UPDATE post SET "
                    + "texto=?, pontos=? WHERE id_post=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, e.getTexto());
            pst.setInt(2, e.getPontos());
            pst.setLong(3, e.getId_post());

            //PASSO 3 
            int resultado = pst.executeUpdate();

            //PASSO 4
            if (resultado > 0) {
                resposta = true;
            }

            //PASSO 5
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resposta;
    }

    public boolean delete(Post e) {
        boolean resposta = false;
        try {
            //PASSO 2
            String sql = "DELETE FROM post WHERE id_post=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setLong(1, e.getId_post());

            //PASSO 3
            int resultado = pst.executeUpdate();

            //PASSO 4
            if (resultado > 0) {
                resposta = true;
            }

            //PASSO 5
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resposta;
    }

    public List<Post> readByUserId(long id_usuario) {
        List<Post> posts = new ArrayList<>();

        try {
            //PASSO 2
            String sql = "SELECT * FROM post WHERE id_usuario=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setLong(1, id_usuario);

            //PASSO 3
            ResultSet rs = pst.executeQuery();

            //PASSO 4
            while (rs != null && rs.next()) {
                Post u = new Post();
                u.setTexto(rs.getString("texto"));
                //u.setPontos( rs.getInt("pontos"));
                posts.add(u);
            }

            //PASSO 5
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return posts;
    }

    public ArrayList<String> retornaTextos(long id_usuario) {
        ArrayList<String> textos = new ArrayList<>();

        try {
            //PASSO 2
            String sql = "SELECT texto FROM post WHERE id_usuario=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setLong(1, id_usuario);
            //System.out.println(sql+id_usuario);

            //PASSO 3
            ResultSet rs = pst.executeQuery();

            //PASSO 4
            while (rs != null && rs.next()) {
                String u = rs.getString("texto");
                textos.add(u);
                System.out.println(u);
            }
            //PASSO 5
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return textos;
    }

    public List<Post> readAll() {
        List<Post> posts = new ArrayList<>();

        try {
            //PASSO 2
            String sql = "SELECT * FROM post ORDER BY pontos DESC";
            PreparedStatement pst = connection.prepareStatement(sql);
            //PASSO 3
            ResultSet rs = pst.executeQuery();
            //PASSO 4
            while (rs != null && rs.next()) {
                Post u = new Post();
                u.setId_post(rs.getLong("id_post"));
                u.setTexto(rs.getString("texto"));
                u.setId_usuario(rs.getLong("id_usuario"));
                u.setPontos(rs.getInt("pontos"));
                posts.add(u);
            }

            //PASSO 5
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return posts;
    }
    
    public Post readUtimo() {
        List<Post> posts = new ArrayList<>();
        try {
            //PASSO 2
            String sql = "SELECT * FROM post ORDER BY id_post DESC";
            PreparedStatement pst = connection.prepareStatement(sql);

            //PASSO 3
            ResultSet rs = pst.executeQuery();

            //PASSO 4
              while (rs != null && rs.next()) {
                Post u = new Post();
                u.setId_post(rs.getLong("id_post"));
                u.setTexto(rs.getString("texto"));
                u.setId_usuario(rs.getLong("id_usuario"));
                u.setPontos(rs.getInt("pontos"));
                u.setData(rs.getDate("data"));
                posts.add(u);
            }
            //PASSO 5
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return posts.get(0);
    }


}
