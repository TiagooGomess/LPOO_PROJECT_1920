package model;

import org.junit.Before;
import org.junit.Test;
import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.Pieces.PieceModel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ArenaModelTest {
    private ArenaModel arenaModel;

    @Before
    public void setup() {
        arenaModel = new ArenaModel();
    }

    @Test
    public void getLevel() {
        arenaModel.setLevel(5);

        assertEquals(5, arenaModel.getLevel());
    }

    @Test
    public void getScore() {
        arenaModel.setScore(1234);

        assertEquals(1234, arenaModel.getScore());
    }

    @Test
    public void getCurrentPieceModel() {
        PieceModel pieceModelMock = mock(PieceModel.class);
        arenaModel.setCurrentPieceModel(pieceModelMock);

        assertEquals(pieceModelMock, arenaModel.getCurrentPieceModel());
    }

    @Test
    public void getNextPieceModel() {
        PieceModel nextPieceModelMock = mock(PieceModel.class);
        PieceModel currentPieceModelMock = mock(PieceModel.class);
        arenaModel.setNextPieceModel(nextPieceModelMock);
        arenaModel.setCurrentPieceModel(currentPieceModelMock);

        assertEquals(nextPieceModelMock, arenaModel.getNextPieceModel());
        assertNotEquals(nextPieceModelMock, arenaModel.getCurrentPieceModel());
    }

    @Test
    public void arenaIsEmpty() {
        assertTrue(arenaModel.arenaIsEmpty());

        List<Block> blocks = new ArrayList<>();
        Block blockMock = mock(Block.class);
        blocks.add(blockMock);

        PieceModel pieceModelMock = mock(PieceModel.class);
        when(pieceModelMock.getBlocks()).thenReturn(blocks);
        arenaModel.addPiece(pieceModelMock);

        assertFalse(arenaModel.arenaIsEmpty());

        arenaModel.removeArenaBlocks(blocks);

        assertTrue(arenaModel.arenaIsEmpty());
    }

    @Test
    public void getAreanaBlocks() {
        List<Block> blocks = new ArrayList<>();
        Block blockMock = mock(Block.class);
        blocks.add(blockMock);

        PieceModel pieceModelMock = mock(PieceModel.class);
        when(pieceModelMock.getBlocks()).thenReturn(blocks);
        arenaModel.addPiece(pieceModelMock);

        assertEquals(blocks, arenaModel.getArenaBlocks());
    }

}
