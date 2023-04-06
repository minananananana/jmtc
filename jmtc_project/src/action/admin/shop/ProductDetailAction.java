package action.admin.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import shop.*;

public class ProductDetailAction implements CommandAction{

   @Override
   public String requestPro(HttpServletRequest request,
         HttpServletResponse response) throws Throwable {
      
      //productList.jsp���� ������ �����͸� �ڵ带 ���� �޴´�
      String code=request.getParameter("code");
      System.out.println(code);
      ProductDAO dao=ProductDAO.getInstance();//��ü ���
      ProductDTO dto=dao.getDetail(code);//dao�޼��� ȣ��
     
      //jsp�� ����Ҽ��ֵ��� ���� ����
      request.setAttribute("code", code);
      request.setAttribute("dto", dto);
      
      return "/admin/shop/productDetail.jsp";
   }

}