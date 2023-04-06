<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>    

	<script>
	  function pwcheck(){
		  if(document.delForm.board_pw.value==""){
			  alert("암호는 필수 입력");
			  document.delForm.board_pw.focus();
			  return false;
		  }//if
		  return true;
	  }
	</script>
	
	<h2 class="txt-center title_bg">자유게시판 글삭제</h2>
	
	<div class="wrapper left_table">
		<form name="delForm" method="post" action="${ctxpath}/board/deletePro.do?member_idx=${sessionScope.idx }&pageNum=${pageNum}" onSubmit="return pwcheck()">
		  <table>
		    <tr>
		      <td colspan="2">
		        <h4>암호를 입력 하세요</h4>
		      </td>
		    </tr>
		    
		    <tr>
		      <td>암호</td>
		      <td class="bd_bt">
		      <input type="password" name="board_pw" id="board_pw" size="12">
		      <input type="hidden" name="board_idx" value="${board_idx}">
		      </td>
		    </tr>
		    
		    <tr>
		      <td colspan="2" align="center">
		        <input type="submit" value="글삭제" class="pointer_btn mt40">
		        <input type="button" value="리스트" onClick="location.href='${ctxpath}/board/list.do?member_idx=${sessionScope.idx }&pageNum=${pageNum}'" class="default_btn mt40">
		      </td>
		    </tr>
		  </table>	
		</form>
	</div>