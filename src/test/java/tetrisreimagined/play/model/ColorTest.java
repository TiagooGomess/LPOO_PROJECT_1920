package tetrisreimagined.play.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColorTest {
    private List<Color> colors;

    public void setup() {
        colors = new ArrayList<>();
        colors.add(new Color("cyan", "#00FFFF"));
        colors.add(new Color("blue", "#0000FF"));
        colors.add(new Color("Orange", "#FFA500"));
    }

    @Test
    public void getCode1() {
        setup();
        assertEquals("#00FFFF", colors.get(0).getCode());
    }

    @Test
    public void getCode2() {
        setup();
        assertEquals("#0000FF", colors.get(1).getCode());
    }

    @Test
    public void getCode3() {
        setup();
        assertEquals("#FFA500", colors.get(2).getCode());
    }
}
