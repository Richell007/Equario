package log;

import java.util.logging.Level;

public class JulLoggerAdapter implements Logger {
    private final java.util.logging.Logger julLogger;

    public JulLoggerAdapter(String nome) {
        this.julLogger = java.util.logging.Logger.getLogger(nome);
    }

    @Override
    public void info(String mensagem) {
        julLogger.log(Level.INFO, mensagem);
    }

    @Override
    public void erro(String mensagem) {
        julLogger.log(Level.SEVERE, mensagem);
    }

    @Override
    public void erro(String mensagem, Throwable causa) {
        julLogger.log(Level.SEVERE, mensagem, causa);
    }
}