package service;

import exceptions.ArquivoException;
import java.util.List;
import model.Aquario;
import model.TipoAquario;
import model.User;
import repository.AquarioRepository;

public class AquarioService {
    private final AquarioRepository repositorio;

    public AquarioService(AquarioRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void adicionarAquario(String nome, double volume, TipoAquario tipo, User dono)
            throws ArquivoException {
        validarNome(nome);
        validarVolume(volume);
        validarTipo(tipo);
        validarDono(dono);

        int id = repositorio.gerarProximoId();
        Aquario novo = new Aquario(id, nome, volume, tipo, dono);
        repositorio.salvar(novo);
    }

    private void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do aquário não pode ser vazio.");
        }
        if (nome.length() > 100) {
            throw new IllegalArgumentException("O nome do aquário deve ter no máximo 100 caracteres.");
        }
    }

    private void validarVolume(double volume) {
        if (volume <= 0) {
            throw new IllegalArgumentException("O volume do aquário deve ser positivo.");
        }
    }

    private void validarTipo(TipoAquario tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("O tipo do aquário é obrigatório.");
        }
    }

    private void validarDono(User dono) {
        if (dono == null) {
            throw new IllegalArgumentException("O dono do aquário é obrigatório.");
        }
    }

    public List<Aquario> listarTodos() {
        return repositorio.listarTodos();
    }
}