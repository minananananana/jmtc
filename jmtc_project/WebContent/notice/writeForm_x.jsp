<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>

<h2 class="txt-center title_bg">공지사항 글쓰기</h2>

<div class="wrapper left_table">
	<form method="post" action="${ctxpath}/notice/writePro.do">
	  <input type="hidden" name="member_idx" value="${sessionScope.idx}">
	  <input type="hidden" name="member_id" value="${sessionScope.id}">
		<table>
			<tr>
				<td>제목</td>
				<td class="bd_bt"><input type="text" name="notice_title" id="notice_title"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td class="bd_bt"><textarea name="notice_content" id="notice_content"></textarea></td>
			</tr>
			<tr>
				<td>중요도</td>
				<td><label><input type="checkbox" name="notice_impo_YN" value="Y">중요 공지사항</label></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button class="pointer_btn mt40">작성하기</button>
				</td>
			</tr>
			
		</table>
	</form>
</div>
