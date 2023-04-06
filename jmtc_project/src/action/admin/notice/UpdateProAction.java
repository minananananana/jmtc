package action.admin.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.NoticeDAO;
import notice.NoticeDTO;
import command.CommandAction;

public class UpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		int notice_idx = Integer.parseInt(request.getParameter("notice_idx"));
		String pageNum = request.getParameter("pageNum");
		String result = "";
		
		NoticeDTO dto = new NoticeDTO();
		
		dto.setNotice_idx(notice_idx);
		dto.setNotice_title(request.getParameter("notice_title"));
		dto.setNotice_content(request.getParameter("notice_content"));
		
		NoticeDAO dao = new NoticeDAO();
		
		result = dao.updateNotice(dto);
		
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("result", result);
		
		return "/admin/notice/updatePro.jsp";
	}

}//class-end
