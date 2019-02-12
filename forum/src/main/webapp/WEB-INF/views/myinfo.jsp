<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<!-- Bootstrap4.1.3 css ================================== -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<!-- MDB ==================================================== -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.6.1/css/mdb.min.css"
	rel="stylesheet">
<!-- Additional Stylesheet =============================== -->
<link rel="stylesheet" href="/styles.css">
<!-- Font Download (https://fonts.google.com/) ============= -->
<!-- Icon Pack Download (https://fontawesome.com/)========== -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
	integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU"
	crossorigin="anonymous">
<!-- CKeditor ============================================== -->
<script src="/ckeditor/ckeditor.js"></script>
<!-- Jquery 3.2.1 ============================================= -->
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<!-- DAUM address API ========================================= -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
</head>
<!-- /HEAD ==================================================== -->
<!-- ========================================================== -->
<!-- ========================================================== -->
<!-- BODY  ==================================================== -->
<body>
	
	<!-- (로그인 전/후 구별) 로그인 전에는 접근 불가. 페이지 이동 / User Info -->
	<c:choose>
		<c:when test="${empty session.s_user_id}">
			<script> alert('잘못된 접근입니다.');
			location.href='/main';	</script>
		</c:when>
		<c:when test="${!empty session.s_user_id}">
			<jsp:include page="/WEB-INF/views/login_af.jsp" flush="true" />
		</c:when>
	</c:choose>

	<div class="container white py-3">
		<table class="table table-sm col-6 mx-auto">
			<tr><td >이름: </td><td>${user.user_name}</td>	</tr>
			<tr><td>우편번호: </td><td>${user.post}</td></tr>
			<tr><td>주소: </td><td>${user.address}</td></tr>
			<tr><td>상세주소: </td><td>${user.detail_address}</td></tr>
		</table>
		<div class="d-flex justify-content-center">
			<button class="btn btn-default btn-sm m-2" onclick="location.href='/main'">확인</button>
		</div>
	</div>


	<!-- ========================================================== -->
	<!-- JavaScript CDN LIST ====================================== -->
	<!-- Placed at the end of the document so the pages load faster -->

	<!-- Popper.js 1.14.3 ========================================= -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<!-- Bootstrap 4.1.3 ========================================== -->
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
	<!-- /JavaScript CDN LIST ===================================== -->
	<script src="js/jquery.twbsPagination.min.js"></script>
	<script type="text/javascript">
		// Replace the <textarea id="editor1"> with a CKEditor
		// instance, using default configuration.
		CKEDITOR.replace('ckeditor');
	</script>
	<!-- ========================================================== -->
</body>
</html>