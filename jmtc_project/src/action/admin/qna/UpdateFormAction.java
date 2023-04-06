package action.admin.qna;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.QnaDAO;
import qna.QnaDTO;
import command.CommandAction;

public class UpdateFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//content.jsp에서 보내준 내용 
		int qna_idx=Integer.parseInt(request.getParameter("qna_idx"));
		String pageNum=request.getParameter("pageNum");
		
		QnaDAO dao=QnaDAO.getInstance();//dao객체얻기
		QnaDTO dto=dao.getUpdate(qna_idx);//num에 해당하는 값을 수정화면에 뿌려줘야한다
		
		//JSP사용할 값 설정
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("dto", dto);
		
		
		
		return "/admin/qna/updateForm.jsp";//view 리턴
		
	}//requestPro()out
}//class out
