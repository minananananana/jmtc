package action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import mysql.*;//DTO,DAO

public class DeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//deleteForm.jsp보내준 값 받기
		int board_idx=Integer.parseInt(request.getParameter("board_idx"));
		String pageNum=request.getParameter("pageNum");
		String board_pw=request.getParameter("board_pw");
		
		BoardDAO dao=BoardDAO.getInstance();//dao객체얻기
		int x=dao.deleteArticle(board_idx, board_pw);//dao메서드 호출
		
		//x==1 삭제성공
		//x==-1 암호틀림
		request.setAttribute("x", x);
		request.setAttribute("pageNum", pageNum);
		
		return "/board/deletePro.jsp";
	}//requestPro()-end

}//class-end
