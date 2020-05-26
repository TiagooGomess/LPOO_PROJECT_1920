package tetrisreimagined.MenuCommands;

import tetrisreimagined.Instructions.InstructionsController;

import java.io.IOException;

public class DoNothing extends InstructionsCommand {

    @Override
    public boolean execute() throws IOException {
        return false;
    }
}
