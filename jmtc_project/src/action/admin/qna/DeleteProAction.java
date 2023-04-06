package action.admin.qna;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.QnaDAO;
import command.CommandAction;

public class DeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//deleteForm.jsp보내준 값 받기
		int qna_idx=Integer.parseInt(request.getParameter("qna_idx"));
		String pageNum=request.getParameter("pageNum");
		String qna_pw=request.getParameter("qna_pw");
		
		QnaDAO dao=QnaDAO.getInstance();//dao 객체 얻기
		int x=dao.deleteQnA(qna_idx, qna_pw);//dao메서드 호출
		
		request.setAttribute("x", x);
		request.setAttribute("pageNum", pageNum);
		
		return "/admin/qna/deletePro.jsp";
	}

}
