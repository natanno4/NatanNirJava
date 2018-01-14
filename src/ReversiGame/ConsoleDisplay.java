import java.util.List;
public class ConsoleDisplay extends GameDisplay {
    public ConsoleDisplay(){}

    public void displayGame(Board b, List<Point>v, Player player, Player lastTurn) {
        System.out.println("current board:");
        System.out.println();

        b.print();
        //prints the message after at least on move.
        if (!firstTurn) {
            System.out.println(lastTurn.getSign() + " played " + lastTurn.getLastPlayPoint());
            System.out.println();

        } else {
            firstTurn = false;
        }
        System.out.println(player.getSign() + ": it'ss your move");
        this.printPlayerOption(v);
    }

    private void printPlayerOption(List<Point> v) {
        int i = 1;
        //no possible points
        if (v.isEmpty()) {
            System.out.println("no possible moves. play passes back to the other player");
            return;
        }
        System.out.print("your possible moves: ");
        //print points from vector
        for (Point p : v) {
            if ((int)v.size() == i) {
                System.out.print(p);
            } else {
                System.out.print(p + ",");
            }
        }
        System.out.println();
        System.out.println();
        System.out.println("please enter your move row col: ");
    }
}