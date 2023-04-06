<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"    %>
<%@ include file="/header/header.jsp" %>

<h2 class="txt-center title_bg">Q&A</h2>

<div class="wrapper">

	<div class="sub_txt flex-between-center">
		전체글:${count}
		  <div class="flex">
			  <%--글 검색 --%>
			  <form name="searchForm" method="post" action="list.do?member_idx=${sessionScope.idx}" onSubmit="return check()" class="flex mr20">
		          <select name="keyField" class="mr10">
		            <option value="qna_title">글제목</option>
		            <option value="qna_name">작성자</option>
		            <option value="qna_content">글내용</option>
		          </select>
		          
		          <input type="text" name="keyWord" id="keyWord" size="16" class="keyword_input mr10">
		          <input type="hidden" name="page" value="0">
		          <input type="submit" value="검색" class="default_btn">
			  </form>
		  
			<%--<button type="button" onclick="location.href='${ctxpath}/admin/qna/writeForm.do2?member_idx=${sessionScope.idx}'" class="default_btn mb10">글쓰기</button> --%>
	    </div>
	 </div>
	 
	<c:if test="${count==0 }">
 	
	<table class="empty_tbl">
     <tr>
        <th>번호</th>
		<th>글제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조횟수</th>
     </tr>
     <tr>
     	<td colspan="5" align="center"> 등록된 글이 없습니다</td>
     </tr>
   </table>
	</c:if>
 
 	<c:if test="${count>0 }">
 	<table class="jmtc_tbl">
  		<tr> 
  			<th>번호</th>
  			<th>글제목</th>
  			<th>작성자</th>
  			<th>작성일</th>
  			<th>조횟수</th>
  			
  		</tr>
		<c:forEach var="list" items="${list}">
		 
		       
		    <tr>
			<td>
				<c:out value="${number }"/>
				<c:set var="number" value="${number-1 }"/>
			</td>
			
			<td>
				<!-- 답글 -->
				<c:if test="${list.qna_levelNo>0 }">
					<img src="${ctxpath }/images/level.gif" width="${5*list.qna_levelNo }" height="16">
					<img src="${ctxpath }/images/re.gif" width="${5*list.qna_levelNo }" height="16">
				</c:if>
				
				<!-- 원글 -->
				<c:if test="${list.qna_levelNo==0 }">
					<img src="${ctxpath }/images/level.gif" width="${5*list.qna_levelNo }" height="16">
				</c:if>
	            <!-- 글내용을 클릭하면 글 내용보기로 가기 -->
				<a href="${ctxpath }/admin/qna/content.do2?qna_idx=${list.qna_idx}&pageNum=${currentPage}&qna_ref=${list.qna_ref}&qna_ordNo=${list.qna_ordNo}&qna_levelNo=${list.qna_levelNo}&member_idx=${sessionScope.idx}">
					${list.qna_title }
				</a>
				
				<!-- 조회수 10번이상이면 got.gif표시 -->
				<c:if test="${list.qna_readcount>=10 }">
					<img src="${ctxpath }/images/hot.gif">
				</c:if>
			
			</td>
			
			
			<td>${list.qna_name }</td>
			<td><fmt:formatDate value="${list.qna_date}" pattern="yyyy-MM-dd"/></td>
			<td>${list.qna_readcount }</td>
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
				<c:if test="${startPage>10}">
				<li class="page_btn">
					<a href="${ctxpath}/admin/qna/list.do2?pageNum=${startPage-10}&member_idx=${sessionScope.idx }">이전블럭</a>
				</li>
				</c:if>
				
				<c:forEach var="i" begin="${startPage}" end="${endPage}">
				
				<c:choose>
				<c:when test="${currentPage == i}">
				<li class="page_btn">
		          <a href="${ctxpath}/admin/qna/list.do2?pageNum=${i}&member_idx=${sessionScope.idx }" class="active">
		          ${i}
		          </a>
		        </li>
		        </c:when>
		        
		        <c:otherwise>
		        <li class="page_btn">
		          <a href="${ctxpath}/admin/qna/list.do2?pageNum=${i}&member_idx=${sessionScope.idx }">
		          ${i}
		          </a>
		       	</li>
		       	</c:otherwise>
		       	</c:choose>
		       	
		       	</c:forEach>
		       
		       	<c:if test="${endPage<pageCount}">
		       	<li class="page_btn">
		          <a href="${ctxpath}/admin/qna/list.do2?pageNum=${startPage+10}&member_idx=${sessionScope.idx }">
		          	다음블럭
		          </a>
		       	</li>
		       	</c:if>
			</ul>
		</div>
	</c:if>
</div>