import controller.AquarioController;
import controller.UserController;
import exceptions.ArquivoException;
import exceptions.LoginInvalidoException;
import exceptions.SenhaInvalidaException;
import model.Aquario;
import model.TipoAquario;
import model.User;
import repository.AquarioRepository;
import repository.UserRepository;
import service.AquarioService;
import service.UserService;

import java.util.List;

public class FacadeSingletonController {
    private static FacadeSingletonController instance;

    private UserController userController;
    private AquarioController aquarioController;

    private FacadeSingletonController() {
        try {
            UserRepository userRepo = new UserRepository();
            AquarioRepository aquarioRepo = new AquarioRepository();

            UserService userService = new UserService(userRepo);
            AquarioService aquarioService = new AquarioService(aquarioRepo);

            userController = new UserController(userService);
            aquarioController = new AquarioController(aquarioService);
        } catch (ArquivoException e) {

            throw new RuntimeException("Falha ao inicializar repositórios: " + e.getMessage(), e);
        }
    }

    public static synchronized FacadeSingletonController getInstance() {
        if (instance == null) {
            instance = new FacadeSingletonController();
        }
        return instance;
    }

    public void adicionarUsuario(String nome, String email, String login, String senha)
            throws LoginInvalidoException, SenhaInvalidaException, ArquivoException {
        userController.adicionarUsuario(nome, email, login, senha);
    }

    public List<User> listarUsuarios() {
        return userController.listarUsuarios();
    }

    public void adicionarAquario(String nome, double volume, String tipoStr, int idDono)
            throws ArquivoException, IllegalArgumentException {

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
    }

    public List<Aquario> listarAquarios() {
        return aquarioController.listarAquarios();
    }

    public int getQuantidadeUsuarios() {
        return userController.listarUsuarios().size();
    }

    public int getQuantidadeAquarios() {
        return aquarioController.listarAquarios().size();
    }

    public int getQuantidadeTotalEntidades() {
        return getQuantidadeUsuarios() + getQuantidadeAquarios();
    }
}