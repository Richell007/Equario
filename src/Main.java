import exceptions.ArquivoException;
import exceptions.LoginInvalidoException;
import exceptions.SenhaInvalidaException;
import model.Aquario;
import model.User;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FacadeSingletonController fachada;
        try {
            fachada = FacadeSingletonController.getInstance();
        } catch (RuntimeException e) {
            System.out.println("ERRO FATAL: " + e.getMessage());
            return;
        }

        System.out.println("--- Sistema de Registro (Usuários e Aquários) ---");

        try (Scanner scanner = new Scanner(System.in)) {
            boolean executando = true;
            while (executando) {
                System.out.println("\n===== MENU =====");
                System.out.println("1 - Adicionar usuário");
                System.out.println("2 - Listar usuários");
                System.out.println("3 - Adicionar aquário");
                System.out.println("4 - Listar aquários");
                System.out.println("5 - Mostrar quantidades");
                System.out.println("6 - Sair");
                System.out.print("Escolha uma opção: ");

                String opcao = scanner.nextLine();

                switch (opcao) {
                    case "1":
                        adicionarUsuario(scanner, fachada);
                        break;
                    case "2":
                        listarUsuarios(fachada);
                        break;
                    case "3":
                        adicionarAquario(scanner, fachada);
                        break;
                    case "4":
                        listarAquarios(fachada);
                        break;
                    case "5":
                        mostrarQuantidades(fachada);
                        break;
                    case "6":
                        executando = false;
                        System.out.println("Encerrando o sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            }
        }
    }

    private static void adicionarUsuario(Scanner scanner, FacadeSingletonController fachada) {
        System.out.print("Digite o nome do usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o email do usuário: ");
        String email = scanner.nextLine();
        System.out.print("Digite o login desejado: ");
        String login = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        try {
            fachada.adicionarUsuario(nome, email, login, senha);
            System.out.println("Usuário adicionado com sucesso!");
        } catch (LoginInvalidoException | SenhaInvalidaException e) {
            System.out.println("ERRO: " + e.getMessage());
        } catch (ArquivoException e) {
            System.out.println("ERRO ao salvar no arquivo: " + e.getMessage());
        }
    }

    private static void listarUsuarios(FacadeSingletonController fachada) {
        System.out.println("\n--- Usuários Registrados ---");
        List<User> todosUsuarios = fachada.listarUsuarios();
        if (todosUsuarios.isEmpty()) {
            System.out.println("Nenhum usuário registrado.");
        } else {
            for (User u : todosUsuarios) {
                System.out.println(u);
            }
        }
    }

    private static void adicionarAquario(Scanner scanner, FacadeSingletonController fachada) {
        List<User> usuarios = fachada.listarUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("Não há usuários cadastrados. Cadastre um usuário primeiro.");
            return;
        }

        System.out.println("\nUsuários disponíveis para serem donos:");
        for (User u : usuarios) {
            System.out.println("ID: " + u.getId() + " | Nome: " + u.getNome());
        }
        System.out.print("Digite o ID do dono do aquário: ");
        int idDono;
        try {
            idDono = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        System.out.print("Digite o nome do aquário: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o volume (em litros): ");
        double volume;
        try {
            volume = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Volume inválido.");
            return;
        }
        System.out.print("Escolha o tipo (DOCE, SALOBRO, MARINHO): ");
        String tipoStr = scanner.nextLine();

        try {
            fachada.adicionarAquario(nome, volume, tipoStr, idDono);
            System.out.println("Aquário adicionado com sucesso!");
        } catch (ArquivoException e) {
            System.out.println("ERRO ao salvar no arquivo: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    private static void listarAquarios(FacadeSingletonController fachada) {
        System.out.println("\n--- Aquários Registrados ---");
        List<Aquario> todosAquarios = fachada.listarAquarios();
        if (todosAquarios.isEmpty()) {
            System.out.println("Nenhum aquário registrado.");
        } else {
            for (Aquario a : todosAquarios) {
                System.out.println(a);
            }
        }
    }

    private static void mostrarQuantidades(FacadeSingletonController fachada) {
        System.out.println("\n--- Quantidades de Entidades ---");
        System.out.println("Usuários cadastrados: " + fachada.getQuantidadeUsuarios());
        System.out.println("Aquários cadastrados: " + fachada.getQuantidadeAquarios());
        System.out.println("Total de entidades (usuários + aquários): " + fachada.getQuantidadeTotalEntidades());
    }
}