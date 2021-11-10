/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.clases;

/**
 *
 * @author Miriam Lopez Surco
 */
public class Usuario {
    private int codUsuario;
    private String nomUsuario;
    private String patUsuario;
    private String matUsuario;
    private int cedUsuario;
    

    public Usuario() {
        nomUsuario="";
        patUsuario="";
        matUsuario="";
    
    }
    //get y set

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getPatUsuario() {
        return patUsuario;
    }

    public void setPatUsuario(String patUsuario) {
        this.patUsuario = patUsuario;
    }

    public String getMatUsuario() {
        return matUsuario;
    }

    public void setMatUsuario(String matUsuario) {
        this.matUsuario = matUsuario;
    }

    public int getCedUsuario() {
        return cedUsuario;
    }

    public void setCedUsuario(int cedUsuario) {
        this.cedUsuario = cedUsuario;
    }

   
    
}
