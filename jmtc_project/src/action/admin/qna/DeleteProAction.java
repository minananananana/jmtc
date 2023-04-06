package action.admin.qna;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.QnaDAO;
import command.CommandAction;

public class DeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//deleteForm.jsp������ �� �ޱ�
		int qna_idx=Integer.parseInt(request.getParameter("qna_idx"));
		String pageNum=request.getParameter("pageNum");
		String qna_pw=request.getParameter("qna_pw");
		
		QnaDAO dao=QnaDAO.getInstance();//dao ��ü ���
		int x=dao.deleteQnA(qna_idx, qna_pw);//dao�޼��� ȣ��
		
		request.setAttribute("x", x);
		request.setAttribute("pageNum", pageNum);
		
		return "/admin/qna/deletePro.jsp";
	}

}
