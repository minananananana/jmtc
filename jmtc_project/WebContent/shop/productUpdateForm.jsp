<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>

<script>
function setThumbnail(event) {
    let reader = new FileReader();
    reader.onload = function(event) {
      let img = document.createElement("img");
      img.setAttribute("src", event.target.result);
      img.setAttribute("height", "180px");
      img.setAttribute("width", "180px");
      let imageContainer = document.querySelector("div#image_container");
      while (imageContainer.firstChild) {
         imageContainer.removeChild(imageContainer.firstChild);
      }
      imageContainer.appendChild(img);
    };
    reader.readAsDataURL(event.target.files[0]);  
}
</script>

<h2 class="txt-center title_bg">상품 수정</h2>
<div class="pd_update_wrap wrapper">
	<form method="post" action="${ctxpath}/shop/productUpdatePro.do?flag=update"  encType="multipart/form-data">
		<table class="left_table">
	      <tr>
	         <td colspan="2">
	            <h3 class="txt-center">${dto.product_name}</h3>
	         </td>
	      </tr>
	      <tr>
            <td class="update_column_one"> 파일 </td>
            <td>
               <div id="image_container" oninput="setThumbnail(event);" class="update_column_image">
             		<c:if test="${dto.product_image != null}">
                    <img src="${ctxpath}/images/${dto.product_image}" width="180" height="180">
                	</c:if>
          		</div>
        	</td>  
         </tr>
	      <tr>
	      	<td>이미지</td>
	         <td>
	            <input type="file" name="product_image" onchange="setThumbnail(event);">
	         </td>
	      </tr>
	      <tr>
	         <td>상품코드</td>
	         <td class="bd_bt">
	            <input type="text" name="product_code" id="product_code" value="${dto.product_code}">
	            <input type="hidden" name="product_idx" value="${dto.product_idx}"> 
	            <input type="hidden" name="product_name" value="${dto.product_name}"> 
	         </td>
	      </tr>
	      <tr>
	      	<td>상품설명</td>
	      	<td class="bd_bt">
	      		<textarea name="product_detail">${dto.product_detail}</textarea>
	      	</td>
	      </tr>
	      <tr>
	         <td>상품가격</td>
	         <td class="bd_bt">
	            <input type="text" name="product_price" id="product_price" value="${dto.product_price}">
	         </td>
	      </tr>
	      <tr>
	         <td>출시날짜</td>
	         <td class="bd_bt">
	         	${dto.product_date}
	         	<input type="hidden" name="product_date" value="${product_date}">
	         </td>
	      </tr>
	      <tr>
	         <td>제조회사</td>
	         <td class="bd_bt">
	            <input type="text" name="product_seller" id="product_seller" value="${dto.product_seller}">
	         </td>
	      </tr>
	      
	      <tr>
	         <td>수량</td>
	         <td class="bd_bt">
	            <input type="text" name="product_stock" id="product_stock" value="${dto.product_stock}">
	         </td>
	      </tr>
	      <tr>
	         <td colspan="2" align="center">
	            <input type="submit" value="상품수정하기" class="pointer_btn mt40">
	         </td>
	      </tr>
	   </table>
	</form>
</div>