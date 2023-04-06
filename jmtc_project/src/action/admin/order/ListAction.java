package action.admin.order;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import order.OrderDAO;

import command.CommandAction;

public class ListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		  //request.setCharacterEncoding("utf-8");
		
		  //int idx = Integer.parseInt(request.getParameter("member_idx"));
	      
	      String pageNum=request.getParameter("pageNum");
	      
	      if(pageNum==null){
	         pageNum="1";
	      }
	      
	      int currentPage=Integer.parseInt(pageNum);
	      int pageSize=10;
	      
	      int startRow=(currentPage-1)*pageSize+1;//페이지의 시작 행 구하기
	      int endRow=currentPage*pageSize;//페이지의 끝 행
	      
	      int count=0;//총 글 갯수 넣을 변수
	      int number=0;//글번호 처리
	      int pageBlock=10;
	      
	      List list=null;//DAO가 넘겨준 데이터 받을 변수
	      
	      OrderDAO dao=new OrderDAO();
	      
	      if(count>0){//글이 존재하면
	    	  
	         list=dao.menuStatus(startRow, pageSize);	//글 가져오는 메서드
	         
	      }else{//글이 없으면
	         list=Collections.EMPTY_LIST;
	      }
	      
	      number=1;//출력할 글번호
	      int pageCount=count/pageSize+(count%pageSize==0?0:1);
	      
	      int startPage=currentPage%pageBlock==0?currentPage-9:(int)(currentPage/10)*10+1;
	      
	      int endPage=startPage+pageBlock-1;
	      
	      
	      //JSP에서 사용할 속성 설정
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
	      
	      System.out.println(list);
	      
	      return "/order/orderList.jsp";

	}//requestPro()-end

}//class-end