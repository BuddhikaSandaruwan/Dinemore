package lk.ijse.dinamore.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import lk.ijse.dinamore.dto.FoodDTO;
import lk.ijse.dinamore.dto.toOrderTableDTO;
import lk.ijse.dinamore.service.ServiceFactory;
import lk.ijse.dinamore.service.custom.CheffService;

import java.applet.Applet;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ManageCheffController implements Initializable {
    @FXML
    private AnchorPane CheffConAncker;

    @FXML
    private JFXTextField txtCheffID;

    @FXML
    private JFXTextField txtCheffName;

    @FXML
    private JFXTextField txtCheffNIC;

    @FXML
    private JFXTextField txtCheffAdress;

    @FXML
    private JFXTextField txtCheffTP;

    @FXML
    private JFXTextField txtCheffUserName;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private TableView<CheffDTO> tableCheff;

    public ManageCheffController() {
    }

    private static CheffService cheffService;

    static {
        try {
            cheffService = (CheffService) PloxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CHEFF);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void backClicked(MouseEvent mouseEvent) throws IOException {
        Parent rootPain= FXMLLoader.load(this.getClass().getResource("/lk/ijse/dinamore/view/Admin.fxml"));
        Scene scene = new Scene(rootPain);
        Stage stage = (Stage) this.CheffConAncker.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void addClicked(MouseEvent mouseEvent) throws Exception {


        CheffDTO cheffDTO=new CheffDTO(txtCheffNIC.getText(),txtCheffName.getText(),txtCheffAdress.getText(),txtCheffTP.getText(),txtCheffUserName.getText(),txtPassword.getText(),null);

        boolean result = cheffService.addCheff(cheffDTO);
        System.out.println(cheffDTO.toString());
        getAll();

        if (result){
            Alert alert= new Alert(Alert.AlertType.CONFIRMATION,"ADD", ButtonType.OK);
            alert.show();
        }else {
            Alert alert= new Alert(Alert.AlertType.CONFIRMATION,"NOT ADD", ButtonType.OK);
            alert.show();
        }
    }
    public void lordTable(){

    }

    public void getAll() throws Exception {
        List<CheffDTO> allCheffs = cheffService.getAllCheffs();
        tableCheff.setItems(FXCollections.observableArrayList(allCheffs));

        tableCheff.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("cheffID"));
        tableCheff.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tableCheff.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("adress"));
        tableCheff.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("cheffNIC"));
        tableCheff.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("tp"));
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
