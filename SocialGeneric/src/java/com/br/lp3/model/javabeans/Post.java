package com.br.lp3.model.javabeans;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 1147106
 */
public class Post implements Serializable {
    
    private long id_post;
    private String texto;
    private int pontos;
    private long id_usuario;  
    private Date data;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    public long getId_post() {
        return id_post;
    }

    public void setId_post(long id_post) {
        this.id_post = id_post;
    }

    public long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }
 

    public Post() {
    }

    public Post(String texto) {
        this.texto = texto;
        this.pontos = pontos;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getPontos() {
        return pontos;
    }

    @Override
    public String toString() {
        return "Post{" + "id_post=" + id_post + ", texto=" + texto + ", pontos=" + pontos + ", id_usuario=" + id_usuario + '}';
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
    public void upvote(){
        pontos++;
    }
    public void downvote(){
        pontos--;
    }

}
