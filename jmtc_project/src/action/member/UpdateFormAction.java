package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import member.*;
public class UpdateFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		String member_id = request.getParameter("member_id");
		int member_idx = Integer.parseInt(request.getParameter("member_idx"));
		
		System.out.println(member_idx);
		System.out.println(member_id);
		
		MemberDAO dao = MemberDAO.getInstance();
		
		MemberDTO dto = dao.getMember(member_idx);
		
		request.setAttribute("dto", dto);
		
		return "/member/updateForm.jsp";
	}

}
