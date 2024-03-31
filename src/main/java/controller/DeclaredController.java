package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DeclaredController implements Initializable {
    @FXML
    private TextField name;

    @FXML
    private ComboBox<String> comboBoxtype;
    @FXML
    private TextField numDocument;
    @FXML
    private TextField releaseDate;
    @FXML
    private TextField email;
    @FXML
    private Button continueBtn;
    @FXML
    private  Button backBtn;

    ObservableList<String> list = FXCollections.observableArrayList("JAVA","C++");
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxtype.setItems(list);

    }
    public void setComboBoxChanged (ActionEvent event){
        email.setText(comboBoxtype.getValue());
    }
}
