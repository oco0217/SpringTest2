<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="../layout/header.jsp" />

<div class="container-md">
	<h1>Member Register Page</h1>

	<form action="/user/register" method="post">

		<div class="mb-3">
			<label for="e" class="form-label">E-Mail</label> <input type="text"
				class="form-control" id="e" placeholder="E-mail입력..." name="email">
		</div>
		<div class="mb-3">
			<label for="p" class="form-label">PW</label> <input type="password"
				class="form-control" id="p" placeholder="Password 입력..." name="pwd">
		</div>
		<div class="mb-3">
			<label for="n" class="form-label">nickName</label> <input type="text"
				class="form-control" id="n" placeholder="닉네임 입력..." name="nickName">
		</div>


		<button type="submit" class="btn btn-primary">회원가입</button>

	</form>

</div>

<jsp:include page="../layout/footer.jsp" />