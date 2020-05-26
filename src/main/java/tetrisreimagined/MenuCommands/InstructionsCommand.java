package tetrisreimagined.MenuCommands;

import tetrisreimagined.Command;
import tetrisreimagined.Instructions.InstructionsController;

import java.io.IOException;

public abstract class InstructionsCommand extends Command {
    public abstract boolean execute() throws IOException;

}
