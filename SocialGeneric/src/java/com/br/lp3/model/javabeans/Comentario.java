package com.br.lp3.model.javabeans;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 1147106
 */
public class Comentario implements Serializable {
    
   public long id_comentario;
   public String texto;
   public long id_usuario;
   public long id_post;

    public long getId_comentario() {
        return id_comentario;
    }

    public Comentario() {
    }

    public void setId_comentario(long id_comentario) {
        this.id_comentario = id_comentario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public long getId_post() {
        return id_post;
    }

    public void setId_post(long id_post) {
        this.id_post = id_post;
    }

}
