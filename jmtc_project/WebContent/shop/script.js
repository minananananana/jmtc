	//상품 상세보기 ************         
	function productDetail(code) {
		 
		document.detail.code.value=code;//////
		//document.detail.action="productDetail.jsp";
		document.detail.submit();
	}
	

	//장바구니 업데이트
	function cartUpdate(form){//***********
		form.flag.value="update";
		form.submit();
	}
	
	//장바구니 내용 삭제 
	function cartDelete(form) {//***************
		//alert("cartDelete"); 
		form.flag.value="del";
		form.submit();
	}

