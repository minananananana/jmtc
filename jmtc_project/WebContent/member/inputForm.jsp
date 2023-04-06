<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

    <script>
     function findAddr(){
       
       new daum.Postcode({
          oncomplete:function(data){
            document.getElementById('member_zipcode').value=data.zonecode;
            document.getElementById('member_addr').value=data.address;
          }
       }).open();
     }//openDaumPostcode()---
    </script>
    
    <script type="text/javascript">
      //Ajax으로 id중복체크
      function idCheck(){
    	  
    	  if($("#member_id").val()==''){
        	    alert("id를 입력 하세요");
        	    $("#member_id").focus();
        	    return false;
          }else{
        	  $.ajax({
        		  type:"POST",
        		  url:"confirmID.jsp",
        		  data:"member_id="+$("#member_id").val(),
        		  dataType:"JSON",//서버가 보내준 자료타입
        		  success:function(data){
        			  //alert(data.x);
        			  if(data.x==1){
        				  //사용중인 id
        				  alert("사용중인 id 입니다");
        				  $("#member_id").val('').focus();
        			  }else{//x==-1
        				  //사용 가능한 id
        				  alert("사용 가능한 id 입니다");
        			      $("#member_idck").val('true');
        			      $("#member_pw").focus();
        			  }
        		  }//success-end
        	  });// $.ajax-end
          }//else-end
      }//idCheck()-end
      
        function aa(){
    	  if($("#member_idck").val()=='false'){
    		  alert("id중복 체크 하세요");
    		  $("#member_id").focus();
    		  return false;
    	  }//if-end
      }//aa()-end
    </script>

  <h2 class="txt-center title_bg">회원가입</h2>
  <div class="wrapper left_table sign_wrap">
	  <form name="inputForm" method="post" action="${ctxpath}/member/inputPro.do">
	    <table>
	      <tr>
	        <td>ID</td>
	        <td class="bd_bt flex-align">
	          <input type="text" name="member_id" id="member_id" size="20" placeholder="id입력">
	          <input type="button" value="ID중복체크" onClick="idCheck()" class="default_btn">
	        </td>
	      </tr>
	      
	      <tr>
	        <td>암호</td>
	        <td class="bd_bt">
	          <input type="password" name="member_pw" id="member_pw" size="12" onFocus="aa()">
	        </td>
	      </tr>
	      
	      <tr>
	        <td>암호확인</td>
	        <td class="bd_bt">
	          <input type="password" name="member_pw_ck" id="member_pw_ck" size="12">
	        </td>      
	      </tr>
	      
	      <tr>
	        <td>이름</td>
	        <td class="bd_bt">
	          <input type="text" name="member_name" id="member_name" size="30" placeholder="입력">
	        </td>
	      </tr>
	      
	      <tr>
	        <td>닉네임</td>
	        <td class="bd_bt">
	          <input type="text" name="member_nick" id="member_nick" size="30" placeholder="입력">
	        </td>
	      </tr>
	      
	      <tr>
	        <td>이메일</td>
	        <td class="bd_bt">
	          <input type="text" name="member_email" id="member_email" size="30" placeholder="abc@naver.com">
	        </td>
	      </tr>
	      
	      <tr>
	        <td>전화</td>
	        <td class="bd_bt">
	          <input type="text" name="member_tel" id="member_tel" size="14" placeholder="전화번호 입력">
	        </td>
	      </tr>
	      
	      <tr>
	        <td>우편번호</td>
	        <td class="bd_bt flex-align">
	          <input type="text" name="member_zipcode" id="member_zipcode" size="7" readonly>
	          <input type="button" value="주소찾기" onClick="findAddr()" class="default_btn">
	        </td>      
	      </tr>
	      
	      <tr>
	        <td>주소</td>
	        <td class="bd_bt">
	          <input type="text" name="member_addr" id="member_addr" size="50" readonly>
	        </td>
	      </tr>
	      
	      <tr>
	        <td>상세주소</td>
	        <td class="bd_bt">
	          <input type="text" name="member_addr_detail" id="member_addr_detail" size="20">
	        </td>
	      </tr>
	      
	      <tr>
	        <td colspan="2" align="center">
	          <input type="submit" value="회원가입" class="pointer_btn mt40">
	          <input type="button" value="가입안함" onClick="location='${ctxpath}/template/template.jsp'" class="default_btn">
	        </td>
	      </tr>
	    </table>
	  </form>
	</div>