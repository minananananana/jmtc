package controller;
import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.*;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import command.CommandAction;

//컨트롤러 (서블릿, 자바 문법)
public class ControllerDispatcher extends HttpServlet {
	
	private Map map = new HashMap();
	
	//init(): 초기화 작업
	public void init(ServletConfig config) throws ServletException {
		
		String path = config.getServletContext().getRealPath("/");		//실제 경로 받기 C:부터 ~~ /10_MVC까지
		
		// WEB-INF/command.properties
		String pros = path + config.getInitParameter("proFile");		// pros = ~/10_MVC/WEB-INF/command.properties
		Properties pp = new Properties();
		FileInputStream ff = null;
		
		try{
			
			ff = new FileInputStream(pros);		//파일읽기
			pp.load(ff);						//스트림으로 열린 Properties 파일 객체를 로드함 / load() 메소드를 통해 파일 정보를 넣어주는 역할
			
		}catch(Exception ex){
			System.out.println("파일 읽기 에러 : "+ex);
		}
		
		//iterator는 Enumeration과 Collection(vector, list, properties) 를 통합하여 받아낼 수 있는 기능을 가지고 있다.
		Iterator keyIter = pp.keySet().iterator();		//Iterator 모든 컬렉션 타입 다 받을 수 있음.  command.properties의 key값들 꺼내서 keyIter에 넣기
		
		while(keyIter.hasNext()){
			String key = (String)keyIter.next();		//key를 하나씩 읽어냄   //command.properties의 key값들
			String className = pp.getProperty(key);		//command.properties의 value값들 = action.board.ListAction
			// /board/list.do=action.board.ListAction
			// className = action.board.ListAction 가 된다.
			
			try{
				
				Class commandClass = Class.forName(className);		//클래스로 만듦		//클래스의 이름을 매개변수로 받아서 Class 객체를 리턴하는 메서드
				Object commandObject = commandClass.newInstance();	//클래스 객체 생성 	//여기서  action.board.ListAction객체 생성
				
				map.put(key, commandObject);			//board/list.do(key)  ,  action.board.ListAction(value)
				
			}catch(Exception ex){
				System.out.println("properties 파일 내용을 클래스로 만들던 중 예외발생 : "+ex);
			}
			
		}//while-end
		
	}//init()-end
	
	
	//웹 브라우저 요청시 get,post
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		reqPro(request,response);	//메서드 호출
		
	}//doGet()-end
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		reqPro(request,response);	//메서드 호출
		
	}//doPost()-end
	
	//사용자 정의 메서드
	private void reqPro(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String view = "";
		CommandAction commandAction = null;		//상위 클래스 변수로 하위 객체 처리 / CommandAction=Interface
		
		try{
			
			String uri = request.getRequestURI();	//context 패스 = 프로젝트 이름
			
			//request.getRequestURI() : /02_jsp/ch04_innerObject/03_request.jsp
			//request.ContextPath() : /02_jsp
			if(uri.indexOf(request.getContextPath()) == 0){
				uri = uri.substring(request.getContextPath().length());		//   /board/list.do
			}//if-end
			
			//commandAction = (CommandAction)map.get(key);
			commandAction = (CommandAction)map.get(uri);			//action.board.ListAction객체를 commandAciton에 넣기
			
			view = commandAction.requestPro(request, response);			//CommandAction.java의 메서드 호출 (추상)		// 객체.메서드 = /board/list.jsp
			
		}catch(Throwable ex){
			throw new ServletException(ex);		//throw는 예외를 강제로 발생시킨 후, 상위 블럭이나 catch문으로 예외를 던진다.
		}
		
		request.setAttribute("CONTENT", view);
		
		RequestDispatcher rd = request.getRequestDispatcher("/template/template.jsp");
		rd.forward(request, response);		//jsp로 포워딩
		
	}//reqPro()-end
	
}//class-end
