package model;

import org.junit.Before;
import org.junit.Test;
import tetrisreimagined.play.model.Position;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PositionTest {
    private List<Position> positions;

    @Before
    public void setup() {
        positions = new ArrayList<>();
        positions.add(new Position(0, 0));
        positions.add(new Position(4, 7));
        positions.add(new Position(24, 37));
        positions.add(new Position(11, 2));
        positions.add(new Position(5, 5));
    }

    @Test
    public void position1() {
        Position p1 = new Position(positions.get(0).getX(), positions.get(0).getY());
        assertEquals(positions.get(0), p1);
    }

    @Test
    public void position2() {
        Position p2 = new Position(positions.get(4).getX(), positions.get(4).getY());
        assertEquals(positions.get(4), p2);
    }

    @Test
    public void getPosition1() {
        assertEquals(11, positions.get(3).getX());
        assertEquals(2, positions.get(3).getY());
    }

    @Test
    public void getPosition2() {
        assertEquals(4, positions.get(1).getX());
        assertEquals(7, positions.get(1).getY());
    }

    // IMPORTANTE: O X VARIA SEMPRE DE 2 EM 2

    @Test
    public void left1() {
        assertEquals(22, positions.get(2).left().getX());
        assertEquals(37, positions.get(2).left().getY());
    }

    @Test
    public void left2() {
        assertEquals(2, positions.get(1).left().getX());
        assertEquals(7, positions.get(1).left().getY());
    }

    @Test
    public void right1() {
        assertEquals(26, positions.get(2).right().getX());
        assertEquals(37, positions.get(2).right().getY());
    }

    @Test
    public void right2() {
        assertEquals(6, positions.get(1).right().getX());
        assertEquals(7, positions.get(1).right().getY());
    }

    @Test
    public void up1() {
        assertEquals(24, positions.get(2).up().getX());
        assertEquals(36, positions.get(2).up().getY());
    }

    @Test
    public void up2() {
        assertEquals(4, positions.get(1).up().getX());
        assertEquals(6, positions.get(1).up().getY());
    }

    @Test
    public void down1() {
        assertEquals(24, positions.get(2).down().getX());
        assertEquals(38, positions.get(2).down().getY());
    }

    @Test
    public void down2() {
        assertEquals(4, positions.get(1).down().getX());
        assertEquals(8, positions.get(1).down().getY());
    }

    @Test
    public void positionEquals1() {
        Position p1 = new Position(4, 7);
        assertEquals(p1, positions.get(1));
    }

    @Test
    public void positionEquals2() {
        Position p1 = new Position(24, 37);
        assertTrue(p1.equals(positions.get(2)));
    }

    @Test
    public void positionEquals3() {
        assertFalse(positions.get(0).equals(null));
    }


}
