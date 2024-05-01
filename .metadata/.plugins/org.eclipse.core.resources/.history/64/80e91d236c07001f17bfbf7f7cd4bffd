<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../layout/header.jsp" />

<div class="container-md">
	<h1>Board Register Page</h1>
	<form action="/board/insert" method="post"
		enctype="multipart/form-data">

		<div class="mb-3">
			<label for="t" class="form-label">title</label> <input type="text"
				class="form-control" id="t" placeholder="title" name="title">
		</div>
		<div class="mb-3">
			<label for="w" class="form-label">writer</label> <input type="text"
				class="form-control" id="w" placeholder="writer" name="writer"
				value="${ses.id }" readonly="readonly">
		</div>
		<div class="mb-3">
			<label for="w" class="form-label">content</label>
			<textarea class="form-control" id="c" name="content"
				aria-label="With textarea"></textarea>
		</div>

		<!-- 파일 입력라인 추가 -->

		<div class="mb-3">
			<label for="file" class="form-label">Files</label> <input type="file"
				class="form-control" id="file" name="files" multiple="multiple"
				style="display: none"> <br>
			<button type="button" class="btn btn-secondary" id="trigger">File
				Uplode...</button>
		</div>

		<!-- 파일 목록 효시라인 -->
		<div class="mb-3" id="fileZone">
		
		</div>

		<button type="submit" class="btn btn-primary" id="regBtn">등록</button>

	</form>
</div>
<jsp:include page="../layout/footer.jsp" />


<script type="text/javascript" src="/resources/js/boardRegister.js"></script>




