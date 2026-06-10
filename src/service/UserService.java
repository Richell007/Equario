package service;

import model.User;
import repository.UserRepository;
import java.util.List;

public class UserService {
    private final UserRepository repositorio;

    public UserService(UserRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void adicionarUsuario(String nome, String email) {
        int id = repositorio.gerarProximoId();
        User novo = new User(id, nome, email);
        repositorio.salvar(novo);
    }

    public List<User> listarTodos() {
        return repositorio.listarTodos();
    }
}