package review;
import java.util.Date;
public class ReviewDTO {

	private int review_idx;
	private String review_id;
	private String review_title;
	private String review_content;
	private int review_score;
	private Date review_date;
	private int review_readcount;
	private int review_ref;
	private int review_ordNo;
	private int review_levelNo;
    int member_idx;
    private String review_image;
	public int getReview_idx() {
		return review_idx;
	}
	public void setReview_idx(int review_idx) {
		this.review_idx = review_idx;
	}
	public String getReview_id() {
		return review_id;
	}
	public void setReview_id(String review_id) {
		this.review_id = review_id;
	}
	public String getReview_title() {
		return review_title;
	}
	public void setReview_title(String review_title) {
		this.review_title = review_title;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public int getReview_score() {
		return review_score;
	}
	public void setReview_score(int review_score) {
		this.review_score = review_score;
	}
	public Date getReview_date() {
		return review_date;
	}
	public void setReview_date(Date review_date) {
		this.review_date = review_date;
	}
	public int getReview_readcount() {
		return review_readcount;
	}
	public void setReview_readcount(int review_readcount) {
		this.review_readcount = review_readcount;
	}
	public int getReview_ref() {
		return review_ref;
	}
	public void setReview_ref(int review_ref) {
		this.review_ref = review_ref;
	}
	public int getReview_ordNo() {
		return review_ordNo;
	}
	public void setReview_ordNo(int review_ordNo) {
		this.review_ordNo = review_ordNo;
	}
	public int getReview_levelNo() {
		return review_levelNo;
	}
	public void setReview_levelNo(int review_levelNo) {
		this.review_levelNo = review_levelNo;
	}
	public int getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}
	public String getReview_image() {
		return review_image;
	}
	public void setReview_image(String review_image) {
		this.review_image = review_image;
	}
    
    
}//class-end
