package service;

import exceptions.ArquivoException;
import log.Logger;
import model.TipoAquario;
import model.User;

public class AquarioServiceProxy implements IAquarioService {
    private final IAquarioService servicoReal;
    private final Logger registroDeAtividades;

    public AquarioServiceProxy(IAquarioService servicoReal, Logger registroDeAtividades) {
        this.servicoReal = servicoReal;
        this.registroDeAtividades = registroDeAtividades;
    }

    @Override
    public void adicionarAquario(String nome, double volume, TipoAquario tipo, User dono) throws ArquivoException {
        this.registroDeAtividades.info("Auditoria: Iniciando tentativa de salvar o aquario nomeado " + nome);
        
        this.servicoReal.adicionarAquario(nome, volume, tipo, dono);
        
        this.registroDeAtividades.info("Auditoria: Operacao de salvamento concluida com sucesso.");
    }
}