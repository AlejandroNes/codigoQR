/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bean;

import com.test.clases.Recursos;
import com.test.conexion.VariablesConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PreDestroy;

/**
 *
 * @author Miriam Lopez Surco
 */
public class RolRecursoBean {

    private Connection connection;
    private VariablesConexion variable;

    public RolRecursoBean() throws SQLException {
        variable = new VariablesConexion();
        variable.inicioConexion();
        connection = variable.getConnection();
        System.out.println("iniciando la conexion");
    }

    @PreDestroy
    public void cerrarConexion() {
        variable.cerrarConexion();
    }
    public List<Recursos> listaRecursos(String rol){
        List<Recursos> listaRecursos=new ArrayList<>();
        Recursos recurso=null;
        StringBuilder query=new StringBuilder();
        query.append(" select r.url,r.texto,r.estado ");
        query.append(" from recursos r ");
        query.append(" inner join rol_rec rr on rr.cod_rec=r.cod_rec ");
        query.append(" inner join rol ro on ro.cod_rol=rr.cod_rol ");
        query.append(" where ro.nombre_rol=? ");
        try {
            PreparedStatement st=connection.prepareStatement(query.toString());
            st.setString(1, rol);
            ResultSet resultado=st.executeQuery();
            while(resultado.next()){
                recurso=new Recursos();
                recurso.setUrl(resultado.getString(1));                
                recurso.setNombreEnlace(resultado.getString(2));
                recurso.setEstado(resultado.getString(3));
                listaRecursos.add(recurso);
            }
        } catch (SQLException e) {
            System.out.println("Error de conexion");
            e.printStackTrace();
        }
        return listaRecursos;
    }

}
