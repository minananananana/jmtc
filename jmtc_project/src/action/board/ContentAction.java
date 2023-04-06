package action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import mysql.*;//DTO,DAO
public class ContentAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//list.jsp ������ ������ �ް�
		int board_idx=Integer.parseInt(request.getParameter("board_idx"));
		String pageNum=request.getParameter("pageNum");
		
		BoardDAO dao=BoardDAO.getInstance();//dao��ü���
		BoardDTO dto=dao.getBoard(board_idx);//dao�޼���ȣ��
		
		//jsp(view)����� �� ����
		request.setAttribute("board_idx", board_idx);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("dto", dto);
		
		return "/board/content.jsp";//view����
	}//requestPro()-end

}//class-end
