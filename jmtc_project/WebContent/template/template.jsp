<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>
	<body>
		<jsp:include page="/module/top.jsp" flush="false"/>
		
		<c:if test="${CONTENT != null}">
		<section class="container">
		<jsp:include page="${CONTENT}" flush="false"/>
		</section>
		</c:if>
		
		<c:if test="${CONTENT == null}">
		<section class="container main">
			<div class="main_img_wrap">
			</div>
		</section>
		</c:if>

		<jsp:include page="/module/bottom.jsp" flush="false"/>
	 
