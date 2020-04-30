package controller.Pieces;

import org.junit.Before;
import org.junit.Test;
import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.Pieces.PieceModel;
import tetrisreimagined.play.model.Position;
import tetrisreimagined.play.observer.Observer;
import tetrisreimagined.play.rules.Pieces.PieceController;
import tetrisreimagined.play.rules.commands.RotateClockWise;
import tetrisreimagined.play.rules.commands.RotateCounterClockWise;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PieceControllerTest {

    List<PieceController> pieceControllers;
    List<PieceModel> pieceModelsMocks;

    ArenaModel arenaModelMock;
    Observer observerMock;

    @Before
    public void setup() {

        pieceControllers = new ArrayList<>();
        pieceModelsMocks = new ArrayList<>();
        List<Block> blocks = new ArrayList<>();


        Block blockMock1 = mock(Block.class);
        Position positionMock1 = mock(Position.class);
        when(positionMock1.getX()).thenReturn(0);
        when(positionMock1.getY()).thenReturn(2);
        when(blockMock1.getPosition()).thenReturn(positionMock1);
        // preciso de criar um mock para o equals, mas não sei como se faz // TODO
        when(blockMock1.getId()).thenReturn(1);

        Block blockMock2 = mock(Block.class);
        Position positionMock2 = mock(Position.class);
        when(positionMock2.getX()).thenReturn(1);
        when(positionMock2.getY()).thenReturn(2);
        when(blockMock2.getPosition()).thenReturn(positionMock2);
        when(blockMock2.getId()).thenReturn(2);

        Block blockMock3 = mock(Block.class);
        Position positionMock3 = mock(Position.class);
        when(positionMock3.getX()).thenReturn(1);
        when(positionMock3.getY()).thenReturn(1);
        when(blockMock3.getPosition()).thenReturn(positionMock3);
        when(blockMock3.getId()).thenReturn(3);

        Block blockMock4 = mock(Block.class);
        Position positionMock4 = mock(Position.class);
        when(positionMock4.getX()).thenReturn(2);
        when(positionMock4.getY()).thenReturn(2);
        when(blockMock4.getPosition()).thenReturn(positionMock4);
        when(blockMock4.getId()).thenReturn(4);

        blocks.add(blockMock1);
        blocks.add(blockMock2);
        blocks.add(blockMock3);
        blocks.add(blockMock4);

        PieceModel pieceModelMock1 = mock(PieceModel.class);
        when(pieceModelMock1.getBlocks()).thenReturn(blocks);
        when(pieceModelMock1.getMinXPosition()).thenReturn(positionMock1);
        when(pieceModelMock1.getMaxXPosition()).thenReturn(positionMock4);
        when(pieceModelMock1.getMaxYPosition()).thenReturn(positionMock1);
        PieceController pieceController1 = new PieceController(pieceModelMock1);

        pieceControllers.add(pieceController1);
        pieceModelsMocks.add(pieceModelMock1);


        arenaModelMock = mock(ArenaModel.class);
        when(arenaModelMock.getArenaBlocks()).thenReturn(blocks);

        observerMock = mock(Observer.class);
        when(observerMock.getWidth()).thenReturn(30);
        when(observerMock.getHeight()).thenReturn(60);

    }

    @Test
    public void getPieceModel() {
        assertEquals(pieceModelsMocks.get(0), pieceControllers.get(0).getPieceModel());
    }

    @Test
    public void canGoRight() {
        assertTrue(pieceControllers.get(0).canGoRight(observerMock, arenaModelMock));
    }

    @Test
    public void canGoLeft() {
        assertFalse(pieceControllers.get(0).canGoLeft(observerMock, arenaModelMock));
    }

    @Test
    public void canGoDown() {
        assertTrue(pieceControllers.get(0).canGoDown(observerMock, arenaModelMock));
    }

//    @Test
//    public void positionHasBlock() {
//        Position positionMock1 = mock(Position.class);
//        when(positionMock1.getX()).thenReturn(1);
//        when(positionMock1.getY()).thenReturn(2);
//
//        assertTrue(pieceControllers.get(0).positionHasBlock(positionMock1, arenaModelMock));
//    }

    @Test
    public void getBlockById1() {
        Position positionMock = mock(Position.class);
        when(positionMock.getX()).thenReturn(0);
        when(positionMock.getY()).thenReturn(2);

        // seria preciso um mock do equals das Position para dar ........

        //assertEquals(1, pieceControllers.get(0).getBlockId(positionMock)); // TODO

    }

    @Test
    public void pieceCanRotateClockWise() {
        RotateClockWise rotateCW = mock(RotateClockWise.class);

        // com esta arquitetura, não sei como fazer testes para isto ahahah TODO

        //assertTrue(pieceControllers.get(0).pieceCanRotateClockWise(observerMock, arenaModelMock));
    }

    @Test
    public void pieceCanRotateCounterClockWise() {
        RotateCounterClockWise rotateCCW = mock(RotateCounterClockWise.class);


        // TODO
        // same ...
    }




}
