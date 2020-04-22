package tetrisreimagined.play.model;

public class TBlock extends PieceModel {

    public TBlock() {
        super();
        this.color = new Color("purple", "#800080");

        this.blocks.add(new Block(new Position(0, 1), this.color));
        this.blocks.add(new Block(new Position(1, 1), this.color));
        this.blocks.add(new Block(new Position(1, 0), this.color));
        this.blocks.add(new Block(new Position(2, 1), this.color));

        this.width = 3;
        this.height = 2;
    }




}
