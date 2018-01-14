package ReversiGUI;



import java.io.IOException;

import ReversiGame.Board;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Box;
public class BoardController extends GridPane {
    private Board board;

    public BoardController(Board board) {
        this.board = board;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReversiBoard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void draw() {
        this.getChildren().clear();
        int height = (int) this.getPrefHeight();
        int width = (int) this.getPrefWidth();
        int length = this.board.getSize();
        int cellHeight = height / length;
        int cellWidth = width / length;
        Rectangle r;
        Circle c ;
        //r.setStroke(Color.BLACK);
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                r = new Rectangle(cellWidth, cellHeight,Color.WHITE);
                r.setStroke(Color.BLACK);

                c = new Circle(cellWidth / 2   ,cellHeight / 2,
                        (cellWidth / 2) - (cellWidth / 6)  , Color.YELLOW);
                c.setStroke(Color.BLACK);
                this.add(r, j, i);
                if (board.getSign(i, j) != ' ') {
                    this.add(c, j , i);
                }



            }

        }

    }
}
