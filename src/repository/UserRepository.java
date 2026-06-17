package repository;

import exceptions.ArquivoException;
import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserRepository {
    private static final String ARQUIVO = "usuarios.dat";
    private List<User> usuarios;
    private int proximoId;

    public UserRepository() throws ArquivoException {
        this.usuarios = carregarDoArquivo();
        this.proximoId = usuarios.stream()
                .mapToInt(User::getId)
                .max()
                .orElse(0) + 1;
    }

    public void salvar(User usuario) throws ArquivoException {
        usuarios.add(usuario);
        try {
            salvarNoArquivo();
        } catch (ArquivoException e) {
            usuarios.remove(usuarios.size() - 1); // rollback
            throw e;
        }
        proximoId++;
    }

    public List<User> listarTodos() {
        return Collections.unmodifiableList(usuarios);
    }

    public int gerarProximoId() {
        return proximoId;
    }

    @SuppressWarnings("unchecked")
    private List<User> carregarDoArquivo() throws ArquivoException {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists() || arquivo.length() == 0) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new ArquivoException("Erro ao carregar usuários do arquivo binário.", e);
        }
    }

    private void salvarNoArquivo() throws ArquivoException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            throw new ArquivoException("Erro ao salvar usuários no arquivo binário.", e);
        }
    }
}