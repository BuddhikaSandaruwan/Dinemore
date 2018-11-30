package lk.ijse.dinamore.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.sun.jmx.remote.internal.ArrayQueue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dinamore.PloxyHandler;
import lk.ijse.dinamore.dto.*;
import lk.ijse.dinamore.memoryID.MemoryID;
import lk.ijse.dinamore.service.ServiceFactory;
import lk.ijse.dinamore.service.custom.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ReseptionController implements Initializable {
    @FXML
    private AnchorPane reseptionAncker;
    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtCustomerNIC;

    @FXML
    private JFXTextField txtDeliveryAdress;

    @FXML
    private JFXTextField txtContactNum;

    @FXML
    private JFXComboBox<FoodDTO> combFood;

    @FXML
    private JFXButton butAdd;

    @FXML
    private JFXButton butSubmit;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtDescount;

    @FXML
    private JFXTextField txtOrderID;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXButton butBack;

    @FXML
    private TableView<toOrderTableDTO> tableOrder;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtStates;

    @FXML
    private JFXTextField txtSearch;

    private FoodService foodService;
    private ReseptionService reseptionService;
    private OrderDetailsService orderDetailsService;
    private OrderService orderService;
    private CustomerService customerService;
    private List<toOrderTableDTO> toOrderTableDTOS=new ArrayQueue<>(10);

    {

        try {
            reseptionService=(ReseptionService)PloxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.RESEPTION);
            foodService = (FoodService) PloxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.FOOD);
            orderDetailsService=(OrderDetailsService)PloxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ORDER_DETAILS);
            orderService=(OrderService)PloxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ORDER);



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void backClick(MouseEvent mouseEvent) throws IOException {
        Parent rootPain= FXMLLoader.load(this.getClass().getResource("/lk/ijse/dinamore/view/Dashbord.fxml"));
        Scene scene = new Scene(rootPain);
        Stage stage = (Stage) this.reseptionAncker.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void addClick(MouseEvent mouseEvent) throws Exception {
        loadAll();
//        txtCustomerName.clear();
//        txtCustomerNIC.clear();
//        txtDeliveryAdress.clear();
//        txtContactNum.clear();
        txtQty.clear();
        txtDescount.setText("00");
    }

    private void loadAll() throws Exception {


        FoodDTO value = combFood.getSelectionModel().getSelectedItem();
        toOrderTableDTO toOrderTableDTO=new toOrderTableDTO(value.getFoodID(),value.getFoodName(),Integer.parseInt(txtQty.getText()),Double.parseDouble(txtPrice.getText()));
        toOrderTableDTOS.add(toOrderTableDTO);

        tableOrder.setItems(FXCollections.observableArrayList(toOrderTableDTOS));

        tableOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemID"));
        tableOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tableOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tableOrder.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("price"));



    }


    public void submitClicked(MouseEvent mouseEvent) throws Exception {



        CustomerDTO customerDTO=new CustomerDTO(txtCustomerNIC.getText(),txtCustomerName.getText(),txtDeliveryAdress.getText(),txtContactNum.getText());
//        CustomerService customerService=(CustomerService)PloxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CUSTOMER);
//        boolean result = customerService.addCustomer(customerDTO);

        ReseptionDTO reseptionDTO = reseptionService.searchReseption(MemoryID.getReseptionID());


        OrderDTO orderDTO=new OrderDTO(0,new Date(),customerDTO,reseptionDTO,Double.parseDouble(txtPrice.getText()),Double.parseDouble(txtDescount.getText()),txtStates.getText(),null,null);
        OrderService orderService=(OrderService)PloxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ORDER);
        orderService.addOrder(orderDTO);



//        tableOrder.getSelectionModel().selectAll();
//        List<toOrderTableDTO> selectedItems = tableOrder.getSelectionModel().getSelectedItems();
//
//
        for (int i=0;i<tableOrder.getItems().size();i++){
            toOrderTableDTO toOrderTableDTO = tableOrder.getItems().get(i);
            FoodDTO foodDTO=new FoodDTO(toOrderTableDTO.getItemID(),toOrderTableDTO.getItemName(),toOrderTableDTO.getPrice());
            OrderDetailsDTO orderDetailsDTO=new OrderDetailsDTO(toOrderTableDTO.getQty(),orderDTO,foodDTO);
            System.out.println("send order details"+orderDetailsDTO);
            List<OrderDetailsDTO>orderDetailsDTOS=new ArrayQueue<>(10);
            orderDetailsDTOS.add(orderDetailsDTO);
            boolean result2 =orderDetailsService.addOrderDetails(orderDetailsDTO);

        }
//        System.out.println(orderDTO);
//        System.out.println(customerDTO);


    }

    private void lordCombFood() throws Exception {
        List<FoodDTO> foods = foodService.getAllFoods();
        combFood.getItems().setAll(foods);
//        combFood.getItems().clear();
//        combFood.getItems().add(0,"- Select -");
//        for (FoodDTO ab:foods){
//            combFood.getItems().add(ab.getFoodName());
////            System.out.println(ab.getFoodID()+"" +ab.getFoodName());
//
//
//        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            lordCombFood();
            txtID.setText(""+MemoryID.getReseptionID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void qtyKeyRelese(KeyEvent keyEvent) {
        try {
            FoodDTO value = combFood.getSelectionModel().getSelectedItem();
            double v = value.getPrice();
            String qty = txtQty.getText();
            double v1 = v * Double.parseDouble(qty);
            txtPrice.setText(v1+"");
        }catch (Exception e){

        }

    }

    public void searchOnAction(ActionEvent actionEvent) throws Exception {
        String searchID = txtSearch.getText();
        int id = Integer.parseInt(searchID);
        orderService.searchOrder(id);
    }
}
