package shop;
//���Ͼ��ε�
//jdk/jre/lib/ext/cos.jar
//WebContent/WEB-INF/lib === tomcat/lib/cos.jar
//DAO:�����Ͻ� ����
//��ǰ ���ε� �� �� cos.jar �ʿ���
import java.sql.*;
import java.util.*;

import javax.sql.*;//DataSource
import javax.naming.*;//lookup

import com.oreilly.servlet.*;//cos.jar
import com.oreilly.servlet.multipart.*;//cos.jar

import javax.servlet.http.*;//HttpServletRequest request

import java.io.*;//�׸����� ���� �ϱ� ����

public class ProductDAO {
	//�̱��� ��ü ���
	private ProductDAO(){}//������, �ܺο��� ��ü ���� ���ϰ�

	private static ProductDAO instance=new ProductDAO();//��ü����

	//JSP���� ����� �޼���
	public static ProductDAO getInstance(){
		return instance;
	}

	//------------------
	// Ŀ�ؼ� Ǯ ���
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
	//��ǰ���
	//-----------------------
	public List getGoodList(){
		
		List<ProductDTO> list=new ArrayList<ProductDTO>();
		ProductDTO dto = null;
		
		try{
			
			con=getCon();
			sql="select * from product order by product_idx desc";
			stmt=con.createStatement();//Statement����
			rs=stmt.executeQuery(sql);//���� ����
			
			while(rs.next()){
				
				//rs������ dto�� ��� dto�� list�� �ִ´�. �׸��� list�� �����Ѵ�
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
			System.out.println("getGoodList()����"+ex);
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
	// �� ����
	//----------------------
	public int getCount(){
		int cnt=0;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select count(*) from product");
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
	//---------------
	// �󼼺���
	//---------------
	public ProductDTO getDetail(String product_code){
		//System.out.println("code:"+code);
		ProductDTO dto=new ProductDTO();
		try{
			con=getCon();
			sql="select * from product where product_code='"+product_code+"'";
			stmt=con.createStatement();//Statement����
			rs=stmt.executeQuery(sql);//��������

			if(rs.next()){
				//rs������ dto�� ��´�. �׸��� dto�����Ѵ�
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
			System.out.println("getDetail() ����:"+ex);
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
	// getProduct(),pro_no�� �ش��ϴ� ������ jsp�� ������ �ش�
	//-------------------------------------------
	public ProductDTO getProduct(int product_idx){
		ProductDTO dto=null;
		try{
			con=getCon();
			sql="select * from product where product_idx="+product_idx;//����ǥ �Ⱦ����� �̷��� �ۼ�
			pstmt=con.prepareStatement(sql);//������ ���� ����
			rs=pstmt.executeQuery();//��������

			while(rs.next()){
				dto=new ProductDTO();

				//rs������ dto�� �ְ�,,,dto�� �����Ѵ�
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
			System.out.println("getProduct() ����:"+ex);
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
			System.out.println("insertProduct ����"+ex);
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
			System.out.println("updateProduct() ����:"+ex);
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
			pstmt=con.prepareStatement(sql);//������ ���� ����
			
			pstmt.executeUpdate();
			
			result = "success";

		}catch(Exception ex){
			System.out.println("updateProduct() ����:"+ex);
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
	// ���� ���� ���
	//------------------------
//	public void reduceProduct(OrderDTO dto){
//		int cart_quantity2=Integer.parseInt(dto.getCart_quantity());
//		try{
//			con=getCon();
//			sql="update product set product_stock=(product_stock-?) where product_idx=? and product_stock>="+cart_quantity2;
//			pstmt=con.prepareStatement(sql);//������ ���� �尨
//			pstmt.setString(1, dto.getCart_quantity());//����
//			pstmt.setInt(2, dto.getProduct_idx());//��ǰ �Ϸ� ��ȣ
//
//			pstmt.executeUpdate();//���� ����
//
//		}catch(Exception ex){
//			System.out.println("reduceProduct() ����:"+ex);
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
