package command;

import controller.AquarioController;
import controller.UserController;
import model.TipoAquario;
import model.User;

public class AdicionarAquarioCommand implements Command<Void> {

    private final UserController userController;
    private final AquarioController aquarioController;
    private final String nome;
    private final double volume;
    private final String tipoStr;
    private final int idDono;

    public AdicionarAquarioCommand(UserController userController, AquarioController aquarioController,
                                    String nome, double volume, String tipoStr, int idDono) {
        this.userController = userController;
        this.aquarioController = aquarioController;
        this.nome = nome;
        this.volume = volume;
        this.tipoStr = tipoStr;
        this.idDono = idDono;
    }

    @Override
    public Void execute() throws Exception {
        TipoAquario tipo;
        try {
            tipo = TipoAquario.valueOf(tipoStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de aquário inválido. Use DOCE, SALOBRO ou MARINHO.");
        }

        User dono = userController.listarUsuarios().stream()
                .filter(u -> u.getId() == idDono)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Usuário com ID " + idDono + " não encontrado."));

        aquarioController.adicionarAquario(nome, volume, tipo, dono);
        return null;
    }
}