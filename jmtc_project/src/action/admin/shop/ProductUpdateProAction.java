package action.admin.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.ProductDAO;
import shop.ProductDTO;
import command.CommandAction;

public class ProductUpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		String flag=request.getParameter("flag");
		String product_idx = request.getParameter("product_idx");
		
		ProductDAO dao = ProductDAO.getInstance();
		ProductDTO dto = new ProductDTO();
		
		boolean result=false;
		int state=100;
		
		if(flag.equals("update")){
			result=dao.updateProduct(request);
			state=1;
			
		}
		
		System.out.println(flag);
		System.out.println(product_idx);
		System.out.println(result);
		
		request.setAttribute("product_idx", product_idx);
		request.setAttribute("result", result);
		request.setAttribute("state", state);
		
		return "/admin/shop/productUpdatePro.jsp";
	}

}//class-end
