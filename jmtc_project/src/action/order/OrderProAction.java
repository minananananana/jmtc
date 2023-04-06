package action.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import order.*;
public class OrderProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		int item=Integer.parseInt(request.getParameter("item"));
		String state="결제완료";
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		OrderDAO dao=new OrderDAO();
		dao.insertOrderCart(item, state, idx);
		
		//request.setAttribute("item", item);
		
		return "/order/orderPro.jsp";
	}//requestPro()-end

}//class-end