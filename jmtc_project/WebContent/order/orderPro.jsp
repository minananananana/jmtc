<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp"%>   
 
<%
	request.setCharacterEncoding("utf-8");
%>  

<meta http-equiv="Refresh" content="0;url=${ctxpath}/order/orderList.do?idx=${sessionScope.idx}">