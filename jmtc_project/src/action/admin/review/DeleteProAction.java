package action.admin.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import review.*;
public class DeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		

	      //deleteForm.jsp������ �� �ޱ�
	      int review_idx=Integer.parseInt(request.getParameter("review_idx"));
	      String pageNum=request.getParameter("pageNum");
	      
	      
	     ReviewDAO dao=ReviewDAO.getInstance();//dao��ü���
	      dao.deleteReview(review_idx);//dao�޼��� ȣ��
	      
	      
	      request.setAttribute("pageNum", pageNum);
	      

		return "/admin/review/deletePro.jsp";
	}

}//class-end
