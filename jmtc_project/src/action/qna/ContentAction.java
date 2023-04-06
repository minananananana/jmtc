package action.qna;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import qna.*;

public class ContentAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {


		//list.jsp������ ������ �ް�
		int qna_idx=Integer.parseInt(request.getParameter("qna_idx"));
		String pageNum=request.getParameter("pageNum");
		
		QnaDAO dao=QnaDAO.getInstance();//dao��ü���
		QnaDTO dto=dao.getBoard(qna_idx);//�޼���ȣ��
		
		//jsp(view)����� �� ����
		request.setAttribute("qna_idx", qna_idx);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("dto", dto);
		
		return "/qna/content.jsp";
	}//requestPro() out

}//class out
