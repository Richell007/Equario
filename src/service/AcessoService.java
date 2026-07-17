package service;

import log.Logger;
import model.RegistroAcesso;
import model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AcessoService {
    private final List<RegistroAcesso> registros = new ArrayList<>();
    private final Logger logger;

    public AcessoService(Logger logger) {
        this.logger = logger;
    }

    public void registrarAcesso(User usuario) {
        RegistroAcesso registro = new RegistroAcesso(usuario.getId(), usuario.getNome(), LocalDateTime.now());
        registros.add(registro);
        logger.info("Acesso registrado: usuário '" + usuario.getNome() + "' (ID " + usuario.getId() + ")");
    }

    public List<RegistroAcesso> listarRegistros() {
        return Collections.unmodifiableList(registros);
    }
}