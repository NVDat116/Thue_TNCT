package dao;

import model.User;
import model.taxInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TaxInfoDAO {
    public boolean insertTaxInfo(taxInfo taxInfo) {
        boolean inserted  = false;
        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "INSERT INTO taxinfo (userId, declarationDate) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, taxInfo.getUserId());
            stmt.setString(2, taxInfo.getDeclarationDate());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                inserted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inserted;
    }
}
