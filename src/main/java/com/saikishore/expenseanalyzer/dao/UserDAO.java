package com.saikishore.expenseanalyzer.dao;

import com.saikishore.expenseanalyzer.model.User;
import com.saikishore.expenseanalyzer.util.DBConnection;
import com.saikishore.expenseanalyzer.util.PasswordUtil;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public boolean registerUser(User user) {

        String sql = "INSERT INTO users(full_name, email, password) VALUES (?, ?, ?)";
    
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
    
            ps.setString(1, user.getFullName().trim());
            ps.setString(2, user.getEmail().trim());
    
            // Hash password before saving
            String hashedPassword = PasswordUtil.hashPassword(user.getPassword());
    
            ps.setString(3, hashedPassword);
    
            return ps.executeUpdate() > 0;
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return false;
    }

    // Login user
    public User loginUser(String email, String password) {

    String sql = "SELECT * FROM users WHERE email = ?";

    try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
    ) {

        ps.setString(1, email);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            String storedHash = rs.getString("password");

            // Verify entered password against the stored BCrypt hash
            if (PasswordUtil.verifyPassword(password, storedHash)) {

                User user = new User();

                user.setId(rs.getInt("id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(storedHash);

                return user;
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}

    // Check whether email already exists
    public boolean emailExists(String email) {

        String sql = "SELECT id FROM users WHERE email = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    // Update password using email
public boolean updatePassword(String email, String newPassword) {

    String sql = "UPDATE users SET password = ? WHERE email = ?";

    try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
    ) {

        // Hash the new password before saving
        String hashedPassword = PasswordUtil.hashPassword(newPassword);

        ps.setString(1, hashedPassword);
        ps.setString(2, email);

        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
}
public User getUserById(int userId) {

    String sql = """
            SELECT *
            FROM users
            WHERE id = ?
            """;

    try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql)
    ) {

        preparedStatement.setInt(1, userId);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {

            User user = new User();

            user.setId(resultSet.getInt("id"));
            user.setFullName(resultSet.getString("full_name"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setCreatedAt(resultSet.getTimestamp("created_at"));

            return user;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;
}
public boolean updateProfile(int id,
    String fullName,
    String email){

String sql="""
UPDATE users
SET full_name=?,
email=?
WHERE id=?
""";

try(
Connection connection=DBConnection.getConnection();
PreparedStatement preparedStatement=
connection.prepareStatement(sql)
){

preparedStatement.setString(1,fullName);
preparedStatement.setString(2,email);
preparedStatement.setInt(3,id);

return preparedStatement.executeUpdate()>0;

}catch(SQLException e){

e.printStackTrace();

}

return false;
}
public boolean verifyPassword(int userId, String password) {

    String sql = """
            SELECT password
            FROM users
            WHERE id = ?
            """;

    try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql)
    ) {

        preparedStatement.setInt(1, userId);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {

            String storedHash = resultSet.getString("password");

            return PasswordUtil.verifyPassword(password, storedHash);
        }

    } catch (SQLException e) {

        e.printStackTrace();

    }

    return false;
}
public boolean emailExistsForAnotherUser(String email, int userId){

    String sql = "SELECT id FROM users WHERE email = ? AND id <> ?";

    try(Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)){

        ps.setString(1, email.trim());
        ps.setInt(2, userId);

        ResultSet rs = ps.executeQuery();

        return rs.next();

    }catch(Exception e){

        e.printStackTrace();
    }

    return false;
}
}