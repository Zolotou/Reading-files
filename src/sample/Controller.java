package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.xmlbeans.XmlException;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    Model model = new Model();

    DocAndDocx docAndDocx = new DocAndDocx();

    ArrayList<String> list = new ArrayList<>();

    String text = new String("times");

    Alert alert = new Alert(Alert.AlertType.ERROR);

    String alertfile = "alert_message";



    @FXML
    private Label label;

    @FXML
    private RadioButton RadioButtonRus;

    @FXML
    private ToggleGroup RadioGroup;

    @FXML
    private RadioButton RadioButtonEng;

    @FXML
    private ToggleButton Tog;
    @FXML
    private CheckBox Count;

    @FXML
    private Pane PaneText;

    @FXML
    private TextArea TextArea;

    @FXML
    private Button Button;

    @FXML
    final FileChooser fileChooser = new FileChooser();

    File filefx;

    // Click on Button to choose File
    @FXML
    public void inputFileChooser() throws IOException, OpenXML4JException, XmlException {
                // choose File in explorer
                File file = fileChooser.showOpenDialog(null);

                filefx = file;
             if (filefx != null) {

                 //set alert message
                 alertfile = filefx.getName();
                 alert.setTitle("Wrong extention");
                 alert.setHeaderText("File in not a .doc or .txt format");
                 alert.setContentText("Oops, your file: " + filefx.getName() + " have wrong extention.\n Please choose anoter text file.");

                 // choose doc or xls file
                 if (file.getPath().toLowerCase().endsWith(".doc") || file.getPath().toLowerCase().endsWith(".docx") || file.getPath().toLowerCase().endsWith(".xls") || file.getPath().toLowerCase().endsWith(".xlsx")) {
                     docAndDocx.read(file.getPath(), list);

                     text = docAndDocx.content;

                     PaneText.setStyle("-fx-background-color: #805757");
                 }
                 // choose txt file
                 else if (file.getPath().toLowerCase().endsWith(".txt")) {
                     model.read(file.getPath(), list);
                     text = model.content;

                     PaneText.setStyle("-fx-background-color: #805757");
                 }
                 // if choosen file in not text format
                 else {
                     alert.showAndWait();
                 }

                 // if Toggle button pressed before you choose file
                 if (Tog.isSelected()) {
                     model.statista(list);
                     TextArea.setText(model.counter);

                     PaneText.setStyle("-fx-background-color: #936f4e");

                 } else {
                     TextArea.setText(text);
                 }
             }   else {System.out.println("Not valid");} //if man don't choose any file



    }
    // Click on Toggle "Chooser"
    @FXML
    public void inputCounterChooser(){
        // if file not txt format
        if(filefx.getPath().toLowerCase().endsWith(".doc") || filefx.getPath().toLowerCase().endsWith(".docx") || filefx.getPath().toLowerCase().endsWith(".xls") || filefx.getPath().toLowerCase().endsWith(".xlsx") ){
            if(Tog.isSelected()){
                model.statista(list);
                TextArea.setText(model.counter);

                PaneText.setStyle("-fx-background-color: #936f4e");
            }
            else {
                TextArea.setText(docAndDocx.content);

                PaneText.setStyle("-fx-background-color: #805757");
            }
        }
        // if file txt format
        else {
            if (Tog.isSelected()) {
                model.statista(list);
                TextArea.setText(model.counter);
                PaneText.setStyle("-fx-background-color: #936f4e");
            } else {
                TextArea.setText(model.content);
                PaneText.setStyle("-fx-background-color: #805757");
            }
        }
    }
    // Localitation Rus
    @FXML
    public void RusSelected(){

            label.setText("Посчитатлка - просто выберите текстовый файл и вам всё посчитают"); label.setStyle("-fx-font: 15 arial;");
            Button.setText("Выбрать файл");
            Tog.setText("Посчитать"); Tog.setStyle("-fx-font: 13 arial;");
            RadioButtonRus.setText("Русский"); RadioButtonEng.setText("Английский"); RadioButtonEng.setStyle("-fx-font: 11 arial");

        alert.setTitle("Неверное расширение");
        alert.setHeaderText("Файл не док или тхт формата");
        alert.setContentText("Упс, похоже файл: "+ alertfile+" имеет неверное расширение.\n Пожалуйста выберите другой текстовый файл.");

        model.howmany = "раз(а)";



    }
    // Localitation Eng
    @FXML
    public  void EngSelected(){

            label.setText("Counter -> Just select a text format file and see for yourself"); label.setStyle("-fx-font: 17 arial;");
            Button.setText("Choose a File");
            Tog.setText("Counter");  Tog.setStyle("-fx-font: 17 arial;");
            RadioButtonRus.setText("Rus"); RadioButtonEng.setText("Eng"); RadioButtonEng.setStyle("-fx-font: 12 arial");

            model.howmany = "times";

        alert.setTitle("Wrong extention");
        alert.setHeaderText("File in not a .doc or .txt format");
        alert.setContentText("Oops, your file: "+ alertfile+" have wrong extention.\n Please choose anoter text file.");



    }



}
