package lk.ijse.dinamore.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dinamore.DB.DBConnection;
import lk.ijse.dinamore.jasperviewer.MyJasperViewer;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageReportController {
    @FXML
    private AnchorPane reportsConAncker;
    public void backClicked(MouseEvent mouseEvent) throws IOException {
        Parent rootPain= FXMLLoader.load(this.getClass().getResource("/lk/ijse/dinamore/view/Admin.fxml"));
        Scene scene = new Scene(rootPain);
        Stage stage = (Stage) this.reportsConAncker.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void aaclick(MouseEvent mouseEvent) throws JRException {
        try {
            InputStream file = getClass().getResourceAsStream("/lk/ijse/dinamore/report/AllCustomers.jasper");
            JasperReport report = JasperCompileManager.compileReport(file);
            HashMap map = new HashMap();
            JasperPrint print = JasperFillManager.fillReport(report, map, DBConnection.getInstance().getConnection());

            new MyJasperViewer(print).setVisible(true);
        } catch (Exception ex) {

        }

    }
}
