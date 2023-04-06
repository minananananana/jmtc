<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>
<%
session.getAttribute("idx");
session.getAttribute("id");
%>

<header>
	<nav class="flex-center">
		<div class="menu_wrap flex-align">
			<ul class="menu flex">
				<li><a href="${ctxpath}/shop/productList.do">메뉴</a></li>
				<c:if test="${!empty sessionScope.idx}">
				<li><a href="${ctxpath}/board/list.do?member_idx=${sessionScope.idx}">자유게시판</a></li>
				</c:if>
				<c:if test="${empty sessionScope.idx}">
				<li><a href="${ctxpath}/board/list.do?member_idx=0">자유게시판</a></li>
				</c:if>
				
				<c:if test="${!empty sessionScope.idx}">
				<li><a href="${ctxpath}/review/list.do?member_idx=${sessionScope.idx}">후기글</a></li>
				</c:if>
				<c:if test="${empty sessionScope.idx}">
				<li><a href="${ctxpath}/review/list.do?member_idx=0">후기글</a></li>
				</c:if>
			</ul>	
			<div class="logo">
				<a href="${ctxpath}/template/template.jsp"><img src="${ctxpath}/images/jmtc_logo.png" alt="존맛탱치킨"/></a>
			</div>
			<ul class="menu flex">
				<c:if test="${!empty sessionScope.idx}">
				<li><a href="${ctxpath}/notice/list.do?member_idx=${sessionScope.idx}">공지사항</a></li>
				</c:if>
				<c:if test="${empty sessionScope.idx}">
				<li><a href="${ctxpath}/notice/list.do?member_idx=0">공지사항</a></li>
				</c:if>
				
				<c:if test="${!empty sessionScope.idx}">
				<li><a href="${ctxpath}/qna/list.do?member_idx=${sessionScope.idx}">Q&A</a></li>
				</c:if>
				<c:if test="${empty sessionScope.idx}">
				<li><a href="${ctxpath}/qna/list.do?member_idx=0">Q&A</a></li>
				</c:if>
				
				<c:if test="${!empty sessionScope.idx && sessionScope.id != 'admin'}">
				<li><a href="${ctxpath}/cart/cartList.do?idx=${sessionScope.idx}">장바구니</a></li>
				</c:if>
			</ul>
		</div>
		<ul class="user flex">
			<c:if test="${!empty sessionScope.idx}">
				<li class="point_c">${sessionScope.id}님</li>
				<li><a href="${ctxpath}/member/logOut.do">로그아웃</a></li>
				<c:if test="${sessionScope.id != 'admin'}">
				<li><a href="${ctxpath}/member/modify.do">마이페이지</a></li>
				</c:if>
				<%-- <c:if test="${sessionScope.id == 'admin'}">
				<li><a href="${ctxpath}/shop/productAdd.do">메뉴등록</a></li>
				</c:if> --%>
			</c:if>
			
			<c:if test="${empty sessionScope.idx}">
				<li><a href="${ctxpath}/member/loginForm.do">로그인</a></li>
				<li><a href="${ctxpath}/member/inputForm.do">회원가입</a></li>
			</c:if>
			
		</ul>
	</nav>
</header>