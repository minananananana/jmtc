package action.admin.qna;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class WriteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		//답글,원글 처리
				int qna_idx=0;
				int qna_ref=1;
				int qna_ordNo=0;
				int qna_levelNo=0;
				
				
				if(request.getParameter("qna_ref")!=null){//답글이면 
					qna_ref=Integer.parseInt(request.getParameter("qna_ref"));
					qna_ordNo=Integer.parseInt(request.getParameter("qna_ordNo"));
					qna_levelNo=Integer.parseInt(request.getParameter("qna_levelNo"));				
				}
				
				if(request.getParameter("qna_idx")!=null){
					qna_idx=Integer.parseInt(request.getParameter("qna_idx"));
				}
				
				//JSP(view)사용할 속성 설정
				request.setAttribute("qna_idx", qna_idx);
				request.setAttribute("qna_ref", qna_ref);
				request.setAttribute("qna_ordNo", qna_ordNo);
				request.setAttribute("qna_levelNo", qna_levelNo);
				
		
		return "/admin/qna/writeForm.jsp";
	}//requestPro() out

}//class out
