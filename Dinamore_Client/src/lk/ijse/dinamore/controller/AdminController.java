package lk.ijse.dinamore.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {
    @FXML
    private AnchorPane AdminAncker;

    public void cheffClick(MouseEvent mouseEvent) throws IOException {
        Parent rootPain= FXMLLoader.load(this.getClass().getResource("/lk/ijse/dinamore/view/ManageCheff.fxml"));
        Scene scene = new Scene(rootPain);
        Stage stage = (Stage) this.AdminAncker.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void deliverClick(MouseEvent mouseEvent) throws IOException {
        Parent rootPain= FXMLLoader.load(this.getClass().getResource("/lk/ijse/dinamore/view/ManageDeliver.fxml"));
        Scene scene = new Scene(rootPain);
        Stage stage = (Stage) this.AdminAncker.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void reseptionClick(MouseEvent mouseEvent) throws IOException {
        Parent rootPain= FXMLLoader.load(this.getClass().getResource("/lk/ijse/dinamore/view/ManageReseption.fxml"));
        Scene scene = new Scene(rootPain);
        Stage stage = (Stage) this.AdminAncker.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void foodClick(MouseEvent mouseEvent) throws IOException {
        Parent rootPain= FXMLLoader.load(this.getClass().getResource("/lk/ijse/dinamore/view/ManageFood.fxml"));
        Scene scene = new Scene(rootPain);
        Stage stage = (Stage) this.AdminAncker.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void reportsClick(MouseEvent mouseEvent) throws IOException {
        Parent rootPain= FXMLLoader.load(this.getClass().getResource("/lk/ijse/dinamore/view/ManageReports.fxml"));
        Scene scene = new Scene(rootPain);
        Stage stage = (Stage) this.AdminAncker.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void backClicked(MouseEvent mouseEvent) throws IOException {
        Parent rootPain= FXMLLoader.load(this.getClass().getResource("/lk/ijse/dinamore/view/Dashbord.fxml"));
        Scene scene = new Scene(rootPain);
        Stage stage = (Stage) this.AdminAncker.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
    }
}
