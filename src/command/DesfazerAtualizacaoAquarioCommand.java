package command;

import controller.AquarioController;

public class DesfazerAtualizacaoAquarioCommand implements Command<Void> {

    private final AquarioController aquarioController;

    public DesfazerAtualizacaoAquarioCommand(AquarioController aquarioController) {
        this.aquarioController = aquarioController;
    }

    @Override
    public Void execute() throws Exception {
        aquarioController.desfazerUltimaAtualizacaoAquario();
        return null;
    }
}