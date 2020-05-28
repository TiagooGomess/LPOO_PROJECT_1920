package tetrisreimagined.controller;

import org.junit.Before;
import org.junit.Test;
import tetrisreimagined.observer.Observer;
import tetrisreimagined.play.controller.ArenaController;
import tetrisreimagined.play.controller.Pieces.PieceController;
import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.Color;
import tetrisreimagined.play.model.Pieces.PieceModel;
import tetrisreimagined.play.model.Position;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ArenaControllerTest {
    ArenaController arenaController;
    ArenaModel arenaModelMock;
    PieceController pieceControllerMock;

    ArenaController arenaController2;
    ArenaModel arenaModel2;
    Observer observerMock2;

    @Before
    public void setup() {

        Observer observerMock = mock(Observer.class);

        when(observerMock.getHeight()).thenReturn(23);
        when(observerMock.getWidth()).thenReturn(10);

        arenaModelMock = mock(ArenaModel.class);
        pieceControllerMock = mock(PieceController.class);

        PieceModel pieceModelMock = mock(PieceModel.class);

        when(pieceControllerMock.getPieceModel()).thenReturn(pieceModelMock);

        arenaController = new ArenaController(observerMock, arenaModelMock);

        // linha 20 -> completa; os blocos da linha 19 têm que descer
        Block block0 = new Block(new Position(0, 20), new Color("",""), 0);
        Block block1 = new Block(new Position(1, 20), new Color("",""), 1);
        Block block2 = new Block(new Position(2, 20), new Color("",""), 2);
        Block block3 = new Block(new Position(3, 20), new Color("",""), 3);
        Block block4 = new Block(new Position(4, 20), new Color("",""), 4);
        Block block5 = new Block(new Position(5, 20), new Color("",""), 5);
        Block block6 = new Block(new Position(6, 20), new Color("",""), 6);
        Block block7 = new Block(new Position(7, 20), new Color("",""), 7);
        Block block8 = new Block(new Position(8, 20), new Color("",""), 8);
        Block block9 = new Block(new Position(9, 20), new Color("",""), 9);

        // linha 21 -> não completa
        Block block10 = new Block(new Position(0, 21), new Color("",""), 10);
        Block block11 = new Block(new Position(1, 21), new Color("",""), 11);
        Block block12 = new Block(new Position(2, 21), new Color("",""), 12);
        Block block13 = new Block(new Position(3, 21), new Color("",""), 13);
        Block block15 = new Block(new Position(5, 21), new Color("",""), 15);
        Block block16 = new Block(new Position(6, 21), new Color("",""), 16);
        Block block17 = new Block(new Position(7, 21), new Color("",""), 17);
        Block block18 = new Block(new Position(8, 21), new Color("",""), 18);
        Block block19 = new Block(new Position(9, 21), new Color("",""), 19);

        // linha 19 -> não completa
        Block block20 = new Block(new Position(0, 22), new Color("",""), 20);
        Block block21 = new Block(new Position(1, 22), new Color("",""), 21);
        Block block22 = new Block(new Position(2, 22), new Color("",""), 22);


        List<Block> blocks = new ArrayList<>();
        blocks.add(block0);
        blocks.add(block1);
        blocks.add(block2);
        blocks.add(block3);
        blocks.add(block4);
        blocks.add(block5);
        blocks.add(block6);
        blocks.add(block7);
        blocks.add(block8);
        blocks.add(block9);

        blocks.add(block10);
        blocks.add(block11);
        blocks.add(block12);
        blocks.add(block13);
        blocks.add(block15);
        blocks.add(block16);
        blocks.add(block17);
        blocks.add(block18);
        blocks.add(block19);

        blocks.add(block20);
        blocks.add(block21);
        blocks.add(block22);

        when(arenaModelMock.getArenaBlocks()).thenReturn(blocks);

        when(arenaModelMock.positionHasBlock(new Position(0, 20))).thenReturn(true);
        when(arenaModelMock.positionHasBlock(new Position(1, 20))).thenReturn(true);
        when(arenaModelMock.positionHasBlock(new Position(2, 20))).thenReturn(true);
        when(arenaModelMock.positionHasBlock(new Position(3, 20))).thenReturn(true);
        when(arenaModelMock.positionHasBlock(new Position(4, 20))).thenReturn(true);
        when(arenaModelMock.positionHasBlock(new Position(5, 20))).thenReturn(true);
        when(arenaModelMock.positionHasBlock(new Position(6, 20))).thenReturn(true);
        when(arenaModelMock.positionHasBlock(new Position(7, 20))).thenReturn(true);
        when(arenaModelMock.positionHasBlock(new Position(8, 20))).thenReturn(true);
        when(arenaModelMock.positionHasBlock(new Position(9, 20))).thenReturn(true);

        when(arenaModelMock.positionHasBlock(new Position(0, 21))).thenReturn(true);
        when(arenaModelMock.positionHasBlock(new Position(1, 21))).thenReturn(true);
        when(arenaModelMock.positionHasBlock(new Position(2, 21))).thenReturn(true);
        when(arenaModelMock.positionHasBlock(new Position(3, 21))).thenReturn(true);
        when(arenaModelMock.positionHasBlock(new Position(4, 21))).thenReturn(false);
        when(arenaModelMock.positionHasBlock(new Position(5, 21))).thenReturn(true);
        when(arenaModelMock.positionHasBlock(new Position(6, 21))).thenReturn(true);
        when(arenaModelMock.positionHasBlock(new Position(7, 21))).thenReturn(true);
        when(arenaModelMock.positionHasBlock(new Position(8, 21))).thenReturn(true);
        when(arenaModelMock.positionHasBlock(new Position(9, 21))).thenReturn(true);

        when(arenaModelMock.positionHasBlock(new Position(0, 22))).thenReturn(true);
        when(arenaModelMock.positionHasBlock(new Position(1, 22))).thenReturn(true);
        when(arenaModelMock.positionHasBlock(new Position(2, 22))).thenReturn(true);
        when(arenaModelMock.positionHasBlock(new Position(3, 22))).thenReturn(false);
        when(arenaModelMock.positionHasBlock(new Position(4, 22))).thenReturn(false);
        when(arenaModelMock.positionHasBlock(new Position(5, 22))).thenReturn(false);
        when(arenaModelMock.positionHasBlock(new Position(6, 22))).thenReturn(false);
        when(arenaModelMock.positionHasBlock(new Position(7, 22))).thenReturn(false);
        when(arenaModelMock.positionHasBlock(new Position(8, 22))).thenReturn(false);
        when(arenaModelMock.positionHasBlock(new Position(9, 22))).thenReturn(false);

        // ---------------------------------------------------------------------------------------

        arenaModel2 = new ArenaModel();
        observerMock2 = mock(Observer.class);
        arenaController2 = new ArenaController(observerMock2, arenaModel2);

        when(observerMock2.getHeight()).thenReturn(20);
        when(observerMock2.getWidth()).thenReturn(20);

    }

    @Test
    public void checkLine1() {
        assertTrue(arenaController.checkLine(20));
    }

    @Test
    public void checkLine2() {
        assertFalse(arenaController.checkLine(21));
    }

    @Test
    public void checkLine3() {
        assertFalse(arenaController.checkLine(22));
    }

    @Test
    public void checkTryMoveDown() {
        // verifica se o y mínimo de uma peça é 0 e se o Y max de uma peça é igual a height - 1.
        arenaController2.nextPiece();
        assertEquals(0, arenaModel2.getCurrentPieceModel().getMinYPosition().getY());
        int counter = 0;
        for (int i = 0; i < 100; i++) {
            counter = arenaController2.tryMoveDown(counter, 1);
        }
        assertEquals(observerMock2.getHeight()-1, arenaModel2.getCurrentPieceModel().getMaxYPosition().getY());
    }

    @Test
    public void moveRight() {
        // verifica se uma peça é movida para a direita com o comando MoveRight
//        arenaController2.nextPiece();
//        arenaController2.getCurrentPieceController()
    }
}
