<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../header/header.jsp" %>

<h2 class="txt-center title_bg">주문현황</h2>

<div class="wrapper notice">
	<h3>메뉴별 판매현황</h3>
	<table>
		<tr>
			<th>메뉴명</th>
			<th>개수</th>
		</tr>
		
		<c:if test="${list != null}">
		<c:forEach var="dto" items="${list}">
		<tr>
			<td>${dto.order_name}</td>
			<td>${dto.order_quantity}</td>
		</tr>
		</c:forEach>
		</c:if>
	</table>
	
	<h3>베스트 멤버</h3>
	<div></div>
	
	<h3>총 매출</h3>
	<div></div>
</div>