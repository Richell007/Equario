package service;

import exceptions.ArquivoException;
import model.TipoAquario;
import model.User;

public interface IAquarioService {
    void adicionarAquario(String nome, double volume, TipoAquario tipo, User dono) throws ArquivoException;
}