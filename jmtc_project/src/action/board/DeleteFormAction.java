package action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class DeleteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//content.jsp ������ �ذ� �ޱ�
		int board_idx=Integer.parseInt(request.getParameter("board_idx"));
		String pageNum=request.getParameter("pageNum");
		
		//JSP���� ����� �� ����
		request.setAttribute("board_idx", board_idx);
		request.setAttribute("pageNum", pageNum);
		
		return "/board/deleteForm.jsp";//view����
	}//requestPro()-end

}//class-end
