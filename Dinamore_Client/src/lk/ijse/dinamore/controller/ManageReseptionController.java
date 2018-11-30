package lk.ijse.dinamore.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dinamore.PloxyHandler;
import lk.ijse.dinamore.dto.CheffDTO;
import lk.ijse.dinamore.dto.ReseptionDTO;
import lk.ijse.dinamore.service.ServiceFactory;
import lk.ijse.dinamore.service.custom.CheffService;
import lk.ijse.dinamore.service.custom.ReseptionService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ManageReseptionController implements Initializable {
    @FXML
    private AnchorPane reseptionConAncker;

    @FXML
    private JFXTextField txtReseptionName;

    @FXML
    private JFXTextField txtAdress;

    @FXML
    private JFXTextField txtTP;

    @FXML
    private JFXTextField txtReNIC;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private TableView<ReseptionDTO> tableReseption;

    private static ReseptionService reseptionService;

    static {
        try {
            reseptionService = (ReseptionService) PloxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.RESEPTION);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void backClicked(MouseEvent mouseEvent) throws IOException {
        Parent rootPain= FXMLLoader.load(this.getClass().getResource("/lk/ijse/dinamore/view/Admin.fxml"));
        Scene scene = new Scene(rootPain);
        Stage stage = (Stage) this.reseptionConAncker.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void getAll() throws Exception {
        List<ReseptionDTO> allCheffs = reseptionService.getAllReseptionS();
        tableReseption.setItems(FXCollections.observableArrayList(allCheffs));

        tableReseption.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
        tableReseption.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("adress"));
        tableReseption.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("tp"));
        tableReseption.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("ReseptionNIC"));
    }

    public void addClicked(MouseEvent mouseEvent) throws Exception {
        String name = txtReseptionName.getText();
        String adress = txtAdress.getText();
        String tp = txtTP.getText();
        String nic = txtReNIC.getText();
        String userName = txtUserName.getText();
        String password = txtPassword.getText();

        ReseptionDTO reseptionDTO=new ReseptionDTO(nic,name,adress,tp,userName,password,null);
        ReseptionService reseptionService=(ReseptionService) PloxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.RESEPTION);

        boolean result = reseptionService.addReseption(reseptionDTO);
        getAll();
        if (result){
            Alert alert= new Alert(Alert.AlertType.CONFIRMATION,"ADD", ButtonType.OK);
            alert.show();
        }else {
            Alert alert= new Alert(Alert.AlertType.CONFIRMATION,"NOT ADD", ButtonType.OK);
            alert.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
