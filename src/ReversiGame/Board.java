package ReversiGame;

public class Board {
    private char[][] board;
    private int size;
    private int numberOfX;
    private int numberOfO;

    /**
     * constructor.
     * @param s size of board
     * initialize 4 discs in center of board, two for each players.
     */
    public Board(int s) {
        this.size = s;

        this.numberOfX = 2;
        this.numberOfO = 2;
        this.board = new char[this.size][this.size];

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.board[i][j] = ' ';
            }
        }
        this.board[(this.size / 2) -1][(this.size / 2) -1] = 'O';
        this.board[(this.size / 2)][(this.size / 2)] = 'O';
        this.board[(this.size / 2) - 1][this.size / 2] = 'X';
        this.board[this.size / 2][(this.size / 2) - 1] = 'X';

    }

    /**
     * print the board.
     */
    public void print() {
        System.out.print(" |");
        for(int j = 0; j < this.size; j++) {
            System.out.print(" " + j+1 + " |");
        }
        System.out.println();
        System.out.print("--");
        for(int m = 0; m < this.size; m++) {
            System.out.print("----");
        }
        System.out.println();
        for (int i = 0; i < this.size; i++) {
            System.out.print((i+1) + "| ");
            for (int j = 0; j < this.size; j++) {
                System.out.print(this.board[i][j] + " | ");
            }
            System.out.println();
            System.out.print("--");
            for (int m = 0; m < this.size; m++) {
                System.out.print("----");
            }
            System.out.println();
        }
    }
    /**
     * PutSign function .
     *  puts the given sign in the given place.
     * then updates the number of disks for each sign.
     * @param i- the row index
     * @param j - the column index.
     * @param s - the sign.

     */
    public void putSign(int i, int j, char s) {
        int countX = 0, countO = 0;
        this.board[i][j] = s;
        for (int k = 0; k < this.size; k++) {
            for (int n = 0; n < this.size; n++) {
                if(board[k][n] == 'X') {
                    countX++;
                }
                if (board[k][n] == 'O') {
                    countO ++;
                }
            }
        }
        this.numberOfX = countX;
        this.numberOfO = countO;
    }

    /**
     * getNumber of X
     * @return num discs of X
     */
    public int getNumberOfX(){
        return this.numberOfX;
    }

    /**
     * getNumber of O
     * @return num discs of O
     */
    public int getNumberOfO(){
        return this.numberOfO;
    }
    /**
     * checkIfTableFull function - returns true if the table is full,else false.
     * @return true if full else false
     */
    public boolean checkIfTableFull() {
        if ((this.size * this.size) == this.numberOfX + this.numberOfO) {
            return true;
        }
        return false;
    }
    /**
     * getSize function - returns the size of the board(nxn).
     * @return the size of the table.
     */
    public int getSize() {
        return size;
    }
    /**
     * getSign function - returns the sing at the given place.
     * @param i- the row index
     * @param j - the column index.
     * @return the sing at the given place.
     */
    public char getSign(int i, int j) {
        return board[i][j];

    }
}
