package tetrisreimagined.play.model;

public class LBlock extends PieceModel {

    public LBlock() {
        super();
        this.color = new Color("Orange", "#FFA500");

        this.blocks.add(new Block(new Position(4, 0), this.color));
        this.blocks.add(new Block(new Position(5, 0), this.color));
        this.blocks.add(new Block(new Position(4, 1), this.color));
        this.blocks.add(new Block(new Position(5, 1), this.color));
        this.blocks.add(new Block(new Position(0, 1), this.color));
        this.blocks.add(new Block(new Position(1, 1), this.color));
        this.blocks.add(new Block(new Position(2, 1), this.color));
        this.blocks.add(new Block(new Position(3, 1), this.color));

        this.width = 6;
        this.height = 2;
    }




}
