package action.admin.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class WriteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		//원글일 때
		int ref = 0;
		int ordNo = 0;
		int levelNo = 0;
		//int member_idx=Integer.parseInt(request.getParameter("member_idx"));
		String notice_impo_YN[] = request.getParameterValues("notice_impo_YN");
		
		
		request.setAttribute("ref", ref);
		request.setAttribute("ordNo", ordNo);
		request.setAttribute("levelNo", levelNo);
		
		//request.setAttribute("notice_impo_YN", notice_impo_YN);
		
		return "/admin/notice/writeForm.jsp";
	}

}//class-end
