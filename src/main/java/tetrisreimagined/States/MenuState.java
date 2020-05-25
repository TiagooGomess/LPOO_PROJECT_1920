package tetrisreimagined.States;

import tetrisreimagined.Game;

import java.io.IOException;

public class MenuState extends GameState {
    public MenuState(Game game) throws IOException, InterruptedException, CloneNotSupportedException {
        super(game);
    }

    @Override
    public void buttonPressed(Game.BUTTON button) throws InterruptedException, CloneNotSupportedException, IOException {
        switch (button) {
            case GAME_PLAY:
                game.setGameState(new GamePlayState(game));
                game.gamePlay(game.getLanternaHandler());
                game.setGameState(new MenuState(game));
                break;
            case LEADERBOARD:
                game.setGameState(new LeaderBoardState(game));
                // game.viewLeaderboard();
                game.setGameState(new MenuState(game));
                break;
            case MULTIPLAYER:
                game.setGameState(new MultiplayerState(game));
                game.gamePlayMultiplayer(game.getLanternaHandler());
                game.setGameState(new MenuState(game));
                break;
        }

    }
}
