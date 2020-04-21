package tetrisreimagined.play.model;

import tetrisreimagined.play.observer.Observable;

import java.util.List;

public class ArenaModel extends Observable<ArenaModel> {
    // Only represents data

    private List<Block> pieces;
}
