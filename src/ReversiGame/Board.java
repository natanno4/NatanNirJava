package ReversiGame;


public class Board {
    private char[][] board;
    private int size;
    private int numberOfX;
    private int numberOfO;

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
        /*
        this.board[3][3] = 'O';
        this.board[4][4] = 'O';
        this.board[3][4] = 'X';
        this.board[4][3] = 'X';
        */
        this.board[(this.size / 2) -1][(this.size / 2) -1] = 'O';
        this.board[(this.size / 2)][(this.size / 2)] = 'O';
        this.board[(this.size / 2) - 1][this.size / 2] = 'X';
        this.board[this.size / 2][(this.size / 2) - 1] = 'X';
    }
    public void print() {
        System.out.print(" |");
        for(int j = 0; j < this.size; j++) {
            System.out.print(" " + (j+1) + " |");
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
    public int getNumberOfX(){
        return this.numberOfX;
    }
    public int getNumberOfO(){
        return this.numberOfO;
    }
    public boolean checkIfTableFull() {
        if ((this.size * this.size) == this.numberOfX + this.numberOfO) {
            return true;
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    public char getSign(int i, int j) {
        return this.board[i][j];

    }
}