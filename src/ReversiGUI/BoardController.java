package ReversiGUI;



import java.io.IOException;

import ReversiGame.Board;
import ReversiGame.Player;
import ReversiGame.PlayerGui;
import ReversiGame.Point;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Box;
public class BoardController extends GridPane {

    private Board board;
    private PlayerGui pOne;
    private PlayerGui pTwo;

    /**
     * constructor.
     * @param board board of game
     * @param one player one
     * @param two player two
     */
    public BoardController(Board board, PlayerGui one, PlayerGui two) {
        this.board = board;
        this.pOne = one;
        this.pTwo = two;
        /**
         * loading the board xml file.
         */
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReversiBoardXML.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * draw function.
     * draw the board accord to his size, and also draw
     * the discs of each players.
     */
    public void draw() {
        this.getChildren().clear();
        int height = (int) this.getPrefHeight();
        int width = (int) this.getPrefWidth();
        int length = this.board.getSize();
        int cellWidth = (width / length);
        int cellHeight = (height / length) - 1;
        Rectangle r;
        Circle c ;
        //draw board and circles.
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                r = new Rectangle(cellWidth, cellHeight,Color.BEIGE);
                r.setStroke(Color.BLACK);
                this.add(r, j, i);
                if (board.getSign(i, j) == pOne.getSign()) {
                    c = new Circle(cellWidth / 2, cellHeight / 2,
                            (cellWidth / 2) - (cellWidth / 3),
                            this.pOne.getColor());
                    c.setStroke(Color.BLACK);
                    c.setStrokeWidth(1);
                    setHalignment(c, HPos.CENTER);
                    setValignment(c, VPos.CENTER);
                    this.add(c, j , i);
                }
                if(board.getSign(i, j) == this.pTwo.getSign()) {
                    c = new Circle(cellWidth / 2, cellHeight / 2,
                            (cellWidth / 2) - (cellWidth / 3), pTwo.getColor());
                    c.setStroke(Color.BLACK);
                    setHalignment(c, HPos.CENTER);
                    setValignment(c, VPos.CENTER);
                    this.add(c, j , i);
                }

            }

        }

    }

    /**
     * convertToTableBlock function.
     * receive values which was pressed in check the position in board.
     * @param x xvalue
     * @param y y value
     * @return cell int board
     */
    public Point convertToTableBlock(double x, double y) {
        int pX = ((int) x /(int) (this.getPrefWidth() / this.board.getSize())) + 1;
        int pY = ((int) y / (int) (this.getPrefHeight() / this.board.getSize())) + 1;
        return new Point(pY, pX);
    }
}