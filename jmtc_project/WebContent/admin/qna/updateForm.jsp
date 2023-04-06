<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>
<h2 class="txt-center title_bg">Q&A 글 수정</h2>
	
<div class="wrapper left_table">
  <form name="updateForm" method="post" action="${ctxpath}/admin/qna/updatePro.do2?member_idx=${sessionScope.idx}&pageNum=${pageNum}">
  	<table class="wid transparent">
  		<tr>
  			<td>이름</td>
  			<td class="bd_bt">
  				<input type="text" name="qna_name" id="qna_name" value="${dto.qna_name }">
  				<input type="hidden" name="qna_idx" value="${dto.qna_idx }">
  			</td>
  		</tr>
  		
  		<tr>
  			<td>글제목</td>
  			<td class="bd_bt"><input type="text" name="qna_title" id="qna_title" value="${dto.qna_title }"></td>  			
  		</tr>
  		<tr>
  			<td>글내용</td>
  			<td class="bd_bt"><textarea name="qna_content" id="qna_content" value="${dto.qna_content}" rows="10" cols="45">${dto.qna_content}</textarea></td>  			
  		</tr>
  		<tr>
  			<td>암호</td>
  			<td class="bd_bt"><input type="password" name="qna_pw" id="qna_pw"></td>  			
  		</tr>
  		<tr>
  			<td colspan="2" align="center">
	  			<input type="submit" value="글수정" class="pointer_btn mt40">
	  			<input type="button" value="리스트" onClick="location='${ctxpath}/admin/qna/list.do2?member_idx=${sessionScope.idx}&pageNum=${pageNum}'" class="default_btn mt40">
  			</td>
  		</tr>
  	</table>
  </form>
</div>