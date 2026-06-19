/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Usuario;
import util.Conexao;

public class UsuarioDAO {

    public Usuario autenticar(String usuario, String senha) {

        Usuario u = null;

        String sql =
        "SELECT * FROM usuarios " +
        "WHERE usuario = ? AND senha = ?";

        try {

            Connection con = Conexao.conectar();

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setString(1, usuario);
            pst.setString(2, senha);

            ResultSet rs = pst.executeQuery();

            if(rs.next()){

                u = new Usuario();

                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setUsuario(rs.getString("usuario"));
                u.setPerfil(rs.getString("perfil"));
            }

        } catch (Exception e) {

            System.out.println(
                    "Erro: " + e.getMessage()
            );
        }

        return u;
    }
}