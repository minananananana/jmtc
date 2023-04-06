package action.qna;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import qna.*;

public class ContentAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {


		//list.jsp보내준 데이터 받고
		int qna_idx=Integer.parseInt(request.getParameter("qna_idx"));
		String pageNum=request.getParameter("pageNum");
		
		QnaDAO dao=QnaDAO.getInstance();//dao객체얻기
		QnaDTO dto=dao.getBoard(qna_idx);//메서드호출
		
		//jsp(view)사용할 값 설정
		request.setAttribute("qna_idx", qna_idx);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("dto", dto);
		
		return "/qna/content.jsp";
	}//requestPro() out

}//class out
