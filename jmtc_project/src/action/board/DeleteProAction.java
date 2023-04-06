package action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import mysql.*;//DTO,DAO

public class DeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//deleteForm.jsp������ �� �ޱ�
		int board_idx=Integer.parseInt(request.getParameter("board_idx"));
		String pageNum=request.getParameter("pageNum");
		String board_pw=request.getParameter("board_pw");
		
		BoardDAO dao=BoardDAO.getInstance();//dao��ü���
		int x=dao.deleteArticle(board_idx, board_pw);//dao�޼��� ȣ��
		
		//x==1 ��������
		//x==-1 ��ȣƲ��
		request.setAttribute("x", x);
		request.setAttribute("pageNum", pageNum);
		
		return "/board/deletePro.jsp";
	}//requestPro()-end

}//class-end
