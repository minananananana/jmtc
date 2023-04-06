package action.cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import cart.*;
public class DeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		

	      int idx=Integer.parseInt(request.getParameter("item"));
	      //String pageNum=request.getParameter("pageNum");
	      
	      
	     CartDAO dao=new CartDAO();//dao��ü���
     	 String result = dao.deleteCart(idx);//dao�޼��� ȣ��
	      
	      
	      //request.setAttribute("pageNum", pageNum);
	      request.setAttribute("result", result);

		return "/cart/deletePro.jsp";
	}

}//class-end
