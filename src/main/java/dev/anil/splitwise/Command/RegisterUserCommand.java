package dev.anil.splitwise.Command;

public class RegisterUserCommand implements Command {

    @Override
    public boolean matches(String command) {
      command = command.toLowerCase();
      String[] tokens = command.split(" ");
      return tokens.length == 4 && tokens[0].compareToIgnoreCase(CommandKeywords.RegisterCommand) == 0;
    }

    @Override
    public void execute(String command) {
        // call to register controller
    }
}
