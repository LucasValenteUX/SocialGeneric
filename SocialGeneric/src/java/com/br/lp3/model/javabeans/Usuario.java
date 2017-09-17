package com.br.lp3.model.javabeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 1147106
 */
public class Usuario implements Serializable{

    private long id_usuario;   
    private String nome_usuario;
    private String senha;
    public List<Post> posts = new ArrayList<Post>();
    private boolean adm;

    public boolean isAdm() {
        return adm;
    }

    public void setAdm(boolean adm) {
        this.adm = adm;
    }
    
    public List<Post> getPosts() {
        return posts;
    }
    
    public Usuario() {
    }

    public Usuario(String nome_usuario, String senha) {
        this.nome_usuario = nome_usuario;
        this.senha = senha;
    }

    public long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id_usuario=" + id_usuario + ", nome_usuario=" + nome_usuario + ", senha=" + senha + '}';
    }
    
}
