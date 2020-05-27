package tetrisreimagined.States;

import tetrisreimagined.Game;
import tetrisreimagined.LanternaHandler;
import tetrisreimagined.MenuCommands.DoNothing;
import tetrisreimagined.MenuCommands.InstructionsCommand;
import tetrisreimagined.play.controller.ArenaController;
import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.view.lantern.GameViewLanterna;

import java.io.IOException;

public class GamePlayState extends GameState {
    ArenaModel arena;
    GameViewLanterna gui;
    ArenaController controller;

    public GamePlayState(Game game, LanternaHandler lanternaHandler) throws InterruptedException, CloneNotSupportedException, IOException {
        super(game);
        this.arena = new ArenaModel();
        this.gui = new GameViewLanterna(lanternaHandler);
        this.controller = new ArenaController(gui, this.arena);
    }

    @Override
    public void buttonPressed(Game.BUTTON button) throws InterruptedException, CloneNotSupportedException, IOException {
        game.setGameState(new MenuState(game, game.getLanternaHandler()));
    }

    @Override
    public InstructionsCommand updateView() throws IOException, InterruptedException, CloneNotSupportedException {
        arena.addObserver(gui);
        controller.start();

        return new DoNothing();
    }
}
