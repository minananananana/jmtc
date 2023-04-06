<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/admin/header/header.jsp" %>
<%
	session.getAttribute("idx");
	session.getAttribute("id");
%>

<header>	
	<div class="flex-between-center admin_top">
		<a href="${ctxpath}/admin/template/template.jsp">JMTChicken</a>
		<ul class="flex">
			<c:if test="${!empty sessionScope.id}">
			<li>${sessionScope.id}님</li>
			<li><a href="${ctxpath}/admin/member/logOut.do2">로그아웃</a></li>
			</c:if>
		</ul>
	</div>
</header>