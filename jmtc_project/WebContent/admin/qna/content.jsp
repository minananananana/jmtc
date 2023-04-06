<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>

  <h2 class="txt-center title_bg">Q&A 글 내용</h2>
  
  <div class="wrapper left_table">
	  <table class="transparent">
	    <tr>
	      <td class="tdcolor">글번호</td>
	      <td class="bd_bt">${dto.qna_idx}</td>
	      <td class="tdcolor">조회수</td>
	      <td class="bd_bt">${dto.qna_readcount}</td>
	    </tr>
	    
	    <tr>
	      <td class="tdcolor">작성자</td>
	      <td class="bd_bt">${dto.qna_name}</td>
	      <td class="tdcolor">작성일</td>
	      <td class="bd_bt">${dto.qna_date}</td>
	    </tr>
	    
	    <tr>
	      <td class="tdcolor">글제목</td>
	      <td colspan="3" class="bd_bt">${dto.qna_title}</td>
	    </tr>
	    
	    <tr>
	      <td class="tdcolor">글내용</td>
	      <td colspan="3" class="bd_bt"><pre>${dto.qna_content}</pre></td>
	    </tr>
	    
	    <tr>
	    	<td colspan="4" align="center">
	    		<c:if test="${dto.qna_ordNo > 0}">
	    		<button type="button" onclick="location.href='${ctxpath }/admin/qna/updateForm.do2?qna_idx=${qna_idx}&pageNum=${pageNum}'" class="default_btn mt40">글수정</button>
	    		</c:if>
	    		<button type="button" onclick="location.href='${ctxpath }/admin/qna/deleteForm.do2?qna_idx=${qna_idx}&pageNum=${pageNum}'" class="default_btn mt40">글삭제</button>	    		
	    		<button type="button" onclick="location.href='${ctxpath }/admin/qna/writeForm.do2?qna_idx=${qna_idx}&pageNum=${pageNum}&&qna_ref=${qna_ref}&qna_ordNo=${qna_ordNo}&qna_levelNo=${qna_levelNo}&member_idx=${sessionScope.idx}'" class="default_btn mt40">답글쓰기</button>
	    		<button type="button" onclick="location.href='${ctxpath }/admin/qna/list.do2?qna_idx=${qna_idx}&member_idx=${sessionScope.idx}&pageNum=${pageNum}'" class="default_btn mt40">리스트</button>
    		</td>
	    </tr>
	    
	  </table>
	</div>