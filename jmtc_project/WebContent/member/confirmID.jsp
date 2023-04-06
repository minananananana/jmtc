<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="member.*"
    %>
<%--confirmID.jsp --%>
<%
String member_id=request.getParameter("member_id");//Ajax에서 넘겨준 데이터 받기
MemberDAO dao=MemberDAO.getInstance();//dao객체얻기
int x=dao.confirmID(member_id);//dao메서드호출, 결과 받고
%>
{
"x":<%=x %>
}