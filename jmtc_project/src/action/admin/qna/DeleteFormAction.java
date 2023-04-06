package action.admin.qna;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
public class DeleteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//content.jsp ������ �ذ� �ޱ�
		int qna_idx=Integer.parseInt(request.getParameter("qna_idx"));
		String pageNum=request.getParameter("pageNum");
		
		//jsp����	 ����� �� ����
		request.setAttribute("qna_idx", qna_idx);
		request.setAttribute("pageNum", pageNum);
		
		return "/admin/qna/deleteForm.jsp";
	}

}
