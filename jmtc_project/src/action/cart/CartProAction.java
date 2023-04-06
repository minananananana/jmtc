package action.cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.CartDAO;
import cart.CartDTO;
import command.CommandAction;

public class CartProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		CartDTO dto = new CartDTO();
		
		dto.setCart_price(Integer.parseInt(request.getParameter("product_price")));
		dto.setCart_name(request.getParameter("product_name"));
		dto.setCart_quantity(Integer.parseInt(request.getParameter("product_stock")));
		dto.setCart_code(request.getParameter("product_code"));
		dto.setCart_image(request.getParameter("product_image"));
		dto.setMember_idx(Integer.parseInt(request.getParameter("idx")));
		
		CartDAO dao = new CartDAO();
		String result = dao.insertCart(dto);
		
		request.setAttribute("result", result);
		
		return "/cart/cartPro.jsp";
	}

}
