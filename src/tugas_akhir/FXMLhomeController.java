/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas_akhir;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author sheva
 */
public class FXMLhomeController implements Initializable {

    @FXML
    private TextField pemasukan;
    @FXML
    private TextField pengeluaran;
    @FXML
    private TextArea hasil;
    @FXML
    private Button btnsubmit;
    @FXML
    private Button btnreset;
     @FXML
    private Button btnup;
     Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    private ObservableList<tabel_data> data;
       @FXML
    private TableView<tabel_data> tabel;

    @FXML
    private TableColumn<tabel_data, String> id;

    @FXML
    private TableColumn<tabel_data, String> hasilpengeluaran;

    @FXML
    private TableColumn<tabel_data, String> pemasukant;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
    }    

    @FXML
    private void submit(ActionEvent event) {
        int masuk = Integer.parseInt(pemasukan.getText());
        int keluar = Integer.parseInt(pengeluaran.getText());
        hasil.setText("Pemasukan   : "+masuk+
                      "\nPengeluaran : "+keluar);
        try {
            String sql = "INSERT INTO data(pengeluaran,pemasukan) VALUES ("+keluar+","+masuk+")";
            java.sql.PreparedStatement pst=con.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @FXML
    private void reset(ActionEvent event) {
        pemasukan.setText("");
        pengeluaran.setText("");
        hasil.setText("");
    }
    
    @FXML
    void update(ActionEvent event) {
        try {
            data = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = stat.executeQuery("SELECT * FROM data");
            while (rs.next()) {
                //get string from db,whichever way 
                data.add(new tabel_data(rs.getString(1), rs.getString(3), rs.getString(2)));
            }

        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
        
        //Set cell value factory to tableview.
        //NB.PropertyValue Factory must be the same with the one set in model class.
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        hasilpengeluaran.setCellValueFactory(new PropertyValueFactory<>("pengeluaran"));
        pemasukant.setCellValueFactory(new PropertyValueFactory<>("masukan"));
        
        tabel.setItems(null);
        tabel.setItems(data);
    }
    
}
