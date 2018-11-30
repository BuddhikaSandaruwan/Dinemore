package lk.ijse.dinamore.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashbordController {
    @FXML
    private AnchorPane DashBordAnker;

    @FXML
    private ImageView picReseption;

    @FXML
    private ImageView picCheff;

    @FXML
    private ImageView picAdmin;

    @FXML
    private ImageView picDeliver;

    public void reseptionClick(MouseEvent mouseEvent) throws Exception {
        Parent rootPain= FXMLLoader.load(this.getClass().getResource("/lk/ijse/dinamore/view/ReseptionLogIn.fxml"));
        Scene scene = new Scene(rootPain);
        Stage stage = (Stage) this.DashBordAnker.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void cheffClick(MouseEvent mouseEvent) throws Exception {
        Parent rootPain= FXMLLoader.load(this.getClass().getResource("/lk/ijse/dinamore/view/CheffLogIn.fxml"));
        Scene scene = new Scene(rootPain);
        Stage stage = (Stage) this.DashBordAnker.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void adminClick(MouseEvent mouseEvent) throws Exception {
        Parent rootPain= FXMLLoader.load(this.getClass().getResource("/lk/ijse/dinamore/view/AdminLogIn.fxml"));
        Scene scene = new Scene(rootPain);
        Stage stage = (Stage) this.DashBordAnker.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void deliverClick(MouseEvent mouseEvent) throws Exception {
        Parent rootPain= FXMLLoader.load(this.getClass().getResource("/lk/ijse/dinamore/view/DeliverLogIn.fxml"));
        Scene scene = new Scene(rootPain);
        Stage stage = (Stage) this.DashBordAnker.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
    }
}
