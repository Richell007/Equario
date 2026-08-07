import command.AdicionarAquarioCommand;
import command.AdicionarUsuarioCommand;
import command.Command;
import command.ListarAquariosCommand;
import command.ListarUsuariosCommand;
import controller.AquarioController;
import controller.UserController;
import exceptions.ArquivoException;
import exceptions.LoginInvalidoException;
import exceptions.SenhaInvalidaException;
import model.Aquario;
import model.User;
import repository.IAquarioRepository;
import repository.IUserRepository;
import repository.RepositoryFactory;
import repository.TipoPersistencia;
import service.AquarioService;
import service.UserService;

import java.util.List;

public class FacadeSingletonController {
    private static FacadeSingletonController instance;

    private UserController userController;
    private AquarioController aquarioController;

    private FacadeSingletonController(TipoPersistencia tipoPersistencia) {
        try {
            IUserRepository userRepo = RepositoryFactory.criarUserRepository(tipoPersistencia);
            IAquarioRepository aquarioRepo = RepositoryFactory.criarAquarioRepository(tipoPersistencia);

            UserService userService = new UserService(userRepo);
            AquarioService aquarioService = new AquarioService(aquarioRepo);

            userController = new UserController(userService);
            aquarioController = new AquarioController(aquarioService);
        } catch (ArquivoException e) {
            throw new RuntimeException("Falha ao inicializar repositórios: " + e.getMessage(), e);
        }
    }

    public static synchronized FacadeSingletonController getInstance(TipoPersistencia tipoPersistencia) {
        if (instance == null) {
            instance = new FacadeSingletonController(tipoPersistencia);
        }
        return instance;
    }

    public static synchronized FacadeSingletonController getInstance() {
        if (instance == null) {
            throw new IllegalStateException(
                "Facade ainda não foi inicializada. Use getInstance(TipoPersistencia) na primeira chamada.");
        }
        return instance;
    }

    /**
     * Invoker: executa um Command e propaga apenas as exceções de negócio
     * conhecidas pela fachada; qualquer outra falha inesperada é encapsulada.
     */
    private <T> T executar(Command<T> command)
            throws LoginInvalidoException, SenhaInvalidaException, ArquivoException {
        try {
            return command.execute();
        } catch (LoginInvalidoException | SenhaInvalidaException | ArquivoException | IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao executar comando: " + e.getMessage(), e);
        }
    }

    public void adicionarUsuario(String nome, String email, String login, String senha)
            throws LoginInvalidoException, SenhaInvalidaException, ArquivoException {
        Command<Void> comando = new AdicionarUsuarioCommand(userController, nome, email, login, senha);
        executar(comando);
    }

    public List<User> listarUsuarios() {
        Command<List<User>> comando = new ListarUsuariosCommand(userController);
        try {
            return executar(comando);
        } catch (LoginInvalidoException | SenhaInvalidaException | ArquivoException e) {
            // ListarUsuariosCommand não lança exceções checadas; não deve ocorrer aqui.
            throw new RuntimeException(e);
        }
    }

    public void adicionarAquario(String nome, double volume, String tipoStr, int idDono)
            throws ArquivoException, IllegalArgumentException {
        Command<Void> comando = new AdicionarAquarioCommand(userController, aquarioController,
                nome, volume, tipoStr, idDono);
        try {
            executar(comando);
        } catch (LoginInvalidoException | SenhaInvalidaException e) {
            // AdicionarAquarioCommand não lança essas exceções; não deve ocorrer aqui.
            throw new RuntimeException(e);
        }
    }

    public List<Aquario> listarAquarios() {
        Command<List<Aquario>> comando = new ListarAquariosCommand(aquarioController);
        try {
            return executar(comando);
        } catch (LoginInvalidoException | SenhaInvalidaException | ArquivoException e) {
            throw new RuntimeException(e);
        }
    }

    public int getQuantidadeUsuarios() {
        return listarUsuarios().size();
    }

    public int getQuantidadeAquarios() {
        return listarAquarios().size();
    }

    public int getQuantidadeTotalEntidades() {
        return getQuantidadeUsuarios() + getQuantidadeAquarios();
    }
}