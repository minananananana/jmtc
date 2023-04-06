<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>

  <h2 class="txt-center title_bg">마이페이지</h2>
  
  <div class="modify_wrap">
	  <table>
	    <tr>
	      <td>
	        <form name="update" method="post" action="${ctxpath}/member/updateForm.do" class="myinfo">
	          <input type="hidden" name="member_id" value="${sessionScope.id}">
	          <input type="hidden" name="member_idx" value="${sessionScope.idx}">
	          
	          <input type="submit" value="내정보수정">
	        </form>
	      </td>
	      
	      <td>
	        <form name="delForm" method="post" action="${ctxpath}/member/deleteForm.do" class="mydrop">
	          <input type="hidden" name="member_id" value="${sessionScope.id}">
	          <input type="hidden" name="member_idx" value="${sessionScope.idx}">
	
	          <input type="submit" value="회원탈퇴">
	        </form>
	      </td>
	      <td>
	      	<form name="delForm" method="post" action="${ctxpath}/order/orderList.do" class="myorder">
	      		<input type="hidden" name="member_id" value="${sessionScope.id}">
	          	<input type="hidden" name="idx" value="${sessionScope.idx}">
	          	<input type="submit" value="주문리스트">
	      	</form>
	      </td>
	    </tr>
	  </table>
  </div>