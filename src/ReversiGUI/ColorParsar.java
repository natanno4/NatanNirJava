package ReversiGUI;

import javafx.scene.paint.Color;

public class ColorParsar {
    Color parsing(String string) {
        String str = string.toLowerCase();
        if (str.equals("green")) {
            return Color.GREEN;
        }
        if (str.equals("red")) {
            return Color.RED;
        }
        if (str.equals("Yellow")) {
            return Color.YELLOW;
        }
        if (str.equals("blue")) {
            return Color.BLUE;
        }
        if (str.equals("gray")) {
            return Color.GRAY;
        }
        if (str.equals("black")) {
            return Color.BLACK;
        }
        if (str.equals("pink")) {
            return Color.PINK;
        }
        return null;
    }
}
