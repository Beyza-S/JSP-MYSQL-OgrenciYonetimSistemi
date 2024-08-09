package DAO;

import Entity.Bolum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static DAO.Veritabani.getConnection;

public class BolumDAO {
    private static final String SELECT_ALL_BOLUM = "SELECT * FROM bolum";

    public List<Bolum> getAllBolum() {
        List<Bolum> bolumler = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOLUM)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ad = rs.getString("ad");
                bolumler.add(new Bolum(id, ad));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bolumler;
    }
}
