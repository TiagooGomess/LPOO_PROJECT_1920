package tetrisreimagined.States;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import tetrisreimagined.Game;
import tetrisreimagined.LanternaHandler;
import tetrisreimagined.Menu.controller.MenuCommands.DoNothing;
import tetrisreimagined.Menu.controller.MenuCommands.InstructionsCommand;
import tetrisreimagined.play.view.lantern.GameViewLanterna;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class GameOverState extends GameState {
    GameViewLanterna gui;

    public GameOverState(Game game, LanternaHandler lanternaHandler) {
        super(game);
        this.gui = new GameViewLanterna(lanternaHandler);
    }

    @Override
    public void buttonPressed(Game.BUTTON button) throws InterruptedException, CloneNotSupportedException, IOException {
        game.setGameState(new MenuState(game, game.getLanternaHandler()));
    }

    @Override
    public InstructionsCommand updateView() throws IOException, InterruptedException, CloneNotSupportedException {
        this.gui.drawBigScore((game.getLanternaHandler().getWidth() / 2) - 8, game.getLanternaHandler().getHeight() / 3, game.getArena().getScore());
        writeScoreToFile(game.getArena().getScore());

        readUntilEnter();

        buttonPressed(Game.BUTTON.MENU);
        return new DoNothing();
    }

    private void readUntilEnter() throws IOException {
        KeyStroke key = null;

        do {
            key = this.gui.getScreen().readInput();
            if(key != null) {
                if(key.getKeyType() == KeyType.Enter)
                    break;
            }

        } while(true);
    }

    private void writeScoreToFile(int score) {
        try {
            File myFile = new File("files/leaderboard.txt");
            myFile.createNewFile();

            Scanner myReader = new Scanner(myFile);
            List<Integer> maxPoints = new ArrayList<>();

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                maxPoints.add(parseInt(data));
            }
            maxPoints.add(score);
            Collections.sort(maxPoints, Collections.reverseOrder());
            myReader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter("files/leaderboard.txt", false));

            for(int index = 0; index < Integer.min(maxPoints.size(), 5); index++) {
                String toAdd = maxPoints.get(index) + "\n";
                writer.append(toAdd);
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}