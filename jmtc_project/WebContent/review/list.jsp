<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>

<c:if test="${sessionScope.idx == '0'}">
<div class="wrapper">
	로그인 하신 후 서비스를 이용할 수 있습니다.	
</div>
</c:if>

 <h2 class="txt-center title_bg">후기글</h2>
 
 <div class="wrapper">
	 
	 <div class="sub_txt flex-between-center">
		전체글:${count}
		
		  <div class="flex">
			  <%--글 검색 --%>
			  <form name="searchForm" method="post" action="list.do?member_idx=${sessionScope.idx}" onSubmit="return check()" class="flex mr20">
		          <select name="keyField" class="mr10">
		            <option value="review_title">글제목</option>
		            <option value="review_id">이름</option>
		            <option value="review_content">글내용</option>
		          </select>
		          
		          <input type="text" name="keyWord" id="keyWord" size="16" class="keyword_input mr10">
		          <input type="hidden" name="page" value="0">
		          <input type="submit" value="검색" class="default_btn">
			  </form>
		  
			<button type="button" onclick="location.href='${ctxpath}/review/writeForm.do?member_idx=${sessionScope.idx}'" class="default_btn mb10">글쓰기</button>
	    </div>
	 </div>
	 
	<c:if test="${count==0}">
	 <table class="empty_tbl">
	    <tr>
	      <th>번호</th>
	       <th>상품정보</th>
	       <th>작성자</th>
	       <th>작성일</th>
	       <th>별점</th>
	       <th>조회수</th>
	    </tr>
	    <tr>
	    	<td colspan="5" align="center">등록된 글이 없습니다</td>
	    </tr>
	  </table>
	 </c:if>
	
	 <c:if test="${count>0}">
	  
	  <table class="jmtc_tbl">
		  <tr>
		    <th>번호</th>
	       <th>상품정보</th>
	       <th>작성자</th>
	       <th>작성일</th>
	       <th>별점</th>
	       <th>조회수</th>
		   
		  </tr>
	  
	  <c:forEach var="dto" items="${list}">
	   <tr>
	   <td>
	   <c:out value="${number}"/>
	   <c:set var="number" value="${number-1}"/>
	   </td>
	   
	   
	   <td>
	  
	   <!-- 글제목을 클릭하면 글 내용보기로 가기 -->
	   <a href="${ctxpath}/review/content.do?review_idx=${dto.review_idx}&pageNum=${currentPage}">
	     ${dto.review_title}
	   </a>
	   
	   
	   </td>
	   
	   <td>${dto.review_id}</td>
	   <td><fmt:formatDate value="${dto.review_date}" pattern="yyyy-MM-dd"/></td>
	   <td>
	   <c:forEach var="i" begin="1" end="${dto.review_score}">
	    <img src="${ctxpath }/images/star_on.png" class="star"/>
	   </c:forEach>
	   <c:forEach var="i" begin="1" end="${5 - dto.review_score}">
	    <img src="${ctxpath }/images/star_off.png" class="star"/>
	   </c:forEach>
	   </td>
	   <td>${dto.review_readcount}</td>
	   
	   </tr>
	  </c:forEach>
	  </table>
	</c:if>
	
	 <%--블럭처리, 페이지 처리 --%>
	 <c:if test="${count>0}">
	<div class="pagination">
		<c:if test="${endPage>pageCount}">
		  <c:set var="endPage" value="${pageCount}"/>
		</c:if>
		<ul>
			<c:if test="${startPage>3}">
			<li class="page_btn">
				<a href="${ctxpath}/review/list.do?member_idx=${sessionScope.idx }&pageNum=${startPage-3}&keyField=${keyField}&keyWord=${keyWord}">이전블럭</a>
			</li>
			</c:if>
			
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
			
			<c:choose>
			<c:when test="${currentPage == i}">
			<li class="page_btn">
	          <a href="${ctxpath}/review/list.do?member_idx=${sessionScope.idx}&pageNum=${i}" class="active">
	          ${i}
	          </a>
	        </li>
	        </c:when>
	        
	        <c:otherwise>
	        <li class="page_btn">
	          <a href="${ctxpath}/review/list.do?member_idx=${sessionScope.idx}&pageNum=${i}">
	          ${i}
	          </a>
	       	</li>
	       	</c:otherwise>
	       	</c:choose>
	       	
	       	</c:forEach>
	       
	       	<c:if test="${endPage<pageCount}">
	       	<li class="page_btn">
	          <a href="${ctxpath}/review/list.do?member_idx=${sessionScope.idx }&pageNum=${startPage+3}&keyField=${keyField}&keyWord=${keyWord}">
	          	다음블럭
	          </a>
	       	</li>
	       	</c:if>
		</ul>
	</div>
	</c:if>
  
</div>