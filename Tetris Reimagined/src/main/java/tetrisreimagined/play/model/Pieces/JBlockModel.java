package tetrisreimagined.play.model.Pieces;

import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.Color;
import tetrisreimagined.play.model.Position;

public class JBlockModel extends PieceModel {

    public JBlockModel() {
        super();
        this.color = new Color("blue", "#0000FF");

        this.blocks.add(new Block(new Position(0, 0), this.color));
        this.blocks.add(new Block(new Position(1, 0), this.color));
        this.blocks.add(new Block(new Position(0, 1), this.color));
        this.blocks.add(new Block(new Position(1, 1), this.color));
        this.blocks.add(new Block(new Position(2, 1), this.color));
        this.blocks.add(new Block(new Position(3, 1), this.color));
        this.blocks.add(new Block(new Position(4, 1), this.color));
        this.blocks.add(new Block(new Position(5, 1), this.color));

        sizeOfBoundingBox = 6;

    }




}
