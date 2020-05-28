package tetrisreimagined.play.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BlockTest {
    private List<Block> blocks;

    @Before
    public void setup() {
        blocks = new ArrayList<>();

        // First Block
        Position positionMock1 = mock(Position.class);
        when(positionMock1.getX()).thenReturn(4);
        when(positionMock1.getY()).thenReturn(5);

        Color colorMock1 = mock(Color.class);
        when(colorMock1.getCode()).thenReturn("#0000FF");

        Block blockMock1 = new Block(positionMock1, colorMock1, 1);
        blocks.add(blockMock1);

        // Second Block
        Position positionMock2 = mock(Position.class);
        when(positionMock2.getX()).thenReturn(7);
        when(positionMock2.getY()).thenReturn(3);

        Color colorMock2 = mock(Color.class);
        when(colorMock2.getCode()).thenReturn("#FF00FF");

        Block blockMock2 = new Block(positionMock2, colorMock2, 2);
        blocks.add(blockMock2);

        // Third Block
        Position positionMock3 = mock(Position.class);
        when(positionMock3.getX()).thenReturn(43);
        when(positionMock3.getY()).thenReturn(57);

        Color colorMock3 = mock(Color.class);
        when(colorMock3.getCode()).thenReturn("#FF0000");

        Block blockMock3 = new Block(positionMock3, colorMock3, 3);
        blocks.add(blockMock3);
    }

    @Test
    public void getId1() {
        assertEquals(1, blocks.get(0).getId());
    }

    @Test
    public void getId2() {
        assertEquals(2, blocks.get(1).getId());
    }

    @Test
    public void getPosition1() {
        Position positionMock = mock(Position.class);
        when(positionMock.getX()).thenReturn(2);
        when(positionMock.getY()).thenReturn(6);

        blocks.get(0).setPosition(positionMock);

        assertEquals(2, blocks.get(0).getPosition().getX());
        assertEquals(6, blocks.get(0).getPosition().getY());
    }

    @Test
    public void getPosition2() {
        Position positionMock = mock(Position.class);
        when(positionMock.getX()).thenReturn(42);
        when(positionMock.getY()).thenReturn(55);

        blocks.get(2).setPosition(positionMock);

        assertEquals(42, blocks.get(2).getPosition().getX());
        assertEquals(55, blocks.get(2).getPosition().getY());
    }

    @Test
    public void getColor1() {
        Color colorMock = mock(Color.class);
        when(colorMock.getCode()).thenReturn("#FF0FFF");

        blocks.get(1).setColor(colorMock);

        assertEquals("#FF0FFF", blocks.get(1).getColor().getCode());
    }

    @Test
    public void getColor2() {
        Color colorMock = mock(Color.class);
        when(colorMock.getCode()).thenReturn("#FFFFFF");

        blocks.get(2).setColor(colorMock);

        assertEquals("#FFFFFF", blocks.get(2).getColor().getCode());
    }
}
