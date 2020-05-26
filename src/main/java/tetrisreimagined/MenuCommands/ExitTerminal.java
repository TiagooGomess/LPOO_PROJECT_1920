package tetrisreimagined.MenuCommands;

import tetrisreimagined.Instructions.InstructionsController;
import tetrisreimagined.Instructions.InstructionsViewLanterna;

import java.io.IOException;

public class ExitTerminal extends InstructionsCommand {
    @Override
    public boolean execute() throws IOException {
        return true;
    }
}
