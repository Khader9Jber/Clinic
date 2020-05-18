package clinic.models;

import clinic.DBHandler;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillService {

    private Bill bill;
    private Service service;
    private int count;
    private Admin admin;
    private String note;

    public BillService(Bill bill, Service service, int count, Admin admin, String note) {
        this.bill = bill;
        this.service = service;
        this.count = count;
        this.admin = admin;
        this.note = note;
    }

    public void save() throws SQLException {
        DBHandler.connection.createStatement().execute("INSERT INTO billservice (BillID, " + (service == null ? "" : "ServiceID, ") + "count, " + (admin == null ? "" : "AdminID, ") + "note) VALUES (" + bill.getId() + (service == null ? "" : (", " + service.getId())) + ", " + count + (admin == null ? "" : (", " + admin.getId())) + ", \"" + note + "\"); ");
    }

    public static List<BillService> findAdmins(int id) throws SQLException {
        ResultSet result = DBHandler.connection.createStatement().executeQuery("SELECT * from billservice WHERE SectionID = " + id + " ;");
        List<BillService> bs = new ArrayList();
        while (result.next()) {
            bs.add(new BillService(Bill.find(result.getInt("BillID")), Service.find(result.getInt("SectionID")), result.getInt("count"), Admin.find(result.getInt("AdminID")), result.getString("note")));
        }
        return bs;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
