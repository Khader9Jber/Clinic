package clinic.models;

import clinic.DBHandler;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Service {

    private final int id;

    private String name;
    private Section section;
    private double cost;

    public Service(int id, String name, Section section, double cost) {
        this.id = id;
        this.name = name;
        this.section = section;
        this.cost = cost;
    }

    public Service(String name, Section section, double cost) throws SQLException {
        this.id = generateId();
        this.name = name;
        this.section = section;
        this.cost = cost;
    }

    public void save() throws SQLException {
        DBHandler.connection.createStatement().execute("INSERT INTO service (id, name, SectionID, cost) VALUES (" + id + ", \"" + name + "\", " + section.getId() + ", " + cost + "); ");
    }

    public void update() throws SQLException {
        DBHandler.connection.createStatement().execute("UPDATE service SET name = \"" + name + "\", SectionID = " + section.getId() + ", cost = " + cost + " WHERE id = " + id + "; ");
    }

    public void delete() throws SQLException {
        DBHandler.connection.createStatement().execute("Delete From service Where id = " + this.id + ";");
    }

    public static Service find(int id) throws SQLException {
        ResultSet result = DBHandler.connection.createStatement().executeQuery("SELECT * from service WHERE id = " + id + " ;");
        return new Service(id, result.getString("name"), Section.find(result.getInt("SectionID")), result.getDouble("cost"));
    }

    public static List<Service> findAll() throws SQLException {
        ResultSet result = DBHandler.connection.createStatement().executeQuery("SELECT * from service;");
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

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    private static int generateId() throws SQLException {
        ResultSet rs = DBHandler.connection.createStatement().executeQuery("select max(id) from service");
        rs.next();
        int maxId = rs.getInt(1);
        return ++maxId;
    }
}
