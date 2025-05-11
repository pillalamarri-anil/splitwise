package dev.anil.splitwise.Command;

public interface Command {

    boolean matches(String command);
    void execute(String command);
}
