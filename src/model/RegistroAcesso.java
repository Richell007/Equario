package model;

import java.time.LocalDateTime;

public class RegistroAcesso {
    private final int usuarioId;
    private final String nomeUsuario;
    private final LocalDateTime dataHora;

    public RegistroAcesso(int usuarioId, String nomeUsuario, LocalDateTime dataHora) {
        this.usuarioId = usuarioId;
        this.nomeUsuario = nomeUsuario;
        this.dataHora = dataHora;
    }

    public int getUsuarioId() { return usuarioId; }
    public String getNomeUsuario() { return nomeUsuario; }
    public LocalDateTime getDataHora() { return dataHora; }
}