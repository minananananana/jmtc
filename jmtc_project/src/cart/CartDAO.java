package cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.util.*;

public class CartDAO {
	
	public CartDAO(){}//생성자
	
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
	
	//장바구니 추가
	public String insertCart(CartDTO dto){
		
		String result = "fail";
		
		try{
			
			con = getCon();
			sql = "insert into cart(cart_orddate, cart_price, cart_name, cart_quantity, cart_code, cart_image, member_idx)";
			sql = sql + " values(NOW(),?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getCart_price());
			pstmt.setString(2, dto.getCart_name());
			pstmt.setInt(3, dto.getCart_quantity());
			pstmt.setString(4, dto.getCart_code());
			pstmt.setString(5, dto.getCart_image());
			pstmt.setInt(6, dto.getMember_idx());
			
			pstmt.executeUpdate();
			
			result = "success";
			
		}catch(Exception ex){
			System.out.println("insertCart 예외"+ex);
		}finally{
			try{
				if(rs != null){rs.close();}
				if(pstmt != null){pstmt.close();}
				if(con != null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		
		return result;
		
	}//insertCart()-end
	
	//--------------
   // 카트목록
   //------------------
   public List getCartList(int member_idx){
	   
	   List<CartDTO> list=new ArrayList<CartDTO>();
      
      try{
         con=getCon();
         sql="select * from cart where member_idx="+member_idx+" order by cart_idx desc";
         stmt=con.createStatement();
         rs=stmt.executeQuery(sql);

         while(rs.next()){
        	CartDTO dto = new CartDTO();
        	
        	dto.setCart_idx(rs.getInt("cart_idx"));
            dto.setCart_code(rs.getString("cart_code"));
            dto.setCart_idx(rs.getInt("cart_idx"));
            dto.setCart_name(rs.getString("cart_name"));
            dto.setCart_orddate(rs.getString("cart_orddate"));
            dto.setCart_price(rs.getInt("cart_price"));
            dto.setCart_state(rs.getString("cart_state"));
            dto.setCart_quantity(rs.getInt("cart_quantity"));
            dto.setCart_image(rs.getString("cart_image"));
            dto.setMember_idx(rs.getInt("member_idx"));
            
            list.add(dto);
         }
          }catch(Exception ex){
            System.out.println("getCartList() 예외"+ex);
         }finally{
            try{
               if(rs!=null){rs.close();}
               if(stmt!=null){stmt.close();}
               if(con!=null){con.close();}
            }catch(Exception ex2){}
         }//finally-end
      
         return list;
         
      }//getCartList()-end
   
 //----------------------
   // 카트 삭제 
   //----------------------
   public String deleteCart(int cart_idx){
   //   System.out.println("cart_idx:"+cart_idx);
	   String result = "fail";
      try{
         con=getCon();
         pstmt=con.prepareStatement("delete from cart where cart_idx="+cart_idx);
         pstmt.executeUpdate();

         result = "success";
         
      }catch(Exception ex){
         System.out.println("deleteCart()예외:"+ex);
      }finally{
         try{
            if(rs!=null){rs.close();}
            if(pstmt!=null){pstmt.close();}
            if(con!=null){con.close();}
         }catch(Exception ex2){}
      }//finally-end

      return result;
   }//deleteArticle()-end

   
}//class-end
