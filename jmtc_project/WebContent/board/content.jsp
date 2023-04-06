<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>

  <h2 class="txt-center title_bg">자유게시판 글내용</h2>
  <div class="wrapper left_table">
	  <table>
	    <tr>
	      <td class="tdcolor">글번호</td>
	      <td class="bd_bt">${dto.board_idx}</td>
	      <td class="tdcolor">조횟수</td>
	      <td class="bd_bt">${dto.board_readcount}</td>
	    </tr>
	    
	    <tr>
	      <td class="tdcolor">작성자</td>
	      <td class="bd_bt">${dto.board_name}</td>
	      <td class="tdcolor">작성일</td>
	      <td class="bd_bt">${dto.board_date}</td>
	    </tr>
	    
	    <tr>
	      <td class="tdcolor">글제목</td>
	      <td colspan="3" class="bd_bt">${dto.board_title}</td>
	    </tr>
	    
	    <tr height="200">
	      <td class="tdcolor">글내용</td>
	      <td colspan="3" class="bd_bt"><pre>${dto.board_content}</pre></td>
	    </tr>
	    
	    <tr>
	      <td colspan="4" align="right">
	        <button type="button" onclick="location.href='${ctxpath}/board/updateForm.do?board_idx=${board_idx}&pageNum=${pageNum}'" class="default_btn mt40">글수정</button>
	        <button type="button" onclick="location.href='${ctxpath}/board/deleteForm.do?board_idx=${board_idx}&pageNum=${pageNum}'" class="default_btn mt40">글삭제</button>
	        <button type="button" onclick="location.href='${ctxpath}/board/writeForm.do?board_idx=${board_idx}&pageNum=${pageNum}&board_ref=${dto.board_ref}&board_ordNo=${dto.board_ordNo}&board_levelNo=${dto.board_levelNo}&member_idx=${sessionScope.idx}'" class="default_btn mt40">답글쓰기</button>
	        <button type="button" onclick="location.href='${ctxpath}/board/list.do?member_idx=${sessionScope.idx }&pageNum=${pageNum}'" class="default_btn mt40">리스트</button>
	      </td>
	    </tr>
	  </table>
  </div>