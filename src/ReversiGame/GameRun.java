package ReversiGame;


import java.util.List;

public class GameRun {

    private Board board;
    private GameLogic gameLogic;
    private Player playerOne;
    private Player playerTwo;
    private Player currentTurn;
    private Player noTurn;

    static final char firstPlayerSign = 'X';
    static final char secondPlayerSign = 'O';
    static final boolean noMove = false;
    static final boolean move = true;
    private Player noMovePlayer;

    /**
     * constructor.
     * @param b board
     * @param gameLogic logic of game
     * @param playerOne player one
     * @param playerTwo player two
     */
    public GameRun(Board b, GameLogic gameLogic, Player playerOne,
                   Player playerTwo) {
        this.board = b;
        this.gameLogic = gameLogic;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.currentTurn = playerOne;
        this.noTurn = playerTwo;

        this.noMovePlayer = null;

    }

    /**
     * playOneTurn function.
     * receive a point that the current player wish to play, check if it is a valid
     * point and arrange the turns if the second player doesnt have one.
     * @param point point to play
     * @return bool
     */
    public boolean playOneTurn(Point point) {

        List<Point> points = this.gameLogic.possiblePoints(this.board,
                currentTurn.getSign());
        int size = points.size();
        for (int i = 0; i < size; i++) {
            Point pnt = new Point(points.get(i).getX(), points.get(i).getY());
            if (pnt.equals(point)) {
                gameLogic.applyMoveWithGivenPoint(board, pnt,
                        this.currentTurn.getSign());
                this.currentTurn.setLastPlayPoint(pnt);
                this.switchTurns();
                this.setPlayerScore();
                if (this.checkIfNoMove(this.currentTurn)) {
                    this.noMovePlayer = this.currentTurn;
                    this.switchTurns();
                } else {
                    this.noMovePlayer = null;
                }
                return true;
            }
        }
        return false;


    }

    /**
     * switchTurns function.
     * switch turns between to players.
     */
    private void switchTurns() {
        Player temp = this.noTurn;
        this.noTurn = this.currentTurn;
        this.currentTurn = temp;
    }

    /**
     * noMoveForBothPlayers.
     * check if both players doesnt have turn to play.
     * @return true if both dont have else false.
     */
    public boolean noMoveForBothPlayers() {

        if (this.checkIfNoMove(this.currentTurn)) {
            if (this.checkIfNoMove(this.noTurn)) {
                return true;
            }
        }
        return false;
    }

    /**
     * checkEndGame function.
     * check if the board is full of discs OR no one has a turn to play.
     * @return true if end else false
     */
    public boolean checkEndGame() {
        if ((this.noMoveForBothPlayers()) || this.board.checkIfTableFull()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * whoWon function.
     * count number of discs of both players and returns the winner player
     * @return winner
     */
    public Player whoWon() {
        if (this.board.getNumberOfX() > this.board.getNumberOfO()) {
            if (this.playerOne.getSign() == firstPlayerSign) {
                return this.playerOne;
            } else {
                return this.playerTwo;
            }
        }
        if (this.board.getNumberOfX() < this.board.getNumberOfO()) {
            if (this.playerOne.getSign() == secondPlayerSign) {
                return this.playerOne;
            } else {
                return this.playerTwo;
            }
        }

        return  null;
    }

    /**
     * whoPlay function.
     * @return who is playing
     */
    public Player whoPlay() {
        return this.currentTurn;
    }

    /**
     * setPlayerScore function.
     * set the score of each player.
     */
    public void setPlayerScore() {
        if (this.playerOne.getSign() == firstPlayerSign) {
            this.playerOne.setScore(this.board.getNumberOfX());
            this.playerTwo.setScore(this.board.getNumberOfO());
        } else {
            this.playerTwo.setScore(this.board.getNumberOfX());
            this.playerOne.setScore(this.board.getNumberOfO());
        }
    }

    /**
     * checkIfNoMove function.
     * check, by a given player, if he can do a move.
     * @param player player
     * @return true if he have turn to play else false
     */
    public boolean checkIfNoMove(Player player) {
        List<Point> points = this.gameLogic.possiblePoints(this.board,
                player.getSign());
        if (points.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * getNoMovePlayer.
     * @return who isnt playing
     */
    public Player getNoMovePlayer() {
        return this.noMovePlayer;
    }


}