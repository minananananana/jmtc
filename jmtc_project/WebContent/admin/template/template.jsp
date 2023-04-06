<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/admin/header/header.jsp" %>
	<body>
		<jsp:include page="/admin/module/top/top.jsp" flush="false"/>

		<section class="main">
			
			
			<c:if test="${sessionScope.idx != null}">
			<aside>
				<div class="aside_inner">
		            <nav>
		                <ul>
		                	<li>
		                        <a href="${ctxpath}/admin/shop/productAdd.do2?member_idx=${sessionScope.idx}">
		                        	메뉴등록
		                        </a>
		                    </li>
		                    <li>
		                        <a href="${ctxpath}/admin/shop/productList.do2?member_idx=${sessionScope.idx}">
		                        	메뉴수정 및 삭제
		                        </a>
		                    </li>
		                    <li class="menu_notice">
		                        <a href="${ctxpath}/admin/notice/list.do2?member_idx=${sessionScope.idx}">
		                        	공지사항
		                        </a>
		                    </li>
		                    <li>
		                        <a href="${ctxpath}/admin/qna/list.do2?member_idx=${sessionScope.idx}">
		                        	Q&A
		                        </a>
		                    </li>
		                    <li>
		                        <a href="${ctxpath}/admin/member/list.do2?member_idx=${sessionScope.idx}">
		                        	회원리스트
		                        </a>
		                    </li>
		                    <li>
		                        <a href="${ctxpath}/admin/review/list.do2?member_idx=${sessionScope.idx}">
		                        	후기글
		                        </a>
		                    </li>
		                    <!-- <li>
		                    	<a href="${ctxpath}/admin/order/list.do2?member_idx=${sessionScope.idx}">
		                    		구매현황
		                    	</a>
		                    </li> -->
		                </ul>
		            </nav>
		        </div>
			</aside>
			<c:if test="${CONTENT2 == null}">
			<section class="container admin_main">
				<div class="admin_main">
					<img src="${ctxpath}/images/admin_main.jpg"/>
				</div>
			</section>
			</c:if>
			</c:if>
			
			<c:if test="${CONTENT2 != null}">
			<section class="container admin">
			<jsp:include page="${CONTENT2}" flush="false"/>
			</section>
			</c:if>
			
			<c:if test="${CONTENT2 == null && sessionScope.idx == null}">
			<section class="container login">
				<div class="login_box">
		            <form method="post" action="${ctxpath}/admin/member/loginPro.do2" class="txt-center">
		                <h1 class="" style="font-size: 2.8rem;">로그인</h1>
		                <input type="text" name="admin_id" placeholder="ID" style="height: 40px; background: #fff;">
		                <input type="password" name="admin_pw" placeholder="Password" style="height: 40px; background: #fff; margin-top:10px;">
		                <button type="submit" class="default_btn mt40">login</button>
		            </form>
		        </div>
			</section>
			</c:if>
		</section>

		<jsp:include page="/admin/module/bottom.jsp" flush="false"/>
	 
