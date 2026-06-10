package controller;

import service.UserService;
import model.User;
import java.util.List;

public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    public void adicionarUsuario(String nome, String email) {
        service.adicionarUsuario(nome, email);
    }

    public List<User> listarUsuarios() {
        return service.listarTodos();
    }
}