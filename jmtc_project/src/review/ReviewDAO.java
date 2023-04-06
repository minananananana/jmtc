package review;

import java.sql.*;//Connection, Statement, PreparedStatement

import javax.servlet.http.HttpServletRequest;
import javax.sql.*;//DataSource
import javax.naming.*;//lookup

import notice.NoticeDTO;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import review.ReviewDTO;
import review.ReviewDAO;
import shop.ProductDTO;

import java.util.*;//List,ArrayList

public class ReviewDAO {
	Connection con=null;
	PreparedStatement pstmt=null;
	Statement stmt=null;
	ResultSet rs=null;
	String sql="";

	//������
	private ReviewDAO(){}

	//��ü 1���� �Һ�, �޸� ���� ȿ��
	private static ReviewDAO instance=new ReviewDAO();

	public static ReviewDAO getInstance(){//JSP���� ����� �޼���.
		return  instance;
	}//getInstance()-end

	//----------------------
	// Ŀ�ؼ� ���
	//----------------------
	private Connection getCon() throws Exception{
		Context ct=new InitialContext();
		DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}//getCon()-end

	//----------------------
	//�۾���, ��۾��� 
	//----------------------
	public void insertReview(ReviewDTO dto){

		int review_idx=dto.getReview_idx();
		int review_ref=dto.getReview_ref();
		int review_ordNo=dto.getReview_ordNo();
		int review_levelNo=dto.getReview_levelNo();

		int number=0;//�� �׷� ó��
		try{
			con=getCon();//Ŀ�ؼ� ���
			pstmt=con.prepareStatement("select max(review_idx) from review");//�ִ� �۹�ȣ ���
			rs=pstmt.executeQuery();

			if(rs.next()){//���� �����ϸ�
				number=rs.getInt(1)+1;//1�� �ʵ� ��ȣ //�ִ� �۹�ȣ+1 ref=number
			}else{//ó�� ���϶�
				number=1;/// ref=number
			}//else-end

			if(review_idx != 0){//����̸� 
				//��� ���� �ֱ� ��ġ Ȯ��
				sql="update review set review_ordNo=review_ordNo+1 where review_ref=? and review_ordNo>?";//re_level�� ��,��۹�ȣ �׷�(����=0,��1=1,��2=2) //re_step�� ��,��� ����
				pstmt=con.prepareStatement(sql);

				pstmt.setInt(1, review_ref);
				pstmt.setInt(2, review_ordNo);
				pstmt.executeUpdate();

				review_ordNo=review_ordNo+1;//*****
				review_levelNo=review_levelNo+1;//*****

			}else{//����
				review_ref=number;
				review_ordNo=0;
				review_levelNo=0;
			}//else-end

			//insert

			sql="insert into review(review_id,review_title,review_content,review_score,review_date,review_ref,review_ordNo,review_levelNo,member_idx) ";
			sql=sql+" values(?,?,?,?,now(),?,?,?,?)";

			pstmt=con.prepareStatement(sql);

			pstmt.setString(1, dto.getReview_id());
			pstmt.setString(2, dto.getReview_title());
			pstmt.setString(3, dto.getReview_content());
			pstmt.setInt(4, dto.getReview_score());
			pstmt.setInt(5,review_ref);
			pstmt.setInt(6,review_ordNo);
			pstmt.setInt(7, review_levelNo);
			
			
			pstmt.setInt(8, dto.getMember_idx());

			pstmt.executeUpdate();

		}catch(Exception ex){
			System.out.println("insertReview()����:"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
	}//insertReview()-end
	//------------------------------------
	// �� ����
	//------------------------------------

	public int getCount(){
		int cnt=0;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select count(*) from review");
			rs=pstmt.executeQuery();

			if(rs.next()){
				cnt=rs.getInt(1);//�ʵ� ��ȣ
			}//if-end
		}catch(Exception ex){
			System.out.println("getCount()����:"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end

		return cnt;
	}//getCount()-end
	//--------------------
	// ����Ʈ
	//--------------------
	public List getList(int start, int cnt,int member_idx,String keyField, String keyWord){
		List<ReviewDTO> list=null;
		try{
			con=getCon();//Ŀ�ؼ� ���

			if(keyWord.equals(null) || keyWord.equals("") || keyWord.length()<1){
				//��ü ����Ʈ
				   sql = "select r.review_idx, r.review_id, r.review_title, r.review_content, r.review_score, r.review_date, r.review_readcount, r.review_ref, r.review_ordNo, r.review_levelNo,r.member_idx, m.member_nick";

		            sql = sql + " from review r LEFT JOIN member m ON r.member_idx=m.member_idx  order by review_ref desc, review_ordNo asc limit ?,?";

			}else{
				//�˻� ����Ʈ
				 sql = "select r.review_idx, r.review_id, r.review_title, r.review_content, r.review_score, r.review_date, r.review_readcount, r.review_ref, r.review_ordNo, r.review_levelNo, r.member_idx, m.member_nick";
				 sql = sql + " from review r LEFT JOIN member m ON r.member_idx=m.member_idx where "+keyField+" like '%"+keyWord+"%' order by review_ordNo asc limit ?,?";
		            
				//sql="select * from board where subject like '%����%' order by pos asc";
			}
			//limit ?,?
			//limit ������ġ, ����

			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, start-1);//mysql�� 0����--->�츮 ������ 1������ ���α׷� �󿡴� 0���� ����   
			pstmt.setInt(2, cnt);
			rs=pstmt.executeQuery();

			while(rs.next()){
				//rs������ dto�� ��´�, dto�� list�� ��´� return list
				list=new ArrayList<ReviewDTO>();
				do{
					ReviewDTO dto=new ReviewDTO();

					dto.setReview_idx(rs.getInt("review_idx"));//num

					dto.setReview_id(rs.getString("review_id"));//write
					dto.setReview_title(rs.getString("review_title"));
					dto.setReview_content(rs.getString("review_content"));
					dto.setReview_score(rs.getInt("review_score"));

					dto.setReview_date(rs.getTimestamp("review_date"));//����� �ú���
					

					dto.setReview_readcount(rs.getInt("review_readcount"));
					dto.setReview_ref(rs.getInt("review_ref"));
					dto.setReview_ordNo(rs.getInt("review_ordNo"));
					dto.setReview_levelNo(rs.getInt("review_levelNo"));
					list.add(dto);
				}while(rs.next());

			}//while-end

		}catch(Exception ex){
			System.out.println("getList() ����:"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		return list;//*****************
	}//getList()-end

	//----------------
	//list2
	//----------------
	
	public List getReview(){
		
		List<ReviewDTO> list2 = null;
		
		try{
			sql = "select review_idx, review_id, review_title, review_date,review_readcount,member_idx";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			list2 = new ArrayList<ReviewDTO>();
			
			while(rs.next()){
				
				ReviewDTO dto = new ReviewDTO();
				
				dto.setReview_idx(rs.getInt("review_idx"));
				dto.setReview_id(rs.getString("review_id"));
				dto.setReview_title(rs.getString("review_title"));
				dto.setReview_date(rs.getDate("review_date"));
				dto.setReview_readcount(rs.getInt("review_readcount"));
				dto.setMember_idx(rs.getInt("member_idx"));
				
				list2.add(dto);
				
			}//while-end
		}catch(Exception ex){
			System.out.println();
		}finally{
			try{
				if(rs != null){rs.close();}
				if(pstmt != null){pstmt.close();}
				if(con != null){con.close();}
			}catch(Exception ex){}
		}
		
		return list2;
	}
	
	//-------------------
	// ��ȸ�� ����, �۳��� ����
	//-------------------
	public ReviewDTO getReview(int review_idx){
		ReviewDTO dto=null;
		try{
			con=getCon();//Ŀ�ؼǾ��

			//��ȸ�� ����
			sql="update review set review_readcount=review_readcount+1 where review_idx="+review_idx;
			pstmt=con.prepareStatement(sql);
			pstmt.executeUpdate();

			//�۳��� ����
			pstmt=con.prepareStatement("select * from review where review_idx="+review_idx);
			rs=pstmt.executeQuery();

			//rs������ dto�� ��´� return dto
			if(rs.next()){
				dto=new ReviewDTO();

				dto.setReview_idx(rs.getInt("review_idx"));
				dto.setReview_id(rs.getString("review_id"));
				dto.setReview_title(rs.getString("review_title"));
				dto.setReview_content(rs.getString("review_content"));
				dto.setReview_score(rs.getInt("review_score"));
				dto.setReview_date(rs.getTimestamp("review_date"));
				dto.setReview_readcount(rs.getInt("review_readcount"));
				dto.setReview_ref(rs.getInt("review_ref"));
				dto.setReview_ordNo(rs.getInt("review_ordNo"));
				dto.setReview_levelNo(rs.getInt("review_levelNo"));
		     	dto.setMember_idx(rs.getInt("member_idx"));


			}//if-end

		}catch(Exception ex){
			System.out.println("getReview() ����:"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		return dto;
	}//getBoard()-end

	//----------------------
	// �� ���� ��
	//----------------------
	public ReviewDTO getUpdate(int review_idx){
		ReviewDTO dto=null;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select * from review where review_idx="+review_idx);
			rs=pstmt.executeQuery();

			if(rs.next()){
				//rs������ dto��´�, return dto
				dto=new ReviewDTO();

				dto.setReview_idx(rs.getInt("review_idx"));
				dto.setReview_id(rs.getString("review_id"));
				dto.setReview_title(rs.getString("review_title"));
				dto.setReview_content(rs.getString("review_content"));
				dto.setReview_score(rs.getInt("review_score"));
				dto.setReview_date(rs.getDate("review_date"));
				dto.setReview_readcount(rs.getInt("review_readcount"));
				dto.setReview_ref(rs.getInt("review_ref"));
				dto.setReview_ordNo(rs.getInt("review_ordNo"));
				dto.setReview_levelNo(rs.getInt("review_levelNo"));
				dto.setMember_idx(rs.getInt("member_idx"));


			}//if-end
		}catch(Exception ex){
			System.out.println("getUpdate()����:"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		return dto;
	}//getUpdate()-end
	//-----------------------
	// DB�� ����
	//-----------------------
	public int updateReview(ReviewDTO dto){
		int x=-100;

		try{
			con=getCon();
			pstmt=con.prepareStatement("select review_id from review where review_idx=?");
			pstmt.setInt(1, dto.getReview_idx());
			rs=pstmt.executeQuery();

			if(rs.next()){

				sql="update review set review_id=?,review_title=?,review_content=?,review_score=? where review_idx=?";
				pstmt=con.prepareStatement(sql);

				pstmt.setString(1, dto.getReview_id());
				pstmt.setString(2, dto.getReview_title());
				pstmt.setString(3, dto.getReview_content());
				pstmt.setInt(4, dto.getReview_score());
				pstmt.setInt(5, dto.getReview_idx());

				pstmt.executeUpdate();

				x=1;
			}else{//��ȣ�� Ʋ����
				x=-1;
			}//if-end
		}catch(Exception ex){
			System.out.println("updateReview()����:"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end

		return x;
	}//updateBoard()-end
	//----------------------
	// �ۻ���
	//----------------------
	public void deleteReview(int review_idx){
		System.out.println("review_idx:"+review_idx);

		try{
			con=getCon();
			pstmt=con.prepareStatement("delete from review where review_idx="+review_idx);
			pstmt.executeUpdate();


		}catch(Exception ex){
			System.out.println("deleteReview()����:"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end


	}//deleteArticle()-end


}//class-end