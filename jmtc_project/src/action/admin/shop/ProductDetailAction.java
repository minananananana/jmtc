package action.admin.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import shop.*;

public class ProductDetailAction implements CommandAction{

   @Override
   public String requestPro(HttpServletRequest request,
         HttpServletResponse response) throws Throwable {
      
      //productList.jsp에서 보내준 데이터를 코드를 통해 받는다
      String code=request.getParameter("code");
      System.out.println(code);
      ProductDAO dao=ProductDAO.getInstance();//객체 얻기
      ProductDTO dto=dao.getDetail(code);//dao메서드 호출
     
      //jsp에 사용할수있도록 값을 설정
      request.setAttribute("code", code);
      request.setAttribute("dto", dto);
      
      return "/admin/shop/productDetail.jsp";
   }

}