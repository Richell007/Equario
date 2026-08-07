package repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Aquario;

public class AquarioRepositoryMemoria implements IAquarioRepository {
    private final List<Aquario> aquarios = new ArrayList<>();
    private int proximoId = 1;

    @Override
    public void salvar(Aquario aquario) {
        aquarios.add(aquario);
        proximoId++;
    }

    @Override
    public void atualizar(Aquario aquario) {
        for (int i = 0; i < aquarios.size(); i++) {
            if (aquarios.get(i).getId() == aquario.getId()) {
                aquarios.set(i, aquario);
                return;
            }
        }
        throw new IllegalArgumentException("Aquário com ID " + aquario.getId() + " não encontrado.");
    }

    @Override
    public List<Aquario> listarTodos() {
        return Collections.unmodifiableList(aquarios);
    }

    @Override
    public int gerarProximoId() {
        return proximoId;
    }
}