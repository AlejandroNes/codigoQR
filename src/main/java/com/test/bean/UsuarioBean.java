/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bean;

import com.test.clases.Usuario;
import com.test.clases.Encripted256;
import com.test.conexion.VariablesConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author miriam Lopez Surco
 */
public class UsuarioBean {

    private Connection connection;
    private VariablesConexion variable;
    //objeto para registrar un usuario
    private PreparedStatement insertUsuario;
    
   
    private Usuario buscarUsuario;

    public UsuarioBean() throws SQLException {
        variable = new VariablesConexion();
        variable.inicioConexion();
        connection = variable.getConnection();
        System.out.println("Iniciando conexion");
    }

    @PreDestroy
    public void cerrarConexion() {
        variable.cerrarConexion();
        System.out.println("Cerrando conexion");
    }

    public String insertarUsuario(HttpServletRequest request) {
        //objeto para llamar al metodo de encriptacion
        Encripted256 obj = new Encripted256();
        String salida = "";
        if (request == null) {
            return "";
        }
        if (connection != null) {
            try {
                StringBuilder query = new StringBuilder();
                query.append(" insert into usuario ");
                query.append(" values(nextval('sec_usu'),?,?,?,?,?,?,?)");
                if (insertUsuario == null) {
                    insertUsuario = connection.prepareStatement(query.toString());
                }
                //obteniendo parametros del jsp
                String nombre = request.getParameter("nombre");
                String paterno = request.getParameter("paterno");
                String materno = request.getParameter("materno");
                int ce = Integer.parseInt(request.getParameter("cedula"));                
                int codRol = Integer.parseInt(request.getParameter("codRol"));
                //generando codigo
                String codigo = paterno.charAt(0) + materno.charAt(0) + nombre.charAt(0)+ String.valueOf(ce);
                System.out.println("codigo" + codigo);
                //pasar los parametros al objeto insertUsuario
                insertUsuario.setInt(1, codRol);
                insertUsuario.setString(2, paterno);
                insertUsuario.setString(3, materno);
                insertUsuario.setString(4, nombre);
                insertUsuario.setInt(5, ce);
                insertUsuario.setString(6, codigo);
                
                //encriptacion del password
                String ced = String.valueOf(ce);
                String pwd = obj.sha256(ced);
                insertUsuario.setString(7, pwd);
                //ejecutando consulta
                int registros = insertUsuario.executeUpdate();
                salida = "Insercion correcta";

            } catch (SQLException e) {
                System.out.println("Error al ejecutar el insert");
                e.printStackTrace();
                String mensajeError = e.getMessage();
                if (mensajeError.contains("llave duplicada")) {
                    salida = "Error al ejecutar el insert, llave duplicada";
                } else {
                    salida = "Error al ejecutar el insert" + e.getMessage();
                }
            }
        }
        return salida;
    }

    public String listarUsuario() {
        StringBuilder salidaTabla = new StringBuilder();
        StringBuilder query = new StringBuilder();
        query.append(" select u.cod_usu,u.nombres,u.ap_pat,u.ap_mat,r.nombre_rol ");
        query.append(" from usuario u ");
        query.append(" inner join rol r on r.cod_rol=u.cod_rol ");
        try {
            Statement st = connection.createStatement();
            ResultSet resultado = st.executeQuery(query.toString());
            while (resultado.next()) {
                salidaTabla.append("<tr>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getInt(1));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(2));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(3));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(4));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(5));
                salidaTabla.append("</td>");
                salidaTabla.append("</tr>");
            }
        } catch (SQLException e) {
            System.out.println("error de conexion");
            e.printStackTrace();
        }
        return salidaTabla.toString();

    }
    
    
 
    public Usuario buscarUsuarioRol(String codigoUsuario){
        StringBuilder query = new StringBuilder();
        query.append(" select u.cod_usu, u.nombres, u.ap_pat, u.ap_mat ");
        query.append(" from usuario u ");
        query.append(" where u.codigo= ? ");
        try {
            PreparedStatement st = connection.prepareStatement(query.toString());
            st.setString(1, codigoUsuario);
            ResultSet resultado = st.executeQuery();
            // verificando que exista algun registro
            if (resultado.next()) {
                buscarUsuario=new Usuario();
                //cargando los datos de la consulta en el objeto a buscarUsuario
                buscarUsuario.setCodUsuario(resultado.getInt(1));
                buscarUsuario.setNomUsuario(resultado.getString(2));
                buscarUsuario.setPatUsuario(resultado.getString(3));
                buscarUsuario.setMatUsuario(resultado.getString(4));
                              

            } else {
                System.out.println("No existe el Usuario");
            }
        } catch (SQLException e) {
            System.out.println("Error de conexion");
            e.printStackTrace();
        }
        return buscarUsuario; 
    }
   
  
    


}
