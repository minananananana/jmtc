package action.admin.qna;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import qna.*;

public class ContentAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//qna_idx=16&pageNum=1&qna_ref=16&qna_ordNo=0&qna_levelNo=0&member_idx=6

		//list.jsp보내준 데이터 받고
		int qna_idx=Integer.parseInt(request.getParameter("qna_idx"));
		int qna_ref=Integer.parseInt(request.getParameter("qna_ref"));
		int qna_ordNo=Integer.parseInt(request.getParameter("qna_ordNo"));
		int qna_levelNo=Integer.parseInt(request.getParameter("qna_levelNo"));
		
		String pageNum=request.getParameter("pageNum");
		
		QnaDAO dao=QnaDAO.getInstance();//dao객체얻기
		QnaDTO dto=dao.getBoard(qna_idx);//메서드호출
		
		//jsp(view)사용할 값 설정
		request.setAttribute("qna_idx", qna_idx);
		request.setAttribute("qna_ref", qna_ref);
		request.setAttribute("qna_ordNo", qna_ordNo);
		request.setAttribute("qna_levelNo", qna_levelNo);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("dto", dto);
		
		return "/admin/qna/content.jsp";
	}//requestPro() out

}//class out
