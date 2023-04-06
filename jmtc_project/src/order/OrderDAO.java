package order;
import java.sql.*;

import javax.sql.*;//DataSource
import javax.naming.*;//lookup

import shop.ProductDTO;

import java.util.*;

//주문처리
public class OrderDAO {
	public OrderDAO(){}//생성자
	
	//커넥션 얻기
	public Connection getCon() throws Exception{
		Context ct=new InitialContext();
		DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}//getCon()-end
	
	Connection con=null;
	PreparedStatement pstmt=null;
	Statement stmt=null;
	ResultSet rs=null;
	String sql="";
	
	//=====================================
		// 카트내용 주문하기
		//=====================================
		public void insertOrderCart(int cart_idx, String state, int member_idx){
			try{
				con=getCon();
				sql="select * from cart where cart_idx="+cart_idx;
				pstmt=con.prepareStatement(sql);
				
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					String imCart_code=rs.getString("cart_code");
					int imQuantity=rs.getInt("cart_quantity");
					String imName=rs.getString("cart_name");
					
					System.out.println(imCart_code);
					System.out.println(imQuantity);
					System.out.println(imName);
					
					sql="insert into `order`";
					sql = sql + " values (0,?,?,?,NOW(),?,?)";
					
					pstmt=con.prepareStatement(sql);
					
					pstmt.setString(1, imCart_code);
					pstmt.setString(2, imName);
					pstmt.setInt(3, imQuantity);
		
					pstmt.setString(4, state);
					pstmt.setInt(5, member_idx);
					
					pstmt.executeUpdate();
				}//if-end
			}catch(Exception ex){
				System.out.println("insertOrderCart()예외:"+ex);
			}finally{
				try{
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				}catch(Exception ex){}
			}//finally-end
		}//insertOrderCart()-end
		
		//==============================
	   // 주문내용 보여주기
	   //==============================
	   public List orderList(int idx,int start, int cnt){
		   
	      List list=new ArrayList();
	      
	      try{
	         con=getCon();
	         sql="select * from `order` where member_idx=? order by order_no desc limit ?,?";
	         pstmt=con.prepareStatement(sql);
	         pstmt.setInt(1, idx);
	         pstmt.setInt(2, start-1);//mysql 0부터
	         pstmt.setInt(3, cnt);
	         
	         rs=pstmt.executeQuery();
	         OrderDTO dto=null;
	         
	         while(rs.next()){
	            dto=new OrderDTO();
	            
	            dto.setOrder_no(rs.getInt("order_no"));
	            dto.setOrder_code(rs.getString("order_code"));
	            dto.setOrder_name(rs.getString("order_name"));
	            dto.setOrder_quantity(rs.getString("order_quantity"));
	            dto.setOrder_date(rs.getTimestamp("order_date"));
	            dto.setOrder_state(rs.getString("order_state"));
	            dto.setMember_idx(rs.getInt("member_idx"));
	            
	            list.add(dto);
	            
	         }//while-end
	      }catch(Exception ex){
	         System.out.println("orderList()예외:"+ex);
	      }finally{
	         try{
	            if(rs!=null){rs.close();}
	            if(pstmt!=null){pstmt.close();}
	            if(con!=null){con.close();}
	         }catch(Exception ex){}
	      }//finally-end
	      
	      return list;
	   }//orderList()-end
	   
	   //-------------------
	   // 주문 갯수
	   //-------------------
	   public int getCount(int idx){
	      int cnt=0;
	      try{
	         con=getCon();
	         pstmt=con.prepareStatement("select count(*) from `order` where member_idx=?");
	         pstmt.setInt(1, idx);
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
	   
	//메뉴별 판매현황
	public List menuStatus(int start, int cnt){
		
		List<OrderDTO> list = new ArrayList<OrderDTO>();
		OrderDTO dto = null;
		
		try{
			con=getCon();
			sql="select order_name, order_quantity from `order` limit ?,?";
			pstmt=con.prepareStatement(sql);//생성시 인자 들어간다
			
			pstmt.setInt(1, start-1);
	        pstmt.setInt(2, cnt);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				dto = new OrderDTO();
				
				dto.setOrder_name(rs.getString("order_name"));
				dto.setOrder_quantity(rs.getString("order_quantity"));
				
				list.add(dto);
				
				System.out.println(list);
				
			}//while-end

		}catch(Exception ex){
			System.out.println("menuStatus() 예외:"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}

		}//finally-end
		
		return list;
	}//menuStatus()-end


	}//class-end