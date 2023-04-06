package action.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.NoticeDAO;
import command.CommandAction;

import java.util.*;

public class ListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		int member_idx=Integer.parseInt(request.getParameter("member_idx"));
		String pageNum=request.getParameter("pageNum");
		String keyField="";
		String keyWord="";
		
		//寃��깋
		if(request.getParameter("keyField")!=null && request.getParameter("keyWord")!=null){
			keyField=request.getParameter("keyField");
			keyWord=request.getParameter("keyWord");
		}
	      if(pageNum==null){
	         pageNum="1";
	      }

	      int currentPage=Integer.parseInt(pageNum);
	      int pageSize=3;
	      
	      int startRow=(currentPage-1)*pageSize+1;
	      int endRow=currentPage*pageSize;
	      
	      int count=0;
	      int number=0;
	      int pageBlock=3;
	      
	      List list=null;
	      List list2=null;
	      
	      NoticeDAO dao = new NoticeDAO();
	      count=dao.noticeCount();
	      
	      if(count>0){
	    	 list2=dao.getImpoNotice();
	         list=dao.getNotice(startRow, pageSize, keyField, keyWord);
	      }else{
	    	 list2=Collections.EMPTY_LIST;
	         list=Collections.EMPTY_LIST;
	      }
	      
	      number=count-(currentPage-1)*pageSize;
	      int pageCount=count/pageSize+(count%pageSize==0?0:1);
	      
	      int startPage=(int)(currentPage/pageBlock)*3+1;

	      pageBlock=3;

	       if(currentPage%pageBlock==0 && currentPage>=pageBlock){
	            startPage=currentPage-2;
	                             //9-9
	                             //10-9=1
	                             //20-9=11
	                             //30-9=21
	       }//if-end
	       
	      int endPage=startPage+pageBlock-1;
	      
	      request.setAttribute("startPage",new Integer(startPage));
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
	      request.setAttribute("list2", list2);
	      //System.out.println(list2);
		return "/notice/list.jsp";
	}

}//class-end
