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
import lk.ijse.dinamore.dto.FoodDTO;
import lk.ijse.dinamore.service.ServiceFactory;
import lk.ijse.dinamore.service.custom.CheffService;
import lk.ijse.dinamore.service.custom.FoodService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ManageFoodController implements Initializable {

    @FXML
    private AnchorPane foodConAncker;
    @FXML
    private JFXTextField txtFoodID;

    @FXML
    private JFXTextField txtFoodName;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private TableView<FoodDTO> tableFood;

    private static FoodService foodService;

    static {
        try {
            foodService = (FoodService) PloxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.FOOD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void backClicked(MouseEvent mouseEvent) throws IOException {
        Parent rootPain= FXMLLoader.load(this.getClass().getResource("/lk/ijse/dinamore/view/Admin.fxml"));
        Scene scene = new Scene(rootPain);
        Stage stage = (Stage) this.foodConAncker.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void getAll() throws Exception {
        List<FoodDTO> allCheffs = foodService.getAllFoods();
        tableFood.setItems(FXCollections.observableArrayList(allCheffs));

        tableFood.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("foodID"));
        tableFood.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("foodName"));
        tableFood.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public void addClicked(MouseEvent mouseEvent) throws Exception {
        FoodDTO foodDTO=new FoodDTO(0,txtFoodName.getText(),Double.parseDouble(txtPrice.getText()));
        FoodService foodService=(FoodService)PloxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.FOOD);

        boolean result=foodService.addFood(foodDTO);
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
