package tetrisreimagined.play.model;

public class Block {

    private Position position;
    private Color color;

    public Block(Position position, Color color) {
        this.position = position;
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

    public void moveLeft() {
        this.position = position.left();
    }

    public void moveRight() {
        this.position = position.right();
    }

    public void moveUp() {
        this.position = position.up();
    }

    public void moveDown() {
        this.position = position.down();
    }
}
