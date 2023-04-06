<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>

<h2 class="txt-center title_bg">장바구니 리스트</h2>

<div class="wrapper">
	<form method="post" action="${ctxpath}/order/orderPro.do">
		<table class="jmtc_tbl">
			<tr>
				<th>이미지</th>
				<th>번호</th>
				<th>코드</th>
				<th>이름</th>
				<th>날짜</th>
				<th>가격</th>
				<th>수량</th>
				<th></th>
			</tr>
			<c:forEach var="dto" items="${list}">
			<tr>
				<td class="cart_thumb"><img src="${ctxpath}/images/${dto.cart_image}"/></td>
				<td>${dto.cart_idx}</td>
				<td>${dto.cart_code}</td>
				<td>${dto.cart_name}</td>
				<td>${dto.cart_orddate}</td>
				<td>${dto.cart_price}</td>
				<td>${dto.cart_quantity}</td>
				<td><a href="${ctxpath}/cart/deletePro.do?item=${dto.cart_idx}">삭제</a></td>
			</tr>
			<input type="hidden" name="item" value="${dto.cart_idx}">
			<input type="hidden" name="idx" value="${sessionScope.idx}">
			</c:forEach>
			<tr>
				<td colspan="9" align="center"><input type="submit" value="주문하기" class="pointer_btn"></td>
			</tr>
		</table>
	</form>
</div>
