package tetrisreimagined.States;

import tetrisreimagined.Game;

public class LeaderBoardState extends GameState {
    public LeaderBoardState(Game game) {
        super(game);
    }

    @Override
    public void buttonPressed(Game.BUTTON button) {
        if(button == Game.BUTTON.MENU) {
            game.setGameState(new MenuState(game));
            // game.viewMenu();
        }
    }
}
