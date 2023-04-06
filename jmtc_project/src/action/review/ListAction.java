package action.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

import java.util.*;

import review.*;//DTO,DAO

//Action���� DAO�޼��带 ȣ���Ͽ� ����� �޴´�
public class ListAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		Integer member_idx=Integer.parseInt(request.getParameter("member_idx"));
		
		String keyField="";
		String keyWord="";
		
		//�˻�
		if(request.getParameter("keyField")!=null && request.getParameter("keyWord")!=null){
		keyField=request.getParameter("keyField");
		keyWord=request.getParameter("keyWord");
		}
		
		if(member_idx == null){
			member_idx = 0;
		}
		
		String pageNum=request.getParameter("pageNum");
		
		if(pageNum==null){
			pageNum="1";
		}
		
		int currentPage=Integer.parseInt(pageNum);
		int pageSize=10;
		
		int startRow=(currentPage-1)*pageSize+1;//�������� ���� row�� ���Ѵ�
		int endRow=currentPage*pageSize;//�������� �� ��
		
		int count=0;//�� �� ���� ���� ����
		int number=0;//�۹�ȣ ó��
		int pageBlock=10;
		
		
		List list=null;//DAO�� �Ѱ��� ������ ���� ����
		
		ReviewDAO dao=ReviewDAO.getInstance();//dao��ü ���
		count=dao.getCount();//�� �� ����
		
		if(count>0){//���� ���� �ϸ�
			list=dao.getList(startRow, pageSize, member_idx, keyField, keyWord);//�ۼ������� DAO�� ������ �����ϰ� �ؾ���
		}else{//���� ������
			list=Collections.EMPTY_LIST;
		}
		
		number=count-(currentPage-1)*pageSize;//����� �۹�ȣ
		int pageCount=count/pageSize+(count%pageSize==0?0:1);
		
		int startPage=(int)(currentPage/pageBlock)*10+1;

		pageBlock=10;
		
		//10,20,30,50 , ������ �������� ���� ����
	    if(currentPage%pageBlock==0 && currentPage>=pageBlock){
	         startPage=currentPage-9;
	                          //9-9
	                          //10-9=1
	                          //20-9=11
	                          //30-9=21
	    }//if-end
	    
		int endPage=startPage+pageBlock-1;
		
		//JSP���� ����� �Ӽ� ����
		request.setAttribute("startPage",new Integer(startPage));
		request.setAttribute("endPage", endPage);
		request.setAttribute("currentPage", currentPage);
		
		request.setAttribute("startRow", startRow);
		request.setAttribute("endRow", endRow);

		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("pageCount", pageCount);
		
		request.setAttribute("count", count);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("number", number);
		

		request.setAttribute("list", list);
		
		return "/review/list.jsp";//view����
	}//requestPro()-end

}//class-end