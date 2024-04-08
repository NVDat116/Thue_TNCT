package dao;

import model.User;

import java.sql.*;

public class UserDAO {
    public int insertUserAndGetId(User user) {
        int userId = -1;
        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "INSERT INTO User (name, dateOfBirth, gender, nationality) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getDateOfbirth());
            stmt.setString(3, user.getGender());
            stmt.setString(4, user.getNationality());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    userId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }

}
