<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<jsp:include page="../layout/header.jsp" />

<sec:authentication property="principal.uvo.email" var="authEmail" />
<sec:authentication property="principal.uvo.nickName" var="authNick" />
<sec:authentication property="principal.uvo.regDate" var="authDate" />
<sec:authentication property="principal.uvo.regDate" var="authLastLogin" />

<div class="container-md">
	<h1>User Modify Page</h1>

	<form action="/user/modify" method="post">


		<div class="mb-3">
			<label for="e" class="form-label">E-Mail</label> <input type="email"
				class="form-control" id="e" placeholder="E-mail입력..." name="email"
				value="${authEmail }" readonly="readonly">
		</div>
		<div class="mb-3">
			<label for="n" class="form-label">닉네임</label> <input type="text"
				class="form-control" id="n" placeholder="이름 입력..." name="nickName"
				value="${authNick }">
		</div>
		<div class="mb-3">
			<label for="p" class="form-label">PW</label> <input type="password"
				class="form-control" id="p" placeholder="Password 입력..." name="pwd">
		</div>
		<div class="mb-3">
			<label for="r" class="form-label">reg_date</label> <input type="text"
				class="form-control" id="r" placeholder="ID입력..." name=""
				value="${authDate }" readonly="readonly">
		</div>
		<div class="mb-3">
			<label for="l" class="form-label">last_login</label> <input
				type="text" class="form-control" id="l" placeholder="ID입력..."
				name="" value="${authLastLogin }" readonly="readonly">
		</div>

		<button type="submit" class="btn btn-primary">수정</button>

			<button type="button" id="userDeleteLink" class="btn btn-danger">회원탈퇴</button>
	</form>
	<form action="/user/remove" method="post" id="userDeleteForm">
        <!-- 인증된 계정의 이메일 -->
        <input type="hidden" name="email" value="${authEmail }">
        </form>

</div>

<jsp:include page="../layout/footer.jsp" />


<script type="text/javascript">

	
 		document.getElementById('userDeleteLink').addEventListener('click',(e)=>{
			
		if (!confirm("삭제를 정말로 하시겠습니까?")) {			
			event.preventDefault();
			return;
		}
		    document.getElementById('userDeleteForm').submit();			

		});
</script>












