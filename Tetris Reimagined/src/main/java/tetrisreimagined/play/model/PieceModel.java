package tetrisreimagined.play.model;

import java.util.ArrayList;
import java.util.List;

public abstract class PieceModel {

    // Names of pieces:
    // https://tetris.fandom.com/wiki/Tetromino
    // https://www.quora.com/What-are-the-different-blocks-in-Tetris-called-Is-there-a-specific-name-for-each-block

    protected List<Block> blocks;
    protected Color color;

    public PieceModel() {
        this.blocks = new ArrayList<>();
    }

    public List<Block> getBlocks() {
        return this.blocks;
    }

    public Position getMinXPosition() {

        int minX = Integer.MAX_VALUE;
        Position position = null; // nunca vai ficar null

        for (Block block: blocks) {
            if (block.getPosition().getX() < minX) {
                minX = block.getPosition().getX();
                position = new Position(minX, block.getPosition().getY());
            }
        }

        return position;
    }

    public Position getMaxXPosition() {
        int maxX = Integer.MIN_VALUE;
        Position position = null;

        for (Block block: blocks) {
            if (block.getPosition().getX() > maxX) {
                maxX = block.getPosition().getX();
                position = new Position(maxX, block.getPosition().getY());
            }
        }

        return position;
    }

    public Position getMaxYPosition() {
        int maxY = Integer.MIN_VALUE;
        Position position = null;

        for (Block block: blocks) {
            if (block.getPosition().getY() > maxY) {
                maxY = block.getPosition().getY();
                position = new Position(block.getPosition().getX(), maxY);
            }
        }

        return position;
    }

}
