package action.admin.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.ProductDAO;

import command.CommandAction;

public class ProductAddProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		String flag=request.getParameter("flag");
		boolean result=false;
		int state=100;
		
		ProductDAO dao = ProductDAO.getInstance();
		
		if(flag.equals("insert")){
			result=dao.insertProduct(request);
			state=1;
			
		}
		
		request.setAttribute("state", state);
		request.setAttribute("result", result);
		
		return "/admin/shop/productAddPro.jsp";
	}

}//class-end

