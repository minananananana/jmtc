<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>

<c:if test="${x==1}">
	<meta http-equiv="Refresh" content="0;${ctxpath}/admin/qna/list.do2?member_idx=${sessionScope.idx}&pageNum=${pageNum}">
</c:if>

<c:if test="${x==-1}">
	<script>
		alert("암호가 틀립니다")
		history.back();
	</script>
</c:if>