/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Douglas
 */
public class Usuario {

    protected int idUsuario;
    protected String nome;
    protected String senha;
    protected int admin = 0;
   

    public Usuario(int idUsuario, String nome, String senha, int admin) {

        this.idUsuario = idUsuario;
        this.nome = nome;
        this.senha = senha;
        this.admin = admin;
        

    }

    public Usuario(String uname, String pwd) {
        this.idUsuario = 1;
        this.nome = uname;
        this.senha = pwd;
        this.admin = 1;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    

   
}
