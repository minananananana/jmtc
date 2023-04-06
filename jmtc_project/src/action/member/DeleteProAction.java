package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import member.*;
public class DeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		String member_id=request.getParameter("member_id");
		String member_pw=request.getParameter("member_pw");
		
		MemberDAO dao=MemberDAO.getInstance();
		int x=dao.deleteMember(member_id, member_pw); //dao�޼��� ȣ��
		
		//jsp���� ����� �Ӽ� ���� 
		 request.setAttribute("x", x);  //x=1 �������� x=-1��ȣƲ�� 
		
		return "/member/deletePro.jsp";
	}

}
