<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%--productList.jsp --%>

<%
request.setCharacterEncoding("utf-8");
%>
	<h2 class="txt-center title_bg">메뉴리스트</h2>
	<div class="wrapper product_list_wrap">
	<c:if test="${list==null}">
      ${"상품이 없습니다"}
    </c:if>

	<c:if test="${list!=null}">
		<table class="transparent">
	    	<tr>
				<td class="flex-wrap">
				<c:forEach var="dto" items="${list}">
				
				<table class="item transparent">
					<tr>
						<td align="center" class="thumb_img">
							<a href="${ctxpath}/admin/shop/productDetail.do2?code=${dto.product_code}">
								<img src="${ctxpath}/images/${dto.product_image}" width="150" height="150">
							</a>
						</td>
					</tr>
					<tr>
						<td align="center" class="flex-center"><span class="pd_name">${dto.product_name}</span></td>
					</tr>
					<tr>
						<td align="center">${dto.product_price}</td>
					</tr>
				</table>
				</c:forEach>
				</td>
			</tr>
		</table>
	</c:if>
	</div>