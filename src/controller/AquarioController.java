package controller;

import exceptions.ArquivoException;
import java.util.List;
import model.Aquario;
import model.TipoAquario;
import model.User;
import service.AquarioService;

public class AquarioController {
    private final AquarioService service;

    public AquarioController(AquarioService service) {
        this.service = service;
    }

    public void adicionarAquario(String nome, double volume, TipoAquario tipo, User dono)
            throws ArquivoException {
        service.adicionarAquario(nome, volume, tipo, dono);
    }

    public List<Aquario> listarAquarios() {
        return service.listarTodos();
    }
}