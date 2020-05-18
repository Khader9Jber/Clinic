package clinic.models;

import clinic.DBHandler;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Patient {

    private final int id;

    private String name;
    private String birthdate;
    private String address;
    private String phone;
    private String img;

    public Patient(int id, String name, String birthdate, String address, String phone, String img) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.address = address;
        this.phone = phone;
        this.img = img;
    }

    public Patient(String name, String birthdate, String address, String phone, String img) throws SQLException {
        this.id = generateId();
        this.name = name;
        this.birthdate = birthdate;
        this.address = address;
        this.phone = phone;
        this.img = img;
    }

    public void save() throws SQLException {
        DBHandler.connection.createStatement().execute("INSERT INTO patient (id, name, birthdate, address, phone, img) VALUES (" + id + ", \"" + name + "\", \"" + birthdate + "\", \"" + address + "\", \"" + phone + "\", \"" + img + "\"); ");
    }

    public void update() throws SQLException {
        DBHandler.connection.createStatement().execute("UPDATE patient SET  name = \"" + name + "\", img = \"" + img + "\", phone = \"" + phone + "\", address = \"" + address + "\", birthdate = \"" + birthdate + "\" WHERE id = " + id + " ;");
    }

    public void delete() throws SQLException {
        DBHandler.connection.createStatement().execute("Delete From patient Where id = " + this.id + ";");
    }

    public static Patient find(int id) throws SQLException {
        ResultSet result = DBHandler.connection.createStatement().executeQuery("SELECT * from patient WHERE id = " + id + " ;");
        return new Patient(result.getInt("id"), result.getString("name"), result.getString("birthdate"), result.getString("address"), result.getString("phone"), result.getString("img"));
    }
    
    public static List<Patient> findAll() throws SQLException {
        ResultSet result = DBHandler.connection.createStatement().executeQuery("SELECT * from patient;");
        List<Patient> patients = new ArrayList();
        while (result.next()) {
            patients.add(new Patient(result.getInt("id"), result.getString("name"), result.getString("birthdate"), result.getString("address"), result.getString("phone"), result.getString("img")));
        }
        return patients;
    }

    public List<Bill> findBills() throws SQLException {
        ResultSet result = DBHandler.connection.createStatement().executeQuery("SELECT * from bill WHERE SectionID = " + id + " ;");
        List<Bill> bills = new ArrayList();
        while (result.next()) {
            bills.add(new Bill(result.getInt("id"), result.getString("date"), this, Admin.find(result.getInt("AdminID")),Bill.ACTIVE));
        }
        return bills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    private static int generateId() throws SQLException {
        ResultSet rs = DBHandler.connection.createStatement().executeQuery("select max(id) from patient section");
        rs.next();
        int maxId = rs.getInt(1);
        return ++maxId;
    }

    public int getId() {
        return this.id;
    }

}
