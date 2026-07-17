package repository;

import exceptions.ArquivoException;
import java.util.List;
import model.User;

public interface IUserRepository {
    void salvar(User usuario) throws ArquivoException;

    List<User> listarTodos();

    int gerarProximoId();
}