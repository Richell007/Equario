package controller;

import exceptions.LoginInvalidoException;
import exceptions.SenhaInvalidaException;
import java.util.List;
import model.User;
import service.UserService;

public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    public void adicionarUsuario(String nome, String email, String login, String senha) throws LoginInvalidoException, SenhaInvalidaException {
        service.adicionarUsuario(nome, email, login, senha);
    }

    public List<User> listarUsuarios() {
        return service.listarTodos();
    }
}