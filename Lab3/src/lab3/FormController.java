/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import java.lang.String;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
/**
 * FXML Controller class
 *
 * @author T0NI
 */
public class FormController implements Initializable {

    @FXML
    private Button convert;
    @FXML
    private TextArea text1;
    @FXML
    private TextArea text2;
    @FXML
    private ComboBox<?> combob1;
    @FXML
    private ComboBox<?> combob2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void handleConvertAction(ActionEvent event) {
        try{
            if(text1.getText() == null || text1.getText().trim().isEmpty()) throw new Exception("Поле пусто");
            byte[] inputText = text1.getText().getBytes(combob1.getSelectionModel().getSelectedItem().toString());
            String outText = new String(inputText, combob2.getSelectionModel().getSelectedItem().toString());
            text2.setText(outText);
        } catch(Exception ex){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Исключение");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
            text2.clear();
        }
        
    }
    
}
