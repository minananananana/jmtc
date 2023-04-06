package action.admin.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.NoticeDAO;
import command.CommandAction;

public class deleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int notice_idx = Integer.parseInt(request.getParameter("notice_idx"));
		String pageNum = request.getParameter("pageNum");
		
		NoticeDAO dao = new NoticeDAO();
		String result = dao.deleteNotice(notice_idx);
		
		request.setAttribute("result", result);
		request.setAttribute("pageNum", pageNum);
		
		return "/admin/notice/deletePro.jsp";
	}

}
