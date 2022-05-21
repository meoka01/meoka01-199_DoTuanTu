package mode;

public class Product {
	protected int id;
	protected String nameProduct;
	protected String price;
	protected String quantity;
	protected String dateInput;
	public Product() {
	}
	
	public Product(String nameProduct, String price, String quantity, String dateInput ) {
		super();
		this.nameProduct = nameProduct;
		this.price = price;
		this.quantity = quantity;
		this.dateInput = dateInput;
	}

	public Product(int id, String nameProduct, String price, String quantity, String dateInput) {
		super();
		this.id = id;
		this.nameProduct = nameProduct;
		this.price = price;
		this.quantity = quantity;
		this.dateInput = dateInput;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameProduct() {
		return nameProduct;
	}
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getDateInput() {
		return dateInput;
	}
	public void setDateInput(String dateInput) {
		this.dateInput = dateInput;
	}
}
