package tetrisreimagined.play.model;

public class Position {

    private int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position left() {
        return new Position(this.x - 2, this.y);
    }

    public Position right() {
        return new Position(this.x + 2, this.y);
    }

    public Position up() {
        return new Position(this.x, this.y - 1);
    }

    public Position down() {
        return new Position(this.x, this.y + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }



}
