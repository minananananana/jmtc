<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>

<c:if test="${idx != null || idx != 0}">
	<meta http-equiv="Refresh" content="0;url=${ctxpath}/template/template.jsp">
	<%
		session.setAttribute("idx", request.getAttribute("idx"));
		session.setAttribute("id", request.getAttribute("id"));
	%>
</c:if>

<c:if test="${idx == null || idx == 0}">
	<script>
		alert("비밀번호가 틀립니다.");
		history.back();
	</script>
</c:if>
  
