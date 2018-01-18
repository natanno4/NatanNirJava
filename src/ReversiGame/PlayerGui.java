package ReversiGame;

import javafx.scene.paint.Color;

import java.util.List;

public class PlayerGui extends Player {

    private Color color;

    public PlayerGui(char sign, Color color) {
        super(sign);
        this.color = color;
    }

    @Override
    public Point playerMove(List<Point> v, Board b) {
        return null;
    }

    public Color getColor() {
        return this.color;
    }
}
