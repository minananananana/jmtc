package shop;
//파일업로드
//jdk/jre/lib/ext/cos.jar
//WebContent/WEB-INF/lib === tomcat/lib/cos.jar
//DAO:비지니스 로직
//상품 업로드 할 때 cos.jar 필요함
import java.sql.*;
import java.util.*;

import javax.sql.*;//DataSource
import javax.naming.*;//lookup

import com.oreilly.servlet.*;//cos.jar
import com.oreilly.servlet.multipart.*;//cos.jar

import javax.servlet.http.*;//HttpServletRequest request

import java.io.*;//그림파일 삭제 하기 위해

public class ProductDAO {
	//싱글톤 객체 사용
	private ProductDAO(){}//생성자, 외부에서 객체 생성 못하게

	private static ProductDAO instance=new ProductDAO();//객체생성

	//JSP에서 사용할 메서드
	public static ProductDAO getInstance(){
		return instance;
	}

	//------------------
	// 커넥션 풀 사용
	//------------------
	private Connection getCon() throws Exception{
		Context ct=new InitialContext();
		DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}//getCon()-end

	Connection con=null;
	Statement stmt=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	String sql=null;

	//-----------------------
	//상품목록
	//-----------------------
	public List getGoodList(){
		
		List<ProductDTO> list=new ArrayList<ProductDTO>();
		ProductDTO dto = null;
		
		try{
			
			con=getCon();
			sql="select * from product order by product_idx desc";
			stmt=con.createStatement();//Statement생성
			rs=stmt.executeQuery(sql);//쿼리 실행
			
			while(rs.next()){
				
				//rs내용을 dto에 담고 dto를 list에 넣는다. 그리고 list를 리턴한다
				dto=new ProductDTO();
				
				dto.setProduct_idx(rs.getInt("product_idx"));
				dto.setProduct_code(rs.getString("product_code"));
				dto.setProduct_name(rs.getString("product_name"));
				dto.setProduct_detail(rs.getString("product_detail"));

				dto.setProduct_price(rs.getInt("product_price"));
				dto.setProduct_seller(rs.getString("product_seller"));
				dto.setProduct_stock(rs.getInt("product_stock"));

				dto.setProduct_date(rs.getDate("product_date"));
				dto.setProduct_image(rs.getString("product_image"));

				list.add(dto);

			}//while out
			
		}catch(Exception ex){
			System.out.println("getGoodList()예외"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(stmt!=null){stmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally out
		
		return list;
		
	}//getGoodList out
	
	//----------------------
	// 글 갯수
	//----------------------
	public int getCount(){
		int cnt=0;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select count(*) from product");
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
	//---------------
	// 상세보기
	//---------------
	public ProductDTO getDetail(String product_code){
		//System.out.println("code:"+code);
		ProductDTO dto=new ProductDTO();
		try{
			con=getCon();
			sql="select * from product where product_code='"+product_code+"'";
			stmt=con.createStatement();//Statement생성
			rs=stmt.executeQuery(sql);//쿼리실행

			if(rs.next()){
				//rs내용을 dto에 담는다. 그리고 dto리턴한다
				dto.setProduct_idx(rs.getInt("product_idx"));
				dto.setProduct_code(rs.getString("product_code"));
				dto.setProduct_name(rs.getString("product_name"));
				dto.setProduct_detail(rs.getString("product_detail"));

				dto.setProduct_price(rs.getInt("product_price"));
				dto.setProduct_seller(rs.getString("product_seller"));
				dto.setProduct_stock(rs.getInt("product_stock"));

				dto.setProduct_date(rs.getDate("product_date"));
				dto.setProduct_image(rs.getString("product_image"));

			}//if-end
		}catch(Exception ex){
			System.out.println("getDetail() 예외:"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(stmt!=null){stmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}

		}//finally-end

		return dto;
	}//getDetail()-end
	//-------------------------------------------
	// getProduct(),pro_no에 해당하는 정보를 jsp로 리턴해 준다
	//-------------------------------------------
	public ProductDTO getProduct(int product_idx){
		ProductDTO dto=null;
		try{
			con=getCon();
			sql="select * from product where product_idx="+product_idx;//물음표 안쓸꺼면 이렇게 작성
			pstmt=con.prepareStatement(sql);//생성시 인자 들어간다
			rs=pstmt.executeQuery();//쿼리실행

			while(rs.next()){
				dto=new ProductDTO();

				//rs내용을 dto에 넣고,,,dto를 리턴한다
				dto.setProduct_idx(rs.getInt("product_idx"));
				dto.setProduct_code(rs.getString("product_code"));
				dto.setProduct_name(rs.getString("product_name"));
				dto.setProduct_detail(rs.getString("product_detail"));

				dto.setProduct_price(rs.getInt("product_price"));
				dto.setProduct_seller(rs.getString("product_seller"));
				dto.setProduct_stock(rs.getInt("product_stock"));

				dto.setProduct_date(rs.getDate("product_date"));
				dto.setProduct_image(rs.getString("product_image"));
			}//while-end
		}catch(Exception ex){
			System.out.println("getProduct() 예외:"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}

		}//finally-end

		return dto;
	}//getProduct()-end
	
	//product-insert
	public boolean insertProduct(HttpServletRequest request){
		
		boolean result = false;
		request.getServletContext().getRealPath("/");
		
		try{
			con = getCon();
			
			String realpath = request.getServletContext().getRealPath("/");
			String uploadPath = "C:\\_imgs\\shop_upload\\";
			
			MultipartRequest mul = new MultipartRequest(request, uploadPath, 5*1024*1024, "utf-8", new DefaultFileRenamePolicy());
			
			sql = "insert into product(product_idx, product_code, product_name, product_detail, product_price, product_seller, product_stock, product_date, product_image)";
			sql = sql + " values(0,?,?,?,?,?,?,NOW(),?)";
			pstmt = con.prepareStatement(sql);
			
			
			pstmt.setString(1, mul.getParameter("product_code"));
			pstmt.setString(2, mul.getParameter("product_name"));
			pstmt.setString(3, mul.getParameter("product_detail"));
			pstmt.setInt(4, Integer.parseInt(mul.getParameter("product_price")));
			pstmt.setString(5, mul.getParameter("product_seller"));
			pstmt.setInt(6, Integer.parseInt(mul.getParameter("product_stock")));
			
			if(mul.getFilesystemName("product_image") != null){		
				pstmt.setString(7, mul.getFilesystemName("product_image"));	
			}else{	
				pstmt.setString(7, "ready.png");
			}
			
			pstmt.executeUpdate();
			
			result = true;
			
		}catch(Exception ex){
			System.out.println("insertProduct 예외"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		
		return result;
		
	}//insertProduct()-end
	
	//product-update
	public boolean updateProduct(HttpServletRequest request){
		
		boolean result = false;
		
		request.getServletContext().getRealPath("/");
		
		try{
			con = getCon();
			
			String realpath = request.getServletContext().getRealPath("/");
			String uploadPath = "C:\\_imgs\\shop_upload\\";
			
			MultipartRequest mul = new MultipartRequest(request, uploadPath, 5*1024*1024, "utf-8", new DefaultFileRenamePolicy());
			
			sql = "update product set product_code=?,product_name=?,product_detail=?,product_price=?,product_seller=?,product_stock=?,product_date=NOW(),product_image=? where product_idx=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, mul.getParameter("product_code"));
			pstmt.setString(2, mul.getParameter("product_name"));
			pstmt.setString(3, mul.getParameter("product_detail"));
			pstmt.setInt(4, Integer.parseInt(mul.getParameter("product_price")));
			pstmt.setString(5, mul.getParameter("product_seller"));
			pstmt.setInt(6, Integer.parseInt(mul.getParameter("product_stock")));
			
			if(mul.getFilesystemName("product_image") != null){		
				pstmt.setString(7, mul.getFilesystemName("product_image"));	
			}else{	
				pstmt.setString(7, "ready.png");
			}
			
			pstmt.setInt(8, Integer.parseInt(mul.getParameter("product_idx")));
			
			pstmt.executeUpdate();
			
			result = true;
			
		}catch(Exception ex){
			System.out.println("updateProduct() 예외:"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}

		}//finally-end
		
		return result;
		
	}//updateProduct()-end
	
	//product-delete
	public String deleteProduct(int product_idx){
		
		String result = "fail";
		
		try{
			con=getCon();
			sql="delete from product where product_idx="+product_idx;
			pstmt=con.prepareStatement(sql);//생성시 인자 들어간다
			
			pstmt.executeUpdate();
			
			result = "success";

		}catch(Exception ex){
			System.out.println("updateProduct() 예외:"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}

		}//finally-end
		
		return result;
		
	}//deleteProduct()-end
	
	//------------------------
	// 남은 물량 계산
	//------------------------
//	public void reduceProduct(OrderDTO dto){
//		int cart_quantity2=Integer.parseInt(dto.getCart_quantity());
//		try{
//			con=getCon();
//			sql="update product set product_stock=(product_stock-?) where product_idx=? and product_stock>="+cart_quantity2;
//			pstmt=con.prepareStatement(sql);//생성시 인자 드감
//			pstmt.setString(1, dto.getCart_quantity());//수량
//			pstmt.setInt(2, dto.getProduct_idx());//상품 일련 번호
//
//			pstmt.executeUpdate();//쿼리 실행
//
//		}catch(Exception ex){
//			System.out.println("reduceProduct() 예외:"+ex);
//		}finally{
//			try{
//				if(rs!=null){rs.close();}
//				if(pstmt!=null){pstmt.close();}
//				if(con!=null){con.close();}
//			}catch(Exception ex2){}
//
//		}//finally-end
//	}//reduceProduct()-end

}//class-end
