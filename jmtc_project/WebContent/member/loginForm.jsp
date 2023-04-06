<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>    

  <h2 class="txt-center title_bg">로그인</h2>
  
  <div class="login_wrap">
	  <form name="loginForm" method="post" action="${ctxpath}/member/loginPro.do" onSubmit="return loginCheck()">
	    <table>
	      <tr>
	        <td><input type="text" name="member_id" id="member_id" size="20" placeholder="아이디"></td>
	      </tr>
	      
	      <tr>
	        <td><input type="password" name="member_pw" id="member_pw" size="20" placeholder="비밀번호"></td>
	      </tr>
	      
	      <tr>
	        <td align="center">
	          <input type="submit" value="로그인" class="flex-center">
	        </td>
	      </tr>
	      
	    </table>
	  </form>
  </div>