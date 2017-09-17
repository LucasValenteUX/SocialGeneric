package com.br.lp3.model.dao;

import com.br.lp3.model.SingletonConnection;
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

public class UserinfoDAO implements GenericDAO<Userinfo> {

    private Connection connection;

    public UserinfoDAO() {
        //PASSO 1 - Estabelecer a conexão
        connection = SingletonConnection.getInstance().getConnection();
    }

    public boolean create(Userinfo e) {
        boolean resposta = false;
        PreparedStatement pst;
        try {
            //PASSO 2 - Criar o Statement
            String sql = "INSERT INTO userinfo(email,nome,pais,id_usuario,anonasc) VALUES(?,?,?,?,?)";
            pst = connection.prepareStatement(sql);
            pst.setString(1, e.getEmail());
            pst.setString(2, e.getNome());
            pst.setString(3, e.getPais());
            pst.setLong(4, e.getId_usuario());
            pst.setString(5, e.getAnoNasc());

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

    public List<Userinfo> read() {
        List<Userinfo> userinfos = new ArrayList<>();

        try {
            //PASSO 2
            String sql = "SELECT * FROM userinfo";
            PreparedStatement pst = connection.prepareStatement(sql);

            //PASSO 3
            ResultSet rs = pst.executeQuery();

            //PASSO 4
            while (rs != null && rs.next()) {
                Userinfo u = new Userinfo();
                u.setEmail(rs.getString("email"));
                u.setNome(rs.getString("nome"));
                u.setAnoNasc(rs.getString("anoNasc"));
                u.setPais(rs.getString("pais"));
                u.setId_usuario(rs.getInt("id_usuario"));
                userinfos.add(u);
            }

            //PASSO 5
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return userinfos;
    }

    public Userinfo readByName(String nome) {
        Userinfo u = null;
        try {
            String sql = "SELECT * FROM userinfo WHERE nome=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, nome);
            ResultSet rs = pst.executeQuery();
            while (rs != null && rs.next()) {
                u = new Userinfo();
                u.setEmail(rs.getString("email"));
                u.setNome(nome);
                u.setAnoNasc(rs.getString("anoNasc"));
                u.setPais(rs.getString("pais"));
                u.setId_usuario(rs.getLong("id_usuario"));
                u.setId_userinfo(rs.getLong("id_userinfo"));
            }
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public boolean update(Userinfo e) {
        boolean resposta = false;
        try {
            //PASSO 2
            String sql = "UPDATE userinfo SET "
                    + "id_usuario=?, email=?, pais=?, anonasc=? WHERE nome=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setLong(1, e.getId_usuario());
            pst.setString(2, e.getEmail());
            pst.setString(3, e.getPais());
            pst.setString(4, e.getAnoNasc());
            pst.setString(5, e.getNome());

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

    public boolean delete(Userinfo e) {
        boolean resposta = false;
        try {
            //PASSO 2
            String sql = "DELETE FROM userinfo WHERE id_usuario=?";
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
            Logger.getLogger(UsuarioDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return resposta;
    }

    public Userinfo readById(long id) {
        Userinfo u = null;
        try {
            //PASSO 2
            String sql = "SELECT * FROM userinfo WHERE id_usuario=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setLong(1, id);

            //PASSO 3
            ResultSet rs = pst.executeQuery();

            //PASSO 4
            while (rs != null && rs.next()) {
                u = new Userinfo();
                u.setEmail(rs.getString("email"));
                u.setNome(rs.getString("nome"));
                u.setAnoNasc(rs.getString("anoNasc"));
                u.setPais(rs.getString("pais"));
                u.setId_usuario(rs.getInt("id_usuario"));
            }

            //PASSO 5
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public boolean updateIDus(Userinfo e) {
        boolean resposta = false;
        try {
            //PASSO 2
            String sql = "UPDATE userinfo SET "
                    + "id_usuario=? WHERE id_userinfo=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setLong(1, e.getId_usuario());
            pst.setLong(2, e.getId_userinfo());

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
