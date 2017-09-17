package com.br.lp3.model.dao;

import com.br.lp3.model.SingletonConnection;
import com.br.lp3.model.javabeans.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO implements GenericDAO<Usuario> {

    private Connection connection;

    public UsuarioDAO() {
        //PASSO 1 - Estabelecer a conexão
        connection = SingletonConnection.getInstance().getConnection();
    }

    @Override
    public boolean create(Usuario e) {
        boolean resposta = false;
        PreparedStatement pst;
        try {
            //PASSO 2 - Criar o Statement
            String sql = "INSERT INTO usuario(nome_usuario,senha) VALUES(?,?)";
            pst = connection.prepareStatement(sql);
            pst.setString(1, e.getNome_usuario());
            pst.setString(2, e.getSenha());

            //PASSO 3 - executar a consulta
            int resultado = pst.executeUpdate();

            //PASSO 4 - tratar o resultado
            if (resultado > 0) {
                resposta = true;
            }

            //PASSO 5 - Fechar tudo
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resposta;
    }

    @Override
    public List<Usuario> read() {
        List<Usuario> usuarios = new ArrayList<>();

        try {
            //PASSO 2
            String sql = "SELECT * FROM usuario";
            PreparedStatement pst = connection.prepareStatement(sql);

            //PASSO 3
            ResultSet rs = pst.executeQuery();

            //PASSO 4
            while (rs != null && rs.next()) {
                Usuario u = new Usuario();
                u.setId_usuario(rs.getLong("id_usuario"));
                u.setNome_usuario(rs.getString("nome_usuario"));
                u.setSenha(rs.getString("senha"));
                usuarios.add(u);
            }

            //PASSO 5
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usuarios;
    }

    @Override
    public Usuario readById(long id) {
        Usuario u = null;
        try {
            //PASSO 2
            String sql = "SELECT * FROM usuario WHERE id_usuario=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setLong(1, id);

            //PASSO 3
            ResultSet rs = pst.executeQuery();

            //PASSO 4
            while (rs != null && rs.next()) {
                u = new Usuario();
                u.setNome_usuario(rs.getString("nome_usuario"));
                u.setSenha(rs.getString("senha"));
                u.setId_usuario(id);
            }

            //PASSO 5
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public Usuario readByName(String nome_usuario) {
        Usuario u = null;
        try {
            String sql = "SELECT * FROM usuario WHERE nome_usuario=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, nome_usuario);
            ResultSet rs = pst.executeQuery();
            while (rs != null && rs.next()) {
                u = new Usuario(nome_usuario,
                        rs.getString("senha"));
                u.setId_usuario(rs.getLong("id_usuario"));
                u.setAdm(rs.getBoolean("adm"));
            }
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    @Override
    public boolean update(Usuario e) {
        boolean resposta = false;
        try {
            //PASSO 2
            String sql = "UPDATE usuario SET "
                    + "nome_usuario=?, senha=? WHERE id_usuario=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, e.getNome_usuario());
            pst.setString(2, e.getSenha());
            pst.setLong(3, e.getId_usuario());

            //PASSO 3 
            int resultado = pst.executeUpdate();

            //PASSO 4
            if (resultado > 0) {
                resposta = true;
            }

            //PASSO 5
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resposta;
    }

    @Override
    public boolean delete(Usuario e) {
        boolean resposta = false;
        try {
            //PASSO 2
            String sql = "DELETE FROM usuario WHERE id_usuario=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setLong(1, e.getId_usuario());

            //PASSO 3
            int resultado = pst.executeUpdate();

            //PASSO 4
            if (resultado > 0) {
                resposta = true;
            }

            //PASSO 5
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resposta;
    }

}
