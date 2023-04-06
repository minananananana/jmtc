<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="aa.css">
	<script src="//code.jquery.com/jquery-3.6.1.min.js"></script>
	<script type="text/javascript" src="aa.js"></script>
	<script>
		function pwcheck(){
			if(document.delForm.qna_pw.value==""){
				alert("암호는 필수 입력");
				document.updateForm.qna_pw.focus();
				return false;
			}//if out
		return true;
		}//pwcheck() out
	</script>
	
</head>
<body>
	<h2>글삭제 폼</h2>
	<form name="delForm" method="post" action="${ctxpath }/admin/qna/deletePro.do2?member_idx=${sessionScope.idx}&pageNum=${pageNum}" onSubmit="return pwcheck()">
		<table border="1" width="350">
			<tr>
				<td colspan="2">
				<h4>암호를 입력하세요</h4>
				</td>
			</tr>
			
			<tr>
				<td>암호</td>
				<td>
					<input type="password" name="qna_pw" id="qna_pw" size="12">
					<input type="hidden" name="qna_idx" value="${qna_idx}">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="글삭제">
					<input type="button" value="리스트" onClick="location.href='${ctxpath}/admin/qna/list.do2?member_idx=${sessionScope.idx}&pageNum=${pageNum}'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>