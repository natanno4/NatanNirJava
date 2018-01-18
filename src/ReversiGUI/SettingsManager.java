package ReversiGUI;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
public class SettingsManager {
    public static String settingsFile = "settings.txt";
    private Integer size;
    private Color Xcolor;
    private Color Ocolor;
    private Character starter;


    public SettingsManager() {
    }

    /**
     * readFromFile function.
     * read the size of board, colors of competitors and who start - all as strings.
     * than call the parse function.
     */
    public void readFromFile() {
        InputStream is;
        BufferedReader reader = null;
        String buffer = null;
        List<String> settings = new ArrayList<>();
        try {
            is = new FileInputStream("settings.txt");
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

    /**
     * parseFields function.
     * receive list of string, and by a convention parse each string
     * to his appropriate field.
     * @param list list of strings
     */
    private void parseFields(List<String>list) {
        this.size = Integer.parseInt(list.get(0));
        this.Xcolor = Color.valueOf(list.get(1));
        this.Ocolor = Color.valueOf(list.get(2));
        this.starter = list.get(3).charAt(0);
    }

    /**
     * getXcolor.
     * @return color
     */
    public Color getXcolor() {
        return this.Xcolor;
    }

    /**
     * getOcolor.
     * @return color
     */
    public Color getOcolor() {
        return this.Ocolor;
    }

    /**
     * getSize.
     * @return size
     */
    public Integer getSize() {
        return this.size;
    }

    /**
     * getStarter
     * @return char represent the starter
     */
    public Character getStarter(){
        return this.starter;
    }

}
