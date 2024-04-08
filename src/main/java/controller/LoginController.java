package controller;

import dao.UserAccDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class LoginController implements Initializable {
    @FXML
    private TextField taxid;
    @FXML
    private PasswordField password;
    @FXML
    private Button backButton;
    @FXML
    private Button loginBtn;
    @FXML
    private Label labelmess;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            if (text.matches("\\d*")) {
                return change;
            }
            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        taxid.setTextFormatter(textFormatter);
        taxid.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isValidTaxNumber(newValue)) {
                labelmess.setText("Vui lòng mã số thuế 10 ký tự!");
            }else labelmess.setText("");
        });
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() < 8) {
                labelmess.setText("Mật khẩu phải lớn hơn hoặc bằng 8 ký tự!");
            }else labelmess.setText("");
        });
    }
    @FXML
    private void backButtonClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/main-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void loginButtonClicked() {
        UserAccDAO userAccDAO = new UserAccDAO();
        boolean check = userAccDAO.checkLogin(taxid.getText(),password.getText());
        try {
            if(check){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/main-view.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) loginBtn.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }else labelmess.setText("Tài khoản chưa chính xác!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean isValidTaxNumber(String taxNumber) {
        return taxNumber.matches("^\\d{10}$");
    }
}
