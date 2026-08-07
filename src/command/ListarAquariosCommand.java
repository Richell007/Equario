package command;

import controller.AquarioController;
import model.Aquario;

import java.util.List;

public class ListarAquariosCommand implements Command<List<Aquario>> {

    private final AquarioController aquarioController;

    public ListarAquariosCommand(AquarioController aquarioController) {
        this.aquarioController = aquarioController;
    }

    @Override
    public List<Aquario> execute() {
        return aquarioController.listarAquarios();
    }
}