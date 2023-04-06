package mysql;
import java.util.Date;
//모델빈=DTO
public class BoardDTO {
	private int board_idx;//글번호
	private String board_name;//작성자
	private String board_title;//글제목
	private String board_content;//글내용
	private String board_pw;//암호
	
	private Date board_date;//날짜
	private int board_readcount;//조횟수
	
	private int board_ref;//글 그룹
	private int board_ordNo;//글 순서 (원글 답글 순서)
	private int board_levelNo;//답글 깊이
	
	private String board_ip;
	int member_idx;
	
	public int getBoard_idx() {
		return board_idx;
	}
	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}
	public String getBoard_name() {
		return board_name;
	}
	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public String getBoard_pw() {
		return board_pw;
	}
	public void setBoard_pw(String board_pw) {
		this.board_pw = board_pw;
	}
	public Date getBoard_date() {
		return board_date;
	}
	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}
	public int getBoard_readcount() {
		return board_readcount;
	}
	public void setBoard_readcount(int board_readcount) {
		this.board_readcount = board_readcount;
	}
	public int getBoard_ref() {
		return board_ref;
	}
	public void setBoard_ref(int board_ref) {
		this.board_ref = board_ref;
	}
	public int getBoard_ordNo() {
		return board_ordNo;
	}
	public void setBoard_ordNo(int board_ordNo) {
		this.board_ordNo = board_ordNo;
	}
	public int getBoard_levelNo() {
		return board_levelNo;
	}
	public void setBoard_levelNo(int board_levelNo) {
		this.board_levelNo = board_levelNo;
	}
	public String getBoard_ip() {
		return board_ip;
	}
	public void setBoard_ip(String board_ip) {
		this.board_ip = board_ip;
	}
	public int getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}
	
	


	
}//class-end
