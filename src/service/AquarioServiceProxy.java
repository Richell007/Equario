package service;

import exceptions.ArquivoException;
import java.util.List;
import log.Logger;
import model.Aquario;
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

    @Override
    public void atualizarAquario(int id, String nome, double volume, TipoAquario tipo) throws ArquivoException {
        this.registroDeAtividades.info("Auditoria: Iniciando tentativa de atualizar o aquario de ID " + id);
        this.servicoReal.atualizarAquario(id, nome, volume, tipo);
        this.registroDeAtividades.info("Auditoria: Operacao de atualizacao concluida com sucesso.");
    }

    @Override
    public void desfazerUltimaAtualizacaoAquario() throws ArquivoException {
        this.registroDeAtividades.info("Auditoria: Iniciando desfazimento da ultima atualizacao de aquario.");
        this.servicoReal.desfazerUltimaAtualizacaoAquario();
        this.registroDeAtividades.info("Auditoria: Desfazimento concluido com sucesso.");
    }


    @Override
    public List<Aquario> listarTodos() {
        return this.servicoReal.listarTodos();
    }

}