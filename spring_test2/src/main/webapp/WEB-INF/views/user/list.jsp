<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../layout/header.jsp" />

<div class="container-md">

	<h1>User List Page</h1>


<%-- 	<table class="table table-hover">
		<thead>
			<tr>
				<th>E-MAIL</th>
				<th>닉네임</th>
				<th>Register Date</th>
				<th>Last Login</th>
				<th>권한</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${list }" var="li">
				<tr>
					<td>${li.email }</td>
					<td>${li.nickName }</td>
					<td>${li.regDate }</td>
					<td>${li.lastLogin }</td>
					<th>
					<c:forEach items="${li.authList }" var="al">
					${al.auth }
					</c:forEach>
					</th>
				</tr>
			</c:forEach>
		</tbody>
	</table> --%>
	
	
	<div class="row row-cols-1 row-cols-md-2 g-4" style="width: 40rem;">
	<c:forEach items="${list }" var="li">
  <div class="col">
    <div class="card">
      <img src="/re/image/images.jpg" class="card-img-top" alt="...">
      <div class="card-body">
        <h5 class="card-title">E-MAIL : ${li.email }</h5>
        <p class="card-text">닉네임 : ${li.nickName }</p>
        <p class="card-text">Register Date : ${li.regDate }</p>
        <p class="card-text">Last Login : ${li.lastLogin }</p>
        
        
        <p class="card-text">권한 : 
        <c:forEach items="${li.authList }" var="al">
         ${al.auth }
         <c:if test="${li.authList.size() > 1 }">
         /
         </c:if>      
        </c:forEach>
        </p>
        
      </div>
    </div>
  </div>
	</c:forEach>
</div>
	
	
	
	
	
	




	<jsp:include page="../layout/footer.jsp" />
</div>
