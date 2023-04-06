package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import member.*;// DTO,DAO
public class LoginProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		String member_id = request.getParameter("member_id");
		String member_pw = request.getParameter("member_pw");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = new MemberDTO();
		
		dto = dao.userCheck(member_id, member_pw);
		
		String id=request.getParameter("member_id");
		String idx = Integer.toString(dto.getMember_idx());
		
		if(idx.equals(null)){
			idx = "0";
		}
		
		request.setAttribute("id", id);
		request.setAttribute("idx", idx);
		request.setAttribute("dto", dto);
		
		return "/member/loginPro.jsp";//∫‰∏Æ≈œ
		
	}//requestPro()-end
}//class-end
