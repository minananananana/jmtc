package action.admin.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






import command.CommandAction;
import review.*;
public class UpdateProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");//writeForm.jsp���� ������ �ѱ� ���ڵ� ó��
		String pageNum=request.getParameter("pageNum");
		ReviewDTO dto=new ReviewDTO();
		String review_score = request.getParameter("star");
		
		//������ ���� dto�� �ֱ�
		dto.setReview_idx(Integer.parseInt(request.getParameter("review_idx")));
		dto.setReview_score(Integer.parseInt(review_score));
		dto.setReview_id(request.getParameter("review_id"));
		dto.setReview_title(request.getParameter("review_title"));
		dto.setReview_content(request.getParameter("review_content"));
		
		//dto�� dao�� �Ѱ��ش�
		ReviewDAO dao=ReviewDAO.getInstance();
		dao.updateReview(dto);
		//x=1  : �������
		//x=-1 : ��ȣƲ��
		
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("dao", dao);
		
	return "/admin/review/updatePro.jsp";//�� ���� 
}//requestPro()-end

}//class-end
