package tetrisreimagined.play.model;

import java.util.ArrayList;

public class IBlock extends Piece {

    public IBlock() {
        super();
        this.color = new Color("blue", "codigodeazul");
        blocks.add(new Block(0, 0, this.color));
        blocks.add(new Block(1, 0, this.color));
        blocks.add(new Block(2, 0, this.color));
        blocks.add(new Block(3, 0, this.color));
    }


}
