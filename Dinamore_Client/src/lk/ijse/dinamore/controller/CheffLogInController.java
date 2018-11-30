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
import lk.ijse.dinamore.dto.CheffDTO;
import lk.ijse.dinamore.dto.ReseptionDTO;
import lk.ijse.dinamore.memoryID.MemoryID;
import lk.ijse.dinamore.password.PasswordUtil;
import lk.ijse.dinamore.service.ServiceFactory;
import lk.ijse.dinamore.service.custom.CheffService;
import lk.ijse.dinamore.service.custom.ReseptionService;

import java.io.IOException;
import java.util.List;

public class CheffLogInController {
    CheffService service;

    {
        try {
            service = (CheffService) PloxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CHEFF);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private AnchorPane cheffLogInAncker;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField txtPassword;
    public void cheffCancelClicked(MouseEvent mouseEvent) throws IOException {
        Parent rootPain= FXMLLoader.load(this.getClass().getResource("/lk/ijse/dinamore/view/Dashbord.fxml"));
        Scene scene = new Scene(rootPain);
        Stage stage = (Stage) this.cheffLogInAncker.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void logInClick(MouseEvent mouseEvent)  {


        List<CheffDTO> allReseptionS = null;
        try {
            allReseptionS = service.getAllCheffs();
        } catch (Exception e) {
            System.out.println(e);
        }

        for (CheffDTO tempDTO:allReseptionS){
            String userName = tempDTO.getUserName();
            String password = tempDTO.getPassword();
            String decriptKey = tempDTO.getDecriptKey();

            boolean ifPasswordMatches= false;
            try {
                ifPasswordMatches = PasswordUtil.verifyUserPassword(txtPassword.getText(), password, decriptKey);
            } catch (Exception e) {
                System.out.println(e);
            }

            if (ifPasswordMatches && txtUserName.getText().equals(userName)){


                MemoryID.setCheffID(tempDTO.getCheffID());
                Parent rootPain= null;
                try {
                    rootPain = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dinamore/view/Cheff.fxml"));
                } catch (IOException e) {
                    System.out.println(e);
                }
                Scene scene = new Scene(rootPain);
                Stage stage = (Stage) this.cheffLogInAncker.getScene().getWindow();
                stage.setScene(scene);
                stage.centerOnScreen();

            }else {
                Alert alert=new Alert(Alert.AlertType.WARNING,"wrong password or user name", ButtonType.OK);
                alert.show();
            }
        }
    }
}
