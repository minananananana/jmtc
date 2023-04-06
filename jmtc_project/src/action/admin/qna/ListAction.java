package action.admin.qna;
import command.CommandAction;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.*;//DAO,DTO

//여기서 DAO를 호출하여 결과를 받는다
public class ListAction implements CommandAction{

	   @Override
	   public String requestPro(HttpServletRequest request,
	         HttpServletResponse response) throws Throwable {
	      
	      Integer member_idx=Integer.parseInt(request.getParameter("member_idx"));
	      request.setCharacterEncoding("UTF-8");
	      String keyField="";
	      String keyWord="";
	     
	      //검색
	      if(request.getParameter("keyField")!=null && request.getParameter("keyWord")!=null){
	      keyField=request.getParameter("keyField");
	      keyWord=request.getParameter("keyWord");
	      }
	 
	      if(member_idx == null){
	         member_idx = 0;
	      }
	      
	      String pageNum=request.getParameter("pageNum");
	      
	      if(pageNum==null){
	         pageNum="1";
	      }
	      
	      int currentPage=Integer.parseInt(pageNum);
	      int pageSize=10;
	      
	      int startRow=(currentPage-1)*pageSize+1;//페이지의 시작 row를 구한다
	      int endRow=currentPage*pageSize;//페이지의 끝 행
	      
	      int count=0;//총 글 갯수 넣을 변수
	      int number=0;//글번호 처리
	      int pageBlock=10;
	      
	      
	      List list=null;//DAO가 넘겨준 데이터 받을 변수
	     
	      
	      
	      QnaDAO dao=QnaDAO.getInstance();//dao객체 얻기
	     
	      if(request.getParameter("keyField")!=null && request.getParameter("keyWord")!=null){
	          count=dao.getFindCount(keyField, keyWord);//검색한 글의 총 갯수
	       }else{
	          count=dao.getCount();//총 글갯수
	       }
	      
	      if(count>0){//글이 존재 하면s
	         list=dao.getList(startRow, pageSize, member_idx, keyField, keyWord);
	      }else{//글이 없으면
	         list=Collections.EMPTY_LIST;
	      }
	      
	      number=count-(currentPage-1)*pageSize;//출력할 글번호
	      int pageCount=count/pageSize+(count%pageSize==0?0:1);
	      
	      int startPage=(int)(currentPage/pageBlock)*10+1;

	      pageBlock=10;
	      
	      //10,20,30,50 , 마지막 페이지의 에러 방지
	       if(currentPage%pageBlock==0 && currentPage>=pageBlock){
	            startPage=currentPage-9;
	                             //9-9
	                             //10-9=1
	                             //20-9=11
	                             //30-9=21
	       }//if-end
	       
	      int endPage=startPage+pageBlock-1;
	      
	      //JSP에서 사용할 속성 설정
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
	      request.setAttribute("member_idx",member_idx);

	
		return "/admin/qna/list.jsp";
	}

}
