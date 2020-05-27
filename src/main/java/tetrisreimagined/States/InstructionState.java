package tetrisreimagined.States;

import tetrisreimagined.Game;
import tetrisreimagined.Instructions.InstructionsController;
import tetrisreimagined.Instructions.InstructionsModel;
import tetrisreimagined.Instructions.InstructionsViewLanterna;
import tetrisreimagined.LanternaHandler;
import tetrisreimagined.MenuCommands.BackToMenu;
import tetrisreimagined.MenuCommands.DoNothing;
import tetrisreimagined.MenuCommands.InstructionsCommand;

import java.io.IOException;

public class InstructionState extends GameState{
    InstructionsModel instructionsModel;
    InstructionsViewLanterna gui;
    InstructionsController controller;

    public InstructionState(Game game, LanternaHandler lanternaHandler) throws InterruptedException, CloneNotSupportedException, IOException {
        super(game);
        this.instructionsModel = new InstructionsModel();
        this.gui = new InstructionsViewLanterna(lanternaHandler);
        this.controller = new InstructionsController(gui, instructionsModel);
    }

    @Override
    public void buttonPressed(Game.BUTTON button) throws InterruptedException, CloneNotSupportedException, IOException {
        game.setGameState(new MenuState(game, game.getLanternaHandler()));
    }

    public InstructionsCommand updateView() throws IOException, InterruptedException, CloneNotSupportedException {
        InstructionsCommand command = controller.start();

        buttonPressed(Game.BUTTON.MENU);
        return new DoNothing();
    }
}
