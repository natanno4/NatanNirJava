package ReversiGame;

import java.util.List;

public abstract class Player {
    static final char firstPlayerSign = 'X';
    static final char secondPlayerSign = 'O';
    private char sign;
    private Point lastPlay;
    private int score;
    public Player(char si) {
        this.sign = si;
        this.score = 2;
    }
    public char getSign() {
        return this.sign;
    }


    public void setLastPlayPoint(Point p){
        lastPlay = p;
    }


    public Point getLastPlayPoint() {
        return this.lastPlay;
    }

    abstract public Point playerMove(List<Point> v, Board b);

    public void updatePlayerScore(Board b) {
        if (sign == firstPlayerSign) {
           this.score = b.getNumberOfX();
        } else {
            this.score = b.getNumberOfO();
        }
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}