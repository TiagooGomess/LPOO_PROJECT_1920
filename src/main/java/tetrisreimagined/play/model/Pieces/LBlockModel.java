package tetrisreimagined.play.model.Pieces;

import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.Color;
import tetrisreimagined.play.model.Position;

public class LBlockModel extends PieceModel {

    public LBlockModel() {
        super();
        this.color = new Color("Orange", "#FFA500");

        this.blocks.add(new Block(new Position(4, 0), this.color, 1));
        this.blocks.add(new Block(new Position(5, 0), this.color, 2));
        this.blocks.add(new Block(new Position(4, 1), this.color, 3));
        this.blocks.add(new Block(new Position(5, 1), this.color, 4));
        this.blocks.add(new Block(new Position(0, 2), this.color, 5));
        this.blocks.add(new Block(new Position(1, 2), this.color, 6));
        this.blocks.add(new Block(new Position(2, 2), this.color, 7));
        this.blocks.add(new Block(new Position(3, 2), this.color, 8));

        this.blocks.add(new Block(new Position(4, 2), this.color, 9));
        this.blocks.add(new Block(new Position(5, 2), this.color, 10));
        this.blocks.add(new Block(new Position(4, 3), this.color, 11));
        this.blocks.add(new Block(new Position(5, 3), this.color, 12));
        this.blocks.add(new Block(new Position(0, 3), this.color, 13));
        this.blocks.add(new Block(new Position(1, 3), this.color, 14));
        this.blocks.add(new Block(new Position(2, 3), this.color, 15));
        this.blocks.add(new Block(new Position(3, 3), this.color, 16));

    }
}
