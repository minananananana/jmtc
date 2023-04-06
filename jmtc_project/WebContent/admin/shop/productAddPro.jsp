<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header/header.jsp" %>

<%--<c:if test="${state == 1}">
	<meta http-equiv="Refresh" content="0;${ctxpath}/admin/shop/productList.do2?member_idx=${sessionScope.idx}">
</c:if>

<c:if test="${state != 1}">
	${result}
</c:if> --%>

<meta http-equiv="Refresh" content="0;${ctxpath}/admin/shop/productList.do2?member_idx=${sessionScope.idx}">