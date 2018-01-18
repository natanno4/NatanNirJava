package ReversiGame;

import java.awt.*;
import java.util.List;

public interface GameLogic {

    /**
     * GameLogic function - returns the possible points to make the
     * move for the given player(sign).
     *
     * @param - b the game board.
     * @param s the sign of the player.
     */
    List<Point> possiblePoints(Board b, char s);

    /**
     * applyMoveWithGivenPoint function - applies the given point in the board.
     * the function also applies (flips) the rival discs around the given point.
     *
     * @param - b the game board.
     * @param s the sign of the player.
     */
    void applyMoveWithGivenPoint(Board b, Point p, char s);

}
