package member;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.*;//DataSource
import javax.naming.*;//lookup

import mysql.BoardDTO;

//DAO : �����Ͻ� ���� ó�� 
public class MemberDAO {

	//�̱��� ��ü ��� : �޸� ���� ȿ�� 
	private static MemberDAO instance=new MemberDAO();//��ü����

	//������ : �ܺ� ���� ��ü ���� ���ϰ� �Ѵ� 
	private MemberDAO(){}

	//JSP���� ����� �޼��� 
	public static MemberDAO getInstance(){
		return instance;
	}

	//---------------------
	// Ŀ�ؼ� Ǯ ��� 
	//---------------------

	private Connection getCon() throws Exception{
		Context ct=new InitialContext();
		DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}

	//��������
	Connection con=null;
	Statement stmt=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	String sql="";

	//-----------------
	// id �ߺ� üũ
	//-----------------
	public int confirmID(String member_id){
		//System.out.println("id:"+id);

		int x=-100;

		try{
			con=getCon();//Ŀ�ؼ� ���
			pstmt=con.prepareStatement("select member_id from member where member_id=?");
			pstmt.setString(1, member_id);

			rs=pstmt.executeQuery();//select

			if(rs.next()){
				x=1;//�̹� ������� id
			}else{
				x=-1;//��� ������ id
			}
		}catch(Exception ex){
			System.out.println("confirmID() ���� :"+ex);
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
	// ȸ�� ���� insert
	//-----------------
	public void insertMember(MemberDTO dto){
		try{
			con=getCon();//Ŀ�ؼ� ���
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
			System.out.println("insertMember() ���� :"+ex);
		}finally{//���� �߻��� ������� ������ ó�� �Ѵ� . �տ� return���� �־ finally���� ó���ȴ� 
			try{
				//if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
	}//insertMember()-end

	//--------------
	//�α���,����
	//--------------
	public MemberDTO userCheck(String member_id, String member_pw){

		MemberDTO dto = null;
		//String result = "fail";
		String dbpw="";

		try{
			con=getCon();//Ŀ�ؼ� ���
			pstmt=con.prepareStatement("select member_pw, member_id, member_idx from member where member_id=?");
			pstmt.setString(1, member_id);

			rs = pstmt.executeQuery();

			if(rs.next()){
				dto = new MemberDTO();
				dbpw=rs.getString("member_pw");

				if(member_pw.equals(dbpw)){//��ȣ�� ��ġ �ϸ� 
					dto.setMember_idx(rs.getInt("member_idx"));

					//result = "success";
				}else {
					//result = "fail";
				}

			}else{//���� id

			}//else-end

		}catch(Exception ex){
			System.out.println("userCheck() ���� :"+ex);

		}finally{//���� �߻��� ������� ������ ó�� �Ѵ� . �տ� return���� �־ finally���� ó���ȴ� 
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end

		return dto;
	}//userCheck()-end

	//------------
	//��ȣ üũ
	//------------
	public int pwCheck(String member_id,String member_pw){
		int x=-100;
		try{
			con=getCon();//Ŀ�ؼ� ���
			pstmt=con.prepareStatement("select * from member where member_id=? and member_pw=?");
			pstmt.setString(1, member_id);
			pstmt.setString(2, member_pw);
			rs=pstmt.executeQuery();//�� ���� 

			if(rs.next()){
				x=1;//��ȣ Ȯ�� �� ��
			}else{
				x=-1;//���� id�� ��ȣ 
			}
		}catch(Exception ex){
			System.out.println("pwCheck() ���� :"+ex);
		}finally{//���� �߻��� ������� ������ ó�� �Ѵ� . �տ� return���� �־ finally���� ó���ȴ� 
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end

		return x;
	}//pwCheck()-end

	//---------------------------
	// ������ ����  front-end������
	//---------------------------
	public MemberDTO getMember(int member_idx){
		MemberDTO dto=null;
		try{
			con=getCon();//Ŀ�ؼ� ��� 
			pstmt=con.prepareStatement("select * from member where member_idx="+member_idx);
			rs=pstmt.executeQuery();

			if(rs.next()){
				//rs������ dto�� �ִ´�
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
			System.out.println("getMember() ���� :"+ex);

		}finally{//���� �߻��� ������� ������ ó�� �Ѵ� . �տ� return���� �־ finally���� ó���ȴ� 
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end

		return dto;
	}//getMember()-end

	//---------------------------
	// DB������ ���� 
	//----------------------------
	public String updateMember(MemberDTO dto){

		String result = "fail";

		try{
			con=getCon();
			sql="update member set member_pw=?, member_name=?, member_nick=?, member_email=?, member_tel=?,member_zipcode=?, member_addr=?, member_addr_detail=? where member_id=?";

			pstmt=con.prepareStatement(sql);//������ ���� ���� 

			pstmt.setString(1, dto.getMember_pw());
			pstmt.setString(2, dto.getMember_name());
			pstmt.setString(3, dto.getMember_nick());

			pstmt.setString(4, dto.getMember_email());
			pstmt.setString(5, dto.getMember_tel());
			pstmt.setString(6, dto.getMember_zipcode());
			pstmt.setString(7, dto.getMember_addr());
			pstmt.setString(8, dto.getMember_addr_detail());
			pstmt.setString(9, dto.getMember_id());

			pstmt.executeUpdate();//��������

			result = "success";

		}catch(Exception ex){
			System.out.println("updateMember() ���� :"+ex);
		}finally{//���� �߻��� ������� ������ ó�� �Ѵ� . �տ� return���� �־ finally���� ó���ȴ� 
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end

		return result;

	}//updateMember()-end

	//---------------
	// DB�� ����
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
				if(member_pw.equals(dbpw)){//��ȣ�� ��ġ �ϸ� ���� 
					pstmt=con.prepareStatement("delete from member where member_id=?");
					pstmt.setString(1, member_id);
					pstmt.executeUpdate();//���� ���� 
					x=1;//����
				}else{
					x=-1;//��ȣ Ʋ��
				}//else-end
			}//if-end


		}catch(Exception ex){
			System.out.println("deleteMember() ���� :"+ex);
		}finally{//���� �߻��� ������� ������ ó�� �Ѵ� . �տ� return���� �־ finally���� ó���ȴ� 
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		return x;
	}//deleteMember()-end
	//----------------------
	// �� ����
	//----------------------
	public int getCount(){
		int cnt=0;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select count(*) from member");
			rs=pstmt.executeQuery();

			if(rs.next()){
				cnt=rs.getInt(1);//�ʵ� ��ȣ

			}
		}catch(Exception ex){
			System.out.println("getCount() ����:"+ex);
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
	// ����Ʈ
	//-------------
	public List getList(int start,int cnt,int member_idx,String keyField, String keyWord){//getList=�޼��� / start, cnt�� �Ű����� / List�� String,int ���� Ÿ���� ����

		//System.out.println("keyField:"+keyField);
		//System.out.println("keyWord:"+keyWord);

		List<MemberDTO> list=null;
		try{
			con=getCon();//Ŀ�ؼ� ���

			if(keyWord.equals(null) || keyWord.equals("") || keyWord.length()<1){
				//��ü ����Ʈ
				sql="select * from member limit ?,?"; //desc�������� asc �������� 

			}else{
				//�˻� ����Ʈ
				sql="select * from member where "+keyField+" like '%"+keyWord+"%' limit ?,?";
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
}//class-end
