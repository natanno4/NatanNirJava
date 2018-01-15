package ReversiGUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.event.Event;
import javax.print.attribute.standard.OutputDeviceAssigned;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
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
    protected void saveSettingsToFile(Event event) {

        try {

            if (this.checkValid()) {

                Color Xcolor = this.colorOfPlayerX.getValue();
                String s = Xcolor.toString();
                System.out.println(s + "natan ugosheniaaaaaaaaaaaa");
                String Ocolor = this.colorOfPlayerO.getValue().toString();
                String size = this.boardSize.getValue().toString();

                String whoStart = this.starter.getValue().toString();

                OutputStreamWriter os = null;
                try {
                    os = new OutputStreamWriter(new FileOutputStream(new File(("src/settings.txt"))));
                    os.write(size);
                    os.write("\n");
                    os.write(s);
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
            }
        } catch (RuntimeException e) {
            System.out.println("error in parse");
        }
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
    private boolean checkValid() {
        /*
        String colorX = this.colorOfPlayerX.getText();
        String colorO = this.colorOfPlayerO.getText();
        String size = this.boardSize.getText();
        String start = this.starter.getText();
        String start2 = start.toUpperCase();
        Integer sizeB = Integer.parseInt(size);
        if (!start2.equals("X") && !start2.equals("O")) {
            return false;
        }
        if (sizeB > 20 || sizeB < 4) {
            return false;
        }
        if (!this.list.contains(colorX) || !this.list.contains(colorO)) {
            return false;
        }
            */
        return true;
    }

}