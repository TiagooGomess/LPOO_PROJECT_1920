package tetrisreimagined.play.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    // Names of pieces:
    // https://tetris.fandom.com/wiki/Tetromino

    protected List<Block> blocks;
    protected Color color;

    public Piece() {
        this.blocks = new ArrayList<>();
    }

    public List<Block> getBlocks() {
        return this.blocks;
    }

    public void moveLeft() {
        for (Block block: this.blocks)
            block.moveLeft();
    }

    public void moveRight() {
        for (Block block: this.blocks)
            block.moveRight();
    }

    public void moveUp() {
        for (Block block: this.blocks)
            block.moveUp();
    }

    public void moveDown() {
        for (Block block: this.blocks)
            block.moveDown();
    }
}
