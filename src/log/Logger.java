package log;

public interface Logger {
    void info(String mensagem);
    void erro(String mensagem);
    void erro(String mensagem, Throwable causa);
}