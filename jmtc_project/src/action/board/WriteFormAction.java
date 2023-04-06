package action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class WriteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		//답글, 원글 처리
		int board_idx=0;
		int board_ref=1;
		int board_ordNo=0;
		int board_levelNo=0;
		
        int member_idx=Integer.parseInt(request.getParameter("member_idx"));
		
		String pageNum=request.getParameter("pageNum");
		//writeForm.do?review_idx=8&pageNum=1&ref=1&review_ordNo=0&review_levelNo=0
		
		if(request.getParameter("board_idx") != null){//답글이면
			board_idx=Integer.parseInt(request.getParameter("board_idx"));
			board_ref=Integer.parseInt(request.getParameter("board_ref"));
			board_ordNo=Integer.parseInt(request.getParameter("board_ordNo"));
			board_levelNo=Integer.parseInt(request.getParameter("board_levelNo"));
		}
		
		//JSP(view)사용할 속성 설정
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("board_idx", board_idx);
		request.setAttribute("board_ref", board_ref);
		request.setAttribute("board_ordNo", board_ordNo);
		request.setAttribute("board_levelNo", board_levelNo);
		
		return "/board/writeForm.jsp";//뷰리턴-->properties로
	}//requestPro()-end

}//class-end
