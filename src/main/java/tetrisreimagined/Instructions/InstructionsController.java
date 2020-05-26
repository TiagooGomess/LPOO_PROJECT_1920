package tetrisreimagined.Instructions;

import tetrisreimagined.MenuCommands.BackToMenu;
import tetrisreimagined.MenuCommands.ExitTerminal;
import tetrisreimagined.MenuCommands.InstructionsCommand;

import java.io.IOException;

public class InstructionsController {
    InstructionsViewLanterna gui;
    InstructionsModel instructionsModel;

    public InstructionsController(InstructionsViewLanterna gui, InstructionsModel instructionsModel) {
        this.gui = gui;
        this.instructionsModel = instructionsModel;
    }

    public InstructionsCommand start() throws IOException, InterruptedException {
        InstructionsCommand command;
        gui.drawAll(instructionsModel);
        do {
            command = gui.getInstructionCommand();

        } while (!(command instanceof BackToMenu));

        return command;
    }
}
