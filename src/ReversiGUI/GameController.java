package revesiApp;

import ReversiGame.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;

import java.util.ResourceBundle;


public class GameController implements Initializable {

    @FXML
    private HBox root;
    @FXML
    private VBox vBox;
    @FXML
    private Label playerOneScore;
    @FXML
    private Label playerTwoScore;

    @FXML
    private Label currentPlayer;

    @FXML
    private Label winner;

    @FXML
    private Label noMove;

    @FXML
    private Button exitGame;
    @FXML
    private Button exit;


    private BoardController boardController;

    private Board b;
    private PlayerGui playerOne;
    private PlayerGui playerTwo;
    private GameLogic gameLogic;

    private GameRun run;
    static final char firstPlayerSign = 'X';
    static final char secondPlayerSign = 'O';
    private Player turn;
    private Player turnCheck;




    public GameController() {
        SettingsManager settingsManager = new SettingsManager();
        settingsManager.readFromFile();
        this.b = new Board(settingsManager.getSize());
        if(settingsManager.getStarter().equals(firstPlayerSign)) {
            playerOne = new PlayerGui(firstPlayerSign, settingsManager.getXcolor());
            playerTwo = new PlayerGui(secondPlayerSign, settingsManager.getOcolor());
        } else {
            playerTwo = new PlayerGui(firstPlayerSign, settingsManager.getXcolor());
            playerOne = new PlayerGui(secondPlayerSign, settingsManager.getOcolor());
        }
        this.gameLogic = new StandardLogic();
        this.run = new GameRun(b, gameLogic,playerOne, playerTwo);
        this.turn = this.playerOne;
        this.turnCheck = this.playerTwo;
        this.noMove = new Label("");


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.boardController = new BoardController(this.b, playerOne, playerTwo);
        this.boardController.setPrefWidth(400);
        this.boardController.setPrefHeight(400);
        root.getChildren().add(0,this.boardController);

        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 190;
            this.boardController.setPrefWidth(boardNewWidth);
            this.boardController.draw();
        });

        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            this.boardController.setPrefHeight(newValue.doubleValue());
            this.boardController.draw();
        });

        this.boardController.draw();
    }


    @FXML
    protected void  exitKey(ActionEvent event) {
        Stage stage = (Stage) this.exit.getScene().getWindow();
        System.exit(0);
    }

    @FXML
    protected  void runSettings(ActionEvent event) {
        try {
            Parent pane;
            pane = (Pane) FXMLLoader.load(getClass().getResource("SettingsXML.fxml"));
            Scene newScene = new Scene(pane);
            Stage mainWindow;
            mainWindow = (Stage)  ((Node)event.getSource()).getScene().getWindow();
            mainWindow.setScene(newScene);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void startGame(ActionEvent event) {
        this.vBox.getChildren().clear();
        String startPlayer = "current player: " + playerOne.getSign();
        this.currentPlayer = new Label(startPlayer);
        String firstScore = playerOne.getSign() + " player score: " +
                playerOne.getScore();
        String secondScore = playerTwo.getSign() + " player score:" +
                playerTwo.getScore();
        this.playerOneScore = new Label(firstScore);
        this.playerTwoScore = new Label(secondScore);
        this.playerOneScore.setTextFill(Color.BLACK);
        this.playerOneScore.setTextFill(Color.BLACK);
        this.vBox.getChildren().add(this.currentPlayer);
        this.vBox.getChildren().add(this.playerOneScore);
        this.vBox.getChildren().add(this.playerTwoScore);
        this.vBox.getChildren().add(this.noMove);
        this.boardController.setOnMouseClicked(mouseEvent-> {
            this.handleOneTurn(mouseEvent.getX(),mouseEvent.getY());

        });
        this.exitGame = new Button();
        this.exitGame.setPrefSize(109, 44);
        this.exitGame.setText("Exit Game");
        this.exitGame.setOnAction(actionEvent -> {
            Stage stage = (Stage) this.exitGame.getScene().getWindow();
            System.exit(0);
        });
        this.vBox.getChildren().add(this.exitGame);

    }

    private void handleOneTurn(double x, double y) {
        /*
        if (this.run.checkEndGame() || this.run.noMoveForBothPlayers()) {
            this.vBox.getChildren().remove(this.noMove);
            this.handleEndGame();
        }
        Point p = this.boardController.convertToTableBlock(x, y);
        if(this.run.playOneTurn(p)) {

            if(this.run.checkIfNoMove((this.turn))) {
                String no = "no possible move for " + this.turn.getSign() +
                        "\n turn moves to next player";
                this.noMove = new Label(no);
                this.vBox.getChildren().add(this.noMove);
            }

            if(this.run.checkIfNoMove((this.run.getComp(this.turn)))) {
                String no = "no possible move for " +this.run.getComp(this.turn).getSign()
                        + "\nmoves to next player";
                this.noMove = new Label(no);
                this.vBox.getChildren().add(this.noMove);
            }

            this.turn = this.run.whoPlay();
            String firstScore = playerOne.getSign() + " player score: " +
                    playerOne.getScore();
            String secondScore = playerTwo.getSign() + " player score:" +
                    playerTwo.getScore();
            String startPlayer = "current player: " + this.turn.getSign();
            this.currentPlayer.setText(startPlayer);
            this.playerOneScore.setText(firstScore);
            this.playerTwoScore.setText(secondScore);
            if (this.run.checkEndGame() || this.run.noMoveForBothPlayers()) {
                this.vBox.getChildren().remove(this.noMove);
                this.handleEndGame();
            }
        }
        this.boardController.draw();
        */
        Point p = this.boardController.convertToTableBlock(x, y);
        if (this.run.playOneTurn(p)) {
            this.turn = this.run.whoPlay();
            String firstScore = playerOne.getSign() + " player score: " +
                    playerOne.getScore();

            String secondScore = playerTwo.getSign() + " player score:" +
                    playerTwo.getScore();
            String startPlayer = "current player: " + this.turn.getSign();
            this.currentPlayer.setText(startPlayer);
            this.playerOneScore.setText(firstScore);
            this.playerTwoScore.setText(secondScore);
            if (this.run.getNoMovePlayer() != null) {

                String no = "no possible move for " + this.run.getNoMovePlayer()
                        .getSign() + "\nmoves to next player";
                this.noMove.setText(no);
            } else {
                this.noMove.setText("");
            }


            if(this.run.checkEndGame()) {
                this.noMove.setText("");
                this.handleEndGame();
            }

        }

        this.boardController.draw();

    }

    public void handleEndGame() {
        this.turn = this.run.whoWon();
        String  winner;
        if (turn == null) {
            winner = "Draw!";
        } else {
            winner = "player " + this.turn.getSign() + " won!!!";
        }
        this.currentPlayer.setText(winner);

    }




}