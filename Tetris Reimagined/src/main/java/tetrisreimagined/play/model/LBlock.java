package tetrisreimagined.play.model;

import java.util.ArrayList;

public class LBlock extends PieceModel {

    public LBlock() {
        super();
        this.color = new Color("Orange", "#FFA500");

        this.blocks.add(new Block(new Position(0, 1), this.color));
        this.blocks.add(new Block(new Position(1, 1), this.color));
        this.blocks.add(new Block(new Position(2, 1), this.color));
        this.blocks.add(new Block(new Position(2, 0), this.color));

        this.width = 3;
        this.height = 2;
    }




}
