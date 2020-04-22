package tetrisreimagined.play.model;

import java.util.ArrayList;
import java.util.List;

public abstract class PieceModel {

    // Names of pieces:
    // https://tetris.fandom.com/wiki/Tetromino

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

    public Position getPosition() { // posição do bloco com menor x e y, quando a peça não está rodada
        return this.position;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
