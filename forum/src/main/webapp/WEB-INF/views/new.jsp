<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
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
	<div class="container white p-1">
		<table class="table w-8 table-sm table-borderless mb-0">
			<!-- action= ....$_SERVER[PHP-SELF]... => Can be hacked. -->
			<!-- !!! Must use htemlspecialchars !!! -->
			<!-- enctype="multipart/form-data" ==> File Upload -->
			<form id="f1" name="f1" method="post" action="/insert">
				<thead>
					<tr>
						<td class="text-center" colspan="3"><b>새 글 작성하기</b></td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="text-right"><b>카테고리</b></td>
						<td>:</td>
						<td><select class="browser-default custom-select"
							name="category" id="category">
								<option value="news" selected>news</option>
								<option value="music">music</option>
								<option value="movie">movie</option>
								<option value="book">book</option>
						</select></td>
					</tr>
					<tr>
						<td class="text-right"><b>제목</b></td>
						<td>:</td>
						<td><input class="form-control form-control-sm" name="topic"
							type="text" id="topic" autocomplete="off" required /></td>
					</tr>
					<tr>
						<td class="text-right"><b>작성자</b></td>
						<td>:</td>
						<td><input class="form-control form-control-sm"
							name="user_id" value="id1"
							readonly /></td>
					</tr>
					<tr>
						<td class="text-right"><b>이메일</b></td>
						<td>:</td>
						<td><input class="form-control form-control-sm" name="email"
							type="text" id="email" autocomplete="off" required /></td>
					</tr>
					<!-- <tr>
						<td class="text-right"><b>파일 첨부</b></td>
						<td>:</td>
						<td><input class="add_file" name="file" type="file" multiple /></td>
					</tr> -->
					<tr>
						<td class="text-right d"><b>내용</b></td>
						<td class="d">:</td>
						<td><textarea class="form-control" name="detail" rows="3"
								id="ckeditor"></textarea></td>
					</tr>
					<tr>
						<td colspan="3" class="text-right">
							<button class="btn btn-default btn-sm" type="submit"
								name="Submit">확인</button>
							<button class="btn btn-default btn-sm" type="reset"
								onClick="location.href='/main'">취소</button>
						</td>
					</tr>
				</tbody>
			</form>
		</table>
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