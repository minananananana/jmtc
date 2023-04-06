package action.admin.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import command.CommandAction;
import review.*;
public class UpdateFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");//updateForm���� ������ �ѱ� ���ڵ� ó��
		
		//content.jsp ������ ���� �ޱ�
		int review_idx=Integer.parseInt(request.getParameter("review_idx"));
		String pageNum=request.getParameter("pageNum");
		
		//dao��ü �� ,num�� �ش��ϴ� ���� ��´�
		ReviewDAO dao=ReviewDAO.getInstance();
		ReviewDTO dto=dao.getUpdate(review_idx);
		
		//JSP���� ����� �� ����
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("dto", dto);
		
		
		return "/admin/review/updateForm.jsp";
	}

}//class-end
