package order;
import java.util.Date;

public class OrderDTO {
	private int order_no; //주문일련번호
	private String order_code; //상품일련번호
	private String order_name;//상품명
	private String order_quantity; //주문수량
	private Date order_date; //주문날짜
	private String order_state; //주문상태
	private int member_idx;
	
	public int getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public String getOrder_code() {
		return order_code;
	}
	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}
	public String getOrder_name() {
		return order_name;
	}
	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}
	public String getOrder_quantity() {
		return order_quantity;
	}
	public void setOrder_quantity(String order_quantity) {
		this.order_quantity = order_quantity;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public String getOrder_state() {
		return order_state;
	}
	public void setOrder_state(String order_state) {
		this.order_state = order_state;
	}
	
	
}//class-end