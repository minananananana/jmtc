package action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import mysql.*;//DTO,DAO

public class WriteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");//writeForm���� ������ �ѱ� ���ڵ�
		
		BoardDTO dto=new BoardDTO();
		//front-end���� �����͸� dto�� ����
		dto.setBoard_idx(Integer.parseInt(request.getParameter("board_idx")));
		dto.setBoard_name(request.getParameter("board_name"));
		dto.setBoard_title(request.getParameter("board_title"));
		dto.setBoard_content(request.getParameter("board_content"));
		dto.setBoard_pw(request.getParameter("board_pw"));
		
		dto.setBoard_ref(Integer.parseInt(request.getParameter("board_ref")));
		dto.setBoard_ordNo(Integer.parseInt(request.getParameter("board_ordNo")));
		dto.setBoard_levelNo(Integer.parseInt(request.getParameter("board_levelNo")));
		
		dto.setBoard_ip(request.getRemoteAddr());//ip
		dto.setMember_idx(Integer.parseInt(request.getParameter("member_idx")));
		
		BoardDAO dao=BoardDAO.getInstance();//dao��ü���
		dao.insertBoard(dto);//dao�޼��� ȣ��
		
		return "/board/writePro.jsp";//�丮��-->properties��
	}//requestPro()-end

}//class-end
