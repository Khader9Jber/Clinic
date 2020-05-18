package clinic.models;

import clinic.Clinic;
import clinic.DBHandler;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Admin {

    public static boolean auth(String email, String password) {
        try {
            ResultSet result = DBHandler.connection.createStatement().executeQuery("SELECT * from admin WHERE email = \"" + email + "\" AND password = \"" + password + "\" ;");
            Clinic.auth(find(result.getInt("id")));
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private final int id;

    private String name;
    private String email;
    private String password;
    private String img;
    private String phone;
    private Section section;
    private String room;
    private String title;
    private String address;
    private String birthdate;

    public Admin(int id, String name, String email, String password, String img, String phone, Section section, String room, String title, String address, String birthday) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.img = img;
        this.phone = phone;
        this.section = section;
        this.room = room;
        this.title = title;
        this.address = address;
        this.birthdate = birthday;
    }

    public Admin(String name, String email, String password, String img, String phone, Section section, String room, String title, String address, String birthday) throws SQLException {
        this.id = generateId();
        this.name = name;
        this.email = email;
        this.password = password;
        this.img = img;
        this.phone = phone;
        this.section = section;
        this.room = room;
        this.title = title;
        this.address = address;
        this.birthdate = birthday;
    }

    public Admin() throws SQLException {
        this.id = generateId();
    }

    public void save() throws SQLException {
        DBHandler.connection.createStatement().execute("INSERT INTO admin (id, name, email, password, img, phone, SectionID, room, title, address, birthdate) VALUES (" + id + ", \"" + name + "\", \"" + email + "\", \"" + password + "\", \"" + img + "\", \"" + phone + "\", " + section.getId() + ", \"" + room + "\", \"" + title + "\", \"" + address + "\", \"" + birthdate + "\"); ");
    }

    public void update() throws SQLException {
        DBHandler.connection.createStatement().execute("UPDATE admin SET  name = \"" + name + "\", email = \"" + email + "\", password = \"" + password + "\", img = \"" + img + "\", phone = \"" + phone + "\", SectionID = " + section.getId() + ", room = \"" + room + "\", title = \"" + title + "\", address = \"" + address + "\", birthdate = \"" + birthdate + "\" WHERE id = " + id + " ;");
    }

    public static Admin find(int id) throws SQLException {
        ResultSet result = DBHandler.connection.createStatement().executeQuery("SELECT * from admin WHERE id = " + id + " ;");
        return new Admin(result.getInt("id"), result.getString("name"), result.getString("email"), result.getString("password"), result.getString("img"), result.getString("phone"), Section.find(result.getInt("SectionID")), result.getString("room"), result.getString("title"), result.getString("address"), result.getString("birthdate"));
    }

    public static List<Admin> findAll() throws SQLException {
        ResultSet result = DBHandler.connection.createStatement().executeQuery("SELECT * from admin;");
        List<Admin> admins = new ArrayList();
        while (result.next()) {
            admins.add(new Admin(result.getInt("id"), result.getString("name"), result.getString("email"), result.getString("password"), result.getString("img"), result.getString("phone"), Section.find(result.getInt("SectionID")), result.getString("room"), result.getString("title"), result.getString("address"), result.getString("birthdate")));
        }
        return admins;
    }

    public void delete() throws SQLException {
        DBHandler.connection.createStatement().execute("Delete From admin Where id = " + this.id + ";");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getImg() {
        return img;
    }

    public String getPhone() {
        return phone;
    }

    public Section getSection() {
        return section;
    }

    public String getRoom() {
        return room;
    }

    public String getTitle() {
        return title;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthdate;
    }

    public void setBirthday(String birthday) {
        this.birthdate = birthday;
    }

    public static int generateId() throws SQLException {
        ResultSet rs = DBHandler.connection.createStatement().executeQuery("select max(id) from admin");
        rs.next();
        int maxId = rs.getInt(1);
        return ++maxId;
    }

    public List<Bill> getBills() {
        try {
            ResultSet result = DBHandler.connection.createStatement().executeQuery("SELECT * from bill WHERE AdminID = " + id + " AND state = " + Bill.WAITING + " ORDER by id DESC;");
            List<Bill> bills = new ArrayList();
            while (result.next()) {                
                bills.add(new Bill(result.getInt("id"), result.getString("date"), Patient.find(result.getInt("PatientID")), this, Bill.WAITING));
            }
            return bills;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

}
