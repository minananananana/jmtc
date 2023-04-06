package action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class DeleteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//content.jsp 데이터 준것 받기
		int board_idx=Integer.parseInt(request.getParameter("board_idx"));
		String pageNum=request.getParameter("pageNum");
		
		//JSP에서 사용할 값 설정
		request.setAttribute("board_idx", board_idx);
		request.setAttribute("pageNum", pageNum);
		
		return "/board/deleteForm.jsp";//view리턴
	}//requestPro()-end

}//class-end
