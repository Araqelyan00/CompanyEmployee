package companyEmployee.manager;

import companyEmployee.db.DBConnectionProvider;
import companyEmployee.model.User;
import companyEmployee.model.UserType;

import java.sql.*;

public class UserManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void saveUser(User user) {
        String sql = "INSERT INTO user (userName, userSurname, userEmail, userPassword, userType) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getUserSurname());
            ps.setString(3, user.getUserEmail());
            ps.setString(4, user.getUserPassword());
            ps.setString(5, user.getUserType().name());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                user.setUserId(rs.getInt(1));
            }
            System.out.println("User saved into DB.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM user WHERE userEmail = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery(sql);
            if (rs.next()) {
                return getUserFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        return User.builder()
                .userId(rs.getInt("userId"))
                .userName(rs.getString("userName"))
                .userSurname(rs.getString("userSurname"))
                .userEmail(rs.getString("userEmail"))
                .userPassword(rs.getString("userPassword"))
                .userType(UserType.valueOf(rs.getString("userType")))
                .build();
    }

    public User getByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM user WHERE userEmail = ? AND userPassword = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return getUserFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


}
