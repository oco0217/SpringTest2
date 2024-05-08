<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="../layout/header.jsp" />

<div class="container-md">
	<h1>User Modify Page</h1>

	<form action="/user/modify" method="post">


		<div class="mb-3">
			<label for="e" class="form-label">E-Mail</label> <input type="email"
				class="form-control" id="e" placeholder="E-mail입력..." name="email"
				value="${uvo.email }" readonly="readonly">
		</div>
		<div class="mb-3">
			<label for="n" class="form-label">닉네임</label> <input type="text"
				class="form-control" id="n" placeholder="이름 입력..." name="nickName"
				value="${uvo.nickName }">
		</div>
		<div class="mb-3">
			<label for="p" class="form-label">PW</label> <input type="password"
				class="form-control" id="p" placeholder="Password 입력..." name="pwd">
		</div>
		<div class="mb-3">
			<label for="r" class="form-label">reg_date</label> <input type="text"
				class="form-control" id="r" placeholder="ID입력..." name=""
				value="${uvo.regDate }" readonly="readonly">
		</div>
		<div class="mb-3">
			<label for="l" class="form-label">last_login</label> <input
				type="text" class="form-control" id="l" placeholder="ID입력..."
				name="" value="${uvo.lastLogin }" readonly="readonly">
		</div>

		<button type="submit" class="btn btn-primary">수정</button>
		<a href="/user/remove">
			<button type="button" class="btn btn-danger" onclick="removePopup()">회원탈퇴</button>
		</a>
	</form>

</div>

<jsp:include page="../layout/footer.jsp" />


<script type="text/javascript">
	function removePopup() {

		if (confirm("삭제를 정말로 하시겠습니까?")) {

		} else {
			event.preventDefault();
		}

	}
</script>










