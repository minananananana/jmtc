<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>
<%--updatePro.jsp --%>
  
  <c:if test="${x==1}">
    <meta http-equiv="Refresh" content="0;${ctxpath}/board/list.do?member_idx=${sessionScope.idx }&pageNum=${pageNum}">
  </c:if>
  
  <c:if test="${x==-1}">
    <script>
      alert("암호가 틀립니다");
      history.back();
    </script>
  </c:if>