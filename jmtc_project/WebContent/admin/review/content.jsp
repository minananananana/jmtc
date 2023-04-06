<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/admin/header/header.jsp" %>
  <h2 class="txt-center title_bg">후기 글내용</h2>
  
  <div class="wrapper left_table">
	  <table class="transparent">
	    <tr>
	  		<td class="tdcolor">제목</td>
	      	<td class="bd_bt">${dto.review_title}</td>
	  	</tr>
	   <tr>
				<td>별점</td>

				<td>
	   <c:forEach var="i" begin="1" end="${dto.review_score}">
	    <img src="${ctxpath }/images/star_on.png" class="star"/>
	   </c:forEach>
	   <c:forEach var="i" begin="1" end="${5 - dto.review_score}">
	    <img src="${ctxpath }/images/star_off.png" class="star"/>
	   </c:forEach>
	   </td>
			</tr>
	
	    <tr height="300">
	      <td class="tdcolor"width>글내용</td>
	      <td class="bd_bt"><pre>${dto.review_content}</pre></td>
	    </tr>
	    
	    <tr>
	    <td class="tdcolor">작성일</td>
	    <td class="bd_bt"> ${dto.review_date}</td>
	    </tr>
	    
	    <tr>
	      <td colspan="3" align="right">
	        <button type="button" onclick="location.href='${ctxpath}/admin/review/updateForm.do2?member_idx=${sessionScope.idx}&review_idx=${dto.review_idx}&pageNum=${pageNum}'" class="default_btn mt40">글수정</button>
	        <button type="button" onclick="location.href='${ctxpath}/admin/review/deletePro.do2?member_idx=${sessionScope.idx}&review_idx=${dto.review_idx}&pageNum=${pageNum}'" class="default_btn mt40">글삭제</button>
	        <button type="button" onclick="location.href='${ctxpath}/admin/review/writeForm.do2?member_idx=${sessionScope.idx}&review_idx=${dto.review_idx}&pageNum=${pageNum}&review_ref=${dto.review_ref}&review_ordNo=${dto.review_ordNo}&review_levelNo=${dto.review_levelNo}'" class="default_btn mt40">답글쓰기</button>
	        <button type="button" onclick="location.href='${ctxpath}/admin/review/list.do2?member_idx=${sessionScope.idx}&pageNum=${pageNum}'" class="default_btn mt40">리스트</button>
	      </td>
	    </tr>
	  </table>
	</div>