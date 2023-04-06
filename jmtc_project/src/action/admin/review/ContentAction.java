package action.admin.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import command.CommandAction;
import review.*;
public class ContentAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//list.jsp 보내준 데이터 받고
		//int member_idx=Integer.parseInt(request.getParameter("member_idx"));
		int review_idx=Integer.parseInt(request.getParameter("review_idx"));
		String pageNum=request.getParameter("pageNum");
		
		
		//dao객체 얻어서, num에 해당한 글을 얻어온다
		ReviewDAO dao=ReviewDAO.getInstance();
		ReviewDTO dto=dao.getReview(review_idx);
		//jsp(view=front-end)사용할 값 설정
		//request.setAttribute("review_idx", review_idx);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("dto", dto);
		
		
		return "/admin/review/content.jsp";
	}

}
