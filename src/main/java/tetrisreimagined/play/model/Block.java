package tetrisreimagined.play.model;

public class Block {

    private Position position;
    private Color color;
    private final int id;

    public Block(Position position, Color color, int id) {
        this.position = position;
        this.color = color;
        this.id = id;
    }

    public int getId() {
        return id;
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
