package action.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.ProductDAO;
import shop.ProductDTO;
import command.CommandAction;

public class ProductUpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		int product_idx = Integer.parseInt(request.getParameter("product_idx"));
		
		ProductDAO dao = ProductDAO.getInstance();
		ProductDTO dto = dao.getProduct(product_idx);
		
		request.setAttribute("dto", dto);
		
		return "/shop/productUpdateForm.jsp";
	}

}//class-end
