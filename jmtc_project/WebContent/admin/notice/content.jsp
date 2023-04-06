<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header/header.jsp" %>

<h2 class="txt-center title_bg">공지사항 글 내용</h2>

<div class="wrapper left_table">
	<table class="transparent">
		<tr>
			<td>제목</td>
			<td class="bd_bt">${dto.notice_title}</td>
		</tr>
		<tr>
			<td>조회수</td>
			<td class="bd_bt">${dto.notice_readcount}</td>
		</tr>
		<tr>
			<td>작성일</td>
			<td class="bd_bt">${dto.notice_date}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td class="bd_bt">
				<textarea>${dto.notice_content}</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="button" onclick="location.href='${ctxpath}/admin/notice/updateForm.do2?notice_idx=${dto.notice_idx}&pageNum=${pageNum}'" class="pointer_btn mt40">글수정</button>
				<button type="button" onclick="location.href='${ctxpath}/admin/notice/list.do2?member_idx=${sessionScope.idx}&pageNum=${pageNum}'" class="default_btn mt40">리스트</button>
			</td>
		</tr>
	</table>
</div>