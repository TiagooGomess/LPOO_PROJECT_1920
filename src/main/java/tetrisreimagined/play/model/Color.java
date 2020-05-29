package tetrisreimagined.play.model;

public class Color {

    private final String code;
    private final String name;

    public Color(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
