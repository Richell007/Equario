package repository;

import exceptions.ArquivoException;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Aquario;

public class AquarioRepository implements IAquarioRepository {
    private static final String ARQUIVO = "aquarios.dat";
    private List<Aquario> aquarios;
    private int proximoId;

    public AquarioRepository() throws ArquivoException {
        this.aquarios = carregarDoArquivo();
        this.proximoId = aquarios.stream()
                .mapToInt(Aquario::getId)
                .max()
                .orElse(0) + 1;
    }

    public void salvar(Aquario aquario) throws ArquivoException {
        aquarios.add(aquario);
        try {
            salvarNoArquivo();
        } catch (ArquivoException e) {
            aquarios.remove(aquarios.size() - 1); // rollback
            throw e;
        }
        proximoId++;
    }

    public List<Aquario> listarTodos() {
        return Collections.unmodifiableList(aquarios);
    }

    public int gerarProximoId() {
        return proximoId;
    }

    @SuppressWarnings("unchecked")
    private List<Aquario> carregarDoArquivo() throws ArquivoException {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists() || arquivo.length() == 0) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Aquario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new ArquivoException("Erro ao carregar aquários do arquivo binário.", e);
        }
    }

    private void salvarNoArquivo() throws ArquivoException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(aquarios);
        } catch (IOException e) {
            throw new ArquivoException("Erro ao salvar aquários no arquivo binário.", e);
        }
    }
}