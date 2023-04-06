<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>   

<h2 class="txt-center title_bg">
   <c:if test="${board_idx==0}">
       자유게시판 글쓰기
   </c:if>
   
   <c:if test="${board_idx!=0}">
       자유게시판 답글쓰기
   </c:if>
 </h2>  
   
   <div class="wrapper left_table">
	   <form name="writeForm" method="post" action="${ctxpath}/board/writePro.do" onSubmit="return board_Check()">
	     <input type="hidden" name="board_idx" value="${board_idx}">
	     <input type="hidden" name="board_ref" value="${board_ref}">
	     <input type="hidden" name="board_ordNo" value="${board_ordNo}">
	     <input type="hidden" name="board_levelNo" value="${board_levelNo}">
	     <input type="hidden" name="member_idx" value="${sessionScope.idx}">
	     
	     <table>
	       <tr>
	         <td>이름</td>
	         <td class="bd_bt"><input type="text" name="board_name" id="board_name" size="20"></td>
	       </tr>
	       
	       <tr>
	         <td>글제목</td>
	         <td class="bd_bt">
	         <c:if test="${board_idx==0}">
	           <input type="text" name="board_title" id="board_title" size="50">
	         </c:if>
	         
	         <c:if test="${board_idx!=0}">
	           <input type="text" name="board_title" id="board_title" size="50" value="[답변]">
	         </c:if>
	         </td>
	       </tr>
	       
	       <tr>
	         <td>글내용</td>
	         <td class="bd_bt">
	         <textarea name="board_content" id="board_content" rows="10" cols="50"></textarea>
	         </td>
	       </tr>
	       
	       <tr>
	         <td>암호</td>
	         <td class="bd_bt"><input type="password" name="board_pw" id="board_pw" size="20"></td>
	       </tr>
	       
	       <tr>
	         <td colspan="2" align="center">
	           <c:if test="${board_idx==0}">
	             <input type="submit" value="글쓰기" class="pointer_btn mt40">
	           </c:if>
	           
	           <c:if test="${board_idx!=0}">
	             <input type="submit" value="답글쓰기" class="pointer_btn mt40">
	           </c:if>
	           <input type="button" value="리스트" onclick="location.href='${ctxpath}/board/list.do?member_idx=${sessionScope.idx}'" class="default_btn mt40">
	       </tr>
	     </table>
	   </form>
   </div>