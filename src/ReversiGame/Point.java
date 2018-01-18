package ReversiGame;

public class Point {

    private int x;
    private int y;
    public Point(int i, int j) {
        this.x = i;
        this.y = j;
    }
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int yV) {
        this.y = yV;
    }

    public void setX(int xV) {
        this.x = xV;
    }
    public void setPoint(int xV, int yV) {
        this.x = xV;
        this.y = yV;
    }
    public boolean equals(Point p) {
        if (this.x == p.getX() && this.y == p.getY()) {
            return true;
        }
        return false;
    }

    public String toString() {
        String s = "(" + this.getX() + "," + this.getY() + ")";
        return s;
    }

}