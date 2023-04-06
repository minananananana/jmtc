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
		
		//content.jsp���� ������ ���� 
		int qna_idx=Integer.parseInt(request.getParameter("qna_idx"));
		String pageNum=request.getParameter("pageNum");
		
		QnaDAO dao=QnaDAO.getInstance();//dao��ü���
		QnaDTO dto=dao.getUpdate(qna_idx);//num�� �ش��ϴ� ���� ����ȭ�鿡 �ѷ�����Ѵ�
		
		//JSP����� �� ����
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("dto", dto);
		
		
		
		return "/admin/qna/updateForm.jsp";//view ����
		
	}//requestPro()out
}//class out
