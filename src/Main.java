import controller.UserController;
import exceptions.LoginInvalidoException;
import exceptions.SenhaInvalidaException;
import java.util.List;
import java.util.Scanner;
import model.User;
import repository.UserRepository;
import service.UserService;

public class Main {
    public static void main(String[] args) {
        UserRepository repository = new UserRepository();
        UserService service = new UserService(repository);
        UserController controller = new UserController(service);

        System.out.println("--- Sistema de Registro de Usuários ---");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Digite o nome do usuário (ou 'sair' para parar): ");
                String nome = scanner.nextLine();

                if (nome.equalsIgnoreCase("sair")) {
                    break;
                }

                System.out.print("Digite o email do usuário: ");
                String email = scanner.nextLine();

                System.out.print("Digite o login desejado: ");
                String login = scanner.nextLine();

                System.out.print("Digite a senha: ");
                String senha = scanner.nextLine();

                try {
                    controller.adicionarUsuario(nome, email, login, senha);
                    System.out.println("Usuário adicionado com sucesso!\n");
                } catch (LoginInvalidoException | SenhaInvalidaException e) {
                    System.out.println("ERRO: " + e.getMessage() + "\n");
                }
            }

            System.out.println("\n--- Usuários Registrados ---");
            List<User> todosUsuarios = controller.listarUsuarios();
            for (User u : todosUsuarios) {
                System.out.println(u);
            }
        }
    }
}