package repository;

import exceptions.ArquivoException;
import model.User;

import java.util.List;

public interface IUserRepository {
    void salvar(User usuario) throws ArquivoException;

    List<User> listarTodos();

    int gerarProximoId();
}