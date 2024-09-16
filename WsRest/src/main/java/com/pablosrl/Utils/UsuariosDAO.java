package com.pablosrl.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO {
    public List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM usuarios order by cod_usuario";

        try (Connection connection = PruebaConexion.getInstance();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
            	//System.out.println(resultSet.getString("cod_usuario"));
                String nombre = resultSet.getString("cod_usuario");
                //String email = resultSet.getString("email");
                // Puedes obtener más columnas de la tabla según tus necesidades

                Usuario usuario = new Usuario(nombre);
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }
}
