package ReversiGame;

public class Point {

    private int x;
    private int y;

    /**
     * constructor.
     * @param i x value
     * @param j y value
     */
    public Point(int i, int j) {
        this.x = i;
        this.y = j;
    }
    /**
     * getX function - returns the x value of the current point.
     */
    public int getX() {
        return this.x;
    }
    /**
     * getY function - returns the y value of the current point.
     * @return y value.
     */
    public int getY() {
        return this.y;
    }
    /**
     * setX function - sets the x value of the current point.
     * @param yV - x value.
     * @return y value.
     */
    public void setY(int yV) {
        this.y = yV;
    }
    /**
     * setX function - sets the x value of the current point.
     * @param xV - x value.
     * @return x value.
     */
    public void setX(int xV) {
        this.x = xV;
    }
    /**
     * setPoint function - sets the point with the given values.
     *
     * @param xV - x value.
     * @param yV - y value.
     */
    public void setPoint(int xV, int yV) {
        this.x = xV;
        this.y = yV;
    }

    /**
     * equals function.
     * check if two points equal.
     * @param p point
     * @return true if equal else false
     */
    public boolean equals(Point p) {
        if (this.x == p.getX() && this.y == p.getY()) {
            return true;
        }
        return false;
    }

    /**
     * toString.
     * @return string represention of point
     */
    public String toString() {
        String s = "(" + this.getX() + "," + this.getY() + ")";
        return s;
    }

}