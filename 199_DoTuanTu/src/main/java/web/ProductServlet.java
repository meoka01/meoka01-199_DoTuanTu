package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDao;
import mode.Product;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDao productDAO;
	
	public void init() {
		productDAO = new ProductDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertProduct(request, response);
				break;
			case "/delete":
				deleteUser(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateProduct(request, response);
				break;
			default:
				listProduct(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Product> listProduct = productDAO.selectAllProduct();
		request.setAttribute("listProduct", listProduct);
		RequestDispatcher dispatcher = request.getRequestDispatcher("product-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Product existingPro = productDAO.selectProduct(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
		request.setAttribute("product", existingPro);
		dispatcher.forward(request, response);

	}

	private void insertProduct(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		//id,nameProduct,price,quantity, dateInput
		String nameProduct = request.getParameter("nameProduct");
		String price = request.getParameter("price");
		String quantity = request.getParameter("quantity");
		String dateInput = request.getParameter("dateInput");
		Product newProduct = new Product(nameProduct, price, quantity,dateInput );
		productDAO.insertProduct(newProduct);
		response.sendRedirect("list");
	}

	private void updateProduct(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		//id,nameProduct,,quantity, dateInput
		int id = Integer.parseInt(request.getParameter("id"));
		String nameProduct = request.getParameter("nameProduct");
		String price = request.getParameter("price");
		String quantity = request.getParameter("quantity");
		String dateInput = request.getParameter("dateInput");
		Product product = new Product(id, nameProduct, price, quantity,dateInput);
		productDAO.updateProduct(product);
		response.sendRedirect("list");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		productDAO.deleteProduct(id);
		response.sendRedirect("list");

	}

}
