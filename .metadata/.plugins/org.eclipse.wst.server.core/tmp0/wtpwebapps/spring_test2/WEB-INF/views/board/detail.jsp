<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../layout/header.jsp" />

<div class="container-md">
	<h1>Board Detail Page</h1>

	<%-- <c:set value="${bdto.bvo }" var="bvo" /> --%>

	<div class="mb-3">
		<label for="n" class="form-label">bno</label> <input type="text"
			class="form-control" id="n" placeholder="bno" name="bno"
			value="${bvo.bno }" readonly="readonly">
	</div>
	<div class="mb-3">
		<label for="t" class="form-label">title</label> <input type="text"
			class="form-control" id="t" placeholder="title" name="title"
			value="${bvo.title }" readonly="readonly">
	</div>
	<div class="mb-3">
		<label for="w" class="form-label">writer</label> <input type="text"
			class="form-control" id="w" placeholder="writer" name="writer"
			value="${bvo.writer }" readonly="readonly">
	</div>
	<div class="mb-3">
		<label for="r" class="form-label">작성일</label> <input type="text"
			class="form-control" id="r" placeholder="reg_date" name="reg_daet"
			value="${bvo.regDate }" readonly="readonly">
	</div>
	<div class="mb-3">
		<label for="w" class="form-label">content</label>
		<textarea class="form-control" id="c" name="content"
			aria-label="With textarea" readonly="readonly">${bvo.content }</textarea>
	</div>

	<!-- FIle upload 표시라인 -->
	<%-- <c:set value="${bdto.flist }" var="flist" /> --%>

	<%-- 	<div class="mb-3">

		<ul class="list-group list-group-flush">

			<!-- 파일 개수만큼 li를 반복하여 파일 표시 타입이 1인경우만 표시 -->
			<!-- li => div => img -->
			<!-- => div => 파일이름, 작성일, span size -->

			<c:forEach items="${flist }" var="fvo">
				<li class="list-group-item"><c:choose>
						<c:when test="${fvo.file_type > 0 }">
							<div>
								<img alt=""
									src="/upload/${fvo.save_dir }/${fvo.uuid}_${fvo.file_name}">
							</div>
						</c:when>
						<c:otherwise>
							<div>
								<!-- 파일 타입이 0인 경우 아이콘 모양 하나 가져와서 넣기 -->
							</div>
						</c:otherwise>
					</c:choose>
					<div>
						<div>${fvo.file_name }</div>
						${fvo.reg_date } <span class="badge text-bg-warning">${fvo.file_size }Byte</span>
					</div></li>
			</c:forEach>
		</ul>

	</div> --%>


	<br>
	<hr>
	<!-- Comment line -->

	<!-- 댓글 등록 라인 -->
	
	<div class="input-group mb-3">
		<span class="input-group-text" id="cmtWriter">관리자</span> <input
			type="text" id="cmtText" class="form-control"
			placeholder="Add Comment..." aria-label="Username"
			aria-describedby="basic-addon1">
		<button type="button" id="cmtAddBtn" class="btn btn-secondary">댓글
			등록</button>
	</div>

	<!-- 댓글 출력 라인 -->

	<ul class="list-group list-group-flush" id="cmtListArea">
		<li class="list-group-item">
			<div class="input-group mb-3">
				<div class="fw-bold">Writer</div>
				content
			</div> <span class="badge rounded-pill text-bg-warning">등록시간(regDate)</span>
		</li>
	</ul>

	<!-- 댓글 더보기 버튼 -->
	<!-- visibility : disable이랑 다르게 모양이 흐트러지지 않는다 -->
	<div class="d-grid gap-2 col-3 mx-auto">
		<button type="button" id="moreBtn" data-page="1" class="btn btn-outline-secondary"
			style="visibility: hidden">More +</button>
	</div>

	<!-- 모달창 라인 -->

	<div class="modal" id="myModal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Writer</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<input type="text" class="form-control" id="cmtTextMod">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-bs-dismiss="modal" id="cmtModBtn">modify</button>
					<button type="button" class="btn btn-secondary"data-bs-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<br>
	<hr>

	<a href="/board/modify?bno=${bvo.bno }"><button type="button"
			class="btn btn-warning">수정</button></a> <a
		href="/board/remove?bno=${bvo.bno }"><button type="button"
			class="btn btn-danger">삭제</button></a> <a href="/board/list"><button
			type="button" class="btn btn-primary">list</button></a> <br> <br>
	<br> <br> <br> <br>

</div>
<jsp:include page="../layout/footer.jsp" />

 <script type="text/javascript">
const bnoVal = `<c:out value="${bvo.bno}"/>`;
/* const writerVal = `<c:out value="${ses.id}"/>`; */
</script>

<script type="text/javascript" src="/re/js/boardDetailComment.js"></script>

<script type="text/javascript">
spreadCommentList(bnoVal);
</script>  






