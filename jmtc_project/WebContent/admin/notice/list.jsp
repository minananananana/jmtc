<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/admin/header/header.jsp" %>

<h2 class="txt-center title_bg">공지사항</h2>

<div class="wrapper notice">
	<p class="sub_txt flex-between-center">
		전체글:${count}
		<button type="button" onclick="location.href='${ctxpath}/admin/notice/writeForm.do2?member_idx=${sessionScope.idx}'" class="default_btn mb10">글쓰기</button>
	</p>
	<table class="jmtc_tbl">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>날짜</th>
			<th>작성자</th>
			<th>조회</th>	
			<th>비고</th>	
		</tr>
		
		<c:if test="${list2 != null}">
		<c:forEach var="dto" items="${list2}">
		<tr class="impo">
			<td class="num txt-center">${dto.notice_idx}</td>
			<td class="title"><a href="${ctxpath}/admin/notice/content.do2?notice_idx=${dto.notice_idx}&pageNum=${currentPage}">${dto.notice_title}</a></td>
			<td class="date txt-center">${dto.notice_date}</td>
			<td class="txt-center">${dto.member_nick}</td>
			<td class="readcount txt-center">${dto.notice_readcount}</td>
			<td onclick="location.href='${ctxpath}/admin/notice/deletePro.do2?notice_idx=${dto.notice_idx}&pageNum=${currentPage}'">삭제</td>
		</tr>
		</c:forEach>
		</c:if>
		
		<c:if test="${list != null}">
		<c:forEach var="dto" items="${list}">
		<tr>
			<td class="num txt-center">${dto.notice_idx}</td>
			<td class="title"><a href="${ctxpath}/admin/notice/content.do2?notice_idx=${dto.notice_idx}&pageNum=${currentPage}">${dto.notice_title}</a></td>
			<td class="date txt-center">${dto.notice_date}</td>
			<td class="txt-center">${dto.member_nick}</td>
			<td class="readcount txt-center">${dto.notice_readcount}</td>
			<td  onclick="location.href='${ctxpath}/admin/notice/deletePro.do2?notice_idx=${dto.notice_idx}&pageNum=${currentPage}'">삭제</td>
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
				<a href="${ctxpath}/admin/notice/list.do2?member_idx=${sessionScope.idx}&pageNum=${startPage-3}">이전블럭</a>
			</li>
			</c:if>
			
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
			
			<c:choose>
			<c:when test="${currentPage == i}">
			<li class="page_btn">
	          <a href="${ctxpath}/admin/notice/list.do2?member_idx=${sessionScope.idx}&pageNum=${i}" class="active">
	          ${i}
	          </a>
	        </li>
	        </c:when>
	        
	        <c:otherwise>
	        <li class="page_btn">
	          <a href="${ctxpath}/admin/notice/list.do2?member_idx=${sessionScope.idx}&pageNum=${i}">
	          ${i}
	          </a>
	       	</li>
	       	</c:otherwise>
	       	</c:choose>
	       	
	       	</c:forEach>
	       
	       	<c:if test="${endPage<pageCount}">
	       	<li class="page_btn">
	          <a href="${ctxpath}/admin/notice/list.do2?member_idx=${sessionScope.idx}&pageNum=${startPage+3}">
	          	다음블럭
	          </a>
	       	</li>
	       	</c:if>
		</ul>
	</div>
	</c:if>
</div>

<script>

	var pathName = window.location.pathname;
	
	if(pathName == "/jmtc_project/admin/notice/list.do2"){
		$(".aside_inner li.menu_notice").addClass("active");
	}
	
</script>