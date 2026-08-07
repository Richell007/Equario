package command;

import controller.UserController;

public class AdicionarUsuarioCommand implements Command<Void> {

    private final UserController userController;
    private final String nome;
    private final String email;
    private final String login;
    private final String senha;

    public AdicionarUsuarioCommand(UserController userController, String nome, String email,
                                    String login, String senha) {
        this.userController = userController;
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
    }

    @Override
    public Void execute() throws Exception {
        userController.adicionarUsuario(nome, email, login, senha);
        return null;
    }
}