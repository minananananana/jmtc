<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>
<%--updatePro.jsp --%>

<c:if test="${result == 'success'}">
	성공
	<meta http-equiv="Refresh" content="0;url=${ctxpath}/template/template.jsp">
</c:if>
<c:if test="${result == 'fail'}">
	실패
</c:if>
