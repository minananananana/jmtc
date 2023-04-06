<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp"%>

<h2 class="txt-center title_bg">
	<c:if test="${review_idx==0 }">
     	후기 글쓰기
     </c:if>


	<c:if test="${review_idx!=0 }">
     	후기 답글쓰기
     </c:if>
</h2>

<div class="wrapper left_table">
	<form name="writeForm" method="post"
		action="${ctxpath}/review/writePro.do">
		<input type="hidden" name="review_idx" value="${review_idx}">
		<input type="hidden" name="review_ref" value="${review_ref}">
		<input type="hidden" name="review_ordNo" value="${review_ordNo}">
		<input type="hidden" name="review_levelNo" value="${review_levelNo}">
		<input type="hidden" name="member_idx" value="${sessionScope.idx}">

		<table>

			<tr>
				<td>별점</td>

				<td class="bd_bt"><select name="star" class="star">

						<option value="1" selected>1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>

				</select></td>
			</tr>

			<tr>
				<td>제목</td>
				<td class="bd_bt"><c:if test="${review_idx==0}">
						<%--원글 일때 --%>
						<input type="text" name="review_title" id="review_title" size="30">
					</c:if> <c:if test="${review_idx!=0}">
						<%--답글 일때 --%>
						<input type="text" name="review_title" id="review_title" size="30"
							value="┗>[답글]">
					</c:if></td>
			</tr>

			<tr>
				<td>작성자</td>
				<td class="bd_bt"><input type="text" name="review_id"
					id="review_id" size="20"></td>
			</tr>
			
			<tr>
				<td>글내용</td>
				<td class="bd_bt"><textarea name="review_content"
						id="review_content" rows="10" cols="50"></textarea></td>
			</tr>


			<tr>
				<td colspan="2" align="center"><c:if test="${review_idx==0}">
						<%--원글 쓰기--%>
						<input type="submit" value="등록" class="pointer_btn mt40">
					</c:if> <c:if test="${review_idx!=0}">
						<%--답글 쓰기--%>
						<input type="submit" value="답글등록" class="pointer_btn mt40">
					</c:if> <input type="button" value="목록"
					onclick="location.href='${ctxpath}/review/list.do?member_idx=${sessionScope.idx}'"
					class="default_btn mt40"></td>
			</tr>

		</table>
	</form>
</div>