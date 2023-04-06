package cart;
import java.util.*;

public class CartDTO {
	int cart_idx;
	String cart_orddate;
	int cart_price;
	String cart_name;
	String cart_state;
	int cart_quantity;
	String cart_code;
	String cart_image;
	int member_idx;
	
	public int getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}
	public String getCart_image() {
		return cart_image;
	}
	public void setCart_image(String cart_image) {
		this.cart_image = cart_image;
	}
	public String getCart_code() {
		return cart_code;
	}
	public void setCart_code(String cart_code) {
		this.cart_code = cart_code;
	}
	public int getCart_idx() {
		return cart_idx;
	}
	public void setCart_idx(int cart_idx) {
		this.cart_idx = cart_idx;
	}
	public String getCart_orddate() {
		return cart_orddate;
	}
	public void setCart_orddate(String cart_orddate) {
		this.cart_orddate = cart_orddate;
	}
	public int getCart_price() {
		return cart_price;
	}
	public void setCart_price(int cart_price) {
		this.cart_price = cart_price;
	}
	public String getCart_name() {
		return cart_name;
	}
	public void setCart_name(String cart_name) {
		this.cart_name = cart_name;
	}
	public String getCart_state() {
		return cart_state;
	}
	public void setCart_state(String cart_state) {
		this.cart_state = cart_state;
	}
	public int getCart_quantity() {
		return cart_quantity;
	}
	public void setCart_quantity(int cart_quantity) {
		this.cart_quantity = cart_quantity;
	}
	
}
