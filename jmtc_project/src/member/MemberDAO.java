package member;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.*;//DataSource
import javax.naming.*;//lookup

import mysql.BoardDTO;

//DAO : 비지니스 로직 처리 
public class MemberDAO {

	//싱글톤 객체 사용 : 메모리 절약 효과 
	private static MemberDAO instance=new MemberDAO();//객체생성

	//생성자 : 외부 에서 객체 생성 못하게 한다 
	private MemberDAO(){}

	//JSP에서 사용할 메서드 
	public static MemberDAO getInstance(){
		return instance;
	}

	//---------------------
	// 커넥션 풀 사용 
	//---------------------

	private Connection getCon() throws Exception{
		Context ct=new InitialContext();
		DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}

	//전역변수
	Connection con=null;
	Statement stmt=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	String sql="";

	//-----------------
	// id 중복 체크
	//-----------------
	public int confirmID(String member_id){
		//System.out.println("id:"+id);

		int x=-100;

		try{
			con=getCon();//커넥션 얻기
			pstmt=con.prepareStatement("select member_id from member where member_id=?");
			pstmt.setString(1, member_id);

			rs=pstmt.executeQuery();//select

			if(rs.next()){
				x=1;//이미 사용중인 id
			}else{
				x=-1;//사용 가능한 id
			}
		}catch(Exception ex){
			System.out.println("confirmID() 예외 :"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end

		return x;
	}//confirmID()-end
	//-----------------
	// 회원 가입 insert
	//-----------------
	public void insertMember(MemberDTO dto){
		try{
			con=getCon();//커넥션 얻기
			pstmt=con.prepareStatement("insert into member values(0,?,?,?,?,?,?,?,?,?)");

			pstmt.setString(1, dto.getMember_id());
			pstmt.setString(2, dto.getMember_pw());
			pstmt.setString(3, dto.getMember_name());
			pstmt.setString(4, dto.getMember_nick());
			pstmt.setString(5, dto.getMember_email());
			pstmt.setString(6, dto.getMember_tel());
			pstmt.setString(7, dto.getMember_zipcode());
			pstmt.setString(8, dto.getMember_addr());
			pstmt.setString(9, dto.getMember_addr_detail());

			pstmt.executeUpdate();//insert,update,delete    rs=pstmt.executeQuery()=>select

		}catch(Exception ex){
			System.out.println("insertMember() 예외 :"+ex);
		}finally{//예외 발생과 상관없이 무조건 처리 한다 . 앞에 return문이 있어도 finally절은 처리된다 
			try{
				//if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
	}//insertMember()-end

	//--------------
	//로그인,인증
	//--------------
	public MemberDTO userCheck(String member_id, String member_pw){

		MemberDTO dto = null;
		//String result = "fail";
		String dbpw="";

		try{
			con=getCon();//커넥션 얻기
			pstmt=con.prepareStatement("select member_pw, member_id, member_idx from member where member_id=?");
			pstmt.setString(1, member_id);

			rs = pstmt.executeQuery();

			if(rs.next()){
				dto = new MemberDTO();
				dbpw=rs.getString("member_pw");

				if(member_pw.equals(dbpw)){//암호가 일치 하면 
					dto.setMember_idx(rs.getInt("member_idx"));

					//result = "success";
				}else {
					//result = "fail";
				}

			}else{//없는 id

			}//else-end

		}catch(Exception ex){
			System.out.println("userCheck() 예외 :"+ex);

		}finally{//예외 발생과 상관없이 무조건 처리 한다 . 앞에 return문이 있어도 finally절은 처리된다 
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end

		return dto;
	}//userCheck()-end

	//------------
	//암호 체크
	//------------
	public int pwCheck(String member_id,String member_pw){
		int x=-100;
		try{
			con=getCon();//커넥션 얻기
			pstmt=con.prepareStatement("select * from member where member_id=? and member_pw=?");
			pstmt.setString(1, member_id);
			pstmt.setString(2, member_pw);
			rs=pstmt.executeQuery();//쿠리 실행 

			if(rs.next()){
				x=1;//암호 확인 한 것
			}else{
				x=-1;//없는 id와 암호 
			}
		}catch(Exception ex){
			System.out.println("pwCheck() 예외 :"+ex);
		}finally{//예외 발생과 상관없이 무조건 처리 한다 . 앞에 return문이 있어도 finally절은 처리된다 
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end

		return x;
	}//pwCheck()-end

	//---------------------------
	// 내정보 수정  front-end보낼것
	//---------------------------
	public MemberDTO getMember(int member_idx){
		MemberDTO dto=null;
		try{
			con=getCon();//커넥션 얻기 
			pstmt=con.prepareStatement("select * from member where member_idx="+member_idx);
			rs=pstmt.executeQuery();

			if(rs.next()){
				//rs내용을 dto에 넣는다
				dto=new MemberDTO();

				dto.setMember_idx(rs.getInt("member_idx"));
				dto.setMember_id(rs.getString("member_id"));
				dto.setMember_pw(rs.getString("member_pw"));
				dto.setMember_name(rs.getString("member_name"));
				dto.setMember_nick(rs.getString("member_nick"));
				dto.setMember_email(rs.getString("member_email"));
				dto.setMember_tel(rs.getString("member_tel"));
				dto.setMember_zipcode(rs.getString("member_zipcode"));
				dto.setMember_addr(rs.getString("member_addr"));
				dto.setMember_addr_detail(rs.getString("member_addr_detail"));

				//System.out.print("dto memId=" + dto.getMember_id());
			}//if-end

		}catch(Exception ex){
			System.out.println("getMember() 예외 :"+ex);

		}finally{//예외 발생과 상관없이 무조건 처리 한다 . 앞에 return문이 있어도 finally절은 처리된다 
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end

		return dto;
	}//getMember()-end

	//---------------------------
	// DB내정보 수정 
	//----------------------------
	public String updateMember(MemberDTO dto){

		String result = "fail";

		try{
			con=getCon();
			sql="update member set member_pw=?, member_name=?, member_nick=?, member_email=?, member_tel=?,member_zipcode=?, member_addr=?, member_addr_detail=? where member_id=?";

			pstmt=con.prepareStatement(sql);//생성시 인자 들어간다 

			pstmt.setString(1, dto.getMember_pw());
			pstmt.setString(2, dto.getMember_name());
			pstmt.setString(3, dto.getMember_nick());

			pstmt.setString(4, dto.getMember_email());
			pstmt.setString(5, dto.getMember_tel());
			pstmt.setString(6, dto.getMember_zipcode());
			pstmt.setString(7, dto.getMember_addr());
			pstmt.setString(8, dto.getMember_addr_detail());
			pstmt.setString(9, dto.getMember_id());

			pstmt.executeUpdate();//쿼리실행

			result = "success";

		}catch(Exception ex){
			System.out.println("updateMember() 예외 :"+ex);
		}finally{//예외 발생과 상관없이 무조건 처리 한다 . 앞에 return문이 있어도 finally절은 처리된다 
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end

		return result;

	}//updateMember()-end

	//---------------
	// DB글 삭제
	//---------------
	public int deleteMember(String member_id,String member_pw){
		int x=-100;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select member_pw from member where member_id='"+member_id+"'");

			// select pw from member where id='park'

			//pstmt.setString(1, id);
			rs=pstmt.executeQuery();

			if(rs.next()){
				String dbpw=rs.getString("member_pw");
				if(member_pw.equals(dbpw)){//암호가 일치 하면 삭제 
					pstmt=con.prepareStatement("delete from member where member_id=?");
					pstmt.setString(1, member_id);
					pstmt.executeUpdate();//쿼리 실행 
					x=1;//삭제
				}else{
					x=-1;//암호 틀림
				}//else-end
			}//if-end


		}catch(Exception ex){
			System.out.println("deleteMember() 예외 :"+ex);
		}finally{//예외 발생과 상관없이 무조건 처리 한다 . 앞에 return문이 있어도 finally절은 처리된다 
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		return x;
	}//deleteMember()-end
	//----------------------
	// 글 갯수
	//----------------------
	public int getCount(){
		int cnt=0;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select count(*) from member");
			rs=pstmt.executeQuery();

			if(rs.next()){
				cnt=rs.getInt(1);//필드 번호

			}
		}catch(Exception ex){
			System.out.println("getCount() 예외:"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally()-end

		return cnt;
	}//getCount()-end
	//-------------
	// 리스트
	//-------------
	public List getList(int start,int cnt,int member_idx,String keyField, String keyWord){//getList=메서드 / start, cnt는 매개변수 / List는 String,int 같은 타입형 변수

		//System.out.println("keyField:"+keyField);
		//System.out.println("keyWord:"+keyWord);

		List<MemberDTO> list=null;
		try{
			con=getCon();//커넥션 얻기

			if(keyWord.equals(null) || keyWord.equals("") || keyWord.length()<1){
				//전체 리스트
				sql="select * from member limit ?,?"; //desc내림차순 asc 오름차순 

			}else{
				//검색 리스트
				sql="select * from member where "+keyField+" like '%"+keyWord+"%' limit ?,?";
				//sql="select * from board where subject like '%문수%' order by pos asc";
			}
			//limit ?,?
			//limit 시작위치, 갯수

			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, start-1);//mysql은 0부터--->우리 눈에는 1이지만 프로그램 상에는 0부터 시작   
			pstmt.setInt(2, cnt);
			rs=pstmt.executeQuery();

			while(rs.next()){
				//rs내용을 dto에 담는다, dto를 list에 담는다 return list
				list=new ArrayList<MemberDTO>();
				do{
					MemberDTO dto=new MemberDTO();

					dto.setMember_idx(rs.getInt("member_idx"));//num

					dto.setMember_id(rs.getString("member_id"));//write
					dto.setMember_pw(rs.getString("member_pw"));
					dto.setMember_name(rs.getString("member_name"));
					dto.setMember_nick(rs.getString("member_nick"));

					dto.setMember_email(rs.getString("member_email"));
					dto.setMember_tel(rs.getString("member_tel"));
					dto.setMember_zipcode(rs.getString("member_zipcode"));
					dto.setMember_addr(rs.getString("member_addr"));					
					dto.setMember_addr_detail(rs.getString("member_addr_detail"));

					list.add(dto);
				}while(rs.next());

			}//while-end

		}catch(Exception ex){
			System.out.println("getList() 예외:"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		return list;//*****************
	}//getList()-end
}//class-end
