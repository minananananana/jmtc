package action.admin.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

import java.util.*;

import shop.*;

public class ProductListAction implements CommandAction{
   
	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//String id=request.getParameter("id");
		List list = null;
		ProductDAO dao=ProductDAO.getInstance();//dao��ü���
	    list=dao.getGoodList();//dao�޼��� ȣ��
		
	    request.setAttribute("list", list);
	    
	    //request.setAttribute("id", id);
	    
		return "/admin/shop/productList.jsp";
	
	}//requestPro()-end

}//class-end
