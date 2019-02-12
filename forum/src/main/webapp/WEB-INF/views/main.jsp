<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
	<!-- (로그인 전/후 구별) Login Form / User Info -->
	<c:choose>
		<c:when test="${empty session.s_user_id}">
			<jsp:include page="/WEB-INF/views/login_bf.jsp" flush="true" />
		</c:when>
		<c:when test="${!empty session.s_user_id}">
			<jsp:include page="/WEB-INF/views/login_af.jsp" flush="true" />
		</c:when>
	</c:choose>
	
	<!-- NAVBAR -->
	<div class="container mb-3 px-0">
		<nav class="navbar navbar-expand-sm navbar-dark default-color">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item active"><a class="nav-link" id="all"
					href="/main">전체보기</a></li>
				<li class="nav-item"><a class="nav-link" id="news"
					href="/main/news">뉴스</a></li>
				<li class="nav-item"><a class="nav-link" id="movie"
					href="/main/movie">영화</a></li>
				<li class="nav-item"><a class="nav-link" id="music"
					href="/main/music">뮤직</a></li>
				<li class="nav-item"><a class="nav-link" id="book"
					href="/main/book">책</a></li>
			</ul>
		</div>
		</nav>
	</div>
	
	<!-- BOARD -->
	<div class="container white p-1">
		<table class="main_table table table-sm table-hover text-center mb-0">
			<thead>
				<tr>
					<td width="10%"><strong>번호</strong></td>
					<td><strong>글제목</strong></td>
					<td><strong>작성자</strong></td>
					<td width="10%"><strong>조회수</strong></td>
					<td width="15%"><strong>작성일</strong></td>
				</tr>
			</thead>
			<tbody id="table_body">
				<c:set var="num" value="1"/>
				<c:forEach var="list" items="${list}">
					<tr onClick="listClick('${list.topic_id}', '${num}')">
						<td>${num}</td>
						<td>${list.topic}</td>
						<td>${list.user_id}</td>
						<td>${list.view}</td>
						<td>${list.datetime}</td>
					</tr>
				<c:set var="num" value="${num + 1}"/>
				</c:forEach>
				<c:remove var="num"/>
			</tbody>
			<tfoot>
				<!-- Search -->
				<form id="f1" name="f1" method="post" action="/main/search">
					<tr>
						<td><select name="type"
							class="form-control form-control-sm">
								<option value="topic">제목</option>
								<option value="user_id">작성자</option>
						</select></td>
						<td width="25%"><input class="form-control form-control-sm"
							name="search" type="text" autocomplete="off" /></td>
						<td class="text-left">
							<button class="btn btn-sm btn-default" type="submit">검색</button>
						</td>
						<td class="text-right" colspan="2"><a role="button"
							class="btn btn-sm btn-default" id="create_btn"><strong>글작성</strong></a>
						</td>
					</tr>
				</form>
			</tfoot>
		</table>
		<br>
		<!-- pagination -->
		<div><ul id="pagination" class="pagination-sm justify-content-center"></ul></div>
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
	<script src="/js/jquery.twbsPagination.min.js"></script>
	<script>
	
		//list Click Event
		function listClick(topic_id, num) {
			<c:choose>
				<c:when test="${empty session.s_user_id}">
					//alert("로그인 후 이용가능합니다.");
					$('#modalLoginForm').modal('toggle');
				</c:when>
				<c:when test="${!empty session.s_user_id}">
					location.href='/topic/'+ topic_id + '/' + num;
				</c:when>
			</c:choose>
		}
	
		//nav item active
		var s_category = '${session.s_category}';
		if (s_category!='') {
			$('.navbar-nav').find('li.active').removeClass('active');
			$('#'+s_category).parent('li').addClass('active');
		}
		//CREATE => check login state
		$("#create_btn").click(function() {
			<c:choose>
			<c:when test="${empty session.s_user_id}">
				//alert("로그인 후 이용가능합니다.");
				$('#modalLoginForm').modal('toggle');
			</c:when>
			<c:when test="${!empty session.s_user_id}">
				location.href = "/new";
			</c:when>
			</c:choose>
		});
		
		//PAGINATION
		//pages total number
		var tp = Math.ceil(${fn:length(list)}/5);
		//number of pages that page shows (화면에 보여주는 최대 페이지 숫자)
		var vp = 5;
		//number of table rows that page shows (한 페이지에 나오는 게시글 갯수)
		var vt = 5;
		
		//Pagination (at least one topic is needed)
		if(tp>0) {
			$('#pagination').twbsPagination({
				totalPages: tp,
				visiblePages: vp,
				next: 'Next',
				prev: 'Prev',
				onPageClick: function (event, page) {
        	//fetch content and render here
        	//first, show all topic
        	$(".main_table tbody tr").each(function(i) {
        		$(this).show();
        	});

        	//second, hide outrange topic
        	$(".main_table tbody tr").each(function(i) {
        		if(parseInt($(this).children().eq(0).text()) >page*vt || parseInt($(this).children().eq(0).text()) <=(page-1)*vt)
        			$(this).hide();
        	});
        }
      });
		}
		else {
			var ins = "<tr><td colspan=5>데이터가 존재하지 않습니다.</td></tr>";
			$('.table tbody').html(ins);
		}
		
	</script>
	<!-- ========================================================== -->
</body>
</html>