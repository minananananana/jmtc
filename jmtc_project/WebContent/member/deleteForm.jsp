<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>    

	<script type="text/javascript">
	  function pwcheck(){
		  if(document.delForm.pw.value==''){
			  alert("암호를 입력 하세요");
			  document.delForm.pw.focus();
			  return false;
		  }//if-end
		  return true;
	  }//pwcheck()-end
	</script>

  <h2 class="txt-center title_bg">회원 탈퇴</h2>
  
  <div class="user_delete_wrap">
	  <form name="delForm" method="post" action="${ctxpath}/member/deletePro.do" onSubmit="return pwcheck()">
	    <table>
	      <tr>
	        <td>암호</td>
	        <td class="bd_bt">
		        <input type="password" name="member_pw" id="member_pw" size="20">
		        <input type="hidden" name="member_id" value="${sessionScope.id}">
		        <input type="hidden" name="member_idx" value="${sessionScope.idx}">
	        </td>
	      </tr>
	      
	      <tr>
	        <td colspan="2" align="center">
	          <input type="submit" value="회원탈퇴" class="pointer_btn mt40">
	          <input type="button" value="취소" onClick="location='${ctxpath}/template/template.jsp'" class="default_btn mt40">
	        </td>
	      </tr>
	    </table>
	  </form>
  </div>