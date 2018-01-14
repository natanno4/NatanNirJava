package ReversiGUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import javax.print.attribute.standard.OutputDeviceAssigned;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class SettingsController {
    @FXML
    private Button save;
    @FXML
    private TextField starter;
    @FXML
    private TextField colorOfPlayerO;
    @FXML
    private TextField colorOfPlayerX;
    @FXML
    private TextField boardSize;

    private List<String> list = Arrays.asList("blue","green","red","gray", "pink","yellow",
            "black", "white");

    @FXML
    protected void saveSettingsToFile(ActionEvent event) {
        try {
            if (this.checkValid()) {
                String Xcolor = this.colorOfPlayerX.getText();
                String Ocolor = this.colorOfPlayerO.getText();
                Integer size = Integer.parseInt(this.boardSize.getText());
                String whoStart = this.starter.toString();

                OutputStreamWriter is = null;
                try {
                    is = new OutputStreamWriter(new FileOutputStream("settings.txt"));
                    is.write(Xcolor);
                    is.write(Ocolor);
                    is.write(size);
                    is.write(whoStart);
                } catch (Exception e) {
                    System.out.println("error");
                } finally {
                    try {
                        if (is != null) {
                            is.close();
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

        String colorX = this.colorOfPlayerX.getText();
        String colorO = this.colorOfPlayerO.getText();

        String size = this.boardSize.getText();

        String start = this.starter.getText();
        String start2 = start.toUpperCase();

        Integer sizeB = Integer.parseInt(size);
        if (!start2.equals("X") || !start2.equals("O")) {
            return false;
        }
        if (sizeB > 20 || sizeB < 4) {
            return false;
        }
        if (!this.list.contains(colorX) || !this.list.contains(colorO)) {
            return false;
        }

        return true;
    }
}