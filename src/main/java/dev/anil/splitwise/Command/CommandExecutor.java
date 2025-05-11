package dev.anil.splitwise.Command;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

public class CommandExecutor {

    private List<Command> commandList;

    public void add(Command command)
    {
        commandList.add(command);
    }

    public void remove(Command command)
    {
        commandList.remove(command);
    }

    public void execute(String command)
    {
        for(Command c : commandList)
        {
            if(c.matches(command))
            {
                c.execute(command);
                break;
            }
        }
    }

}
