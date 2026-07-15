import controller.UserController;
import exceptions.ArquivoException;
import exceptions.LoginInvalidoException;
import exceptions.SenhaInvalidaException;
import java.util.List;
import java.util.Scanner;
import model.User;
import repository.UserRepository;
import service.UserService;

public class Main {
    public static void main(String[] args) {
        UserRepository repository;
        try {
            repository = new UserRepository();
        } catch (ArquivoException e) {
            System.out.println("ERRO FATAL ao carregar dados: " + e.getMessage());
            return;
        }
        UserService service = new UserService(repository);
        UserController controller = new UserController(service);
        System.out.println("--- Sistema de Registro de Usuários ---");

        try (Scanner scanner = new Scanner(System.in)) {
            boolean executando = true;
            while (executando) {
                System.out.println("\n===== MENU =====");
                System.out.println("1 - Adicionar usuário");
                System.out.println("2 - Listar usuários");
                System.out.println("3 - Sair");
                System.out.print("Escolha uma opção: ");

                String opcao = scanner.nextLine();

                switch (opcao) {
                    case "1":
                        adicionarUsuario(scanner, controller);
                        break;
                    case "2":
                        listarUsuarios(controller);
                        break;
                    case "3":
                        executando = false;
                        System.out.println("Encerrando o sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            }
        }
    }

    private static void adicionarUsuario(Scanner scanner, UserController controller) {
        System.out.print("Digite o nome do usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o email do usuário: ");
        String email = scanner.nextLine();
        System.out.print("Digite o login desejado: ");
        String login = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        try {
            controller.adicionarUsuario(nome, email, login, senha);
            System.out.println("Usuário adicionado com sucesso!");
        } catch (LoginInvalidoException | SenhaInvalidaException e) {
            System.out.println("ERRO: " + e.getMessage());
        } catch (ArquivoException e) {
            System.out.println("ERRO ao salvar no arquivo: " + e.getMessage());
        }
    }

    private static void listarUsuarios(UserController controller) {
        System.out.println("\n--- Usuários Registrados ---");
        List<User> todosUsuarios = controller.listarUsuarios();
        if (todosUsuarios.isEmpty()) {
            System.out.println("Nenhum usuário registrado.");
        } else {
            for (User u : todosUsuarios) {
                System.out.println(u);
            }
        }
    }
}