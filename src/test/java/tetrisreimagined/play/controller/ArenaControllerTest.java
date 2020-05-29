package tetrisreimagined.play.controller;

import jdk.swing.interop.SwingInterOpUtils;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Positive;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tetrisreimagined.observer.Observer;
import tetrisreimagined.play.controller.Commands.*;
import tetrisreimagined.play.controller.Pieces.PieceController;
import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.Color;
import tetrisreimagined.play.model.Pieces.PieceModel;
import tetrisreimagined.play.model.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ArenaControllerTest {
    ArenaController arenaController;
    ArenaModel arenaModelMock;
    PieceController pieceControllerMock;

    ArenaController arenaController2;
    ArenaModel arenaModel2;
    Observer observerMock2;

    @BeforeEach
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

    public void executeRandomCommand(long seed, PieceModel pModel, Observer<ArenaModel> gui, ArenaModel gameModel, PieceController pController) {
        Random random = new Random(seed);
        Integer r = random.nextInt(6);

        switch (r) {
            case 0:
                new MoveLeft(pModel, gui, gameModel).execute(pController);
                break;
            case 1:
                new MoveRight(pModel, gui, gameModel).execute(pController);
                break;
            case 2:
                new MoveDown(pModel, gui, gameModel, false).execute(pController);
                break;
            case 3:
                new RotateClockWise(pModel, gui, gameModel).execute(pController);
                break;
            case 4:
                new MoveDown(pModel, gui, gameModel, true).execute(pController);
                break;
            case 5:
                new RotateCounterClockWise(pModel, gui, gameModel).execute(pController);
                break;
        }
    }

    @Property(tries = 250)
    public void checkTryMoveDown(@ForAll long seed) {
        arenaModel2 = new ArenaModel();
        observerMock2 = mock(Observer.class);
        arenaController2 = new ArenaController(observerMock2, arenaModel2);

        when(observerMock2.getHeight()).thenReturn(20);
        when(observerMock2.getWidth()).thenReturn(20);

        arenaController2.setRandomSeed(seed);

        for (int times = 0; times < 25; times++) {
            arenaController2.nextPiece();
            int counter = 0;

            for (int i = 0; i < 25; i++) {
                executeRandomCommand(seed, arenaController2.getCurrentPieceController().getPieceModel(), observerMock2,
                        arenaModel2, arenaController2.getCurrentPieceController());
                counter = arenaController2.tryMoveDown(counter, 1);
            }

            assert (0 <= arenaModel2.getCurrentPieceModel().getMinYPosition().getY());
            assert (observerMock2.getHeight() - 1 >= arenaModel2.getCurrentPieceModel().getMaxYPosition().getY());

            assert (observerMock2.getWidth() - 1 >= arenaModel2.getCurrentPieceModel().getMaxXPosition().getX());
            assert (0 <= arenaModel2.getCurrentPieceModel().getMinXPosition().getX());
        }
    }
}
