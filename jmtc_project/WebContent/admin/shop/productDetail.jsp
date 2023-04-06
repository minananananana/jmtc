<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="shop.*"
    import="java.util.*"
    %>
<%@include file="../header/header.jsp" %>

<h2 class="txt-center title_bg">상품 상세보기</h2>
<div>어드민인디</div>
<div class="pd_detail_wrap wrapper">

	<c:if test="${sessionScope.id == 'admin'}">
	<form method="post" action="${ctxpath}/admin/shop/productUpdateFrom.do2?flag=update">
	   <table class="left_table transparent">
	      <tr>
	         <td colspan="2">
	            <h3 class="txt-center">${dto.product_name}</h3>
	         </td>
	      </tr>
	      <tr>
	         <td colspan="2" align="center" class="thumb_img">
	            <img src="${ctxpath}/images/${dto.product_image}">
	         </td>
	      </tr>
	      <tr>
	         <td>상품코드</td>
	         <td class="bd_bt">
	            <input type="text" name="product_code" id="product_code" value="${dto.product_code}" readonly>
	            <input type="hidden" name="product_idx" value="${dto.product_idx}">
	         </td>
	      </tr>
	      <tr>
	         <td>상품가격</td>
	         <td class="bd_bt">
	            <input type="text" name="product_price" id="product_price" value="${dto.product_price}" readonly>
	         </td>
	      </tr>
	      <tr>
	         <td>출시날짜</td>
	         <td class="bd_bt">${dto.product_date}</td>
	      </tr>
	      <tr>
	         <td>제조회사</td>
	         <td class="bd_bt">
	            <input type="text" name="product_seller" id="product_seller" value="${dto.product_seller}" readonly>
	         </td>
	      </tr>
	      
	      <tr>
	         <td>수량</td>
	         <td class="bd_bt">
	            <select name="product_stock" id="product_stock">
	               <option value="1">1</option>
	               <option value="2">2</option>
	               <option value="3">3</option>
	               <option value="4">4</option>
	               <option value="5">5</option>
	            </select>
	         </td>
	      </tr>
	      <tr>
	         <td colspan="2" align="center">
	            <input type="submit" value="상품수정" class="pointer_btn mt40">
	            <button type="button" onclick="location.href='${ctxpath}/admin/shop/productDeletePro.do2?product_idx=${dto.product_idx}'" class="default_btn mt40">상품삭제</button>
	            <input type="hidden" name="product_idx" id="product_idx" value="${dto.product_idx}">
	         </td>
	         <!-- state:상태
	         1. 접수중
	         2. 접수
	         3. 입금확인
	         4. 배송준비
	         5. 배송중
	         6. 배송완료
	          -->
	      </tr>
	   </table>
	</form>
	</c:if>
</div>