import java.util.ArrayList;
import java.util.List;

public class GameFlow {

    private Player  turn;
    private Player  playerX;
    private Player  playerO;
    private GameLogic  logic;
    private GameDisplay  display;
    private Player noTurn;
    private Board  b;
    private boolean noMovesX;
    private boolean noMovesO;
    private boolean senderMove;


    /**
     * GameFlow function - creates a game flow object.
     * runs the game
     *
     *@param b the game board.
     */
    public GameFlow(Board b) {
        this.logic = new StandardLogic();
        this.noMovesX = false;
        this.noMovesO = false;
        this.senderMove = true;
        playerX = new StandardPlayer('X');
        playerO = new StandardPlayer('O');
        turn = playerX;
        noTurn = playerX;
    }


    /**
     * playOneTurn function - runs one turn- logic + display.
     *
     */
    public void playOneTurn() {
        //get possible point for the current player and turn.
        List<Point> points = logic.possiblePoints(b, turn.getSign());
        //display turn
        display.displayGame(b, points, turn, noTurn);
        //handle no moves situation
        if (points.isEmpty()) {
            if (turn.getSign() == 'X') {
                noMovesX = true;
            } else {
                noMovesO = true;
            }
            return;
        } else {
            noMovesX = false;
            noMovesX = false;
        }
        //gets player point
        Point p = turn.playerMove(points, b);
        turn.setLastPlayPoint(p);
        //apply the point in the game
        logic.applyMoveWithGivenPoint(b, p, turn.getSign());
    }



    /**
     * runGame function - runs the game.
     * changes turns.
     * checks who wins.
     *
     */
    public void runGame() {
        while((!b.checkIfTableFull())) {
            if ((noMovesX) && (noMovesO)) {
                break;
            }
            this.playOneTurn();
            //change turns
            if(turn.getSign() == 'X') {
                turn = playerO;
                noTurn = playerX;
            } else {
                turn = playerX;
                noTurn = playerO;
            }
        }
        //player O won.
        if(b.getNumberOfO() > b.getNumberOfX()) {
            display.endGameDisplay(playerO, playerX, b.getNumberOfO(), b.getNumberOfX()
                    , false,b);
            return;
        }
        //player X won.
        if(b.getNumberOfO() < b.getNumberOfX()) {
            display.endGameDisplay(playerX, playerO,b.getNumberOfX(), b.getNumberOfO()
                    , false, b);
            return;
        }
        //draw.
        display.endGameDisplay(playerX, playerO ,b.getNumberOfX(), b.getNumberOfO(),
                true, b);

    }


}
