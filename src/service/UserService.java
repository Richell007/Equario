package service;

import exceptions.ArquivoException;
import exceptions.LoginInvalidoException;
import exceptions.SenhaInvalidaException;
import java.util.List;
import model.User;
import repository.UserRepository;

public class UserService {
    private final UserRepository repositorio;

    public UserService(UserRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void adicionarUsuario(String nome, String email, String login, String senha)
            throws LoginInvalidoException, SenhaInvalidaException, ArquivoException {
        validarLogin(login);
        validarSenha(senha, nome, email);
        int id = repositorio.gerarProximoId();
        User novo = new User(id, nome, email, login, senha);
        repositorio.salvar(novo);
    }

    private void validarLogin(String login) throws LoginInvalidoException {
        if (login == null || login.trim().isEmpty()) {
            throw new LoginInvalidoException("O login não pode ser vazio.");
        }
        if (login.length() > 12) {
            throw new LoginInvalidoException("O login deve ter no máximo 12 caracteres.");
        }
        if (login.matches(".*\\d.*")) {
            throw new LoginInvalidoException("O login não pode conter números.");
        }
    }

    private void validarSenha(String senha, String nome, String email) throws SenhaInvalidaException {
        if (senha == null || senha.length() < 8 || senha.length() > 128) {
            throw new SenhaInvalidaException("A senha deve ter entre 8 e 128 caracteres.");
        }
        if (senha.equalsIgnoreCase(nome) || senha.equalsIgnoreCase(email)) {
            throw new SenhaInvalidaException("A senha não pode ser idêntica ao nome ou e-mail.");
        }

        int tiposDeCaracteres = 0;
        if (senha.matches(".*[A-Z].*")) tiposDeCaracteres++;
        if (senha.matches(".*[a-z].*")) tiposDeCaracteres++;
        if (senha.matches(".*[0-9].*")) tiposDeCaracteres++;
        if (senha.matches(".*[!@$%^&*()<>?\\[\\]{}|_+\\-=.].*")) tiposDeCaracteres++;

        if (tiposDeCaracteres < 3) {
            throw new SenhaInvalidaException("A senha deve incluir no mínimo três dos seguintes tipos: maiúsculas, minúsculas, números e símbolos.");
        }
    }

    public List<User> listarTodos() {
        return repositorio.listarTodos();
    }
}