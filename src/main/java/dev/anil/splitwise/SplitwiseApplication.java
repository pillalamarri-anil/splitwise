package dev.anil.splitwise;

import dev.anil.splitwise.Command.Command;
import dev.anil.splitwise.Command.CommandExecutor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SplitwiseApplication implements CommandLineRunner {

    private CommandExecutor commandExecutor = new CommandExecutor();

    public static void main(String[] args) {
        SpringApplication.run(SplitwiseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();
            if (command.equals("exit")) {
                break;
            }
            else
                commandExecutor.execute(command);
        }

    }
}
