<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>
<%--main.jsp --%>
  <%--로그인을 안했을때--%>
  
  <c:if test="${empty sessionScope.id}">
    <table width="100%" border="0">
      <tr>
        <td align="right">
          <a href="${ctxpath}/member/loginForm.do">로그인</a>
          <a href="${ctxpath}/member/inputForm.do">회원가입</a>
        </td>
      </tr>
    </table>
  </c:if>
  <%--로그인을 했을때--%>
  
  <c:if test="${!empty sessionScope.id}">
    <table width="100%" border="0">
      <tr>
        <td align="right">
          ${sessionScope.id}님 방문을 환영합니다.&nbsp;&nbsp;
          
          <form method="post" action="${ctxpath}/member/logOut.do">
            <input type="submit" value="로그아웃">
            <input type="button" value="내정보수정" onClick="location.href='${ctxpath}/member/modify.do'">            
          </form>
          
        </td>
      </tr>
    </table>
  </c:if>