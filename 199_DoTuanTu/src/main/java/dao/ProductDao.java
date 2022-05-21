package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mode.Product;

public class ProductDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/vinmart?allowPublicKeyRetrieval=true&useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "24120";

	private static final String INSERT_Product_SQL = "INSERT INTO product" + "  (nameProduct, price, quantity, dateInput) VALUES "
			+ " (?, ?, ?, ?);";

	private static final String SELECT_Product_BY_ID = "select id,nameProduct,price,quantity,dateInput from product where id =?";
	private static final String SELECT_ALL_Product = "select * from product";
	private static final String DELETE_Product_SQL = "delete from product where id = ?;";
	private static final String UPDATE_Product_SQL = "update product set nameProduct = ?, price= ?, quantity =?, dateInput =? where id = ?;";

	public void UserDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			System.out.println("cc");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertProduct(Product pro) throws SQLException {
		System.out.println(INSERT_Product_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_Product_SQL)) {
			preparedStatement.setString(1, pro.getNameProduct());
			preparedStatement.setString(2, pro.getPrice());
			preparedStatement.setString(3, pro.getQuantity());
			preparedStatement.setString(4, pro.getDateInput());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Product selectProduct(int id) {
		Product pro = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_Product_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				//id,nameProduct,price,quantity, dateInput
				String nameProduct = rs.getString("nameProduct");
				String price = rs.getString("price");
				String quantity = rs.getString("quantity");
				String dateInput = rs.getString("dateInput");
				pro = new Product(id, nameProduct, price, quantity, dateInput);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return pro;
	}

	public List<Product> selectAllProduct() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Product> pro = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_Product);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String nameProduct = rs.getString("nameProduct");
				String price = rs.getString("price");
				String quantity = rs.getString("quantity");
				String dateInput = rs.getString("dateInput");
				
				pro.add(new Product(id, nameProduct, price, quantity,dateInput));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return pro;
	}

	public boolean deleteProduct(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_Product_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateProduct(Product pro) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_Product_SQL);) {
			statement.setString(1, pro.getNameProduct());
			statement.setString(2, pro.getPrice());
			statement.setString(3, pro.getQuantity());
			statement.setString(4, pro.getDateInput());
			statement.setInt(5, pro.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
