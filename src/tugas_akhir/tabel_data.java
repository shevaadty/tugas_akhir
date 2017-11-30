/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas_akhir;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author sheva
 */
public class tabel_data {
    private final StringProperty id;
    private final StringProperty masukan;
    private final StringProperty pengeluaran;
    
public tabel_data(String name, String email, String department) {
        this.id = new SimpleStringProperty(name);
        this.masukan = new SimpleStringProperty(email);
        this.pengeluaran = new SimpleStringProperty(department);
    }
    public String getid() {
        return id.get();
    }

    public String getmasukan() {
        return masukan.get();
    }

    public String getpengeluaran() {
        return pengeluaran.get();
    }
    
    public void setid(String value) {
        id.set(value);
    }

    public void setmasukan(String value) {
        masukan.set(value);
    }

    public void setpengeluaran(String value) {
        pengeluaran.set(value);
    }
    
    public StringProperty idProperty() {
        return id;
    }

    public StringProperty masukanProperty() {
        return masukan;
    }

    public StringProperty pengeluaranProperty() {
        return pengeluaran;
    }
}

