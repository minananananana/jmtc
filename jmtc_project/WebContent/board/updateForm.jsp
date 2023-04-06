<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>

   <script>
     function pwcheck(){
   	  if(document.updateForm.board_pw.value==""){
   		  alert("암호는 필수 입력");
   		  document.updateForm.board_pw.focus();
   		  return false;
   	  }//if
   	  return true;
     }
   </script>

  <h2 class="txt-center title_bg">자유게시판 글 수정</h2>
  <div class="wrapper left_table">
	  <form name="updateForm" method="post" action="${ctxpath}/board/updatePro.do?member_idx=${sessionScope.idx }&pageNum=${pageNum}" onSubmit="return pwcheck()">
	    <table>
	      <tr>
	        <td>이름</td>
	        <td class="bd_bt">
		        <input type="text" name="board_name" id="board_name" value="${dto.board_name}">
		        <input type="hidden" name="board_idx" value="${dto.board_idx}">
	        </td>
	      </tr>
	      
	      <tr>
	        <td>글제목</td>
	        <td class="bd_bt"><input type="text" name="board_title" id="board_title" value="${dto.board_title}"></td>
	      </tr>
	      
	      <tr>
	        <td>글내용</td>
	        <td class="bd_bt">
	        	<textarea name="board_content" id="board_content" rows="10" cols="45">${dto.board_content}</textarea>
	        </td>
	      </tr>
	      
	      <tr>
	        <td>암호</td>
	        <td class="bd_bt"><input type="password" name="board_pw" id="board_pw"></td>
	      </tr>
	      
	      <tr>
	        <td colspan="2" align="center">
	          <input type="submit" value="글수정" class="pointer_btn mt40">
	          <input type="button" value="리스트" onClick="location='${ctxpath}/board/list.do?member_idx=${sessionScope.idx }&pageNum=${pageNum}'" class="default_btn mt40">
	        </td>
	      </tr>
	    </table>
	  </form>
  </div>