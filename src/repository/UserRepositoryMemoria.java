package repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.User;

public class UserRepositoryMemoria implements IUserRepository {
    private final List<User> usuarios = new ArrayList<>();
    private int proximoId = 1;

    @Override
    public void salvar(User usuario) {
        usuarios.add(usuario);
        proximoId++;
    }

    @Override
    public List<User> listarTodos() {
        return Collections.unmodifiableList(usuarios);
    }

    @Override
    public int gerarProximoId() {
        return proximoId;
    }
}