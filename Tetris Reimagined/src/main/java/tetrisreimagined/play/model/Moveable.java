package tetrisreimagined.play.model;

public class Moveable extends Element {

    public Moveable(int x, int y) {
        super(x, y);
    }

    public Position moveLeft() {
        return super.getPosition().left();
    }

    public Position moveRight() {
        return super.getPosition().right();
    }

    public Position moveUp() {
        return super.getPosition().up();
    }

    public Position moveDown() {
        return super.getPosition().down();
    }

}
