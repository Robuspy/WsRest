<%@page import="com.pablosrl.Utils.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="com.pablosrl.Utils.UsuariosDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Usuarios</title>
</head>
<body>
    <h1>Usuarios</h1>
    <table>
        <tr>
            <th>Nombre</th>
        </tr>
        <% 
        UsuariosDAO usuariosDAO = new UsuariosDAO();
        List<Usuario> usuarios = usuariosDAO.obtenerUsuarios();
        for (Usuario usuario : usuarios) { %>
            <tr>
                <td><%= usuario.getNombre() %></td>
            
            </tr>
        <% } %>
    </table>
</body>
</html>
