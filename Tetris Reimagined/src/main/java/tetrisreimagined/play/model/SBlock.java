package tetrisreimagined.play.model;

import java.util.ArrayList;

public class SBlock extends Piece {

    public SBlock() {
        this.blocks = new ArrayList<>();
        this.color = new Color("limegreen", "#32CD32");

        this.blocks.add(new Block(new Position(0, 1), this.color));
        this.blocks.add(new Block(new Position(1, 1), this.color));
        this.blocks.add(new Block(new Position(1, 0), this.color));
        this.blocks.add(new Block(new Position(2, 0), this.color));
    }
}
