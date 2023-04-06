package action.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

import java.util.*;

import shop.*;

public class ProductListAction implements CommandAction{
    List list;
	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		String id=request.getParameter("id");
		ProductDAO dao=ProductDAO.getInstance();//dao객체얻기
	    list=dao.getGoodList();//dao메서드 호출
		
	    request.setAttribute("list", list);
	    
	    request.setAttribute("id", id);
	    
		return "/shop/productList.jsp";
	
	}//requestPro()-end

}//class-end
