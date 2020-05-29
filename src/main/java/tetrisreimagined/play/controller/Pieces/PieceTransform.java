package tetrisreimagined.play.controller.Pieces;

public class PieceTransform {

    public static int[][] transposeMatrix(int[][] occupiedBlock, int yLenght, int xLenght) {
        int[][] result = new int[xLenght][yLenght];

        for(int row = 0; row < yLenght; row++) {
            for(int col = 0; col < xLenght; col++)
                result[col][row] = occupiedBlock[row][col];
        }

        return result;
    }

    public static int[][] reverseColumnsOrder(int[][] transposedOccupied, int xLenght, int yLenght) {
        int[][] result = new int[xLenght][yLenght];

        int tempX = 0;
        for(int col = yLenght - 1; col >= 0; col--) {
            for(int row = 0; row < xLenght; row++)
                result[row][tempX] = transposedOccupied[row][col];
            tempX++;
        }

        return result;
    }

    public static int[][] reverseLinesOrder(int[][] transposedOccupied, int xLenght, int yLenght) {

        int[][] result = new int[xLenght][yLenght];

        int tempY = 0;
        for(int row = xLenght - 1; row >= 0; row--) {
            for(int col = 0; col < yLenght; col++)
                result[tempY][col] = transposedOccupied[row][col];
            tempY++;
        }

        return result;
    }

}
