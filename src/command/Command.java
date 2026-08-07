package command;

public interface Command<T> {
    T execute() throws Exception;
}