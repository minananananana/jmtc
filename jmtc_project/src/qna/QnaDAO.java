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
	
	
	//������ : private �ܺ� ��ü �������� ���Ѵ�
	private QnaDAO(){}
	
	//��ü 1���� ���� �Ѵ�, �޸� ���� ȿ���� �ִ�
	private static QnaDAO instance=new QnaDAO();//��ü ����
	
	//static����, �޼���� ��ü ���� ���� �ʰ� ����� �� �ִ�.
	//Ŭ���� �̸�.�޼���()
	public static QnaDAO getInstance(){//JSP���� ����� �޼���, �޸� ���� ȿ��
		return instance;
	}//getInstance() out
	
	//-------------
	//Ŀ�ؼ� ���
	//-------------
	private Connection getCon() throws Exception{
		Context ct=new InitialContext();
		DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}//getCon() out
	//----------------------
	   // �۾���, ��� ����
	   //----------------------
	   public void insertBoard(QnaDTO dto){
	      //�۳��뺸�⿡(content.jsp)�� ��� ���� �Ҷ� ������ ������
	      
	      int qna_idx=dto.getQna_idx();
	      int qna_ref=dto.getQna_ref();
	      int qna_ordNo=dto.getQna_ordNo();
	      int qna_levelNo=dto.getQna_levelNo();
  
	      int number=0;//�� �׷� ó��
	      
	      try{
	         con=getCon();//Ŀ�ؼ� ���
	         pstmt=con.prepareStatement("select max(qna_idx) from qna");//�ִ� �۹�ȣ ���
	         rs=pstmt.executeQuery();
	         
	         if(rs.next()){//���� �����ϸ�
	        	 number=rs.getInt(1)+1;
	         }else{//ó�� ���϶�
	            number=1;/// ref=number
	         }//else-end
	         
	         number=rs.getInt(1)+1;//1�� �ʵ� ��ȣ //�ִ� �۹�ȣ+1 ref=number
	         if(qna_idx != 0){//����̸� 
	        	 
	            //��� ���� �ֱ� ��ġ Ȯ��
	        	
	            sql="update qna set qna_ordNo=qna_ordNo+1 where qna_ref=? and qna_ordNo>?";
	            //                �׷��� ���� �׸��� ������� ������ ���� ����ۺ��� ũ�� �����+1 �ض�
	            pstmt=con.prepareStatement(sql);
	            
	            pstmt.setInt(1, qna_ref);
	            pstmt.setInt(2, qna_ordNo);
	            pstmt.executeUpdate();
	            
	            qna_ordNo=qna_ordNo+1;//*****
	            qna_levelNo=qna_levelNo+1;//*****
	            
	       
	         }else{//����
	        	 
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
	         System.out.println("insertBoard()����:"+ex);
	      }finally{
	         try{
	            if(rs!=null){rs.close();}
	            if(pstmt!=null){pstmt.close();}
	            if(con!=null){con.close();}
	         }catch(Exception ex2){}
	      }//finally-end
	   }//insertBoard()-end
	 //-------------------
	   // �� ����(������ó�� , ��ó���ϱ�����)
	   //-------------------
	   public int getCount(){
	      int cnt=0;
	      try{
	         con=getCon();
	         pstmt=con.prepareStatement("select count(*) from qna");
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
	   // ����Ʈ mysql
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

	         //limit ?,?                      ref ��������, re_step ��������
	         //limit ������ġ,����
	         }else{
	        	 sql="select * from qna where "+keyField+" like '%"+keyWord+"%' order by qna_ordNo asc limit ?,?";
			 }//else out   
	        
	         pstmt=con.prepareStatement(sql);//������ ���� ��
	         pstmt.setInt(1, start-1);//mysql 0���� start�� 1�ν����ϱ⶧����
	         pstmt.setInt(2, cnt);
	         rs=pstmt.executeQuery();
	         
	         while(rs.next()){
	            //rs������ dto�� ��´�, dto�� ����Ʈ�� ��´�, list�� ����
	            list=new ArrayList<QnaDTO>();
	            do{
	               QnaDTO dto=new QnaDTO();
	               dto.setQna_idx(rs.getInt("qna_idx"));//num
	               dto.setQna_name(rs.getString("qna_name"));//writer
	               dto.setQna_title(rs.getString("qna_title"));
	               dto.setQna_content(rs.getString("qna_content"));
	           
	               
	               dto.setQna_date(rs.getTimestamp("qna_date"));//����� �ú���
	               
//	               System.out.println(rs.getTimestamp("regdate"));
//	               System.out.println(rs.getDate("regdate"));//�����
//	               System.out.println(rs.getString("regdate"));
	               
	               //NOW():����� �ú���
	               //curdate(): �����
	               //sysdate: ����Ŭ
	        
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
	         System.out.println("getList()����:"+ex);
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
	   //��ȸ�� ����, �۳��� ���� content.jsp
	   //-------------------------
	   public QnaDTO getBoard(int qna_idx){
		   QnaDTO dto=null;
		   try{
			   con=getCon();//Ŀ�ؼ� ���
			   
			   //��ȸ�� ����
			   sql="update qna set qna_readcount=qna_readcount+1 where qna_idx="+qna_idx;//������ �Ӹ� Ű�� ��ġ�ϸ� readcount�� �������Ѷ�
			   pstmt=con.prepareStatement(sql);
			   pstmt.executeUpdate();
			   //��ȸ�� ���� ��
			   
			   //�۳��� ����
			   pstmt=con.prepareStatement("select * from qna where qna_idx="+qna_idx);
			   rs=pstmt.executeQuery();
			   
			   //rs������ dto����´�. return dto
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
			   System.out.println("getBoard() ����"+ex);
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
	   //�ۻ���
	   //--------------------------------
	   public int deleteQnA(int qna_idx, String qna_pw){
		   String dbUserid="";
		   int x=-100;
		   try{
			 con=getCon();
			 pstmt=con.prepareStatement("select qna_pw from qna where qna_idx="+qna_idx);//������ ���� ��
			 rs=pstmt.executeQuery();//���� ����
			 
			 if(rs.next()){
				 dbUserid=rs.getString("qna_pw");
				 if(qna_pw.equals(dbUserid)){
				 //��ȣ�� ��ġ�ϸ� �ۻ���
				 pstmt=con.prepareStatement("delete from qna where qna_idx="+qna_idx);
				 pstmt.executeUpdate();//���� ����
				 
				 x=1;
			     }else{//��ȣ�� Ʋ���� ó��
				 x=-1;
			     }
			 }//if out
		   }catch(Exception ex){
			   System.out.println("deleteQnA() ����"+ex);
			   
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
	   //�ۼ��� ��  ����Ʈ����� �����⶧���� return dto
	   //----------------------------
	   public QnaDTO getUpdate(int qna_idx){
		   QnaDTO dto=null;
		   
		   try{
			   con=getCon();
			   pstmt=con.prepareStatement("select * from qna where qna_idx="+qna_idx);
			   rs=pstmt.executeQuery();
			   
			   if(rs.next()){//rs�ڷḦ �ϳ��� �о��
				   //rs������ dto��´�, return dto
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
			   System.out.println("getUpdate()����"+ex);
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
	   //DB�ۼ���
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
					   pstmt=con.prepareStatement(sql);//������ ���� ��ﳪ��
					   
					   pstmt.setString(1, dto.getQna_name());
				       pstmt.setString(2, dto.getQna_title());
				       pstmt.setString(3, dto.getQna_content());
				       pstmt.setInt(4, dto.getQna_idx());
					   
				       pstmt.executeUpdate();//���� ����
				         
				       x=1; //������ ����
				         
				   }else{//��ȣ�� Ʋ����
					   x=-1;
				   }
			   }//if out
			   
		   }catch(Exception ex){
			   System.out.println("updateBoard()����"+ex);
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
	      // �˻��� �� ����
	      //-------------------
	         public int getFindCount(String keyField, String keyWord){
	            int cnt=0;
	            try{
	               con=getCon();
	               pstmt=con.prepareStatement("select count(*) from qna where "+keyField+" like '%"+keyWord+"%'");
	               rs=pstmt.executeQuery();
	               
	               if(rs.next()){
	                  cnt=rs.getInt(1);//�ʵ� ��ȣ
	               }//if-end
	            }catch(Exception ex){
	               System.out.println("getFindCount()����:"+ex);
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
