package command;

import controller.AquarioController;
import model.TipoAquario;

public class AtualizarAquarioCommand implements Command<Void> {

    private final AquarioController aquarioController;
    private final int id;
    private final String nome;
    private final double volume;
    private final String tipoStr;

    public AtualizarAquarioCommand(AquarioController aquarioController, int id,
                                    String nome, double volume, String tipoStr) {
        this.aquarioController = aquarioController;
        this.id = id;
        this.nome = nome;
        this.volume = volume;
        this.tipoStr = tipoStr;
    }

    @Override
    public Void execute() throws Exception {
        TipoAquario tipo;
        try {
            tipo = TipoAquario.valueOf(tipoStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de aquário inválido. Use DOCE, SALOBRO ou MARINHO.");
        }
        aquarioController.atualizarAquario(id, nome, volume, tipo);
        return null;
    }
}