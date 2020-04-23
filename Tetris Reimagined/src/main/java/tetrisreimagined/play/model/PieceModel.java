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

    public Position getPosition() { // posição do bloco com menor x e y (TODO mudar isto (just for test))
        //return this.blocks.get(0).getPosition();
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;

        for (Block block: blocks) {
            if (block.getPosition().getX() < minX)
                minX = block.getPosition().getX();
            if (block.getPosition().getY() < minY)
                minY = block.getPosition().getY();
        }

        return new Position(minX, minY);

    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
