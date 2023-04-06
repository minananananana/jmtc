package qna;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import qna.QnaDTO;

import org.apache.tomcat.jdbc.pool.DataSource;

public class QnaDAO {
	Connection con=null;
	PreparedStatement pstmt=null;
	Statement stmt=null;
	ResultSet rs=null;
	String sql="";
	
	
	//생성자 : private 외부 객체 생성하지 못한다
	private QnaDAO(){}
	
	//객체 1개로 서비스 한다, 메모리 절약 효과가 있다
	private static QnaDAO instance=new QnaDAO();//객체 생성
	
	//static변수, 메서드는 객체 생성 하지 않고도 사용할 수 있다.
	//클래스 이름.메서드()
	public static QnaDAO getInstance(){//JSP에서 사용할 메서드, 메모리 절약 효과
		return instance;
	}//getInstance() out
	
	//-------------
	//커넥션 얻기
	//-------------
	private Connection getCon() throws Exception{
		Context ct=new InitialContext();
		DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}//getCon() out
	//----------------------
	   // 글쓰기, 답글 쓰기
	   //----------------------
	   public void insertBoard(QnaDTO dto){
	      //글내용보기에(content.jsp)에 답글 쓰기 할때 보내준 데이터
	      
	      int qna_idx=dto.getQna_idx();
	      int qna_ref=dto.getQna_ref();
	      int qna_ordNo=dto.getQna_ordNo();
	      int qna_levelNo=dto.getQna_levelNo();
  
	      int number=0;//글 그룹 처리
	      
	      try{
	         con=getCon();//커넥션 얻기
	         pstmt=con.prepareStatement("select max(qna_idx) from qna");//최대 글번호 얻기
	         rs=pstmt.executeQuery();
	         
	         if(rs.next()){//글이 존재하면
	        	 number=rs.getInt(1)+1;
	         }else{//처음 글일때
	            number=1;/// ref=number
	         }//else-end
	         
	         number=rs.getInt(1)+1;//1은 필드 번호 //최대 글번호+1 ref=number
	         if(qna_idx != 0){//답글이면 
	        	 
	            //답글 끼워 넣기 위치 확보
	        	
	            sql="update qna set qna_ordNo=qna_ordNo+1 where qna_ref=? and qna_ordNo>?";
	            //                그룹이 같고 그리고 원답글의 순서가 이전 원답글보다 크면 원답글+1 해라
	            pstmt=con.prepareStatement(sql);
	            
	            pstmt.setInt(1, qna_ref);
	            pstmt.setInt(2, qna_ordNo);
	            pstmt.executeUpdate();
	            
	            qna_ordNo=qna_ordNo+1;//*****
	            qna_levelNo=qna_levelNo+1;//*****
	            
	       
	         }else{//원글
	        	 
	        	 qna_ref=number;
	        	 qna_ordNo=0;
	        	 qna_levelNo=0;
	         }//else-end
  
		         //insert
		         sql="insert into qna(qna_name, qna_title, qna_content, qna_pw ,qna_date, qna_ref, qna_ordNo, qna_levelNo, member_idx, qna_secret) ";
		         sql=sql+"values(?,?,?,?,now(),?,?,?,?,?)";
		         
		         pstmt=con.prepareStatement(sql);
		         
		         pstmt.setString(1, dto.getQna_name());
		         pstmt.setString(2, dto.getQna_title());
		         pstmt.setString(3, dto.getQna_content());
		         pstmt.setString(4, dto.getQna_pw());
		         //NOW()
		         pstmt.setInt(5, qna_ref);
		         pstmt.setInt(6, qna_ordNo);
		         pstmt.setInt(7, qna_levelNo);
		         pstmt.setInt(8, dto.getMember_idx());
		         pstmt.setString(9, dto.getQna_secret());
      
	         pstmt.executeUpdate();
	         
	      }catch(Exception ex){
	         System.out.println("insertBoard()예외:"+ex);
	      }finally{
	         try{
	            if(rs!=null){rs.close();}
	            if(pstmt!=null){pstmt.close();}
	            if(con!=null){con.close();}
	         }catch(Exception ex2){}
	      }//finally-end
	   }//insertBoard()-end
	 //-------------------
	   // 글 갯수(페이지처리 , 블럭처리하기위해)
	   //-------------------
	   public int getCount(){
	      int cnt=0;
	      try{
	         con=getCon();
	         pstmt=con.prepareStatement("select count(*) from qna");
	         rs=pstmt.executeQuery();
	         
	         if(rs.next()){
	            cnt=rs.getInt(1);//필드 번호
	         }//if-end
	      }catch(Exception ex){
	         System.out.println("getCount()예외:"+ex);
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
	   // 리스트 mysql
	   //--------------------
	   public List getList(int start, int cnt, int member_idx, String keyField, String keyWord){
	      
		   List<QnaDTO> list=null;
	      System.out.println(keyField);
	      System.out.println(keyWord);
	      
	      try{
	         con=getCon();
	         
	         if(keyWord.equals(null)||keyWord.equals("")||keyWord.length()<1){
	        

	        	 sql = "select q.qna_idx,q.qna_name,q.qna_title,q.qna_pw,q.qna_content,q.qna_date,q.qna_readcount,q.qna_ref,q.qna_ordNo,q.qna_levelNo,q.member_idx,q.qna_secret,m.member_nick";
	        	 sql = sql + " from qna q LEFT JOIN member m ON q.member_idx=m.member_idx order by qna_ref desc, qna_ordNo asc limit ?,?";

	         //limit ?,?                      ref 내림차순, re_step 오름차순
	         //limit 시작위치,개수
	         }else{
	        	 sql="select * from qna where "+keyField+" like '%"+keyWord+"%' order by qna_ordNo asc limit ?,?";
			 }//else out   
	        
	         pstmt=con.prepareStatement(sql);//생성시 인자 들어감
	         pstmt.setInt(1, start-1);//mysql 0부터 start가 1로시작하기때문에
	         pstmt.setInt(2, cnt);
	         rs=pstmt.executeQuery();
	         
	         while(rs.next()){
	            //rs내용을 dto에 담는다, dto를 리스트에 담는다, list를 리턴
	            list=new ArrayList<QnaDTO>();
	            do{
	               QnaDTO dto=new QnaDTO();
	               dto.setQna_idx(rs.getInt("qna_idx"));//num
	               dto.setQna_name(rs.getString("qna_name"));//writer
	               dto.setQna_title(rs.getString("qna_title"));
	               dto.setQna_content(rs.getString("qna_content"));
	           
	               
	               dto.setQna_date(rs.getTimestamp("qna_date"));//년월일 시분초
	               
//	               System.out.println(rs.getTimestamp("regdate"));
//	               System.out.println(rs.getDate("regdate"));//년월일
//	               System.out.println(rs.getString("regdate"));
	               
	               //NOW():년월일 시분초
	               //curdate(): 년월일
	               //sysdate: 오라클
	        
	               dto.setQna_readcount(rs.getInt("qna_readcount"));
	               dto.setQna_ref(rs.getInt("qna_ref"));
	               dto.setQna_ordNo(rs.getInt("qna_ordNo"));
	               dto.setQna_levelNo(rs.getInt("qna_levelNo"));
	               dto.setMember_idx(rs.getInt("member_idx"));
	               dto.setQna_secret(rs.getString("qna_secret"));
	               
	               list.add(dto);//******
	            }while(rs.next());
	          
	         }//while-end
	         
	      }catch(Exception ex){
	         System.out.println("getList()예외:"+ex);
	      }finally{
	         try{
	            if(rs!=null){rs.close();}
	            if(pstmt!=null){pstmt.close();}
	            if(con!=null){con.close();}
	         }catch(Exception ex2){}
	      }//finally-end
	      return list;
	   }//getList()-end
	 //----------------------------
	   //조회수 증가, 글내용 보기 content.jsp
	   //-------------------------
	   public QnaDTO getBoard(int qna_idx){
		   QnaDTO dto=null;
		   try{
			   con=getCon();//커넥션 얻기
			   
			   //조회수 증가
			   sql="update qna set qna_readcount=qna_readcount+1 where qna_idx="+qna_idx;//프라이 머리 키가 일치하면 readcount를 증가시켜라
			   pstmt=con.prepareStatement(sql);
			   pstmt.executeUpdate();
			   //조회수 증가 끝
			   
			   //글내용 보기
			   pstmt=con.prepareStatement("select * from qna where qna_idx="+qna_idx);
			   rs=pstmt.executeQuery();
			   
			   //rs내용을 dto에담는다. return dto
			   if(rs.next()){
				   dto=new QnaDTO();
				   
				   dto.setQna_idx(rs.getInt("qna_idx"));
				   dto.setQna_name(rs.getString("qna_name"));
				   dto.setQna_title(rs.getString("qna_title"));
				   dto.setQna_pw(rs.getString("qna_pw"));
				   dto.setQna_content(rs.getString("qna_content"));
				   
				   dto.setQna_date(rs.getTimestamp("qna_date"));
				   dto.setQna_readcount(rs.getInt("qna_readcount"));
				   
				   dto.setQna_ref(rs.getInt("qna_ref"));
				   dto.setQna_ordNo(rs.getInt("qna_ordNo"));
				   dto.setQna_levelNo(rs.getInt("qna_levelNo"));
				   dto.setMember_idx(rs.getInt("member_idx"));
			   }
		   }catch(Exception ex){
			   System.out.println("getBoard() 예외"+ex);
		   }finally{
			   try{
		            if(rs!=null){rs.close();}
		            if(pstmt!=null){pstmt.close();}
		            if(con!=null){con.close();}
		         }catch(Exception ex2){}
		   }//finally out
		   return dto;
	   }//getBoard() out
	   
	   
       //---------------------------------
	   //글삭제
	   //--------------------------------
	   public int deleteQnA(int qna_idx, String qna_pw){
		   String dbUserid="";
		   int x=-100;
		   try{
			 con=getCon();
			 pstmt=con.prepareStatement("select qna_pw from qna where qna_idx="+qna_idx);//생성시 인자 들어감
			 rs=pstmt.executeQuery();//쿼리 실행
			 
			 if(rs.next()){
				 dbUserid=rs.getString("qna_pw");
				 if(qna_pw.equals(dbUserid)){
				 //암호가 일치하면 글삭제
				 pstmt=con.prepareStatement("delete from qna where qna_idx="+qna_idx);
				 pstmt.executeUpdate();//쿼리 실행
				 
				 x=1;
			     }else{//암호가 틀릴때 처리
				 x=-1;
			     }
			 }//if out
		   }catch(Exception ex){
			   System.out.println("deleteQnA() 예외"+ex);
			   
		   }finally{
			   try{
				   if(rs!=null){rs.close();}
		           if(pstmt!=null){pstmt.close();}
		           if(con!=null){con.close();}
			   }catch(Exception ex2){}
		   }//finally out
		   return x;
		   
	   }//deleteArticle() out
	   //----------------------------
	   //글수정 폼  프론트엔드로 보내기때문에 return dto
	   //----------------------------
	   public QnaDTO getUpdate(int qna_idx){
		   QnaDTO dto=null;
		   
		   try{
			   con=getCon();
			   pstmt=con.prepareStatement("select * from qna where qna_idx="+qna_idx);
			   rs=pstmt.executeQuery();
			   
			   if(rs.next()){//rs자료를 하나씩 읽어낸다
				   //rs내용을 dto담는다, return dto
				    dto=new QnaDTO();
				    
				    dto.setQna_idx(rs.getInt("qna_idx"));
				    dto.setQna_name(rs.getString("qna_name"));
				    dto.setQna_title(rs.getString("qna_title"));
				    dto.setQna_content(rs.getString("qna_content"));
				    dto.setQna_pw (rs.getString("qna_pw"));
				   
				    dto.setQna_date(rs.getTimestamp("qna_date"));
				    dto.setQna_readcount(rs.getInt("qna_readcount"));
				   
				    dto.setQna_ref(rs.getInt("qna_ref"));
				    dto.setQna_ordNo(rs.getInt("qna_ordNo"));
				    dto.setQna_levelNo(rs.getInt("qna_levelNo"));
		
				    dto.setMember_idx(rs.getInt("member_idx"));
				    
			     
			   }//if out
		   }catch(Exception ex){
			   System.out.println("getUpdate()예외"+ex);
		   }finally{
			   try{
				   if(rs!=null){rs.close();}
		           if(pstmt!=null){pstmt.close();}
		           if(con!=null){con.close();}
			   }catch(Exception ex2){}
		   }
		   return dto;
	   }//getUpdate() out
	   //---------------------------
	   //DB글수정
	   //--------------------------
	   public int updateBoard(QnaDTO dto){
		   int x=-100;
		   String dbUserid="";
		   try{
			   con=getCon();
			   pstmt=con.prepareStatement("select * from qna where qna_idx=?");
			   pstmt.setInt(1, dto.getQna_idx());
			   rs=pstmt.executeQuery();
			   
			   if(rs.next()){
				   dbUserid=rs.getString("qna_pw");
				   if(dto.getQna_pw().equals(dbUserid)){
					   sql="update qna set qna_name=?, qna_title=?, qna_content=? where qna_idx=?";
					   pstmt=con.prepareStatement(sql);//생성시 인자 들억나다
					   
					   pstmt.setString(1, dto.getQna_name());
				       pstmt.setString(2, dto.getQna_title());
				       pstmt.setString(3, dto.getQna_content());
				       pstmt.setInt(4, dto.getQna_idx());
					   
				       pstmt.executeUpdate();//쿼리 실행
				         
				       x=1; //정상적 수행
				         
				   }else{//암호가 틀리면
					   x=-1;
				   }
			   }//if out
			   
		   }catch(Exception ex){
			   System.out.println("updateBoard()예외"+ex);
		   }finally{
			   try{
				   if(rs!=null){rs.close();}
		           if(pstmt!=null){pstmt.close();}
		           if(con!=null){con.close();}
			   }catch(Exception ex2){}
		   }//finally out
		   
		   return x;
	   }//updateBoard()out
	 //-------------------
	      // 검색한 글 갯수
	      //-------------------
	         public int getFindCount(String keyField, String keyWord){
	            int cnt=0;
	            try{
	               con=getCon();
	               pstmt=con.prepareStatement("select count(*) from qna where "+keyField+" like '%"+keyWord+"%'");
	               rs=pstmt.executeQuery();
	               
	               if(rs.next()){
	                  cnt=rs.getInt(1);//필드 번호
	               }//if-end
	            }catch(Exception ex){
	               System.out.println("getFindCount()예외:"+ex);
	            }finally{
	               try{
	                  if(rs!=null){rs.close();}
	                  if(pstmt!=null){pstmt.close();}
	                  if(con!=null){con.close();}
	               }catch(Exception ex2){}
	            }//finally-end
	            
	            return cnt;
	         }//getFindCount()-end
}//QnaDAO() out
