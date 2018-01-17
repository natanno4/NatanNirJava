package ReversiGame;

import javafx.scene.paint.Color;

import java.util.List;

public class PlayerGui extends Player {

    private Color color;

    /**
     * constructor.
     * @param sign sign of player
     * @param color color of discs
     */
    public PlayerGui(char sign, Color color) {
        super(sign);
        this.color = color;
    }

    @Override
    public Point playerMove(List<Point> v, Board b) {
        return null;
    }

    /**
     * get color of discs.
     * @return color
     */
    public Color getColor() {
        return this.color;
    }
}