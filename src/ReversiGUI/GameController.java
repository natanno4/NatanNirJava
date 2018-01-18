package ReversiGUI;

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

import java.io.IOException;
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


    /**
     * constructor.
     * before menu + board is on, read the properties from file.
     * than initiliaze all components of game: logic, players, flow etc.
     */
    public GameController() {
        SettingsManager settingsManager = new SettingsManager();
        //get properties from file.
        settingsManager.readFromFile();
        this.b = new Board(settingsManager.getSize());
        //set players.
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

    /**
     * initialize function.
     * initliaze boardController, and set his board & players.
     * set width and than draw it.
     * @param location
     * @param resources
     */
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

    /**
     * exitKey function.
     * exit from game.
     * @param event event
     */
    @FXML
    protected void  exitKey(ActionEvent event) {
        Stage stage = (Stage) this.exit.getScene().getWindow();
        System.exit(0);
    }

    /**
     * runSettings function.
     * load the settingsXML fxml file that shows the settings
     * of game.
     * @param event event
     */
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

    /**
     * startGame function.
     * remove all menu buttons, show score and name of both
     * players and set the exit button.
     * @param event
     */
    @FXML
    protected void startGame(ActionEvent event) {
        this.vBox.getChildren().clear();
        //initialize game status labels.
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

        //create inner exit game button
        this.exitGame = new Button();
        this.exitGame.setPrefSize(109, 44);
        this.exitGame.setText("Exit Game");
        this.exitGame.setOnAction(actionEvent -> {
            Stage stage = (Stage) this.exitGame.getScene().getWindow();
            System.exit(0);
        });
        this.vBox.getChildren().add(this.exitGame);

    }

    /**
     * handleOneTurn function.
     * play one turn in game.
     * check if game is over and check if the next player
     * has no turns and print the result accordingly.
     * @param x x value
     * @param y y value
     */
    private void handleOneTurn(double x, double y) {
        Point p = this.boardController.convertToTableBlock(x, y);
        //check if the chosen point is valid and play if yes.
        if (this.run.playOneTurn(p)) {
            //change turn
            this.turn = this.run.whoPlay();
            //set game status labels.
            String firstScore = playerOne.getSign() + " player score: " +
                    playerOne.getScore();
            String secondScore = playerTwo.getSign() + " player score:" +
                    playerTwo.getScore();
            String startPlayer = "current player: " + this.turn.getSign();
            this.currentPlayer.setText(startPlayer);
            this.playerOneScore.setText(firstScore);
            this.playerTwoScore.setText(secondScore);
            //check if next player has move.
            if (this.run.getNoMovePlayer() != null) {

                String no = "no possible move for " + this.run.getNoMovePlayer()
                        .getSign() + "\nmoves to next player";
                this.noMove.setText(no);
            } else {
                this.noMove.setText("");
            }
            //check for end game
            if(this.run.checkEndGame()) {
                this.noMove.setText("");
                this.handleEndGame();
            }

        }

        this.boardController.draw();

    }

    /**
     * handleEndGame function.
     * print the winner of game.
     */
    public void handleEndGame() {
        //get winner
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