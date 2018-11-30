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
import lk.ijse.dinamore.dto.DeliverDTO;
import lk.ijse.dinamore.main.Main;
import lk.ijse.dinamore.memoryID.MemoryID;
import lk.ijse.dinamore.password.PasswordUtil;
import lk.ijse.dinamore.service.ServiceFactory;
import lk.ijse.dinamore.service.custom.CheffService;
import lk.ijse.dinamore.service.custom.DeliverService;

import java.io.IOException;
import java.util.List;

public class DeliverLogInController {
    @FXML
    private AnchorPane deliverLogInAncker;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField txtPassword;

    DeliverService deliverService;

    {
        try {
            deliverService = (DeliverService) PloxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.DELIVER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void deliverCancelClicked(MouseEvent mouseEvent) throws Exception {

        Parent rootPain= FXMLLoader.load(this.getClass().getResource("/lk/ijse/dinamore/view/Dashbord.fxml"));
        Scene scene = new Scene(rootPain);
        Stage stage = (Stage) this.deliverLogInAncker.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();

    }

    public void logInClick(MouseEvent mouseEvent) throws IOException {


        List<DeliverDTO> allDelivers = null;
        try {
            allDelivers = deliverService.getAllDelivers();
        } catch (Exception e) {
            System.out.println(e);
        }

        for (DeliverDTO tempDTO:allDelivers){
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

                MemoryID.setDeliverID(tempDTO.getDeliverID());
                Parent rootPain= FXMLLoader.load(this.getClass().getResource("/lk/ijse/dinamore/view/Deliver.fxml"));
                Scene scene = new Scene(rootPain);
                Stage stage = (Stage) this.deliverLogInAncker.getScene().getWindow();
                stage.setScene(scene);
                stage.centerOnScreen();

            }else {
                Alert alert=new Alert(Alert.AlertType.WARNING,"wrong password or user name", ButtonType.OK);
                alert.show();
            }
        }
    }
}
