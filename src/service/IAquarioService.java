package service;

import exceptions.ArquivoException;
import model.TipoAquario;
import model.User;

public interface IAquarioService {
    void adicionarAquario(String nome, double volume, TipoAquario tipo, User dono) throws ArquivoException;

    void atualizarAquario(int id, String nome, double volume, TipoAquario tipo) throws ArquivoException;

    void desfazerUltimaAtualizacaoAquario() throws ArquivoException;
}