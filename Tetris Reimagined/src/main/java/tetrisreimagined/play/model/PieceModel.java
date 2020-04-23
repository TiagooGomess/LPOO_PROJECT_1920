package tetrisreimagined.play.model;

import java.util.ArrayList;
import java.util.List;

public abstract class PieceModel {

    // Names of pieces:
    // https://tetris.fandom.com/wiki/Tetromino
    // https://www.quora.com/What-are-the-different-blocks-in-Tetris-called-Is-there-a-specific-name-for-each-block

    protected List<Block> blocks;
    protected Color color;
    protected Position position;
    protected int width, height;

    public PieceModel() {
        this.blocks = new ArrayList<>();
    }

    public List<Block> getBlocks() {
        return this.blocks;
    }

    public Position getPosition() { // posição do bloco com menor x e y (TODO delete this shit (just for test))

        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;

        for (Block block: blocks) {
            minX = Math.min(minX, block.getPosition().getX());
            minY = Math.min(minY, block.getPosition().getY());
        }

        return new Position(minX, minY);
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getMinX() {
        int minX = Integer.MAX_VALUE;

        for (Block block: blocks)
            minX = Math.min(minX, block.getPosition().getX());

        return minX;
    }

    public int getMaxX() {
        int maxX = Integer.MIN_VALUE;

        for (Block block: blocks)
            maxX = Math.max(maxX, block.getPosition().getX());

        return maxX;
    }

    public int getMaxY() {
        int maxY = Integer.MIN_VALUE;

        for (Block block: blocks)
            maxY = Math.max(maxY, block.getPosition().getY());

        return maxY;
    }

    public List<Position> getPositions() {
        List<Position> positions = new ArrayList<>();
        for (Block block: blocks)
            positions.add(block.getPosition());
        return positions;
    }
}
