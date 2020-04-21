package tetrisreimagined.play.model;

public class Block {

    private Position position;
    private Color color;

    public Block(int x, int y, Color color) {
        this.position = new Position(x, y);
        this.color = color;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
