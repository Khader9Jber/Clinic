package clinic.models;

import clinic.DBHandler;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Section {

    private final int id;
    private String name;

    public Section(String name) throws SQLException {
        this.name = name;
        id = generateId();
    }

    public Section(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public void save() throws SQLException {
        DBHandler.connection.createStatement().execute("INSERT INTO section (id, name) VALUES (" + id + ", \"" + name + "\"); ");
    }

    public void update() throws SQLException {
        DBHandler.connection.createStatement().execute("UPDATE section SET name = \"" + name + "\" WHERE id = " + id + "; ");
    }

    public void delete() throws SQLException {
        DBHandler.connection.createStatement().execute("Delete From section Where id = " + this.id + ";");
    }

    public static Section find(int id) throws SQLException {
        ResultSet result = DBHandler.connection.createStatement().executeQuery("SELECT * from section WHERE id = " + id + " ;");
        return new Section(id, result.getString("name"));
    }

    public static Section find(String name) throws SQLException {
        ResultSet result = DBHandler.connection.createStatement().executeQuery("SELECT * from section WHERE name = " + name + " ;");
        return new Section(result.getInt("id"), name);
    }

    public static List<Section> findAll() throws SQLException {
        ResultSet result = DBHandler.connection.createStatement().executeQuery("SELECT * from section;");
        List<Section> sections = new ArrayList();
        while (result.next()) {
            sections.add(new Section(result.getInt("id"), result.getString("name")));
        }
        return sections;
    }

    public List<Admin> findAdmins() throws SQLException {
        ResultSet result = DBHandler.connection.createStatement().executeQuery("SELECT * from admin WHERE SectionID = " + id + " ;");
        List<Admin> admins = new ArrayList();
        while (result.next()) {
            admins.add(new Admin(result.getInt("id"), result.getString("name"), result.getString("email"), result.getString("password"), result.getString("img"), result.getString("phone"), Section.find(result.getInt("SectionID")), result.getString("room"), result.getString("title"), result.getString("address"), result.getString("birthdate")));
        }
        return admins;
    }

    public List<Service> findServices() throws SQLException {
        ResultSet result = DBHandler.connection.createStatement().executeQuery("SELECT * from service WHERE SectionID = " + id + " ;");
        List<Service> services = new ArrayList();
        while (result.next()) {
            services.add(new Service(result.getInt("id"), result.getString("name"), Section.find(result.getInt("SectionID")), result.getDouble("cost")));
        }
        return services;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private static int generateId() throws SQLException {
        ResultSet rs = DBHandler.connection.createStatement().executeQuery("select max(id) from section");
        rs.next();
        int maxId = rs.getInt(1);
        return ++maxId;
    }

}
