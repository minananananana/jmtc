package shop;
import java.util.Date;

public class ProductDTO {
	private int product_idx;//상품일련번호 p.k
	private String product_code;//상품코드 unique
	private String product_name;//상품이름
	
	private String product_detail;//설명
	private int product_price;//상품 가격
	
	private String product_seller;//회사
	private int product_stock;//보유 수량
	
	private Date product_date;//상품 등록 날짜
	private String product_image;//상품이미지
	
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
