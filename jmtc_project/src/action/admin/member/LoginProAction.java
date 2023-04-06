package action.admin.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberDTO;
import command.CommandAction;

public class LoginProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		String admin_id = request.getParameter("admin_id");
		String admin_pw = request.getParameter("admin_pw");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = new MemberDTO();
		
		dto = dao.userCheck(admin_id, admin_pw);
		
		String id = request.getParameter("admin_id");
		String idx = Integer.toString(dto.getMember_idx());
		
		request.setAttribute("id", id);
		request.setAttribute("idx", idx);
		request.setAttribute("dto", dto);
		
		return "/admin/member/loginPro.jsp";
		
	}

}
