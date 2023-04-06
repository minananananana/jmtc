<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>

  <h2 class="txt-center title_bg">Q&A 글 내용</h2>
  
  <div class="wrapper left_table">
	  <table>
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
	    		<button type="button" onclick="location.href='${ctxpath }/qna/updateForm.do?qna_idx=${qna_idx}&pageNum=${pageNum}'" class="default_btn mt40">글수정</button>
	    		<button type="button" onclick="location.href='${ctxpath }/qna/deleteForm.do?qna_idx=${qna_idx}&pageNum=${pageNum}'" class="default_btn mt40">글삭제</button>
	    		
	    		<c:if test="${sessionScope.id == 'admin'}">
	    		<button type="button" onclick="location.href='${ctxpath }/qna/writeForm.do?qna_idx=${qna_idx}&pageNum=${pageNum}&qna_ref=${dto.qna_ref}&qna_ordNo=${dto.qna_ordNo}&qna_levelNo=${dto.qna_levelNo}&member_idx=${sessionScope.idx}'" class="default_btn mt40">답글쓰기</button>
	    		</c:if>
	    		
	    		<button type="button" onclick="location.href='${ctxpath }/qna/list.do?qna_idx=${qna_idx}&member_idx=${sessionScope.idx}&pageNum=${pageNum}'" class="default_btn mt40">리스트</button>
    		</td>
	    </tr>
	    
	  </table>
	</div>