package lk.ijse.dinamore.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dinamore.PloxyHandler;
import lk.ijse.dinamore.dto.ReseptionDTO;
import lk.ijse.dinamore.memoryID.MemoryID;
import lk.ijse.dinamore.password.PasswordUtil;
import lk.ijse.dinamore.service.ServiceFactory;
import lk.ijse.dinamore.service.custom.CheffService;
import lk.ijse.dinamore.service.custom.ReseptionService;

import java.io.IOException;
import java.util.List;

public class ReseptionLogInController{
    @FXML
    private AnchorPane reseptionLogInAncker;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField txtPassword;

    ReseptionService service;

    {
        try {
            service = (ReseptionService) PloxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.RESEPTION);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reseptionCancelClick(MouseEvent mouseEvent) throws IOException {
        Parent rootPain= FXMLLoader.load(this.getClass().getResource("/lk/ijse/dinamore/view/Dashbord.fxml"));
        Scene scene = new Scene(rootPain);
        Stage stage = (Stage) this.reseptionLogInAncker.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void logInClicked(MouseEvent mouseEvent) throws Exception {

        List<ReseptionDTO> allReseptionS = service.getAllReseptionS();

        for (ReseptionDTO tempDTO:allReseptionS){
            String userName = tempDTO.getUserName();
            String password = tempDTO.getPassword();
            String decriptKey = tempDTO.getDecriptKey();

            boolean ifPasswordMatches= PasswordUtil.verifyUserPassword(txtPassword.getText(), password, decriptKey);

            if (ifPasswordMatches && txtUserName.getText().equals(userName)){

                MemoryID.setReseptionID(tempDTO.getReseptionID());

                Parent rootPain= FXMLLoader.load(this.getClass().getResource("/lk/ijse/dinamore/view/Reseption.fxml"));
                Scene scene = new Scene(rootPain);
                Stage stage = (Stage) this.reseptionLogInAncker.getScene().getWindow();
                stage.setScene(scene);
                stage.centerOnScreen();

            }else {
                Alert alert=new Alert(Alert.AlertType.WARNING,"wrong password or user name", ButtonType.OK);
                alert.show();
            }
        }
    }
}
