package controller.Pieces;


import org.junit.Test;
import tetrisreimagined.play.rules.Pieces.PieceTransform;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class PieceTransformTest {

    @Test
    public void testTranspose() {
        int[][] originalMatrix = {{0, 0, 1}, {1, 1, 1}};
        int yLen = 2, xLen = 3;
        int[][] result = PieceTransform.transposeMatrix(originalMatrix, yLen, xLen);

        int[][] expectedMatrix = {{0, 1}, {0, 1}, {1, 1}};

        assertArrayEquals(expectedMatrix, result);
    }

    @Test
    public void testReverseColumnsOrder() {
        int[][] originalMatrix = {{0, 0, 1}, {1, 1, 1}};
        int yLen = 2, xLen = 3;
        int[][] result = PieceTransform.reverseColumnsOrder(originalMatrix, yLen, xLen);

        int[][] expectedMatrix = {{1, 0, 0}, {1, 1, 1}};

        assertArrayEquals(expectedMatrix, result);
    }

    @Test
    public void testReverseLinesOrder() {
        int[][] originalMatrix = {{0, 0, 1}, {1, 1, 1}};
        int yLen = 2, xLen = 3;
        int[][] result = PieceTransform.reverseLinesOrder(originalMatrix, yLen, xLen);

        int[][] expectedMatrix = {{1, 1, 1}, {0, 0, 1}};

        assertArrayEquals(expectedMatrix, result);
    }
}
