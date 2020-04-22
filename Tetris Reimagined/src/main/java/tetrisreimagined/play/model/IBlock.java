package tetrisreimagined.play.model;

public class IBlock extends PieceModel {

    public IBlock() {
        super();
        this.color = new Color("cyan", "#00FFFF");

        blocks.add(new Block(new Position(0, 0), this.color));
        blocks.add(new Block(new Position(1, 0), this.color));
        blocks.add(new Block(new Position(2, 0), this.color));
        blocks.add(new Block(new Position(3, 0), this.color));

        this.position = blocks.get(0).getPosition();

        this.width = 4;
        this.height = 1;

    }



}
