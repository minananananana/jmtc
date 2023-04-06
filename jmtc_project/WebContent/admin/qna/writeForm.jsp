<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>

<h2 class="txt-center title_bg">
	<c:if test="${qna_idx==0 }">
	Q&A 글쓰기
	</c:if>
	
	<c:if test="${qna_idx!=0 }">
	Q&A 답글쓰기
	</c:if>
</h2>

<div class="wrapper left_table">
	<form name="writeForm" method="post" action="${ctxpath }/admin/qna/writePro.do2" onSubmit="return check()">
		<input type="hidden" name="qna_idx" value="${qna_idx}">
		<input type="hidden" name="qna_ref" value="${qna_ref}">
		<input type="hidden" name="qna_ordNo" value="${qna_ordNo}">
		<input type="hidden" name="qna_levelNo" value="${qna_levelNo}">
		<input type="hidden" name="member_idx" value="${sessionScope.idx}"><%--멤버에 있다 --%>
		
		<table class="transparent">
			
			<tr>
				<td>이름</td>
				<td class="bd_bt"><input type="text" name="qna_name" id="qna_name" size="20"></td>
			</tr>
			
			<tr>
				<td>글제목</td>
				<td class="bd_bt">
					<c:if test="${qna_idx==0 }">
						<input type="text" name="qna_title" id="qna_title" size="50">
					</c:if>
					
					<c:if test="${qna_idx!=0 }">
						<input type="text" name="qna_title" id="qna_title" size="50" value="[답글]">
					</c:if>
				</td>
			</tr>
			<tr>
				<td>글내용</td>
				<td class="bd_bt">
					<textarea name="qna_content" id="qna_content" rows="10" cols="50" ></textarea>
				</td>
			</tr>
			<tr>
				<td>암호</td>
				<td class="bd_bt">
					<input type="password" name="qna_pw" id="qna_pw" size="20">
				</td>
			</tr>	
			<tr>	
				<td>공개여부</td>
    			<td align="center" class="radio_wrap">
					<label><input type="radio" name="qna_secret" id="qna_secret" value="1">공개</label>
					<label><input type="radio" name="qna_secret" id="qna_secret" value="0">비공개</label>
				</td>
			<tr>
				<td colspan="2" align="center">
					<%--<c:if test="${qna_idx==0 }">
						<input type="submit" value="글쓰기" class="pointer_btn mt40">
	 				</c:if> --%>
	 				
	 				<input type="submit" value="답글쓰기" class="pointer_btn mt40">
	 				
					<input type="button" value="리스트" onclick="location.href='${ctxpath }/admin/qna/list.do2?member_idx=${sessionScope.idx}'" class="default_btn mt40">
				</td>
				
			</tr>
		</table>
	</form>
</div>
