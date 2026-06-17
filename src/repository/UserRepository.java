package repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.User;

public class UserRepository {
    private final List<User> usuarios = new ArrayList<>();
    private int proximoId = 1;

    public void salvar(User usuario) {
        usuarios.add(usuario);
        proximoId++;
    }

    public List<User> listarTodos() {
        return Collections.unmodifiableList(usuarios);
    }

    public int gerarProximoId() {
        return proximoId;
    }
}