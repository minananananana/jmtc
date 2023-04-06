package notice;
import javax.sql.*;

import java.sql.*;

import javax.naming.*;

import java.util.*;

public class NoticeDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
	public NoticeDAO(){}
	
	public Connection getCon() throws Exception {
		
		Context ct = new InitialContext();
		DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
				
		return ds.getConnection();
		
	}//getCon()-end
	
	//insert
	public String insertNotice(NoticeDTO dto){
		
		String result = "fail";
		
		try{
			
			conn = getCon();
			sql = "insert into notice(notice_title, notice_content, notice_readcount, notice_date, notice_impo_YN, notice_ref, notice_ordNo, notice_levelNo, member_idx)";
			sql = sql + " value(?,?,0,NOW(),?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getNotice_title());
			pstmt.setString(2, dto.getNotice_content());
			pstmt.setString(3, dto.getNotice_impo_YN());
			pstmt.setInt(4, dto.getNotice_ref());
			pstmt.setInt(5, dto.getNotice_ordNo());
			pstmt.setInt(6, dto.getNotice_levelNo());
			pstmt.setInt(7, dto.getMember_idx());
			
			pstmt.executeUpdate();
			
			result = "success";
			
		}catch(Exception ex){
			System.out.println("insertNotice 예외"+ex);
		}finally{
			try{
				if(rs != null){rs.close();}
				if(pstmt != null){pstmt.close();}
				if(conn != null){conn.close();}
			}catch(Exception ex){}
		}
		
		return result;
		
	}//insertNotice()-end
	
	
	//select-기본글
	public List getNotice(int start, int cnt, String keyField, String keyWord){
		
		List<NoticeDTO> list = null;
		
		try{
			
			conn = getCon();
			
			if(keyWord.equals(null) || keyWord.equals("") || keyWord.length()<1){
				sql = "select n.notice_idx, n.notice_title, n.notice_date, n.notice_readcount, n.member_idx, m.member_nick";
				sql = sql + " from notice n LEFT JOIN member m ON n.member_idx=m.member_idx where n.notice_impo_YN='N' order by notice_idx desc limit ?,?";
			}else{
				//검색 리스트
				sql = "select n.notice_idx, n.notice_title, n.notice_date, n.notice_readcount, n.member_idx, m.member_nick";
				sql = sql + " from notice n LEFT JOIN member m ON n.member_idx=m.member_idx where n.notice_impo_YN='N' AND "+keyField+" like '%"+keyWord+"%' order by notice_idx desc limit ?,?";
			}
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, cnt);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<NoticeDTO>();
			
			while(rs.next()){
				
				NoticeDTO dto = new NoticeDTO();
				
				dto.setNotice_idx(rs.getInt("notice_idx"));
				dto.setNotice_title(rs.getString("notice_title"));
				dto.setNotice_date(rs.getDate("notice_date"));
				dto.setNotice_readcount(rs.getInt("notice_readcount"));
				dto.setMember_idx(rs.getInt("member_idx"));
				dto.setMember_nick(rs.getString("member_nick"));
				
				list.add(dto);
				
			}//while-end

		}catch(Exception ex){
			System.out.println("getNotice 에러"+ex);
		}finally{
			try{
				if(rs != null){rs.close();}
				if(pstmt != null){pstmt.close();}
				if(conn != null){conn.close();}
			}catch(Exception ex){}
		}
		
		return list;
		
	}//getNotice()-end
	
	//select-중요글
	public List getImpoNotice(){
		
		List<NoticeDTO> list2 = null;
		
		try{
			sql = "select n.notice_idx, n.notice_title, n.notice_date, n.notice_readcount, n.member_idx, m.member_nick";
			sql = sql + " from notice n LEFT JOIN member m ON n.member_idx=m.member_idx where n.notice_impo_YN='Y' order by notice_idx desc";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			list2 = new ArrayList<NoticeDTO>();
			
			while(rs.next()){
				
				NoticeDTO dto = new NoticeDTO();
				
				dto.setNotice_idx(rs.getInt("notice_idx"));
				dto.setNotice_title(rs.getString("notice_title"));
				dto.setNotice_date(rs.getDate("notice_date"));
				dto.setNotice_readcount(rs.getInt("notice_readcount"));
				dto.setMember_idx(rs.getInt("member_idx"));
				dto.setMember_nick(rs.getString("member_nick"));
				
				list2.add(dto);
				
			}//while-end
		}catch(Exception ex){
			System.out.println();
		}finally{
			try{
				if(rs != null){rs.close();}
				if(pstmt != null){pstmt.close();}
				if(conn != null){conn.close();}
			}catch(Exception ex){}
		}
		
		return list2;
	}
	
	//총게시글 구하기
	public int noticeCount(){
		
		int cnt = 0;
		
		try{
			
			conn = getCon();
			sql = "select count(*) from notice";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				cnt = rs.getInt(1);
			}
			
		}catch(Exception ex){
			System.out.println("noticeCount 에러"+ex);
		}
		
		return cnt;
		
	}//noticeCount()-end
	
	//readcount
	public void readCount(int idx){
		
		try{
			
			conn = getCon();
			sql = "update notice set notice_readcount=notice_readcount+1 where notice_idx="+idx;
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
		}catch(Exception ex){
			System.out.println("readCount 에러"+ex);
		}finally{
			try{
				if(rs != null){rs.close();}
				if(pstmt != null){pstmt.close();}
				if(conn != null){conn.close();}
			}catch(Exception ex){}
		}
		
	}//readCount();
	
	//content
	public NoticeDTO getContent(int idx){
		
		NoticeDTO dto = null;
		
		try{
			
			conn = getCon();
			sql = "select * from notice where notice_idx="+idx;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				dto = new NoticeDTO();
				
				dto.setNotice_idx(rs.getInt("notice_idx"));
				dto.setNotice_title(rs.getString("notice_title"));
				dto.setNotice_content(rs.getString("notice_content"));
				dto.setNotice_readcount(rs.getInt("notice_readcount"));
				dto.setNotice_date(rs.getDate("notice_date"));
			}
			
		}catch(Exception ex){
			System.out.println("getContent 에러"+ex);
		}finally{
			try{
				if(rs != null){rs.close();}
				if(pstmt != null){pstmt.close();}
				if(conn != null){conn.close();}
			}catch(Exception ex){}
		}
		
		return dto;
		
	}//getContent()-end
	
	//update
	public String updateNotice(NoticeDTO dto){
		
		String result = "fail";
		
		try{
			
			conn = getCon();
			sql = "update notice set notice_title=?,notice_content=? where notice_idx=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getNotice_title());
			pstmt.setString(2, dto.getNotice_content());
			pstmt.setInt(3, dto.getNotice_idx());
			
			pstmt.executeUpdate();
			
			result = "success";
			
		}catch(Exception ex){
			System.out.println("updateNotice 에러"+ex);
		}
		
		return result;
		
	}//updateNotice()-end
	
	
	//delete
	public String deleteNotice(int notice_idx){
		
		String result = "fail";
		
		try{
			
			conn = getCon();
			sql = "delete from notice where notice_idx="+notice_idx;
			pstmt = conn.prepareStatement(sql);
			
			pstmt.executeUpdate();
			
			result = "success";
			
		}catch(Exception ex){
			System.out.println("updateNotice 에러"+ex);
		}
		
		return result;
	}
	
}//class-end
