import java.util.List;

public abstract class GameDisplay {
    protected boolean firstTurn;
    public GameDisplay() {
        this.firstTurn = true;
    }
    public void endGameDisplay(Player winner, Player loser, int numberOfDiscW, int numberOfDiscL,
                               boolean draw, Board b) {
        System.out.println("end game table:");;
        b.print();
        if (!draw) {
            System.out.println("no more possible moves.");
            System.out.println("player" + winner.getSign() + "win");
            System.out.println("it's a draw");
        }
        //print players scores.
        System.out.println("some stats:");
        System.out.println(winner.getSign() + "end game with" + numberOfDiscW + "discs");
        System.out.println(loser.getSign() + "end game with" + numberOfDiscL + "discs");

    }
    abstract public void displayGame(Board b, List<Point> v, Player player, Player lastTurn);
}