<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>

<form method="post" action="/admin/shop/productDeletePro.do2">
	<input type="hidden" name="product_code" value="${dto.product.idx}">
</form>