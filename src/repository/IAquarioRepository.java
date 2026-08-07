package repository;

import exceptions.ArquivoException;
import model.Aquario;

import java.util.List;

public interface IAquarioRepository {
    void salvar(Aquario aquario) throws ArquivoException;

    void atualizar(Aquario aquario) throws ArquivoException;

    List<Aquario> listarTodos();

    int gerarProximoId();
}