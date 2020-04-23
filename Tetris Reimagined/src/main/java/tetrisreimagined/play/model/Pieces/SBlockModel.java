package tetrisreimagined.play.model.Pieces;

import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.Color;
import tetrisreimagined.play.model.Position;

public class SBlockModel extends PieceModel {

    public SBlockModel() {
        super();
        this.color = new Color("limegreen", "#32CD32");

        this.blocks.add(new Block(new Position(0, 1), this.color));
        this.blocks.add(new Block(new Position(1, 1), this.color));
        this.blocks.add(new Block(new Position(2, 1), this.color));
        this.blocks.add(new Block(new Position(3, 1), this.color));
        this.blocks.add(new Block(new Position(2, 0), this.color));
        this.blocks.add(new Block(new Position(3, 0), this.color));
        this.blocks.add(new Block(new Position(4, 0), this.color));
        this.blocks.add(new Block(new Position(5, 0), this.color));

        sizeOfBoundingBox = 6;

    }


}
