<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp"%>  

<%
	request.setCharacterEncoding("utf-8");
%>  

  <h2 class="txt-center title_bg">주문리스트</h2>
  
  <div class="order_list_wrap wrapper">
  <c:if test="${count==0}">
    <table class="empty_tbl">
      <tr>
        <th>주문번호</th>
        <th>상품명</th>
        <th>주문수량</th>
        <th>주문날짜</th>
        <th>주문상태</th>
      </tr>
      <tr>
      	<td colspan="5" align="center">구매한 상품이 없습니다</td>
      </tr>
    </table>
  </c:if>
  
  <c:if test="${count>0}"><%--글이 있으면 --%>
    
    <table class="jmtc_tbl">

      <tr>
        <th>주문번호</th>
        <th>상품명</th>
        <th>주문수량</th>
        <th>주문날짜</th>
        <th>주문상태</th> 
      </tr>
      
      <c:forEach var="dto" items="${list}">
        <tr class="content">
          <td>
          ${number}
          <c:set var="number" value="${number+1}"/>
          </td>
          
          <td>${dto.order_name}</td>
          <td>${dto.order_quantity}</td>
          <td><fmt:formatDate value="${dto.order_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
          <td>${dto.order_state}</td>
        </tr>
      </c:forEach>
    </table>
  </c:if>
  
  <%--블럭처리, 페이지 처리 --%>
  <table class="wid">
    <tr>
      <td align="center">
        <c:if test="${count>0}"><%--글이 존재하면 --%>
          
          <%--에러방지--%>
          <c:if test="${endPage>pageCount}">
            <c:set var="endPage" value="${pageCount}"/>
          </c:if>
          
          <%--이전블럭 --%>
          <c:if test="${startPage>10}">
            <a href="${ctxpath}/order/orderList.do?pageNum=${startPage-10}">
              &lt;&lt;
            </a>
          </c:if>
          
          <%--페이지처리 --%>
          <c:forEach var="i" begin="${startPage}" end="${endPage}">
            <a href="${ctxpath}/order/orderList.do?pageNum=${i}">
              [${i}]
            </a>
          </c:forEach>
          
          <%--다음블럭 --%>
          <c:if test="${endPage<pageCount}">
            <a href="${ctxpath}/order/orderList.do?pageNum=${startPage+10}">
            &gt;&gt;
            </a>
          </c:if>
          
        </c:if>
      </td>
    </tr>
  </table>
</div>