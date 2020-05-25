package tetrisreimagined.Leaderboard.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class LeaderboardModel {

    List<Integer> scores;
    public LeaderboardModel() {
       scores = new ArrayList<>();
    }

    public void readLeaderboardFile(String filename) throws IOException {
        File myFile = new File("files/leaderboard.txt");
        myFile.createNewFile();

        Scanner myReader = new Scanner(myFile);

        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            scores.add(parseInt(data));
        }

        myReader.close();
    }

    public List<Integer> getScores() {
        return scores;
    }
}
