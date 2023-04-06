<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/admin/header/header.jsp" %>

<c:if test="${sessionScope.idx == '0'}">
<div class="wrapper">
	로그인 하신 후 서비스를 이용할 수 있습니다.	
</div>
</c:if>

 <h2 class="txt-center title_bg">회원리스트</h2>
  
 <div class="wrapper">
 	<div class="sub_txt flex-between-center">
		전체글:${count}
	 	<div class="flex">
			  <%--글 검색 --%>
			  <form name="searchForm" method="post" action="list.do2?member_idx=${sessionScope.idx}" onSubmit="return check()" class="flex mr20">
		          <select name="keyField" class="mr10">
		            <option value="member_id">회원ID</option>
            		<option value="member_name">회원이름</option>
		          </select>
		          
		          <input type="text" name="keyWord" id="keyWord" size="16" class="keyword_input mr10">
		          <input type="hidden" name="page" value="0">
		          <input type="submit" value="검색" class="default_btn">
			  </form>
		</div>
	</div>
	 <c:if test="${count==0}">
	 <table class="empty_tbl">
	    <tr>
	       <th>번호</th>
		   <th>ID</th>
		   <th>PW</th>
		   <th>회원이름</th>
		   <th>닉네임</th>
		   <th>이메일</th>
		   <th>전화번호</th>
		   <th>주소</th>
		   <th>상세주소</th>
	    </tr>
	    <tr>
	    	<td colspan="5" align="center">등록된 회원이 없습니다</td>
	    </tr>
	  </table>
	 </c:if>
	
	 <c:if test="${count>0}">
	  <table class="jmtc_tbl">
		  <tr>
		   <th>번호</th>
		   <th>ID</th>
		   <th>PW</th>
		   <th>회원이름</th>
		   <th>닉네임</th>
		   <th>이메일</th>
		   <th>전화번호</th>
		   <th>주소</th>
		   <th>상세주소</th>
		   
		  </tr>
	  
	  <c:forEach var="dto" items="${list}">
	   <tr>
	   <td>
	   <c:out value="${number}"/>
	   <c:set var="number" value="${number-1}"/>
	   </td>
	   
	   
	   <td>${dto.member_id}</td>
	   <td>${dto.member_pw}</td>
	   <td>${dto.member_name}</td>
	   <td>${dto.member_nick}</td>
	   <td>${dto.member_email}</td>
	   <td>${dto.member_tel}</td>
	   <td>${dto.member_addr}</td>
	   <td>${dto.member_addr_detail}</td>
	   
	   </tr>
	  </c:forEach>
	  </table>
	</c:if>
	
	<c:if test="${count>0}">
	<div class="pagination">
		<ul>
        <%--에러방지 --%>
        <c:if test="${endPage>pageCount}">
          <c:set var="endPage" value="${pageCount}"/>
        </c:if>
        
        <%--이전 블럭 --%>
        <c:if test="${startPage>10}">
        <li class="page_btn">
          <a href="${ctxpath}/admin/member/list.do2?member_idx=${sessionScope.idx }&pageNum=${startPage-10}">
          [이전블럭]
          </a>
        </li>
        </c:if>
        
        <%--페이지 처리 --%>
        <c:forEach var="i" begin="${startPage}" end="${endPage}">
			
			<c:choose>
			<c:when test="${currentPage == i}">
			<li class="page_btn">
	          <a href="${ctxpath}/admin/member/list.do2?member_idx=${sessionScope.idx }&pageNum=${i}" class="active">
	          ${i}
	          </a>
          	</li>
	        </c:when>
	        
	        <c:otherwise>
	        <li class="page_btn">
	          <a href="${ctxpath}/admin/member/list.do2?member_idx=${sessionScope.idx }&pageNum=${i}">
	          ${i}
	          </a>
	       	</li>
	       	</c:otherwise>
	       	</c:choose>
        </c:forEach>
        
        <%--다음블럭 --%>
        <c:if test="${endPage<pageCount}">
          <a href="${ctxpath}/admin/member/list.do2?member_idx=${sessionScope.idx }&pageNum=${startPage+10}">
          [다음블럭]
          </a>
        </c:if>
		</ul>
	</div>
	</c:if>
	
</div>