package ReversiGUI;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
public class SettingsManager {
    public static String settingsFile = "src/settings.txt";
    private Integer size;
    private Color Xcolor;
    private Color Ocolor;
    private Character starter;

    public SettingsManager() {

    }
    public void readFromFile() {
        InputStream is;
        BufferedReader reader = null;
        String buffer = null;
        List<String> settings = new ArrayList<>();
        try {
            is = new FileInputStream(settingsFile);
            reader = new BufferedReader(new InputStreamReader(is));

            while ((buffer = reader.readLine()) != null) {
                settings.add(buffer);
            }
        } catch (IOException e) {
            return;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("error in reading");
                }

            }
        }
        this.parseFields(settings);
    }
    private void parseFields(List<String>list) {
        this.size = Integer.parseInt(list.get(0));
        this.Xcolor = Color.valueOf(list.get(1));
        this.Ocolor = Color.valueOf(list.get(2));
        this.starter = list.get(3).charAt(0);
    }
    public Color getXcolor() {
        return this.Xcolor;
    }
    public Color getOcolor() {
        return this.Ocolor;
    }
    public Integer getSize() {
        return this.size;
    }
    public Character getStarter(){
        return this.starter;
    }
}
