package qna;

import java.util.Date;

public class QnaDTO {
	private int qna_idx;
	private String qna_name;
	private String qna_pw;
	private String qna_title;
	private String qna_content;
	private Date qna_date;
	private int qna_readcount;
	 
	private int qna_ref;
	private int qna_ordNo;
	private int qna_levelNo;
	private int member_idx;
	private String qna_secret;
	
	
	
	
	public String getQna_secret() {
		return qna_secret;
	}
	public void setQna_secret(String qna_secret) {
		this.qna_secret = qna_secret;
	}
	public String getQna_pw() {
		return qna_pw;
	}
	public void setQna_pw(String qna_pw) {
		this.qna_pw = qna_pw;
	}
	public String getQna_name() {
		return qna_name;
	}
	public void setQna_name(String qna_name) {
		this.qna_name = qna_name;
	}
	public int getQna_idx() {
		return qna_idx;
	}
	public void setQna_idx(int qna_idx) {
		this.qna_idx = qna_idx;
	}
	public String getQna_title() {
		return qna_title;
	}
	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}
	public String getQna_content() {
		return qna_content;
	}
	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}
	public Date getQna_date() {
		return qna_date;
	}
	public void setQna_date(Date qna_date) {
		this.qna_date = qna_date;
	}
	public int getQna_readcount() {
		return qna_readcount;
	}
	public void setQna_readcount(int qna_readcount) {
		this.qna_readcount = qna_readcount;
	}
	public int getQna_ref() {
		return qna_ref;
	}
	public void setQna_ref(int qna_ref) {
		this.qna_ref = qna_ref;
	}
	public int getQna_ordNo() {
		return qna_ordNo;
	}
	public void setQna_ordNo(int qna_ordNo) {
		this.qna_ordNo = qna_ordNo;
	}
	public int getQna_levelNo() {
		return qna_levelNo;
	}
	public void setQna_levelNo(int qna_levelNo) {
		this.qna_levelNo = qna_levelNo;
	}
	public int getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}
	
	

}
