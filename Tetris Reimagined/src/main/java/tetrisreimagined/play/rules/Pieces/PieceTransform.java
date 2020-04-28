package tetrisreimagined.play.rules.Pieces;

import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.Position;

import java.util.List;

public class PieceTransform {

    public int getBlockId(Position position, List<Block> blocks) {
        for(Block block: blocks) {
            if(block.getPosition().equals(position))
                return block.getId();
        }
        return 0;
    }

    public int[][] transposeMatrix(int[][] occupiedBlock, int yLenght, int xLenght) {
        int[][] result = new int[xLenght][yLenght];

        for(int row = 0; row < yLenght; row++) {
            for(int col = 0; col < xLenght; col++)
                result[col][row] = occupiedBlock[row][col];
        }

        return result;
    }

    public int[][] reverseColumnsOrder(int[][] transposedOccupied, int xLenght, int yLenght) {
        int[][] result = new int[xLenght][yLenght];

        int tempX = 0;
        for(int col = yLenght - 1; col >= 0; col--) {
            for(int row = 0; row < xLenght; row++)
                result[row][tempX] = transposedOccupied[row][col];
            tempX++;
        }

        return result;
    }

    public int[][] reverseLinesOrder(int[][] transposedOccupied, int xLenght, int yLenght) {

        int[][] result = new int[xLenght][yLenght];

        int tempY = 0;
        for(int row = xLenght - 1; row >= 0; row--) {
            for(int col = 0; col < yLenght; col++)
                result[tempY][col] = transposedOccupied[row][col];
            tempY++;
        }

        return result;
    }

    public Block getBlockById(int id, List<Block> blocks) {
        for(Block block: blocks) {
            if(block.getId() == id)
                return block;
        }

        return null;
    }
}
