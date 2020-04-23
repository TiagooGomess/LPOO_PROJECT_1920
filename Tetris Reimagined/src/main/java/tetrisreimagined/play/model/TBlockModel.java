package tetrisreimagined.play.model;

public class TBlockModel extends PieceModel {

    public TBlockModel() {
        super();
        this.color = new Color("purple", "#800080");

        this.blocks.add(new Block(new Position(0, 1), this.color));
        this.blocks.add(new Block(new Position(1, 1), this.color));
        this.blocks.add(new Block(new Position(2, 1), this.color));
        this.blocks.add(new Block(new Position(3, 1), this.color));
        this.blocks.add(new Block(new Position(4, 1), this.color));
        this.blocks.add(new Block(new Position(5, 1), this.color));
        this.blocks.add(new Block(new Position(2, 0), this.color));
        this.blocks.add(new Block(new Position(3, 0), this.color));

    }




}
