package action.admin.qna;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
public class DeleteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//content.jsp 데이터 준것 받기
		int qna_idx=Integer.parseInt(request.getParameter("qna_idx"));
		String pageNum=request.getParameter("pageNum");
		
		//jsp에서	 사용할 값 설정
		request.setAttribute("qna_idx", qna_idx);
		request.setAttribute("pageNum", pageNum);
		
		return "/admin/qna/deleteForm.jsp";
	}

}
