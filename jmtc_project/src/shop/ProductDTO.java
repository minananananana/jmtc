package shop;
import java.util.Date;

public class ProductDTO {
	private int product_idx;//��ǰ�Ϸù�ȣ p.k
	private String product_code;//��ǰ�ڵ� unique
	private String product_name;//��ǰ�̸�
	
	private String product_detail;//����
	private int product_price;//��ǰ ����
	
	private String product_seller;//ȸ��
	private int product_stock;//���� ����
	
	private Date product_date;//��ǰ ��� ��¥
	private String product_image;//��ǰ�̹���
	
	public int getProduct_idx() {
		return product_idx;
	}
	public void setProduct_idx(int product_idx) {
		this.product_idx = product_idx;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_detail() {
		return product_detail;
	}
	public void setProduct_detail(String product_detail) {
		this.product_detail = product_detail;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public String getProduct_seller() {
		return product_seller;
	}
	public void setProduct_seller(String product_seller) {
		this.product_seller = product_seller;
	}
	public int getProduct_stock() {
		return product_stock;
	}
	public void setProduct_stock(int product_stock) {
		this.product_stock = product_stock;
	}
	public Date getProduct_date() {
		return product_date;
	}
	public void setProduct_date(Date product_date) {
		this.product_date = product_date;
	}
	public String getProduct_image() {
		return product_image;
	}
	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}
	
}//class-end
