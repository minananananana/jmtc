package action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mysql.BoardDAO;
import command.CommandAction;
import mysql.*;
public class UpdateFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//content.jsp 보내준 내용 받기
		int board_idx=Integer.parseInt(request.getParameter("board_idx"));
		String pageNum=request.getParameter("pageNum");
		
		BoardDAO dao=BoardDAO.getInstance();//dao객체얻기
		BoardDTO dto=dao.getUpdate(board_idx);//dao메서드호출
		
		//JSP에서 사용할 값 설정
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("dto", dto);
		
		return "/board/updateForm.jsp";//view리턴
	
	}//requestPro()-end

}//class-end
