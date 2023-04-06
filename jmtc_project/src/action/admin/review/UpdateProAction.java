package action.admin.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






import command.CommandAction;
import review.*;
public class UpdateProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");//writeForm.jsp에서 보내준 한글 인코딩 처리
		String pageNum=request.getParameter("pageNum");
		ReviewDTO dto=new ReviewDTO();
		String review_score = request.getParameter("star");
		
		//보내준 값을 dto에 넣기
		dto.setReview_idx(Integer.parseInt(request.getParameter("review_idx")));
		dto.setReview_score(Integer.parseInt(review_score));
		dto.setReview_id(request.getParameter("review_id"));
		dto.setReview_title(request.getParameter("review_title"));
		dto.setReview_content(request.getParameter("review_content"));
		
		//dto를 dao로 넘겨준다
		ReviewDAO dao=ReviewDAO.getInstance();
		dao.updateReview(dto);
		//x=1  : 정상수정
		//x=-1 : 암호틀림
		
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("dao", dao);
		
	return "/admin/review/updatePro.jsp";//뷰 리턴 
}//requestPro()-end

}//class-end
