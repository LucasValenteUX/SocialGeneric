package com.br.lp3.model.javabeans;

import java.io.Serializable;

public class Userinfo implements Serializable {

    private String email;
    private String nome;
    private String anoNasc;
    private String pais;
    private long id_usuario;
    private long id_userinfo;


    public Userinfo() {
    }

    public String getEmail() {
        return email;
    }

    public long getId_userinfo() {
        return id_userinfo;
    }

    public void setId_userinfo(long id_userinfo) {
        this.id_userinfo = id_userinfo;
    }

    @Override
    public String toString() {
        return "Userinfo{" + "email=" + email + ", nome=" + nome + ", anoNasct=" + anoNasc + ", pais=" + pais + ", id_usuario=" + id_usuario + '}';
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAnoNasc() {
        return anoNasc;
    }

    public void setAnoNasc(String anoNasc) {
        this.anoNasc = anoNasc;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }


}
