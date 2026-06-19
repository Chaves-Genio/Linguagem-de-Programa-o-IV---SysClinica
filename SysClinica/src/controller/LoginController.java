/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.UsuarioDAO;
import model.Usuario;

public class LoginController {

    private UsuarioDAO usuarioDAO;

    public LoginController() {

        usuarioDAO = new UsuarioDAO();
    }

    public Usuario autenticar(
            String usuario,
            String senha
    ) {

        return usuarioDAO.autenticar(
                usuario,
                senha
        );
    }
}
