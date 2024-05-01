<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../layout/header.jsp" />


<div  class="container-md">
<h1>Board Modify Page</h1>

<c:set value="${bdto.bvo }" var="bvo"></c:set>

<form action="/board/modify" method="post" enctype="multipart/form-data">
<div class="mb-3">
  <label for="n" class="form-label">bno</label>
  <input type="text" class="form-control" id="n" placeholder="bno" name="bno" value="${bvo.bno }" readonly="readonly">
</div>
<div class="mb-3">
  <label for="t" class="form-label">title</label>
  <input type="text" class="form-control" id="t" placeholder="title" name="title" value="${bvo.title }">
</div>
<div class="mb-3">
  <label for="w" class="form-label">writer</label>
  <input type="text" class="form-control" id="w" placeholder="writer" name="writer" value="${bvo.writer }" readonly="readonly">
</div>
<div class="mb-3">
  <label for="r" class="form-label">reg_date</label>
  <input type="text" class="form-control" id="r" placeholder="reg_date" name="reg_daet" value="${bvo.reg_date }" readonly="readonly">
</div>
<div class="mb-3">
  <label for="c" class="form-label">content</label>
  <textarea class="form-control" id="c" name="content" aria-label="With textarea">${bvo.content }</textarea>
</div>

<br>
<hr>


<!-- FIle upload 표시라인 -->
<c:set value="${bdto.flist }" var="flist" />

	<div class="mb-3">

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
						<button type="button" class="btn btn-outline-danger file-x" data-uuid="${fvo.uuid }"  data-bno="${fvo.bno }">X</button>
						
					</div></li>
			</c:forEach>
		</ul>

	</div>

<br>
<hr>
		<!-- 파일 추가 -->

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
		
		

		<a><button type="submit" class="btn btn-warning" id="regBtn">수정</button></a> 
</form>
<a href="/board/list"><button type="button" class="btn btn-primary">list</button></a> 

</div>
<jsp:include page="../layout/footer.jsp" />

<script type="text/javascript" src="/resources/js/boardModify.js"></script>
<script type="text/javascript" src="/resources/js/boardRegister.js"></script>

