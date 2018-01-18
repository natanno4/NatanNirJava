package ReversiGUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.Event;
import javax.print.attribute.standard.OutputDeviceAssigned;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Observable;

public class SettingsController {
    @FXML
    private Button save;
    @FXML
    private ChoiceBox starter;
    @FXML
    private ColorPicker colorOfPlayerO;
    @FXML
    private ColorPicker colorOfPlayerX;
    @FXML
    private ChoiceBox boardSize;

    private ObservableList<Integer> options = FXCollections.observableArrayList(4, 6, 8
    , 10, 12, 14 , 16, 18, 20);

    @FXML
    protected void initialize() {
        this.boardSize.setItems(options);
        this.starter.setItems(FXCollections.observableArrayList("X", "O"));
    }
    @FXML
    private Label errorLabel;
    @FXML
    private Pane pane;

    /**
     * saveSettingsToFile function.
     * check for every fields required, and convert them to string so he can write it
     * to file. if not all fields are full or both players have same color -> restart
     * the fields.
     * @param event event
     */
    @FXML
    protected void saveSettingsToFile(Event event) {
        boolean isWrongInput = false;
        try {
                String Xcolor = this.colorOfPlayerX.getValue().toString();
                String Ocolor = this.colorOfPlayerO.getValue().toString();
                if (Xcolor.equals(Ocolor)) {
                    throw new RuntimeException("e");
                }
                String size = this.boardSize.getValue().toString();

                String whoStart = this.starter.getValue().toString();

                OutputStreamWriter os = null;
                try {
                    os = new OutputStreamWriter(new FileOutputStream(new File(("settings.txt"))));
                    os.write(size);
                    os.write("\n");
                    os.write(Xcolor);
                    os.write("\n");
                    os.write(Ocolor);
                    os.write("\n");
                    os.write(whoStart);
                } catch (Exception e) {
                    System.out.println("error");
                } finally {
                    try {
                        if (os != null) {
                            os.close();
                        }
                    } catch (IOException e) {
                        System.out.println("error in closing file");
                    }
                }

        } catch (RuntimeException e) {

            try {
                this.pane = (Pane) FXMLLoader.load(getClass().getResource("SettingsXML.fxml"));
                Scene scene = new Scene(this.pane);
                Stage stage;
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                isWrongInput = true;
            } catch (IOException e1) {
                e1.printStackTrace();
            }


        }

        if (isWrongInput) {
            //if same color for both players OR not all fields are full
            return;
        }
        //return to menu
        try {
            Parent hbox;
            hbox = (HBox) FXMLLoader.load(getClass().getResource("ReversiGameXML.fxml"));
            Scene scene = new Scene(hbox);
            Stage stage;
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void cancel(Event event) {
        try {
            Parent hbox;
            hbox = (HBox) FXMLLoader.load(getClass().getResource("ReversiGameXML.fxml"));
            Scene scene = new Scene(hbox);
            Stage stage;
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}