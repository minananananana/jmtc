package action.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.NoticeDAO;
import notice.NoticeDTO;
import command.CommandAction;

public class ContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		String pageNum = request.getParameter("pageNum");
		
		NoticeDAO dao = new NoticeDAO();
		NoticeDTO dto = dao.getContent(idx);
		dao.readCount(idx);
		
		request.setAttribute("idx", idx);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("dto", dto);
		
		return "/notice/content.jsp";
	}

}
