package controller;
import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.*;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import command.CommandAction;

//��Ʈ�ѷ� (����, �ڹ� ����)
public class ControllerDispatcher extends HttpServlet {
	
	private Map map = new HashMap();
	
	//init(): �ʱ�ȭ �۾�
	public void init(ServletConfig config) throws ServletException {
		
		String path = config.getServletContext().getRealPath("/");		//���� ��� �ޱ� C:���� ~~ /10_MVC����
		
		// WEB-INF/command.properties
		String pros = path + config.getInitParameter("proFile");		// pros = ~/10_MVC/WEB-INF/command.properties
		Properties pp = new Properties();
		FileInputStream ff = null;
		
		try{
			
			ff = new FileInputStream(pros);		//�����б�
			pp.load(ff);						//��Ʈ������ ���� Properties ���� ��ü�� �ε��� / load() �޼ҵ带 ���� ���� ������ �־��ִ� ����
			
		}catch(Exception ex){
			System.out.println("���� �б� ���� : "+ex);
		}
		
		//iterator�� Enumeration�� Collection(vector, list, properties) �� �����Ͽ� �޾Ƴ� �� �ִ� ����� ������ �ִ�.
		Iterator keyIter = pp.keySet().iterator();		//Iterator ��� �÷��� Ÿ�� �� ���� �� ����.  command.properties�� key���� ������ keyIter�� �ֱ�
		
		while(keyIter.hasNext()){
			String key = (String)keyIter.next();		//key�� �ϳ��� �о   //command.properties�� key����
			String className = pp.getProperty(key);		//command.properties�� value���� = action.board.ListAction
			// /board/list.do=action.board.ListAction
			// className = action.board.ListAction �� �ȴ�.
			
			try{
				
				Class commandClass = Class.forName(className);		//Ŭ������ ����		//Ŭ������ �̸��� �Ű������� �޾Ƽ� Class ��ü�� �����ϴ� �޼���
				Object commandObject = commandClass.newInstance();	//Ŭ���� ��ü ���� 	//���⼭  action.board.ListAction��ü ����
				
				map.put(key, commandObject);			//board/list.do(key)  ,  action.board.ListAction(value)
				
			}catch(Exception ex){
				System.out.println("properties ���� ������ Ŭ������ ����� �� ���ܹ߻� : "+ex);
			}
			
		}//while-end
		
	}//init()-end
	
	
	//�� ������ ��û�� get,post
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		reqPro(request,response);	//�޼��� ȣ��
		
	}//doGet()-end
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		reqPro(request,response);	//�޼��� ȣ��
		
	}//doPost()-end
	
	//����� ���� �޼���
	private void reqPro(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String view = "";
		CommandAction commandAction = null;		//���� Ŭ���� ������ ���� ��ü ó�� / CommandAction=Interface
		
		try{
			
			String uri = request.getRequestURI();	//context �н� = ������Ʈ �̸�
			
			//request.getRequestURI() : /02_jsp/ch04_innerObject/03_request.jsp
			//request.ContextPath() : /02_jsp
			if(uri.indexOf(request.getContextPath()) == 0){
				uri = uri.substring(request.getContextPath().length());		//   /board/list.do
			}//if-end
			
			//commandAction = (CommandAction)map.get(key);
			commandAction = (CommandAction)map.get(uri);			//action.board.ListAction��ü�� commandAciton�� �ֱ�
			
			view = commandAction.requestPro(request, response);			//CommandAction.java�� �޼��� ȣ�� (�߻�)		// ��ü.�޼��� = /board/list.jsp
			
		}catch(Throwable ex){
			throw new ServletException(ex);		//throw�� ���ܸ� ������ �߻���Ų ��, ���� ���̳� catch������ ���ܸ� ������.
		}
		
		request.setAttribute("CONTENT", view);
		
		RequestDispatcher rd = request.getRequestDispatcher("/template/template.jsp");
		rd.forward(request, response);		//jsp�� ������
		
	}//reqPro()-end
	
}//class-end
