package command;

import controller.UserController;
import model.User;

import java.util.List;

public class ListarUsuariosCommand implements Command<List<User>> {

    private final UserController userController;

    public ListarUsuariosCommand(UserController userController) {
        this.userController = userController;
    }

    @Override
    public List<User> execute() {
        return userController.listarUsuarios();
    }
}