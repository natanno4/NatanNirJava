import java.util.List;

public abstract class Player {
    private char sign;
    private Point lastPlay;
    public Player(char si) {
        this.sign = si;
    }
    public char getSign() {
        return this.sign;
    }


    public void setLastPlayPoint(Point p){
        this.lastPlay.setX(p.getX());
        this.lastPlay.setY(p.getY());
    }


    public Point getLastPlayPoint() {
        return this.lastPlay;
    }

   abstract public Point playerMove(List<Point> v, Board b);
}