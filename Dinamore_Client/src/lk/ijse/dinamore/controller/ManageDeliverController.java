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
import lk.ijse.dinamore.dto.DeliverDTO;
import lk.ijse.dinamore.service.ServiceFactory;
import lk.ijse.dinamore.service.custom.CheffService;
import lk.ijse.dinamore.service.custom.DeliverService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ManageDeliverController implements Initializable {

    public static DeliverService deliverService;

    static {
        try {
            deliverService = (DeliverService) PloxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.DELIVER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private AnchorPane deliverConAncker;

    @FXML
    private JFXTextField txtDeliverID;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtDriLisiNo;

    @FXML
    private JFXTextField txtVehicalID;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXTextField txtTp;

    @FXML
    private JFXTextField txtAdress;

    @FXML
    private JFXTextField txtPasword;


    @FXML
    private TableView<DeliverDTO> tableDeliver;
    public void backClicked(MouseEvent mouseEvent) throws IOException {
        Parent rootPain= FXMLLoader.load(this.getClass().getResource("/lk/ijse/dinamore/view/Admin.fxml"));
        Scene scene = new Scene(rootPain);
        Stage stage = (Stage) this.deliverConAncker.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void getAll() throws Exception {
        List<DeliverDTO> allCheffs = deliverService.getAllDelivers();
        tableDeliver.setItems(FXCollections.observableArrayList(allCheffs));

        tableDeliver.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("deliverID"));
        tableDeliver.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tableDeliver.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("drivingLisionID"));
        tableDeliver.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("vehicalNo"));



    }

    public void addClick(MouseEvent mouseEvent) throws Exception {

        DeliverDTO deliverDTO=new DeliverDTO(txtDriLisiNo.getText(),txtName.getText(),txtAdress.getText(),txtTp.getText(),txtVehicalID.getText(),txtUserName.getText(),txtPasword.getText(),null);
        DeliverService deliverService=(DeliverService) PloxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.DELIVER);

        boolean result = deliverService.addDeliver(deliverDTO);
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
