<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>
<%--writePro.jsp --%>
글쓰기 성공
<br>
DB가서 확인
<meta http-equiv="Refresh" content="0;${ctxpath}/board/list.do?member_idx=${sessionScope.idx}">