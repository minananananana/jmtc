package action.admin.qna;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.QnaDAO;
import qna.QnaDTO;
import command.CommandAction;

public class UpdateProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		//updateForm.jsp 보내준 데이터 받기
		String pageNum=request.getParameter("pageNum");
		QnaDTO dto=new QnaDTO();
		
		dto.setQna_idx(Integer.parseInt(request.getParameter("qna_idx")));
		dto.setQna_name(request.getParameter("qna_name"));
		dto.setQna_title(request.getParameter("qna_title"));
		dto.setQna_content(request.getParameter("qna_content"));
		dto.setQna_pw(request.getParameter("qna_pw"));
		
				QnaDAO dao=QnaDAO.getInstance();
				
				int x=dao.updateBoard(dto);
				
				request.setAttribute("x", x);
				request.setAttribute("pageNum", pageNum);
				
		return "/admin/qna/updatePro.jsp";
	}//requestPro() out
}//class out
