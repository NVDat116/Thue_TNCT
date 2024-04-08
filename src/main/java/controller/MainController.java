package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private MenuBar menuBar;
    @FXML
    private Label labelU;
    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Button submitBtn;
    @FXML
    private Button loginBtn;
    @FXML
    private Button registerBtn;
    @FXML
    private  MenuItem trangchuMenuItem;
    @FXML
    private  MenuItem kekhaiItem;
    @FXML
    private  Label notificationS;
    private int selectedIndex= -1;
    private boolean comboBoxChanged = false;
    ObservableList<String> listDoituong = FXCollections.observableArrayList("Hộ gia đình, nhóm cá nhân kinh doanh, cá nhân kinh doanh TT105","Cá nhân nước ngoài sử dụng tiền viện trợ nhân đạo, viện trợ không hoàn lại của người nước ngoài mua hàng hóa dịch vụ có thuế giá trị gia tăng ở Việt Nam để viện trợ không hoàn lại, viện trợ nhân đạo; Cá nhân có thu nhập từ tiền lương, tiền công do các tổ chức Quốc tế, Đại sứ quán, Lãnh sự quán tại Việt Nam trả; Cá nhân cư trú có thu nhập từ tiền lương, tiền công do các tổ chức, cá nhân trả từ nước ngoài TT105","Cá nhân cư trú có thu nhập từ tiền lương, tiền công do các tổ chức, cá nhân trả từ nước ngoài TT105","Cá nhân cư trú có thu nhập từ tiền lương, tiền công do các tổ chức Quốc tế, Đại sứ quán, Lãnh sự quán tại Việt Nam trả nhưng tổ chức này chưa thực hiện khấu trừ thuế TT105","Cá nhân khác TT105");

    @FXML
    private void loginButtonClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/login-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = (Stage) loginBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void registerButtonClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/register-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) registerBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void submitButtonClicked() {
        try {
            if(comboBoxChanged==true){
                if(selectedIndex==4){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/declaredFirstTime-view.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) submitBtn.getScene().getWindow();
                    stage.setScene(scene);
                    DeclaredController controller = loader.getController();
                    controller.setLabel("05-ĐK-TCT");
                    stage.show();
                }else {
                    notificationS.setText("Đối tượng đang trong quá trình hoàn thiện!");
                }
            }else {
                notificationS.setText("Vui lòng chọn 1 đối tượng phù hợp!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        hideComponents();
        comboBox.setItems(listDoituong);
        comboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                selectedIndex=comboBox.getSelectionModel().getSelectedIndex();
                comboBoxChanged=true;
            }

        });
        notificationS.setText("");
        kekhaiItem.setOnAction(e -> {
            showComponents();
            submitBtn.requestFocus();
        });
        trangchuMenuItem.setOnAction(e -> {
            hideComponents();
        });
    }
    public void showComponents() {
        labelU.setVisible(true);
        comboBox.setVisible(true);
        submitBtn.setVisible(true);
    }

    public void hideComponents() {
        labelU.setVisible(false);
        comboBox.setVisible(false);
        submitBtn.setVisible(false);
    }
    private void hideLoginRegisterButtons() {
        loginBtn.setVisible(false);
        registerBtn.setVisible(false);
    }

}
