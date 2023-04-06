<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>
<%
	String id = (String)session.getAttribute("id");
	session.getAttribute("idx");
%>

    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

    <script>
     function findAddr(){
       
       new daum.Postcode({
          oncomplete:function(data){
            document.getElementById('zipcode').value=data.zonecode;
            document.getElementById('addr').value=data.address;
          }
       }).open();
     }//openDaumPostcode()---
    </script>

  <h2 class="txt-center title_bg">내정보수정</h2>
  <div class="left_table wrapper">
	  <form method="post" action="${ctxpath}/member/updatePro.do">
	  	<table>
	  		<tr>
	  			<td class="tbl_title">ID</td>
		        <td class="bd_bt">
		        ${dto.member_id}
		        <input type="hidden" name="member_id" id="member_id" value="${sessionScope.id}">
		        <input type="hidden" name="member_idx" id="member_idx" value="${sessionScope.idx}">
		        </td>
	  		</tr>
	  		<tr>
		        <td class="tbl_title">암호</td>
		        <td class="bd_bt">
		          <input type="password" name="member_pw" id="member_pw" size="20">
		        </td>
		    </tr>
		    <tr>
		    	<td class="tbl_title">암호확인</td>
		        <td class="bd_bt">
		          <input type="password" name="member_pw2" id="member_pw2" size="20">
		        </td>
		    </tr>
		    <tr>
		    	<td class="tbl_title">이름</td>
		        <td class="bd_bt">
		          <input type="text" name="member_name" id="member_name" size="40" value="${dto.member_name}">
		        </td>
		    </tr>
		    <tr>
		    	<td class="tbl_title">닉네임</td>
		        <td class="bd_bt">
		          <input type="text" name="member_nick" id="member_nick" size="40" value="${dto.member_nick}">
		        </td>
		    </tr>
		    <tr>
		    	<td class="tbl_title">이메일</td>
		        <td class="bd_bt">
		          <input type="text" name="member_email" id="member_email" size="40" value="${dto.member_email}">
		        </td>
		    </tr>
		    <tr>
		    	<td class="tbl_title">전화번호</td>
		        <td class="bd_bt">
		          <input type="text" name="member_tel" id="member_tel" size="40" value="${dto.member_tel}">
		        </td>
		    </tr>
		    <tr>
		    	<td class="tbl_title">우편번호</td>
		        <td class="bd_bt flex-align">
		          <input type="text" name="member_zipcode" id="member_zipcode" size="7" value="${dto.member_zipcode}" readonly>
		          <input type="button" value="주소찾기" onClick="findAddr()" class="findAddr_btn">
		        </td>
		    </tr>
		    <tr>
		    	<td class="tbl_title">주소</td>
		        <td class="bd_bt">
		          <input type="text" name="member_addr" id="member_addr" size="50" value="${dto.member_addr}" readonly>
		        </td>
		    </tr>
		    <tr>
		    	<td class="tbl_title">상세주소</td>
		        <td class="bd_bt">
		          <input type="text" name="member_addr_detail" id="member_addr_detail" size="20" value="${dto.member_addr_detail}">
		        </td>
		    </tr>
		    <tr>
		    	<td colspan="2" align="center">
		          <input type="submit" value="내정보수정" class="pointer_btn mt40">
		          <input type="button" value="취소" onClick="${ctxpath}/template/template.jsp" class="default_btn mt40">
		        </td>
		    </tr>
	  	</table>
	  </form>
  </div>