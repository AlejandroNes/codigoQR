/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bean;

import com.test.conexion.VariablesConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author kelvi
 */
public class cliente {
        //atributos
    private VariablesConexion variable;
    private Connection connection;
    // objeto que permite realizar el insert de producto
    private PreparedStatement insert_cliente;

    //constructores
    public cliente() throws SQLException {
        variable = new VariablesConexion();
        variable.inicioConexion();
        connection = variable.getConnection();
        System.out.println("Iniciando la conexion");
    }

    @PreDestroy
    public void cerrarConexion() {
        variable.cerrarConexion();
    }

    public String registrar_cliente(HttpServletRequest request) {
        String salida = "";
        if (request == null) {
            return "";
        }
        if (connection != null) {
            try {
                StringBuilder query = new StringBuilder();
                query.append(" insert into cliente (nombre,nit) ");
                query.append("values (?,?) ");
                if (insert_cliente == null) {
                    insert_cliente = connection.prepareStatement(query.toString());
                }
                //rescatando los datos del formulario
                String nombre = request.getParameter("nombreTitular");
                int nit = Integer.parseInt(request.getParameter("nit"));

                //pasando los datos a la consulta
                
                insert_cliente.setString(1, nombre);
                insert_cliente.setInt(2, nit);   
//ejecutando la consulta
                int registro = insert_cliente.executeUpdate();
                if (registro == 1) {
                    salida = "REGISTRADO!!!!";
                } else {
                    salida = "Error al registrar";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                String mensajeError = e.getMessage();
                if (mensajeError.contains("llave duplicada")) {
                    salida = "Error al ejecutar el insert, llave duplicada";
                } else {
                    salida = "Error en la ejecucion: " + mensajeError;
                }
            }
        }
        return salida;
    }
}
