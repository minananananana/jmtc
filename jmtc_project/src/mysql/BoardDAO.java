package mysql;
import java.sql.*;//Connection, Statement, PreparedStatement,ResultSet

import javax.sql.*;//DataSource
import javax.naming.*;//lookup

import mysql.BoardDTO;

import java.util.*;//List,ArrayList

//DAO:비지니스 로직
public class BoardDAO {
	Connection con=null;//클래스 객체
	PreparedStatement pstmt=null;
	Statement stmt=null;
	ResultSet rs=null;
	String sql="";

	//생성자 : 외부 객체생성 못한다
	private BoardDAO(){}

	//객체 1개로 서비한다, 메모리 절약 효과가 있다
	private static BoardDAO instance=new BoardDAO();//객체생성 , static은 변수하나만 사용가능

	//static변수,메서드는 객체생성 하지 않고도 사용할 수 있다
	//클래스이름.메서드()
	public static BoardDAO getInstance(){//JSP에서 사용할 메서드
		return instance;
	}
	//--------------------
	// 커넥션 얻기
	//--------------------
	private Connection getCon() throws Exception{
		Context ct=new InitialContext();
		DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}//getCon()-end

	//------------------------
	// 글 쓰기, 답글쓰기
	//------------------------
	public void insertBoard(BoardDTO dto){
		//글내용보기(content.jsp)에 답글 쓰기 할떄 보내준 데이터
		int board_idx=dto.getBoard_idx();
		int board_ref=dto.getBoard_ref();
		int board_ordNo=dto.getBoard_ordNo();
		int board_levelNo=dto.getBoard_levelNo();

		int number=0;//글 그룹처리

		try{
			con=getCon();//커넥션 얻기
			pstmt=con.prepareStatement("select max(board_idx) from board");//최대 글 번호 얻기
			rs=pstmt.executeQuery();

			if(rs.next()){//글이 존재하면 
				number=rs.getInt(1)+1;//1은 필드 번호// 최대 글번호+1 ref=number // 글을 추가시킨다는 뜻
				//rs.getString("writer");//writer 필드이름
			}else{//처음 글일때
				number=1;// ref=number
			}//else-end

			if(board_idx != 0){//답글이면 --->0이 아니니까 글이 있다는뜻
				//답글 끼워 넣기 위치 확보
				sql="update board set board_ordNo=board_ordNo+1 where board_ref=? and board_ordNo>?";//+1을 하여 빈공간을 확보하고 답글이 들어갈 자리를 만든다
				pstmt=con.prepareStatement(sql);//생성시 인자 들어간다

				pstmt.setInt(1, board_ref);
				pstmt.setInt(2, board_ordNo);
				pstmt.executeUpdate();

				board_ordNo=board_ordNo+1;
				board_levelNo=board_levelNo+1;

			}else{//원글
				board_ref=number;
				board_ordNo=0;
				board_levelNo=0;		
			}//else-end

			//insert
			sql="insert into board(board_name,board_title,board_content,board_pw,board_date,board_ref,board_ordNo,board_levelNo,board_ip,member_idx)";
			sql=sql+" values(?,?,?,?,NOW(),?,?,?,?,?)";

			pstmt=con.prepareStatement(sql);//생성시 인자 들어간다

			//?값 채우기
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
			pstmt.executeUpdate();//쿼리 실행
			//}
		}catch(Exception ex){
			System.out.println("insertBoard()예외:"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally()-end
	}//insertBoard()-end
	//----------------------
	// 글 갯수
	//----------------------
	public int getCount(){
		int cnt=0;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select count(*) from board");
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

		List<BoardDTO> list=null;
		try{
			con=getCon();//커넥션 얻기
			
			if(keyWord.equals(null) || keyWord.equals("") || keyWord.length()<1){
				//전체 리스트
				sql="select * from board order by board_ref desc, board_ordNo asc limit ?,?"; //desc내림차순 asc 오름차순 

			}else{
				//검색 리스트
				sql="select * from board where "+keyField+" like '%"+keyWord+"%' order by board_ordNo asc limit ?,?";
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
				list=new ArrayList<BoardDTO>();
				do{
					BoardDTO dto=new BoardDTO();

					dto.setBoard_idx(rs.getInt("board_idx"));//num

					dto.setBoard_name(rs.getString("board_name"));//write
					dto.setBoard_title(rs.getString("board_title"));
					dto.setBoard_content(rs.getString("board_content"));
					dto.setBoard_pw(rs.getString("board_pw"));

					dto.setBoard_date(rs.getTimestamp("board_date"));//년월일 시분초

					//System.out.println(rs.getTimestamp("regdate"));
					//System.out.println(rs.getDate("regdate"));
					//System.out.println(rs.getString("regdate"));
					//2022-12-29 12:55:49.0 -->getTimestamp
					//2022-12-29  -->getDate
					//2022-12-29 12:55:49.0  -->getString

					//NOW():년월일 시분초
					//curdate() : 년월일
					// sysdate  : 오라클--->오라클은 ()안들어감

					dto.setBoard_readcount(rs.getInt("board_readcount"));
					dto.setBoard_ref(rs.getInt("board_ref"));
					dto.setBoard_ordNo(rs.getInt("board_ordNo"));
					dto.setBoard_levelNo(rs.getInt("board_levelNo"));

					dto.setBoard_ip(rs.getString("board_ip"));

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

	//------------------
	// 조횟수 증가, 글내용 보기
	//------------------
	public BoardDTO getBoard(int board_idx){
		BoardDTO dto=null;
		try{
			con=getCon();//커넥션 얻기

			//조횟수 증가
			sql="update board set board_readcount=board_readcount+1 where board_idx="+board_idx;
			pstmt=con.prepareStatement(sql);
			pstmt.executeUpdate();
			//조횟수 증가 끝

			//글 내용보기
			pstmt=con.prepareStatement("select * from board where board_idx="+board_idx);
			rs=pstmt.executeQuery();

			//rs내용을 dto담는다. return dto
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
			System.out.println("getBoard() 예외:"+ex);
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
	// 글 수정 폼
	//----------------------
	public BoardDTO getUpdate(int board_idx){
		BoardDTO dto=null;

		try{
			con=getCon();
			pstmt=con.prepareStatement("select * from board where board_idx="+board_idx);//인자 생성

			rs=pstmt.executeQuery();

			if(rs.next()){
				//rs내용을 dto에 담는다, return dto
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
			System.out.println("getUpdate() 예외:"+ex);
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
	// DB글 수정
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
				if(dto.getBoard_pw().equals(dbpw)){//암호가 일치 하면 글 수정
					sql="update board set board_name=?,board_title=?,board_content=? where board_idx=?";
					pstmt=con.prepareStatement(sql);//인자 생성

					pstmt.setString(1, dto.getBoard_name());
					pstmt.setString(2, dto.getBoard_title());
					pstmt.setString(3,dto.getBoard_content());
					pstmt.setInt(4, dto.getBoard_idx());

					pstmt.executeUpdate();

					x=1;
				}else{//암호가 틀리면
					x=-1;
				}
			}//if-end
		}catch(Exception ex){
			System.out.println("updateBoard() 예외:"+ex);
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
	// 글 삭제
	//-------------------------------
	public int deleteArticle(int board_idx,String board_pw){
		//System.out.println("board_idx:"+board_idx);
		//System.out.println("board_pw:"+board_pw);
		String dbpw="";
		int x=-100;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select board_pw from board where board_idx="+board_idx);//생성시 인자 들어감
			rs=pstmt.executeQuery();//쿼리실행


			if(rs.next()){
				dbpw=rs.getString("board_pw");
				if(board_pw.equals(dbpw)){
					//암호가 일치하면 글삭제
					pstmt=con.prepareStatement("delete from board where board_idx="+board_idx);
					pstmt.executeUpdate();//쿼리실행

					x=1;//삭제성공
				}else{//암호가 틀릴때
					x=-1;
				}
			}//if-end


		}catch(Exception ex){
			System.out.println("deleteArticle() 예외:"+ex);
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
