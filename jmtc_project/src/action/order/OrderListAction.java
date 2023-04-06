package action.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.CartDAO;
import qna.QnaDAO;
import order.*;
import command.CommandAction;

import java.util.*;

public class OrderListAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		  //request.setCharacterEncoding("utf-8");
		
		  int idx = Integer.parseInt(request.getParameter("idx"));
	      
	      String pageNum=request.getParameter("pageNum");
	      
	      if(pageNum==null){
	         pageNum="1";
	      }
	      
	      int currentPage=Integer.parseInt(pageNum);
	      int pageSize=10;
	      
	      int startRow=(currentPage-1)*pageSize+1;//�������� ���� �� ���ϱ�
	      int endRow=currentPage*pageSize;//�������� �� ��
	      
	      int count=0;//�� �� ���� ���� ����
	      int number=0;//�۹�ȣ ó��
	      int pageBlock=10;
	      
	      List list=null;//DAO�� �Ѱ��� ������ ���� ����
	      
	      OrderDAO dao=new OrderDAO();
	      count=dao.getCount(idx);//�� �۰���
	      
	      if(count>0){//���� �����ϸ�
	         list=dao.orderList(idx,startRow, pageSize);
	      }else{//���� ������
	         list=Collections.EMPTY_LIST;
	      }
	      
	      number=1;//����� �۹�ȣ
	      int pageCount=count/pageSize+(count%pageSize==0?0:1);
	      
	      int startPage=currentPage%pageBlock==0?currentPage-9:(int)(currentPage/10)*10+1;
	      
	      int endPage=startPage+pageBlock-1;
	      
	      
	      //JSP���� ����� �Ӽ� ����
	      request.setAttribute("startPage", new Integer(startPage));
	      request.setAttribute("endPage", endPage);
	      request.setAttribute("currentPage", currentPage);
	      
	      request.setAttribute("startRow", startRow);
	      request.setAttribute("endRow", endRow);
	      
	      request.setAttribute("pageBlock", pageBlock);
	      request.setAttribute("pageCount", pageCount);
	      
	      request.setAttribute("count", count);
	      request.setAttribute("pageSize", pageSize);
	      request.setAttribute("number", number);
	      
	      request.setAttribute("list", list);
	      
	      return "/order/orderList.jsp";

	}//requestPro()-end

}//class-end