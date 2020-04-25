package tetrisreimagined.play.model.Pieces;

import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.Color;
import tetrisreimagined.play.model.Position;

public class OBlockModel extends PieceModel {

    public OBlockModel() {
        super();
        this.color = new Color("yellow", "#FFFF00");

        this.blocks.add(new Block(new Position(0, 0), this.color, 1));
        this.blocks.add(new Block(new Position(1, 0), this.color, 2));
        this.blocks.add(new Block(new Position(2, 0), this.color, 3));

        this.blocks.add(new Block(new Position(0, 1), this.color, 5));
        this.blocks.add(new Block(new Position(1, 1), this.color, 6));
        this.blocks.add(new Block(new Position(2, 1), this.color, 7));

        sizeOfBoundingBox = 4;

    }




}
