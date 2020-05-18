package clinic.models;

import clinic.DBHandler;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Bill {

    public final static int ACTIVE = 0;
    public final static int FINISHED = 1;
    public final static int REVIEW = 2;
    public final static int WAITING = 3;

    private final int id;
    private String date;
    private Patient patient;
    private Admin admin;
    private int state;

    public Bill(int id, String date, Patient patient, Admin admin, int state) {
        this.id = id;
        this.date = date;
        this.patient = patient;
        this.admin = admin;
        this.state = state;
    }

    public Bill(String date, Patient patient, Admin admin, int state) throws SQLException {
        this.id = generateId();
        this.date = date;
        this.patient = patient;
        this.admin = admin;
        this.state = state;
    }

    public void save() throws SQLException {
        
        DBHandler.connection.createStatement().execute("INSERT INTO bill (id, date, PatientID, AdminID, state) VALUES (" + id + ", \"" + date + "\", " + patient.getId() + ", " + admin.getId() + ", " + state + "); ");
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    private static int generateId() throws SQLException {
        ResultSet rs = DBHandler.connection.createStatement().executeQuery("select max(id) from bill");
        rs.next();
        int maxId = rs.getInt(1);
        return ++maxId;
    }

    public int getId() {
        return this.id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public static Bill find(int id) throws SQLException {
        ResultSet result = DBHandler.connection.createStatement().executeQuery("SELECT * from section WHERE id = " + id + " ;");
        return new Bill(id, result.getString("date"), Patient.find(result.getInt("PatientID")), Admin.find(result.getInt("AdminID")), result.getInt("state"));
    }
}
