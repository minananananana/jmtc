<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header/header.jsp" %>

<c:if test="${result == 'success'}">
	<meta http-equiv="Refresh" content="0;${ctxpath}/admin/notice/list.do2?member_idx=${sessionScope.idx}&pageNum=${pageNum}">
</c:if>
<c:if test="${result == 'fail'}">
	실패
</c:if>