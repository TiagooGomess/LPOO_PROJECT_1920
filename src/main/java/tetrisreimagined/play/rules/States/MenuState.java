package tetrisreimagined.play.rules.States;

import tetrisreimagined.Game;

public class MenuState extends GameState {
    public MenuState(Game game) {
        super(game);
    }

    @Override
    public void buttonPressed(Game.BUTTON button) {
        switch (button) {
            case GAME_PLAY:
                game.setGameState(new GamePlayState(game));
                // game.gamePlay();
            case LEADERBOARD:
                game.setGameState(new LeaderBoardState(game));
                // game.viewLeaderboard();
            case MULTIPLAYER:
                game.setGameState(new MultiplayerState(game));
                // game.multiplayerGame();
        }

    }
}
