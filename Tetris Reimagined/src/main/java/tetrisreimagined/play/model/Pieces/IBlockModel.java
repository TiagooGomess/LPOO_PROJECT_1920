package tetrisreimagined.play.model.Pieces;

import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.Color;
import tetrisreimagined.play.model.Position;

public class IBlockModel extends PieceModel {

    public IBlockModel() {
        super();
        this.color = new Color("cyan", "#00FFFF");

        blocks.add(new Block(new Position(0, 0), this.color));
        blocks.add(new Block(new Position(1, 0), this.color));
        blocks.add(new Block(new Position(2, 0), this.color));
        blocks.add(new Block(new Position(3, 0), this.color));

        blocks.add(new Block(new Position(4, 0), this.color));
        blocks.add(new Block(new Position(5, 0), this.color));
        blocks.add(new Block(new Position(6, 0), this.color));
        blocks.add(new Block(new Position(7, 0), this.color));

        blocks.add(new Block(new Position(0, 1), this.color));
        blocks.add(new Block(new Position(1, 1), this.color));
        blocks.add(new Block(new Position(2, 1), this.color));
        blocks.add(new Block(new Position(3, 1), this.color));

        blocks.add(new Block(new Position(4, 1), this.color));
        blocks.add(new Block(new Position(5, 1), this.color));
        blocks.add(new Block(new Position(6, 1), this.color));
        blocks.add(new Block(new Position(7, 1), this.color));


    }



}
