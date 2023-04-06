package action.admin.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import command.CommandAction;
import review.*;
public class UpdateFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");//updateForm에서 보내준 한글 인코딩 처리
		
		//content.jsp 보내준 내용 받기
		int review_idx=Integer.parseInt(request.getParameter("review_idx"));
		String pageNum=request.getParameter("pageNum");
		
		//dao객체 얻어서 ,num에 해당하는 글을 얻는다
		ReviewDAO dao=ReviewDAO.getInstance();
		ReviewDTO dto=dao.getUpdate(review_idx);
		
		//JSP에서 사용할 값 설정
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("dto", dto);
		
		
		return "/admin/review/updateForm.jsp";
	}

}//class-end
