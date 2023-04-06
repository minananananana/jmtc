package action.admin.qna;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

import qna.*;

public class WriteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");//writeForm���� ������ �ѱ� ���ڵ�
		
		QnaDTO dto=new QnaDTO();
		
		//����Ʈ ���忡�� ���� �����͸� dto�� ����
		dto.setQna_idx(Integer.parseInt(request.getParameter("qna_idx")));
		dto.setQna_name(request.getParameter("qna_name"));
		dto.setQna_title(request.getParameter("qna_title"));
		dto.setQna_content(request.getParameter("qna_content"));
		dto.setQna_pw(request.getParameter("qna_pw"));
		
		dto.setQna_ref(Integer.parseInt(request.getParameter("qna_ref")));
		dto.setQna_ordNo(Integer.parseInt(request.getParameter("qna_ordNo")));
		dto.setQna_levelNo(Integer.parseInt(request.getParameter("qna_levelNo")));
		
		dto.setMember_idx(Integer.parseInt(request.getParameter("member_idx")));
		dto.setQna_secret(request.getParameter("qna_secret"));
		
		QnaDAO dao=QnaDAO.getInstance();//dao��ü���
		dao.insertBoard(dto);//dao�޼��� ȣ��
		
		
		return "/admin/qna/writePro.jsp";
	}//requestPro() out

}//class out
