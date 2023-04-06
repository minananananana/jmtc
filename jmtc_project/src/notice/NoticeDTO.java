package notice;
import java.util.*;

public class NoticeDTO {
	
	private int notice_idx;
	private String notice_title;
	private String notice_content;
	private int notice_readcount;
	private Date notice_date;
	private String notice_impo_YN;
	private int notice_ref;
	private int notice_ordNo;
	private int notice_levelNo;
	private int member_idx;
	
	private String member_nick;
	
	public String getMember_nick() {
		return member_nick;
	}
	public void setMember_nick(String member_nick) {
		this.member_nick = member_nick;
	}
	public int getNotice_idx() {
		return notice_idx;
	}
	public void setNotice_idx(int notice_idx) {
		this.notice_idx = notice_idx;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public int getNotice_readcount() {
		return notice_readcount;
	}
	public void setNotice_readcount(int notice_readcount) {
		this.notice_readcount = notice_readcount;
	}
	public Date getNotice_date() {
		return notice_date;
	}
	public void setNotice_date(Date notice_date) {
		this.notice_date = notice_date;
	}
	public String getNotice_impo_YN() {
		return notice_impo_YN;
	}
	public void setNotice_impo_YN(String notice_impo_YN) {
		this.notice_impo_YN = notice_impo_YN;
	}
	public int getNotice_ref() {
		return notice_ref;
	}
	public void setNotice_ref(int notice_ref) {
		this.notice_ref = notice_ref;
	}
	public int getNotice_ordNo() {
		return notice_ordNo;
	}
	public void setNotice_ordNo(int notice_ordNo) {
		this.notice_ordNo = notice_ordNo;
	}
	public int getNotice_levelNo() {
		return notice_levelNo;
	}
	public void setNotice_levelNo(int notice_levelNo) {
		this.notice_levelNo = notice_levelNo;
	}
	public int getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}
	
	
	
}
