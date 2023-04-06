package action.qna;
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
				int member_idx=Integer.parseInt(request.getParameter("member_idx"));
				
				if(request.getParameter("qna_idx")!=null){//답글이면 
					qna_idx=Integer.parseInt(request.getParameter("qna_idx"));
					qna_ref=Integer.parseInt(request.getParameter("qna_ref"));
					qna_ordNo=Integer.parseInt(request.getParameter("qna_ordNo"));
					qna_levelNo=Integer.parseInt(request.getParameter("qna_levelNo"));
									
				}
				//JSP(view)사용할 속성 설정
				request.setAttribute("qna_idx", qna_idx);
				request.setAttribute("qna_ref", qna_ref);
				request.setAttribute("qna_ordNo", qna_ordNo);
				request.setAttribute("qna_levelNo", qna_levelNo);
				
		
		return "/qna/writeForm.jsp";
	}//requestPro() out

}//class out
