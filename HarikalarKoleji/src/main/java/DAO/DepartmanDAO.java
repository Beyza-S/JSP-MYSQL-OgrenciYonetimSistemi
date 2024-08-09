package DAO;

import Entity.Departman;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static DAO.Veritabani.getConnection;

public class DepartmanDAO {
    private static final String SELECT_ALL_DEPARTMAN = "SELECT * FROM departman";

    public List<Departman> getAllDepartman() {
        List<Departman> departmanlar = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DEPARTMAN)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ad = rs.getString("ad");
                departmanlar.add(new Departman(id, ad));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departmanlar;
    }
}
