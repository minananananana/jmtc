package action.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class WriteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		
		//답글 원글 처리
		int review_idx=0;
		int review_ref=1;
		int review_ordNo=0;
		int review_levelNo=0;
		
		int member_idx=Integer.parseInt(request.getParameter("member_idx"));
	
		String pageNum=request.getParameter("pageNum");
		//writeForm.do?review_idx=8&pageNum=1&ref=1&review_ordNo=0&review_levelNo=0
				
		if(request.getParameter("review_idx") != null){//답글이면 
			review_idx=Integer.parseInt(request.getParameter("review_idx"));
			review_ref=Integer.parseInt(request.getParameter("review_ref"));
			review_ordNo=Integer.parseInt(request.getParameter("review_ordNo"));
			review_levelNo=Integer.parseInt(request.getParameter("review_levelNo"));
			
		}
		
		//JSP(view)에서 사용할 속성 설정
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("review_idx", review_idx);
		request.setAttribute("review_ref", review_ref);
		request.setAttribute("review_ordNo", review_ordNo);
		request.setAttribute("review_levelNo", review_levelNo);

		return "/review/writeForm.jsp";
	}

}//class-end
