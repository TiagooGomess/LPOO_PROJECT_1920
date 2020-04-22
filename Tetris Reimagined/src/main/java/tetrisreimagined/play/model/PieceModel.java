package tetrisreimagined.play.model;

import java.util.ArrayList;
import java.util.List;

public abstract class PieceModel {

    // Names of pieces:
    // https://tetris.fandom.com/wiki/Tetromino

    protected List<Block> blocks;
    protected Color color;

    public PieceModel() {
        this.blocks = new ArrayList<>();
    }

    public List<Block> getBlocks() {
        return this.blocks;
    }

}
