package action.admin.qna;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

import qna.*;

public class WriteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");//writeForm에서 보내준 한글 인코딩
		
		QnaDTO dto=new QnaDTO();
		
		//프론트 엔드에서 보낸 데이터를 dto에 저장
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
		
		QnaDAO dao=QnaDAO.getInstance();//dao객체얻기
		dao.insertBoard(dto);//dao메서드 호출
		
		
		return "/admin/qna/writePro.jsp";
	}//requestPro() out

}//class out
