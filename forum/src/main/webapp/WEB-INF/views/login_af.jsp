<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container px-0 d-flex mb-2">
	<div class="d-table-cell mr-auto align-items-center pt-3">
		<b class="text-default align-middle">회원등급</b>
		<span class="badge badge-default"> ${session.s_user_grade} </span>
	</div>
	<div class="d-table-cell">
		<b class="text-default align-middle"> ${session.s_user_name}님(${session.s_user_id})</b>
		<button class="btn btn-outline-default btn-sm px-2" id="myinfo_btn">
			<b>내정보</b>
		</button>
		<button class="btn btn-outline-default btn-sm px-2" id="my_btn">
			<b>내 글</b>
		</button>
		<button class="btn btn-outline-default btn-sm px-2" id="logout_btn">
			<i class="fas fa-sign-in-alt"></i> <b>logout</b>
		</button>
	</div>
</div>

<script>
	//my info
	$("#myinfo_btn").click( function () {
		location.href = '';
	});

	//my post
	$("#my_btn").click( function () {
		location.href = '';
	});

	$("#logout_btn").click( function () {
		alert("로그아웃되었습니다.");
		location.href="/logout";
	});
</script>