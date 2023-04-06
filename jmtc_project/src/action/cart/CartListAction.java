package action.cart;
import cart.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import command.CommandAction;

public class CartListAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		int member_idx = Integer.parseInt(request.getParameter("idx"));
		CartDAO cartdao=new CartDAO();
		List list=null;
		
		
		list=cartdao.getCartList(member_idx);//cartdao메서드 호출
		
		request.setAttribute("list", list);
		
		return "/cart/cartList.jsp";
	}

}//class-end
