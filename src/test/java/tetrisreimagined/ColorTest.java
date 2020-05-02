package tetrisreimagined;

import org.junit.Before;
import org.junit.Test;
import tetrisreimagined.play.model.Color;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ColorTest {
    private List<Color> colors;

    @Before
    public void setup() {
        colors = new ArrayList<>();
        colors.add(new Color("cyan", "#00FFFF"));
        colors.add(new Color("blue", "#0000FF"));
        colors.add(new Color("Orange", "#FFA500"));
    }

    @Test
    public void getCode1() {
        assertEquals("#00FFFF", colors.get(0).getCode());
    }

    @Test
    public void getCode2() {
        assertEquals("#0000FF", colors.get(1).getCode());
    }

    @Test
    public void getCode3() {
        assertEquals("#FFA500", colors.get(2).getCode());
    }
}
