package lk.ijse.dinamore.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dinamore.PloxyHandler;
import lk.ijse.dinamore.dto.*;
import lk.ijse.dinamore.memoryID.MemoryID;
import lk.ijse.dinamore.service.ServiceFactory;
import lk.ijse.dinamore.service.custom.CheffService;
import lk.ijse.dinamore.service.custom.OrderDetailsService;
import lk.ijse.dinamore.service.custom.OrderService;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class CheffController implements Initializable {

    private static OrderService orderService;
    private static CheffService cheffService;
    private static OrderDetailsService orderDetailsService;
    private OrderDTO toUpdate;
    private int id;
    private CustomerDTO cusNic;
    private ReseptionDTO reseNIC;
    private double totalPrice;
    private double discount;
    private CheffDTO cheffNIC;
    private DeliverDTO deliverNIC;

    static {
        try {
            orderService = (OrderService) PloxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ORDER);
            cheffService = (CheffService) PloxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CHEFF);
            orderDetailsService = (OrderDetailsService) PloxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ORDER_DETAILS);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private AnchorPane cheffAncker;
    @FXML
    private JFXTextField txtID;
    @FXML
    private JFXTextField txtOrderID;
    @FXML
    private TableView<CheffTable> tableCoocking;

    @FXML
    private JFXTextField txtState;

    public void backClicked(MouseEvent mouseEvent) throws IOException {
        Parent rootPain= FXMLLoader.load(this.getClass().getResource("/lk/ijse/dinamore/view/Dashbord.fxml"));
        Scene scene = new Scene(rootPain);
        Stage stage = (Stage) this.cheffAncker.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtID.setText(""+MemoryID.getCheffID());
    }

    public void getOrderClicked(MouseEvent mouseEvent) throws Exception {
        txtState.setText("Cooking");
        List<OrderDTO> allOrder = orderService.getAllOrder();
        OrderDTO finals=null;
        String states="pending";
        for (OrderDTO cheff:allOrder){
            boolean a=cheff.getState().equalsIgnoreCase(states);
            if(a){
                finals=cheff;
                a=false;
            }
        }

        id=finals.getOrderID();
        cusNic = finals.getCusNic();
        reseNIC = finals.getReseNIC();
        totalPrice = finals.getTotalPrice();
        discount = finals.getDiscount();
        String newstates="Cooking";
        cheffNIC = finals.getCheffNIC();
        deliverNIC = finals.getDeliverNIC();

        int cheffID=Integer.parseInt(txtID.getText());
        CheffDTO newCheff = cheffService.searchCheff(cheffID);


        toUpdate=new OrderDTO(id,new Date(),cusNic,reseNIC,totalPrice,discount,newstates,newCheff,deliverNIC);
        boolean b = orderService.updateOrder(toUpdate);

        List<OrderDetailsDTO> allOrderDetails = orderDetailsService.getAllOrderDetails();
        for (OrderDetailsDTO temp:allOrderDetails){
            FoodDTO foodID = temp.getFoodID();
            CheffTable cheffTable=new CheffTable(foodID.getFoodID(),foodID.getFoodName(),temp.getQty());
            tableCoocking.setItems(FXCollections.observableArrayList(cheffTable));

            tableCoocking.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("foodID"));
            tableCoocking.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("foodName"));
            tableCoocking.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
        }


//        List<FoodDTO>news = null;
//        List<OrderDetailsDTO> orderDetailsDTOS = finals.getOrderDetailsDTOS();
//        for (OrderDetailsDTO ode:orderDetailsDTOS){
//            FoodDTO foodID = ode.getFoodID();
//            news.add(foodID);
//        }
//
//
//
//        List<OrderDetailsDTO> allOrderDetails = orderDetailsService.getAllOrderDetails();
////        for (OrderDetailsDTO dto:allOrderDetails){
////            OrderDTO orderID = dto.getOrderID();
////            String orderID1 = ""+orderID.getOrderID();
////            if ((Boolean) orderID1.equalsIgnoreCase(String.valueOf(id))){
////
////            }
////        }
//
//        tableCoocking.setItems(FXCollections.observableArrayList(news));
//
//        tableCoocking.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("foodID"));
//        tableCoocking.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("foodName"));
//        tableCoocking.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("price"));
//
//

    }

    public void RaddyClicked(MouseEvent mouseEvent) throws Exception {
        txtState.setText("CoockingComplete");
        int cheffID=Integer.parseInt(txtID.getText());
        CheffDTO newCheff = cheffService.searchCheff(cheffID);


        toUpdate=new OrderDTO(id,new Date(),cusNic,reseNIC,totalPrice,discount,"CoockingComplete",newCheff,deliverNIC);
        boolean b = orderService.updateOrder(toUpdate);

    }
}
