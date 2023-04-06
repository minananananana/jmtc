package action.admin.notice;

import javax.naming.NoInitialContextException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import notice.*;

public class WriteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		String notice_impo_YN = request.getParameter("notice_impo_YN");
		
		NoticeDTO dto = new NoticeDTO();
		
		dto.setNotice_title(request.getParameter("notice_title"));
		dto.setNotice_content(request.getParameter("notice_content"));
		dto.setMember_idx(Integer.parseInt(request.getParameter("member_idx")));
		dto.setNotice_impo_YN(notice_impo_YN);
		
		NoticeDAO dao = new NoticeDAO();
		
		String result = dao.insertNotice(dto);
		
		request.setAttribute("result", result);
		
		return "/admin/notice/writePro.jsp";
	}

}//class-end
