import java.util.List;
import java.util.Scanner;
import model.User;
import repository.UserRepository;
import service.UserService;

public class Main {
    public static void main(String[] args) {
        UserRepository repository = new UserRepository();
        UserService service = new UserService(repository);

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

                service.adicionarUsuario(nome, email);
                System.out.println("Usuário adicionado com sucesso!\n");
            }

            System.out.println("\n--- Usuários Registrados ---");
            
            List<User> todosUsuarios = service.listarTodos();

            for (User u : todosUsuarios) {
                System.out.println(u);
            }
            
        } 
    }
}