<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>

<c:if test="${result == 'success'}">
	성공
	<meta http-equiv="Refresh" content="0;${ctxpath}/admin/notice/list.do2?member_idx=${sessionScope.idx}">
</c:if>

<c:if test="${result == 'fail'}">
	실패
	<script>
	alert("글작성이 실패했습니다.");
	history.back();
	</script>
</c:if>
