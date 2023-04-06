<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/header/header.jsp" %>
  <h2 class="txt-center title_bg">후기 글 수정</h2>

  <div class="wrapper left_table">
  	<form name="updateForm" method="post" action="${ctxpath}/review/updatePro.do?member_idx=${sessionScope.idx}&pageNum=${pageNum}">
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
	        <td>이름</td>
	        <td class="bd_bt">
	        	<input type="text" name="review_id" id="review_id" value="${dto.review_id}">
	        	<input type="hidden" name="review_idx" value="${dto.review_idx}">
	        </td>
	      </tr>
	     
	      <tr>
	        <td>제목</td>
	        <td class="bd_bt"><input type="text" name="review_title" id="review_title" value="${dto.review_title}"></td>
	      </tr>
	      
	      <tr>
	        <td>내용</td>
	        <td class="bd_bt">
	        	<textarea name="review_content" id="review_content" rows="10" cols="45">${dto.review_content}</textarea>
	        </td>
	      </tr>
	      
	     
	      
	      <tr>
	        <td colspan="2" align="center">
	          <input type="submit" value="글수정" class="pointer_btn mt40">
	          <input type="button" value="리스트" onClick="location='${ctxpath}/review/list.do?member_idx=${sessionScope.idx}&pageNum=${pageNum}'" class="default_btn mt40">
	        </td>
	      </tr>
	      
	    </table>
  	</form>
  </div>