
import java.util.ArrayList;
import java.util.List;

public class StandardLogic implements GameLogic {

    private List<Point> pointPossible;

    //constructor
    public StandardLogic() {
        pointPossible = new ArrayList<Point>();
    }


    /**
     * checkForPointInGame function - check if the given point is
     * possible point.
     *
     * @param p the point.
     * @return true if yes, false if not.
     */
    public Boolean checkForPointInGame(Point p) {
        for(Point point : pointPossible) {
            if (p.equals(point)) {
                return true;
            }
        }
        return false;

    }

    /**
     * getCompetitor function - returns the rival of the given player sign.
     *
     * @param s the player sign.
     * @return the rival sign of the given player sign
     */
    private char getCompetitor(char s) {
        if(s == 'X') {
            return 'O';
        }
        return 'X';
    }


    /**
     * checkSequence function - the function checks around the given rival to
     * add a possible point to move.the function runs to the given direction
     * and search for an empty space.
     *
     * @param b the game board.
     * @param rival - the rival sign.
     * @param rowOperator - determine the check direction for the row index.
     * @param colOperator - determine the check direction for the col index.
     * @param i -  the roe index.
     * @param j - the j col index.
     */
    private void checkSequence(Board b, char rival, int rowOperator,
                                      int colOperator, int i, int j) {
        while ((i > -1) && (j > -1) && (i < b.getSize()) && (j < b.getSize())) {
            if (b.getSign(i,j) == ' ') {
                Point point= new Point(i + 1, j + 1);
                if(!checkForPointInGame(point)) {
                    pointPossible.add(point);
                }
                return;
            }
            //check for rival sequence
            if (b.getSign(i, j) == rival) {
                i = i + rowOperator;
                j = j + colOperator;
                continue;
            }
            return;
        }
    }

    /**
     * checkSorrounding function - the function checks all 8 possible direction
     * of the given indexes for its rival and puts the possible apply points
     * in the vector using checkSequence, or using checkSequenceAndAplly
     * to apply the points after the player move.
     *
     * @param s the player sign.
     * @param b the game board.
     * @param i the row index of with the sign.
     * @param j the col index of with the sign.
     * @param option - true for search and put, false for search and apply.
     */
    private void checkSorrounding(char s, Board b, int i, int j, boolean option) {
        char rival = getCompetitor(s);
        //check for each 8 possible sides:
        if ((i - 2 > -1) && (j - 2 > -1) && (b.getSign(i - 1, j - 1) == rival)) {
            if(option) {
                checkSequence(b, rival, -1, -1, i - 2, j - 2);
            } else {
                checkSequenceAndAplly(b, rival, -1, -1, i - 2, j - 2);
            }
        }
        if ((i - 2 > -1)  && (b.getSign(i -1, j) == rival)) {
            if(option) {
                checkSequence(b, rival, -1, 0, i - 2, j);
            } else {
                checkSequenceAndAplly(b, rival, -1, 0, i - 2, j);
            }
        }
        if ((i - 2 > -1) && (j + 2 < b.getSize()) && (b.getSign(i - 1, j + 1) == rival)) {
            if(option) {
                checkSequence(b, rival, -1, 1, i - 2, j + 2);
            } else {
                checkSequenceAndAplly(b, rival, -1, 1, i - 2, j + 2);
            }
        }
        if ((j + 2 < b.getSize()) && (b.getSign(i, j + 1)== rival)) {
            if(option) {
                checkSequence(b, rival, 0, 1, i, j + 2);
            } else {
                checkSequenceAndAplly(b, rival, 0, 1, i, j + 2);
            }
        }
        if ((i + 2 < b.getSize()) && (j + 2 < b.getSize()) && (b.getSign(i + 1, j + 1) == rival)) {
            if(option) {
                checkSequence(b, rival, 1, 1, i + 2, j + 2);
            } else {
                checkSequenceAndAplly(b, rival, 1, 1, i + 2, j + 2);
            }
        }
        if ((i + 2 < b.getSize()) && (b.getSign(i + 1, j) == rival)) {
            if(option) {
                checkSequence(b, rival, 1, 0, i + 2, j);
            } else {
                checkSequenceAndAplly(b, rival, 1, 0, i + 2, j);
            }
        }
        if ((i + 2 < b.getSize()) && (j - 2 > -1) && (b.getSign(i + 1, j - 1) == rival)) {
            if(option) {
                checkSequence(b, rival, 1, -1, i + 2, j - 2);
            } else {
                checkSequenceAndAplly(b, rival, 1, -1, i + 2, j - 2);
            }
        }
        if ((j - 2 > -1) && (b.getSign(i, j - 1) == rival)) {
            if(option) {
                checkSequence(b, rival, 0, -1, i, j- 2);
            } else {
                checkSequenceAndAplly(b, rival, 0, -1, i, j- 2);
            }

        }

    }

    /**
     * checkSequenceAndAplly function - the function checks around the given rival to
     * given direction and apply the player sign instead of the rival disc.
     * the function will "flip" rival disc only if it will find the player sign in the
     * sequence.
     *
     * @param b the game board.
     * @param rival - the rival sign.
     * @param rowOperator - determine the check direction for the row index.
     * @param colOperator - determine the check direction for the col index.
     * @param i -  the roe index.
     * @param j - the j col index.
     */
    private void checkSequenceAndAplly(Board b, char rival, int rowOperator,
                               int colOperator, int i, int j) {
        while ((i > -1) && (j > -1) && (i < b.getSize()) && (j < b.getSize())) {
            if (b.getSign(i,j) == getCompetitor(rival)) {
                //go back to flip rivals
                i = i + (-1 * rowOperator);
                j = j + (-1 * colOperator);
                while(b.getSign(i,j) != getCompetitor(rival)) {
                    //flip rival and go back
                    b.putSign(i,j,
                            getCompetitor(rival));
                    i = i + (-1 * rowOperator);
                    j = j + (-1 * colOperator);
                }
                return;
            }
            //rival sequence
            if (b.getSign(i, j) == rival) {
                i = i + rowOperator;
                j = j + colOperator;
                continue;
            }
            return;
        }
    }


    @Override
    public List<Point> possiblePoints(Board b, char s) {

        for(int i = 0; i < b.getSize();i++) {
            for(int j = 0; j < b.getSize();j++){
                //check for player sign in the table
                if(b.getSign(i, j) == s) {
                    checkSorrounding(s, b, i, j, true);
                }
            }
        }
        return pointPossible;

    }
    @Override
    public void applyMoveWithGivenPoint(Board b, Point p, char s) {
        b.putSign(p.getX() - 1, p.getY() -1, s);
        //using apply function
        checkSorrounding(s, b, p.getX() - 1 , p.getY() - 1 , false);
        pointPossible.clear();
    }

}
