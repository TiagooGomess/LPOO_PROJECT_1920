package tetrisreimagined.play.model;

import java.util.ArrayList;

public class JBlock extends PieceModel {

    public JBlock() {
        super();
        this.color = new Color("blue", "#0000FF");

        this.blocks.add(new Block(new Position(0, 0), this.color));
        this.blocks.add(new Block(new Position(0, 1), this.color));
        this.blocks.add(new Block(new Position(1, 1), this.color));
        this.blocks.add(new Block(new Position(2, 1), this.color));

        this.width = 3;
        this.height = 2;
    }




}
