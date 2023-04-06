package action.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.NoticeDAO;
import notice.NoticeDTO;
import command.CommandAction;

public class UpdateFormAction_bak implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		String pageNum = request.getParameter("pageNum");
		
		NoticeDAO dao = new NoticeDAO();
		NoticeDTO dto = new NoticeDTO();
		
		dto = dao.getContent(idx);
		
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
		
		return "/notice/updateForm.jsp";
	}

}//class-end
