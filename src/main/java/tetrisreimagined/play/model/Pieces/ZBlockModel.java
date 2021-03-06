package tetrisreimagined.play.model.Pieces;

import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.Color;
import tetrisreimagined.play.model.Position;

public class ZBlockModel extends PieceModel {

    public ZBlockModel() {
        super();
        this.color = new Color("red", "#FF0000");

        this.blocks.add(new Block(new Position(0, 0), this.color, 1));
        this.blocks.add(new Block(new Position(1, 0), this.color, 2));
        this.blocks.add(new Block(new Position(2, 0), this.color, 3));
        this.blocks.add(new Block(new Position(3, 0), this.color, 4));
        this.blocks.add(new Block(new Position(2, 1), this.color, 5));
        this.blocks.add(new Block(new Position(3, 1), this.color, 6));
        this.blocks.add(new Block(new Position(4, 2), this.color, 7));
        this.blocks.add(new Block(new Position(5, 2), this.color, 8));

        this.blocks.add(new Block(new Position(0, 1), this.color, 9));
        this.blocks.add(new Block(new Position(1, 1), this.color, 10));
        this.blocks.add(new Block(new Position(2, 2), this.color, 11));
        this.blocks.add(new Block(new Position(3, 2), this.color, 12));
        this.blocks.add(new Block(new Position(2, 3), this.color, 13));
        this.blocks.add(new Block(new Position(3, 3), this.color, 14));
        this.blocks.add(new Block(new Position(4, 3), this.color, 15));
        this.blocks.add(new Block(new Position(5, 3), this.color, 16));
    }
}
