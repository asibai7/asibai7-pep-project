package DAO;

import Model.Account;
import Util.ConnectionUtil;
import java.sql.*;

public class AccountDAO {
    public Account addAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sqlcheck = "SELECT * FROM Account WHERE username = ?";
            PreparedStatement pscheck = connection.prepareStatement(sqlcheck);
            pscheck.setString(1, account.getUsername());
            ResultSet rscheck = pscheck.executeQuery();
            if (rscheck.next()){
                return null; 
            }
            String sql = "INSERT INTO Account (username, password) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1){
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()){
                    int id = rs.getInt(1);
                    return new Account(id, account.getUsername(), account.getPassword());
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public Account getAccountByUsernameAndPassword(String username, String password){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM Account WHERE username = ? AND password = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("account_id");
                String u = rs.getString("username");
                String p = rs.getString("password");
                return new Account(id, u, p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account getAccountById(int id) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM Account WHERE account_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Account(rs.getInt("account_id"),
                        rs.getString("username"),
                        rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
