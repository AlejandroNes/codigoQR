<%-- 
    Document   : reporteProductoProv
    Created on : 05-10-2021, 08:31:57 PM
    Author     : Miriam Lopez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="proveedorBean" scope="session" class="com.test.bean.ProveedorBean"/>
        <form method="POST" action="reporteProductoProveedor">
            <table border="1">
                <thead>
                    <tr>
                        <th colspan="2"> REPORTE DE PRODUCTOS DE UN PROVEEDOR</th>
                        
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Seleccione Provvedor</td>
                        <td>
                            <select name="codProv">
                               <%=proveedorBean.listarProveedorSelect()%>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="Generar Reporte" name="generar" id="generar" />
                        </td>
                        
                    </tr>
                </tbody>
            </table>

        </form>
    </body>
</html>
