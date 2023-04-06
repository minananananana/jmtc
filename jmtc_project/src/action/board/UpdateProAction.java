package action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import mysql.*;
public class UpdateProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		//updateForm.jsp 보내준 데이터 받기
		String pageNum=request.getParameter("pageNum");
		
		BoardDTO dto=new BoardDTO();
		
		dto.setBoard_idx(Integer.parseInt(request.getParameter("board_idx")));
		dto.setBoard_name(request.getParameter("board_name"));
		dto.setBoard_title(request.getParameter("board_title"));
		dto.setBoard_content(request.getParameter("board_content"));
		dto.setBoard_pw(request.getParameter("board_pw"));
		
		BoardDAO dao=BoardDAO.getInstance();//dao객체생성
		int x=dao.updateBoard(dto);//dao메서드 호출
		//x=1;//정상 수정
		//x=-1;//암호 틀림
		
		request.setAttribute("x", x);
		request.setAttribute("pageNum", pageNum);
		
		return "/board/updatePro.jsp";//view리턴
	
	}//requestPro()-end

}//class-end
