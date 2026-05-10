package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSource {
  public static Connection connect() {
    String db_file = "jdbc:sqlite:resources/bank.db";
    Connection conn = null;

    try {
      conn = DriverManager.getConnection(db_file);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return conn;
  }

  public static Customer getCustomerByUsername(String username) {
    String sql = "SELECT id, name, username, password, account_id FROM Customers WHERE username = ?";
    Customer customer = null;

    try (
      Connection conn = connect();
      PreparedStatement statement = conn.prepareStatement(sql)
    ) {
      statement.setString(1, username);

      try (ResultSet resultSet = statement.executeQuery()) {
        customer = new Customer(
          resultSet.getInt("id"),
          resultSet.getString("name"),
          resultSet.getString("username"),
          resultSet.getString("password"),
          resultSet.getInt("account_id")
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return customer;
  }

  public static Account getAccountById(int id) {
    String sql = "SELECT id, type, balance FROM accounts WHERE id = ?";
    Account account = null;

    try (
      Connection conn = connect();
      PreparedStatement statement = conn.prepareStatement(sql)
    ) {
      statement.setInt(1, id);
      try (ResultSet resultSet = statement.executeQuery()) {
        account = new Account(
            resultSet.getInt("id"),
            resultSet.getString("type"),
            resultSet.getDouble("balance"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return account;
  }

  public static void updateAccountBalance(int accId, double balance) {
    String sql = "UPDATE Accounts SET balance = ? WHERE id = ?";

    try (
      Connection conn = connect();
      PreparedStatement statement = conn.prepareStatement(sql)
    ) {
      statement.setDouble(1, balance);
      statement.setInt(2, accId);

      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
