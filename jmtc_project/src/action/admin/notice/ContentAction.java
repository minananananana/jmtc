package action.admin.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.NoticeDAO;
import notice.NoticeDTO;
import command.CommandAction;

public class ContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int notice_idx = Integer.parseInt(request.getParameter("notice_idx"));
		//int member_idx = Integer.parseInt(request.getParameter("member_idx"));
		String pageNum = request.getParameter("pageNum");
		
		NoticeDAO dao = new NoticeDAO();
		NoticeDTO dto = dao.getContent(notice_idx);
		dao.readCount(notice_idx);
		
		//request.setAttribute("member_idx", member_idx);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("dto", dto);
		
		return "/admin/notice/content.jsp";
	}

}
