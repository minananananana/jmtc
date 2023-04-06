<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>

<h2 class="txt-center title_bg">공지사항 글 수정</h2>

<div class="wrapper left_table">
	<form method="post" action="${ctxpath}/admin/notice/updatePro.do2?member_idx=${sessionScope.idx}">
		<table class="transparent">
			<tr>
				<td>제목</td>
				<td class="bd_bt"><input type="text" name="notice_title" value="${dto.notice_title}"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td class="bd_bt">
					<textarea name="notice_content">${dto.notice_content}</textarea>
					<input type="hidden" name="notice_idx" value="${dto.notice_idx}">
					<input type="hidden" name="pageNum" value="${pageNum}">
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<button class="pointer_btn mt40">수정하기</button>
					<button type="button" onclick="${ctxpath}/notice/list.do?member_idx=${sessionScope.idx}" class="default_btn mt40">리스트</button>
				</td>
			</tr>
			
		</table>
</form>
</div>