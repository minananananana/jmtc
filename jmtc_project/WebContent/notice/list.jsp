<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>

<h2 class="txt-center title_bg">공지사항</h2>

<div class="wrapper notice">
	<div class="sub_txt flex-between-center">
		전체글:${count}
		
		<div class="flex">
		  <%--글 검색 --%>
		  <form name="searchForm" method="post" action="list.do?member_idx=${sessionScope.idx}" onSubmit="return check()" class="flex mr20">
	          <select name="keyField" class="mr10">
	            <option value="notice_title">글제목</option>
	            <option value="m.member_nick">이름</option>
	            <option value="notice_content">글내용</option>
	          </select>
	          
	          <input type="text" name="keyWord" id="keyWord" size="16" class="keyword_input mr10">
	          <input type="hidden" name="page" value="0">
	          <input type="submit" value="검색" class="default_btn">
		  </form>
		</div>
		
	</div>
	<table class="jmtc_tbl">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>날짜</th>
			<th>작성자</th>
			<th>조회</th>		
		</tr>
		
		<c:if test="${list2 != null}">
		<c:forEach var="dto" items="${list2}">
		<tr class="impo">
			<td class="num txt-center">${dto.notice_idx}</td>
			<td class="title"><a href="${ctxpath}/notice/content.do?idx=${dto.notice_idx}&pageNum=${currentPage}">${dto.notice_title}</a></td>
			<td class="date txt-center">${dto.notice_date}</td>
			<td class="txt-center">${dto.member_nick}</td>
			<td class="readcount txt-center">${dto.notice_readcount}</td>
		</tr>
		</c:forEach>
		</c:if>
		
		
		<c:if test="${list != null}">
		<c:forEach var="dto" items="${list}">
		<tr>
			<td class="num txt-center">${dto.notice_idx}</td>
			<td class="title"><a href="${ctxpath}/notice/content.do?idx=${dto.notice_idx}&pageNum=${currentPage}">${dto.notice_title}</a></td>
			<td class="date txt-center">${dto.notice_date}</td>
			<td class="txt-center">${dto.member_nick}</td>
			<td class="readcount txt-center">${dto.notice_readcount}</td>
		</tr>
		</c:forEach>
		
		</c:if>
		
		<c:if test="${list == null && list2 == null}">
		<tr>
			<td colspan="4">공지사항이 없습니다.</td>
		</tr>
		</c:if>
	</table>
	
	<c:if test="${count>0}">
	<div class="pagination">
		<c:if test="${endPage>pageCount}">
		  <c:set var="endPage" value="${pageCount}"/>
		</c:if>
		<ul>
			<c:if test="${startPage>3}">
			<li class="page_btn">
				<a href="${ctxpath}/notice/list.do?member_idx=${sessionScope.idx }&pageNum=${startPage-3}&keyField=${keyField}&keyWord=${keyWord}">이전블럭</a>
			</li>
			</c:if>
			
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
			
			<c:choose>
			<c:when test="${currentPage == i}">
			<li class="page_btn">
	          <a href="${ctxpath}/notice/list.do?member_idx=${sessionScope.idx}&pageNum=${i}" class="active">
	          ${i}
	          </a>
	        </li>
	        </c:when>
	        
	        <c:otherwise>
	        <li class="page_btn">
	          <a href="${ctxpath}/notice/list.do?member_idx=${sessionScope.idx}&pageNum=${i}">
	          ${i}
	          </a>
	       	</li>
	       	</c:otherwise>
	       	</c:choose>
	       	
	       	</c:forEach>
	       
	       	<c:if test="${endPage<pageCount}">
	       	<li class="page_btn">
	          <a href="${ctxpath}/notice/list.do?member_idx=${sessionScope.idx }&pageNum=${startPage+3}&keyField=${keyField}&keyWord=${keyWord}">
	          	다음블럭
	          </a>
	       	</li>
	       	</c:if>
		</ul>
	</div>
	</c:if>
</div>