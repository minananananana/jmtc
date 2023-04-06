package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.*;
import command.CommandAction;

public class UpdateProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		//updtaform.jsp에서 보내준 데어터를 dto에e 담고 dto를 dao메서트를 호출해서 dao로 보낸다 
		
		
		String member_id=request.getParameter("member_id");
		int member_idx = Integer.parseInt(request.getParameter("member_idx"));
		
		MemberDTO dto=new MemberDTO();
		
		dto.setMember_idx(member_idx);
		dto.setMember_id(member_id);
		dto.setMember_pw(request.getParameter("member_pw"));
		dto.setMember_name(request.getParameter("member_name"));
		dto.setMember_nick(request.getParameter("member_nick"));
		dto.setMember_email(request.getParameter("member_email"));
		dto.setMember_tel(request.getParameter("member_tel"));
		dto.setMember_zipcode(request.getParameter("member_zipcode"));
		dto.setMember_addr(request.getParameter("member_addr"));
		dto.setMember_addr_detail(request.getParameter("member_addr_detail"));
		
		MemberDAO dao=MemberDAO.getInstance(); 
		String result = dao.updateMember(dto);
		
		request.setAttribute("result", result);
		
		return "/member/updatePro.jsp"; //뷰리턴
	}

}
