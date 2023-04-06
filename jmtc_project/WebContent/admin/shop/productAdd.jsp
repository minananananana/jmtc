<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header/header.jsp" %>

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

<h2 class="txt-center title_bg">상품등록</h2>

<div class="wrapper left_table">
	<form method="post" action="${ctxpath}/admin/shop/productAddPro.do2?flag=insert" encType="multipart/form-data">
		<table class="transparent">
			<tr>
				<td>상품코드</td>
				<td class="bd_bt"><input type="text" name="product_code"></td>
			</tr>
			<tr>
				<td>상품이름</td>
				<td class="bd_bt"><input type="text" name="product_name"></td>
			</tr>
			<tr>
				<td>상품상세</td>
				<td class="bd_bt"><input type="text" name="product_detail"></td>
			</tr>
			<tr>
				<td>상품가격</td>
				<td class="bd_bt"><input type="text" name="product_price"></td>
			</tr>
			<tr>
				<td>상품판매자</td>
				<td class="bd_bt"><input type="text" name="product_seller"></td>
			</tr>
			<tr>
				<td>재고수량</td>
				<td class="bd_bt"><input type="text" name="product_stock"></td>
			</tr>
			<tr>
				<td>상품이미지</td>
				<td class="bd_bt">
					<div id="image_container" oninput="setThumbnail(event);" class="update_column_image">
             		<c:if test="${dto.product_image != null}">
                    	<img src="${ctxpath}/images/${dto.product_image}" width="180" height="180">
                	</c:if>
          			</div>
					<input type="file" name="product_image" onchange="setThumbnail(event);">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button class="pointer_btn mt40">상품등록</button>
				</td>
			</tr>
		</table>
	</form>
</div>