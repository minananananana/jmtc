package action.admin.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.NoticeDAO;
import notice.NoticeDTO;
import command.CommandAction;

public class UpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int notice_idx = Integer.parseInt(request.getParameter("notice_idx"));
		String pageNum = request.getParameter("pageNum");
		
		NoticeDAO dao = new NoticeDAO();
		NoticeDTO dto = new NoticeDTO();
		
		dto = dao.getContent(notice_idx);
		
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
		
		return "/admin/notice/updateForm.jsp";
	}

}//class-end
