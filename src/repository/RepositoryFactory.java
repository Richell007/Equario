package repository;

import exceptions.ArquivoException;

public class RepositoryFactory {

    public static IUserRepository criarUserRepository(TipoPersistencia tipo) throws ArquivoException {
        switch (tipo) {
            case ARQUIVO:
                return new UserRepository();
            case MEMORIA:
                return new UserRepositoryMemoria();
            default:
                throw new IllegalArgumentException("Tipo de persistência não suportado: " + tipo);
        }
    }

    public static IAquarioRepository criarAquarioRepository(TipoPersistencia tipo) throws ArquivoException {
        switch (tipo) {
            case ARQUIVO:
                return new AquarioRepository();
            case MEMORIA:
                return new AquarioRepositoryMemoria();
            default:
                throw new IllegalArgumentException("Tipo de persistência não suportado: " + tipo);
        }
    }
}