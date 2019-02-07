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
		<!-- View Table -->
		<table class="view_table table table-borderless table-sm mb-0">
			<thead>
				<tr>
					<td colspan="3"><b>${topic.topic}</b></td>
					<td class="text-right" width="20%">${topic.datetime}</td>
				</tr>
				<tr>
					<td colspan="4">�ۼ���: ${topic.user_id}</td>
				</tr>
				<tr>
					<td width="10%" class="border-bottom border-dark d">÷������:</td>
					<td colspan="3">
						<a class="file_info" href="" download="">
							<i class="far fa-save"></i> &nbsp;
						</a><br>
					</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="4">${topic.detail}</td>
				</tr>
				<tr>
					<td colspan="2" width="15%">
						<button class="btn btn-sm btn-default p-2" id="next_btn">&lt</button>
						<button class="btn btn-sm btn-default p-2" id="prev_btn">&gt</button>
					</td>
					<td colspan="2" class="text-right">
						<button class="btn btn-sm btn-default" id="edit_btn" onClick="location.href='/edit/${topic.topic_id}'">����</button>
						<button class="btn btn-sm btn-default" id="delete_btn" onClick="location.href='/delete/${topic.topic_id}'">����</button>
						<button class="btn btn-sm btn-default" onclick="location.href = '/main'">�۸��</button>
					</td>
				</tr>
			</tbody>
		</table>
		<!-- /View Table -->
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
	</script>
	<!-- ========================================================== -->
</body>
</html>