package exceptions;

public class ArquivoException extends Exception {
    public ArquivoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}