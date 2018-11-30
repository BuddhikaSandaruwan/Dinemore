package lk.ijse.dinamore.controller;

import com.jfoenix.controls.JFXButton;
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
import lk.ijse.dinamore.dto.*;
import lk.ijse.dinamore.memoryID.MemoryID;
import lk.ijse.dinamore.service.ServiceFactory;
import lk.ijse.dinamore.service.custom.CheffService;
import lk.ijse.dinamore.service.custom.DeliverService;
import lk.ijse.dinamore.service.custom.OrderDetailsService;
import lk.ijse.dinamore.service.custom.OrderService;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class DeliverController implements Initializable {

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtCustomerNIC;

    @FXML
    private JFXTextField txtDeliveryAdress;

    @FXML
    private JFXTextField txtTP;

    @FXML
    private JFXButton butComplete;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtOrderID;

    @FXML
    private JFXTextField txtStates;



    @FXML
    private AnchorPane deliverAnncker;

    @FXML
    private JFXTextField txtID;


    private static OrderService orderService;
    private static DeliverService deliverService;
    private OrderDTO toUpdate;
    private int id;
    private CustomerDTO cusNic;
    private ReseptionDTO reseNIC;
    private double totalPrice;
    private double discount;
    private CheffDTO cheffNIC;
    private DeliverDTO deliverNIC;
    OrderDTO finals=null;

    public DeliverController() {
    }
    static {
        try {
            orderService = (OrderService) PloxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ORDER);
            deliverService = (DeliverService) PloxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.DELIVER);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void backClicked(MouseEvent mouseEvent) throws IOException {
        Parent rootPain= FXMLLoader.load(this.getClass().getResource("/lk/ijse/dinamore/view/Dashbord.fxml"));
        Scene scene = new Scene(rootPain);
        Stage stage = (Stage) this.deliverAnncker.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtID.setText(""+MemoryID.getDeliverID());
    }

    public void completeClicked(MouseEvent mouseEvent) throws Exception {
        txtStates.setText("DeliveringComplete");
        int cheffID=Integer.parseInt(txtID.getText());
        DeliverDTO newDeliver = deliverService.searchDeliver(cheffID);


        toUpdate=new OrderDTO(id,new Date(),cusNic,reseNIC,totalPrice,discount,"DeliveringComplete",cheffNIC,newDeliver);
        boolean b = orderService.updateOrder(toUpdate);
        if (b){
            Alert alert=new Alert(Alert.AlertType.INFORMATION,"Complete", ButtonType.OK);
            alert.show();
        }else {
            Alert alert=new Alert(Alert.AlertType.INFORMATION,"Complete", ButtonType.OK);
            alert.show();
        }
    }

    public void getOrderClicked(MouseEvent mouseEvent) throws Exception {
        txtStates.setText("Delivering");
        List<OrderDTO> allOrder = orderService.getAllOrder();
        String states="CoockingComplete";
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
        String newstates="Delivering";
        cheffNIC = finals.getCheffNIC();
        deliverNIC = finals.getDeliverNIC();
        double finalPrice=totalPrice-discount;

        CustomerDTO newCustomer = finals.getCusNic();
        System.out.println(newCustomer.getName());

        String name = newCustomer.getName();
        String nic = newCustomer.getNic();
        String adress = newCustomer.getAdress();
        String tp = newCustomer.getTp();

        txtOrderID.setText(id+"");
        txtPrice.setText(finalPrice+"");
        txtCustomerName.setText(name);
        txtCustomerNIC.setText(nic);
        txtDeliveryAdress.setText(adress);
        txtTP.setText(tp);


        int cheffID=Integer.parseInt(txtID.getText());
        DeliverDTO newDeliver = deliverService.searchDeliver(cheffID);


        toUpdate=new OrderDTO(id,new Date(), this.cusNic,reseNIC,totalPrice,discount,newstates,cheffNIC,newDeliver);
        boolean b = orderService.updateOrder(toUpdate);

    }
}
