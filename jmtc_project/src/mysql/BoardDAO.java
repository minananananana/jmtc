package mysql;
import java.sql.*;//Connection, Statement, PreparedStatement,ResultSet

import javax.sql.*;//DataSource
import javax.naming.*;//lookup

import mysql.BoardDTO;

import java.util.*;//List,ArrayList

//DAO:�����Ͻ� ����
public class BoardDAO {
	Connection con=null;//Ŭ���� ��ü
	PreparedStatement pstmt=null;
	Statement stmt=null;
	ResultSet rs=null;
	String sql="";

	//������ : �ܺ� ��ü���� ���Ѵ�
	private BoardDAO(){}

	//��ü 1���� �����Ѵ�, �޸� ���� ȿ���� �ִ�
	private static BoardDAO instance=new BoardDAO();//��ü���� , static�� �����ϳ��� ��밡��

	//static����,�޼���� ��ü���� ���� �ʰ� ����� �� �ִ�
	//Ŭ�����̸�.�޼���()
	public static BoardDAO getInstance(){//JSP���� ����� �޼���
		return instance;
	}
	//--------------------
	// Ŀ�ؼ� ���
	//--------------------
	private Connection getCon() throws Exception{
		Context ct=new InitialContext();
		DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}//getCon()-end

	//------------------------
	// �� ����, ��۾���
	//------------------------
	public void insertBoard(BoardDTO dto){
		//�۳��뺸��(content.jsp)�� ��� ���� �ҋ� ������ ������
		int board_idx=dto.getBoard_idx();
		int board_ref=dto.getBoard_ref();
		int board_ordNo=dto.getBoard_ordNo();
		int board_levelNo=dto.getBoard_levelNo();

		int number=0;//�� �׷�ó��

		try{
			con=getCon();//Ŀ�ؼ� ���
			pstmt=con.prepareStatement("select max(board_idx) from board");//�ִ� �� ��ȣ ���
			rs=pstmt.executeQuery();

			if(rs.next()){//���� �����ϸ� 
				number=rs.getInt(1)+1;//1�� �ʵ� ��ȣ// �ִ� �۹�ȣ+1 ref=number // ���� �߰���Ų�ٴ� ��
				//rs.getString("writer");//writer �ʵ��̸�
			}else{//ó�� ���϶�
				number=1;// ref=number
			}//else-end

			if(board_idx != 0){//����̸� --->0�� �ƴϴϱ� ���� �ִٴ¶�
				//��� ���� �ֱ� ��ġ Ȯ��
				sql="update board set board_ordNo=board_ordNo+1 where board_ref=? and board_ordNo>?";//+1�� �Ͽ� ������� Ȯ���ϰ� ����� �� �ڸ��� �����
				pstmt=con.prepareStatement(sql);//������ ���� ����

				pstmt.setInt(1, board_ref);
				pstmt.setInt(2, board_ordNo);
				pstmt.executeUpdate();

				board_ordNo=board_ordNo+1;
				board_levelNo=board_levelNo+1;

			}else{//����
				board_ref=number;
				board_ordNo=0;
				board_levelNo=0;		
			}//else-end

			//insert
			sql="insert into board(board_name,board_title,board_content,board_pw,board_date,board_ref,board_ordNo,board_levelNo,board_ip,member_idx)";
			sql=sql+" values(?,?,?,?,NOW(),?,?,?,?,?)";

			pstmt=con.prepareStatement(sql);//������ ���� ����

			//?�� ä���
			pstmt.setString(1, dto.getBoard_name());
			pstmt.setString(2, dto.getBoard_title());
			pstmt.setString(3, dto.getBoard_content());
			pstmt.setString(4, dto.getBoard_pw());
			//NOW()
			pstmt.setInt(5, board_ref);
			pstmt.setInt(6, board_ordNo);
			pstmt.setInt(7, board_levelNo);

			pstmt.setString(8, dto.getBoard_ip());
			pstmt.setInt(9, dto.getMember_idx());
			//for(int i=0; i<100; i++){
			pstmt.executeUpdate();//���� ����
			//}
		}catch(Exception ex){
			System.out.println("insertBoard()����:"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally()-end
	}//insertBoard()-end
	//----------------------
	// �� ����
	//----------------------
	public int getCount(){
		int cnt=0;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select count(*) from board");
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

		List<BoardDTO> list=null;
		try{
			con=getCon();//Ŀ�ؼ� ���
			
			if(keyWord.equals(null) || keyWord.equals("") || keyWord.length()<1){
				//��ü ����Ʈ
				sql="select * from board order by board_ref desc, board_ordNo asc limit ?,?"; //desc�������� asc �������� 

			}else{
				//�˻� ����Ʈ
				sql="select * from board where "+keyField+" like '%"+keyWord+"%' order by board_ordNo asc limit ?,?";
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
				list=new ArrayList<BoardDTO>();
				do{
					BoardDTO dto=new BoardDTO();

					dto.setBoard_idx(rs.getInt("board_idx"));//num

					dto.setBoard_name(rs.getString("board_name"));//write
					dto.setBoard_title(rs.getString("board_title"));
					dto.setBoard_content(rs.getString("board_content"));
					dto.setBoard_pw(rs.getString("board_pw"));

					dto.setBoard_date(rs.getTimestamp("board_date"));//����� �ú���

					//System.out.println(rs.getTimestamp("regdate"));
					//System.out.println(rs.getDate("regdate"));
					//System.out.println(rs.getString("regdate"));
					//2022-12-29 12:55:49.0 -->getTimestamp
					//2022-12-29  -->getDate
					//2022-12-29 12:55:49.0  -->getString

					//NOW():����� �ú���
					//curdate() : �����
					// sysdate  : ����Ŭ--->����Ŭ�� ()�ȵ�

					dto.setBoard_readcount(rs.getInt("board_readcount"));
					dto.setBoard_ref(rs.getInt("board_ref"));
					dto.setBoard_ordNo(rs.getInt("board_ordNo"));
					dto.setBoard_levelNo(rs.getInt("board_levelNo"));

					dto.setBoard_ip(rs.getString("board_ip"));

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

	//------------------
	// ��Ƚ�� ����, �۳��� ����
	//------------------
	public BoardDTO getBoard(int board_idx){
		BoardDTO dto=null;
		try{
			con=getCon();//Ŀ�ؼ� ���

			//��Ƚ�� ����
			sql="update board set board_readcount=board_readcount+1 where board_idx="+board_idx;
			pstmt=con.prepareStatement(sql);
			pstmt.executeUpdate();
			//��Ƚ�� ���� ��

			//�� ���뺸��
			pstmt=con.prepareStatement("select * from board where board_idx="+board_idx);
			rs=pstmt.executeQuery();

			//rs������ dto��´�. return dto
			if(rs.next()){
				dto=new BoardDTO();

				dto.setBoard_idx(rs.getInt("board_idx"));//num
				dto.setBoard_name(rs.getString("board_name"));//write
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_content(rs.getString("board_content"));
				dto.setBoard_pw(rs.getString("board_pw"));

				dto.setBoard_date(rs.getTimestamp("board_date"));
				dto.setBoard_readcount(rs.getInt("board_readcount"));

				dto.setBoard_ref(rs.getInt("board_ref"));
				dto.setBoard_ordNo(rs.getInt("board_ordNo"));
				dto.setBoard_levelNo(rs.getInt("board_levelNo"));

				dto.setBoard_ip(rs.getString("board_ip"));
				dto.setMember_idx(rs.getInt("member_idx"));
			}

		}catch(Exception ex){
			System.out.println("getBoard() ����:"+ex);
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
	public BoardDTO getUpdate(int board_idx){
		BoardDTO dto=null;

		try{
			con=getCon();
			pstmt=con.prepareStatement("select * from board where board_idx="+board_idx);//���� ����

			rs=pstmt.executeQuery();

			if(rs.next()){
				//rs������ dto�� ��´�, return dto
				dto=new BoardDTO();
				dto.setBoard_idx(rs.getInt("board_idx"));//num
				dto.setBoard_name(rs.getString("board_name"));//write
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_content(rs.getString("board_content"));
				dto.setBoard_pw(rs.getString("board_pw"));

				dto.setBoard_date(rs.getTimestamp("board_date"));
				dto.setBoard_readcount(rs.getInt("board_readcount"));

				dto.setBoard_ref(rs.getInt("board_ref"));
				dto.setBoard_ordNo(rs.getInt("board_ordNo"));
				dto.setBoard_levelNo(rs.getInt("board_levelNo"));

				dto.setBoard_ip(rs.getString("board_ip"));
				dto.setMember_idx(rs.getInt("member_idx"));

			}
		}catch(Exception ex){
			System.out.println("getUpdate() ����:"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		return dto;
	}//getUpdate()-end
	//-----------------
	// DB�� ����
	//-----------------
	public int updateBoard(BoardDTO dto){
		int x=-100;
		String dbpw="";
		try{
			con=getCon();
			pstmt=con.prepareStatement("select board_pw from board where board_idx=?");
			pstmt.setInt(1, dto.getBoard_idx());
			rs=pstmt.executeQuery();

			if(rs.next()){
				dbpw=rs.getString("board_pw");
				if(dto.getBoard_pw().equals(dbpw)){//��ȣ�� ��ġ �ϸ� �� ����
					sql="update board set board_name=?,board_title=?,board_content=? where board_idx=?";
					pstmt=con.prepareStatement(sql);//���� ����

					pstmt.setString(1, dto.getBoard_name());
					pstmt.setString(2, dto.getBoard_title());
					pstmt.setString(3,dto.getBoard_content());
					pstmt.setInt(4, dto.getBoard_idx());

					pstmt.executeUpdate();

					x=1;
				}else{//��ȣ�� Ʋ����
					x=-1;
				}
			}//if-end
		}catch(Exception ex){
			System.out.println("updateBoard() ����:"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}	
		}//finally-end
		return x;
	}//updateBoard()-end
	//-------------------------------
	// �� ����
	//-------------------------------
	public int deleteArticle(int board_idx,String board_pw){
		//System.out.println("board_idx:"+board_idx);
		//System.out.println("board_pw:"+board_pw);
		String dbpw="";
		int x=-100;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select board_pw from board where board_idx="+board_idx);//������ ���� ��
			rs=pstmt.executeQuery();//��������


			if(rs.next()){
				dbpw=rs.getString("board_pw");
				if(board_pw.equals(dbpw)){
					//��ȣ�� ��ġ�ϸ� �ۻ���
					pstmt=con.prepareStatement("delete from board where board_idx="+board_idx);
					pstmt.executeUpdate();//��������

					x=1;//��������
				}else{//��ȣ�� Ʋ����
					x=-1;
				}
			}//if-end


		}catch(Exception ex){
			System.out.println("deleteArticle() ����:"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		return x;
	}//deleterArticle()-end


}//class-end
