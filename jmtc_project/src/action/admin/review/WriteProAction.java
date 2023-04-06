package action.admin.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import review.*;
public class WriteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");//writeForm.jsp���� ������ �ѱ� ���ڵ� ó��
		
		ReviewDTO dto=new ReviewDTO();
		String a = request.getParameter("review_score");
		//front-end���� ���� �����͸� dto�� ����
		
		String star="";
		
		//�˻�
		
		star=request.getParameter("star");
		
		dto.setReview_score(Integer.parseInt(star));
		dto.setReview_title(request.getParameter("review_title"));
		dto.setReview_id(request.getParameter("review_id"));
		dto.setReview_content(request.getParameter("review_content"));
	    
          
	    dto.setReview_idx(Integer.parseInt(request.getParameter("review_idx")));
		dto.setReview_ref(Integer.parseInt(request.getParameter("review_ref")));
		dto.setReview_ordNo(Integer.parseInt(request.getParameter("review_ordNo")));
		dto.setReview_levelNo(Integer.parseInt(request.getParameter("review_levelNo")));
		
		dto.setMember_idx(Integer.parseInt(request.getParameter("member_idx")));
		
		//dao �޼��� ȣ��
		ReviewDAO dao=ReviewDAO.getInstance();
		dao.insertReview(dto);
		
		return "/admin/review/writePro.jsp";
	}

}//class-end
