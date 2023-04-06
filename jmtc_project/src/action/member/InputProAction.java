package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import member.*;//DTO,DAO
public class InputProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		request.setCharacterEncoding("UTF-8");//ù��° �ٿ� 
		 
		//inputForm.jsp�Ѱ��� �ڷḦ dto��� , dto�� dao�޼���� �Ѱ��ش� 
		MemberDTO dto=new MemberDTO();
		
		dto.setMember_id(request.getParameter("member_id"));
		dto.setMember_pw(request.getParameter("member_pw"));
		dto.setMember_name(request.getParameter("member_name"));
		dto.setMember_nick(request.getParameter("member_nick"));
		dto.setMember_email(request.getParameter("member_email"));
		dto.setMember_tel(request.getParameter("member_tel"));
		dto.setMember_zipcode(request.getParameter("member_zipcode"));
		dto.setMember_addr(request.getParameter("member_addr"));
		dto.setMember_addr_detail(request.getParameter("member_addr_detail"));
		
		MemberDAO dao=MemberDAO.getInstance();//dao��ü ��� 
		dao.insertMember(dto);//dao�޼��� ȣ�� 
		
		//String id=request.getParameter("id");
		//request.setAttribute("id", id);
		
		return "/member/inputPro.jsp";//�� ���� 
	}//requestPro()-end

}//class-end
