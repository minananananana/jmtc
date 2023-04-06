package action.admin.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.ProductDAO;
import command.CommandAction;

public class ProductDeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int product_idx = Integer.parseInt(request.getParameter("product_idx"));
		
		ProductDAO dao = ProductDAO.getInstance();
		String result = dao.deleteProduct(product_idx);
		
		request.setAttribute("result", result);
		
		return "/admin/shop/productDeletePro.jsp";
	}

}//class-end
