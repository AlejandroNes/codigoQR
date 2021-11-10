/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.clases;

import java.io.Serializable;

/**
 *
 * @author Miriam Lopez Surco
 */
public class Recursos implements Serializable{
    //atributos
    private String url;
    private String nombreEnlace;
    private String estado;
    //constructor

    public Recursos() {
        url="";        
        nombreEnlace="";
        estado="";
    }
    //getter y setter

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }    

    public String getNombreEnlace() {
        return nombreEnlace;
    }

    public void setNombreEnlace(String nombreEnlace) {
        this.nombreEnlace = nombreEnlace;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
