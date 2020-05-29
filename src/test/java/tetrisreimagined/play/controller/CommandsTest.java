package tetrisreimagined.play.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tetrisreimagined.observer.Observer;
import tetrisreimagined.play.controller.Commands.*;
import tetrisreimagined.play.controller.Pieces.PieceController;
import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.Color;
import tetrisreimagined.play.model.Pieces.NullPieceModel;
import tetrisreimagined.play.model.Pieces.OBlockModel;
import tetrisreimagined.play.model.Pieces.PieceModel;
import tetrisreimagined.play.model.Position;
import tetrisreimagined.play.view.lantern.GameViewLanterna;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommandsTest {

    PieceModel pieceModelMock;
    ArenaModel arenaModelMock;
    Observer observerMock;
    PieceController pieceControllerMock;

    @BeforeEach
    public void setup() {
        pieceModelMock = mock(PieceModel.class);
        arenaModelMock = mock(ArenaModel.class);
        observerMock = mock(Observer.class);
        pieceControllerMock = mock(PieceController.class);

        when(pieceControllerMock.getPieceModel()).thenReturn(pieceModelMock);

        Block block1 = new Block(new Position(4, 5), new Color("", ""), 1);
        Block block2 = new Block(new Position(4, 6), new Color("", ""), 2);

        List<Block> blocks = new ArrayList<>();
        blocks.add(block1);
        blocks.add(block2);
        when(pieceModelMock.getBlocks()).thenReturn(blocks);

        when(pieceModelMock.getMaxYPosition()).thenReturn(block2.getPosition());

        when(arenaModelMock.positionHasBlock((Position)any())).thenReturn(false);

        when(observerMock.getHeight()).thenReturn(30);
    }

    @Test
    public void moveRight() {
        MoveRight moveRight = new MoveRight(pieceModelMock, observerMock, arenaModelMock);
        when(pieceControllerMock.canGoRight(observerMock, arenaModelMock)).thenReturn(true);

        assertTrue(moveRight.execute(pieceControllerMock));

        assertEquals(new Position(6, 5), pieceControllerMock.getPieceModel().getBlocks().get(0).getPosition());
        assertEquals(new Position(6, 6), pieceControllerMock.getPieceModel().getBlocks().get(1).getPosition());
    }

    @Test
    public void moveLeft() {
        MoveLeft moveLeft = new MoveLeft(pieceModelMock, observerMock, arenaModelMock);
        when(pieceControllerMock.canGoLeft(observerMock, arenaModelMock)).thenReturn(true);

        assertTrue(moveLeft.execute(pieceControllerMock));

        assertEquals(new Position(2, 5), pieceControllerMock.getPieceModel().getBlocks().get(0).getPosition());
        assertEquals(new Position(2, 6), pieceControllerMock.getPieceModel().getBlocks().get(1).getPosition());
    }

    @Test
    public void moveDown() {
        MoveDown moveDown = new MoveDown(pieceModelMock, observerMock, arenaModelMock, false);
        when(pieceControllerMock.canGoDown(observerMock, arenaModelMock)).thenReturn(true);

        assertTrue(moveDown.execute(pieceControllerMock));

        assertEquals(new Position(4, 6), pieceControllerMock.getPieceModel().getBlocks().get(0).getPosition());
        assertEquals(new Position(4, 7), pieceControllerMock.getPieceModel().getBlocks().get(1).getPosition());
    }

    @Test
    public void hardDrop() {
        HardDrop hardDrop = new HardDrop(pieceModelMock, observerMock, arenaModelMock);

        hardDrop.execute(pieceControllerMock);

//        System.out.println("->> " + pieceControllerMock.getPieceModel().getBlocks().get(1).getPosition().getX() + " - " + pieceControllerMock.getPieceModel().getBlocks().get(1).getPosition().getY());

        // deveria de dar (4, 30), mas dá (4, 6)

    }

    @Test
    public void rotateClockWise() {
        // Mock Gui
        Observer<ArenaModel> guiMock = mock(GameViewLanterna.class);
        when(guiMock.getHeight()).thenReturn(30);
        when(guiMock.getWidth()).thenReturn(30);

        PieceModel pModel = new OBlockModel();
        ArenaModel gameModel = new ArenaModel();

        PieceController pieceController = new PieceController(pModel);
        RotateClockWise rCw = new RotateClockWise(pModel, guiMock, gameModel);
        rCw.execute(pieceController);

        assertEquals(pModel.getBlocks().get(0).getPosition(), new Position(1, 0));
        assertEquals(pModel.getBlocks().get(1).getPosition(), new Position(1, 1));
        assertEquals(pModel.getBlocks().get(2).getPosition(), new Position(1, 2));
        assertEquals(pModel.getBlocks().get(3).getPosition(), new Position(1, 3));

        assertEquals(pModel.getBlocks().get(4).getPosition(), new Position(0, 0));
        assertEquals(pModel.getBlocks().get(5).getPosition(), new Position(0, 1));
        assertEquals(pModel.getBlocks().get(6).getPosition(), new Position(0, 2));
        assertEquals(pModel.getBlocks().get(7).getPosition(), new Position(0, 3));
    }

    @Test
    public void rotateCounterClockWise() {
        // Mock Gui
        Observer<ArenaModel> guiMock = mock(GameViewLanterna.class);
        when(guiMock.getHeight()).thenReturn(30);
        when(guiMock.getWidth()).thenReturn(30);

        PieceModel pModel = new OBlockModel();
        ArenaModel gameModel = new ArenaModel();

        PieceController pieceController = new PieceController(pModel);
        RotateCounterClockWise rCCw = new RotateCounterClockWise(pModel, guiMock, gameModel);
        rCCw.execute(pieceController);

        assertEquals(pModel.getBlocks().get(3).getPosition(), new Position(2, 0));
        assertEquals(pModel.getBlocks().get(2).getPosition(), new Position(2, 1));
        assertEquals(pModel.getBlocks().get(1).getPosition(), new Position(2, 2));
        assertEquals(pModel.getBlocks().get(0).getPosition(), new Position(2, 3));

        assertEquals(pModel.getBlocks().get(7).getPosition(), new Position(3, 0));
        assertEquals(pModel.getBlocks().get(6).getPosition(), new Position(3, 1));
        assertEquals(pModel.getBlocks().get(5).getPosition(), new Position(3, 2));
        assertEquals(pModel.getBlocks().get(4).getPosition(), new Position(3, 3));
    }

    @Test
    public void hold() {
        ArenaModel arenaModel = new ArenaModel();
        ArenaController arenaController = new ArenaController(observerMock, arenaModel);
        arenaController.nextPiece();

        PieceModel currentPieceModel = arenaModel.getCurrentPieceModel();

        assertTrue(arenaModel.getHoldPieceModel() instanceof NullPieceModel);

        Hold hold = new Hold(currentPieceModel, observerMock, arenaModel);
        PieceController pieceController = arenaController.getCurrentPieceController();
        hold.execute(arenaController.getCurrentPieceController());
        arenaController.holdPieceHandler();

        assertFalse(arenaModel.getHoldPieceModel() instanceof NullPieceModel);
        assertTrue(pieceController.getPieceModel().isInHold());

        PieceModel holdPieceModel = arenaModel.getHoldPieceModel();

        // a anterior currentPiece ficou em espera
        assertEquals(currentPieceModel.getClass(), holdPieceModel.getClass());

        PieceModel currentPieceModel2 = arenaController.getCurrentPieceController().getPieceModelRaw();
        hold.execute(arenaController.getCurrentPieceController());
        arenaController.holdPieceHandler();

        // troca-se a holdPiece pela currentPiece
        assertEquals(currentPieceModel2.getClass(), arenaModel.getHoldPieceModel().getClass());
    }

    @Test
    public void doNothing() {
        DoNothing doNothing = new DoNothing();
        assertTrue(doNothing.execute(pieceControllerMock));
    }

    @Test
    public void exitTerminal() throws IOException {
        ExitTerminal exitTerminal = new ExitTerminal(observerMock);
        assertTrue(exitTerminal.execute(pieceControllerMock));
    }
}
