package action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import mysql.*;//DTO,DAO
public class ContentAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//list.jsp 보내준 데이터 받고
		int board_idx=Integer.parseInt(request.getParameter("board_idx"));
		String pageNum=request.getParameter("pageNum");
		
		BoardDAO dao=BoardDAO.getInstance();//dao객체얻기
		BoardDTO dto=dao.getBoard(board_idx);//dao메서드호출
		
		//jsp(view)사용할 값 설정
		request.setAttribute("board_idx", board_idx);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("dto", dto);
		
		return "/board/content.jsp";//view리턴
	}//requestPro()-end

}//class-end
