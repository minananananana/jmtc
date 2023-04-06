<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>

<h2 class="txt-center title_bg">상품등록</h2>

<div class="wrapper left_table">
	<form method="post" action="${ctxpath}/shop/productAddPro.do?flag=insert" encType="multipart/form-data">
		<table>
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
				<td class="bd_bt"><input type="file" name="product_image"></td>
			</tr>
			<tr>
				<td colspan="2" align="">
					<button class="pointer_btn mt40">상품등록</button>
				</td>
			</tr>
		</table>
	</form>
</div>