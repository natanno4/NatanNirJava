package ReversiGame;


import java.util.List;

public class GameRun {

    private Board board;
    private GameLogic gameLogic;
    private Player playerOne;
    private Player playerTwo;
    private Player currentTurn;
    private Player noTurn;
    private boolean noMovePlOne;
    private boolean noMovePlTow;
    static final char firstPlayerSign = 'X';
    static final char secondPlayerSign = 'O';
    static final boolean noMove = false;
    static final boolean move = true;
    private Player noMovePlayer;

    public GameRun(Board b, GameLogic gameLogic, Player playerOne,
                   Player playerTwo) {
        this.board = b;
        this.gameLogic = gameLogic;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.currentTurn = playerOne;
        this.noTurn = playerTwo;
        this.noMovePlOne = false;
        this.noMovePlTow = false;
        this.noMovePlayer = null;

    }


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

/*
        List<Point> points = this.gameLogic.possiblePoints(this.board,
                currentTurn.getSign());
        int size = points.size();
        for (int i = 0; i < size; i++) {
            Point pnt = new Point(points.get(i).getX(), points.get(i).getY());
            if (pnt.equals(point)) {
                gameLogic.applyMoveWithGivenPoint(board, pnt,
                        this.currentTurn.getSign());
                this.switchTurns();
                this.setPlayerScore();

            }
            */

    }

    private void switchTurns() {
        Player temp = this.noTurn;
        this.noTurn = this.currentTurn;
        this.currentTurn = temp;
    }

    public boolean noMoveForBothPlayers() {

        if (this.checkIfNoMove(this.currentTurn)) {
            if (this.checkIfNoMove(this.noTurn)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkEndGame() {
        if ((this.noMoveForBothPlayers()) || this.board.checkIfTableFull()) {
            return true;
        } else {
            return false;
        }
    }

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

    public Player whoPlay() {
        return this.currentTurn;
    }
    ///////
    public void setPlayerScore() {
        if (this.playerOne.getSign() == firstPlayerSign) {
            this.playerOne.setScore(this.board.getNumberOfX());
            this.playerTwo.setScore(this.board.getNumberOfO());
        } else {
            this.playerTwo.setScore(this.board.getNumberOfX());
            this.playerOne.setScore(this.board.getNumberOfO());
        }
    }

    public boolean isNoMove(Player player) {
        if(player == this.playerOne) {
            if (this.noMovePlOne) {
                return true;
            }
            return false;
        }
        if (this.noMovePlTow) {
            return true;
        }
        return false;
    }

    public boolean checkIfNoMove(Player player) {
        List<Point> points = this.gameLogic.possiblePoints(this.board,
                player.getSign());
        if (points.isEmpty()) {
            return true;
        }
        return false;
    }

    public Player getComp(Player player) {
        if (player == this.playerOne) {
            return this.playerTwo;
        }
        return this.playerOne;
    }

    public Player getNoMovePlayer() {
        return this.noMovePlayer;
    }


}
