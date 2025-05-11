package dev.anil.splitwise.Command;

import dev.anil.splitwise.controllers.Controller;
import dev.anil.splitwise.dtos.UserSettleUpRequestDto;
import dev.anil.splitwise.dtos.UserSettleUpResponseDto;

public class SettleUpCommand implements Command {

    private Controller controller;

    public SettleUpCommand(Controller controller) {
        this.controller = controller;
    }

    @Override
    public boolean matches(String command) {
        command.toLowerCase();
        String[] commandParts = command.split(" ");
        return commandParts.length == 2 && 0 == commandParts[0].compareToIgnoreCase("settleup");
    }

    @Override
    public void execute(String command) {

        String[] commandParts = command.split(" ");

        UserSettleUpRequestDto userSettleUpRequestDto = new UserSettleUpRequestDto();
        userSettleUpRequestDto.setUserId(Long.valueOf(commandParts[1]));
        UserSettleUpResponseDto responseDto = controller.SettleupUser(userSettleUpRequestDto);
    }
}
