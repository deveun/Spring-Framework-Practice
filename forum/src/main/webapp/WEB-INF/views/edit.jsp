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
	<div class="container white p-1">
		<!-- Update Table -->
		<table class="update_table table table-sm table-borderless mb-0">
			<!-- action= ....$_SERVER[PHP-SELF]... => Can be hacked. -->
			<!-- !!! Must use htemlspecialchars !!! -->
			<form id="form1" name="form1" method="post" action="/update">
				<thead>
					<tr>
						<td><b>카테고리</b></td>
						<td>:</td>
						<td><input hidden name="topic_id" type="text" id="topic_id"
							value="${topic.topic_id}" /> <select class="custom-select"
							name="category" id="category">
								<option id="news" value="news">news</option>
								<option id="music" value="music">music</option>
								<option id="movie" value="movie">movie</option>
								<option id="book" value="book">book</option>
						</select></td>
						<td class="text-right">${topic.datetime}</td>
					</tr>
					<tr>
						<td>제목</td>
						<td>:</td>
						<td><input class="form-control" name="topic" type="text"
							id="topic" value="${topic.topic}" autocomplete="off" /></td>

					</tr>
					<tr>
						<td>작성자</td>
						<td>:</td>
						<td><input class="form-control" name="user_id" type="text"
							id="user_id" value="${topic.user_id}" readonly /></td>
						<td></td>
					</tr>
					<tr>
						<td class="d">첨부파일</td>
						<td class="d">:</td>
						</td>
						<!-- <td class="d"><input class="add_file" name="file[]"
							type="file" multiple /><br> <?php 
						while($rows_file=mysqli_fetch_array($result1)){ ?> <a
							class="file_info" onClick="deleteFile(this)"
							id="<?php echo $rows_file['file_id'];?>"> <i
								class="far fa-save"></i> &nbsp;<?php echo $rows_file['file_name']; ?>
								..... <small>삭제</small> <br></a> <?php } ?></td>-->
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="4"><textarea class="form-control" name="detail"
								rows="3" id="ckeditor" autocomplete="off">${topic.detail}</textarea>
						</td>
					</tr>
					<tr>
						<td colspan="4" class="text-right">
							<button class="btn btn-sm btn-default" type="submit"
								name="Submit">확인</button>
							<button class="btn btn-sm btn-default" type="button"
								id="cancel_btn"
								onClick="location.href= '/topic/${topic.topic_id}'">취소</button>
							<button class="btn btn-sm btn-default" type="button"
								onclick="location.href = '/main'">글목록</button>
						</td>
					</tr>
				</tbody>
			</form>
		</table>
		<!-- Update Table -->
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