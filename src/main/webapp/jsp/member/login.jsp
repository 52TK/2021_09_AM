
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<h1>로그인</h1>

	<!--<a href="https://www.naver.com" onclick="if( confirm('이동 할래??') == false) return false;">naver</a>-->

	<script>
		var LoginForm__submitDone = false;
		function LoginForm__submit(form) {
			if (LoginForm__submitDone) {
				alert('로그인 중 입니다.');
				return;
			}
			form.loginId.value = form.loginId.value.trim();
			if (form.loginId.value.length == 0) {
				alert('로그인 아이디를 입력해주세요.');
				form.loginId.focus();
				return;
			} 
			form.loginPwConfirm.value = form.loginPwConfirm.value.trim();
			if (form.loginPw.value != form.loginPwConfirm.value) {
				alert('비밀번호가 일치하지 않습니다.');
				form.loginPw.focus();
				return;
			}
			form.sumbit();
			LoginForm__submitDone = true;
		}
	</script>


	<form action="doLogin" method="POST"
		onsubmit="LoginForm__submit(this); return false;">

		<div>
			로그인 아이디 : <input placeholder="로그인 아이디를 입력해주세요."
				name="loginId" type="text" />
		</div>
		
		<div>
			로그인 비밀번호 : <input placeholder="로그인 비밀번호를 입력해주세요."
				name="loginPw" type="password" />
		</div>
		
		<div>
			<!-- <input type="submit" value="작성" /> -->
			<button type="submit">로그인</button>
			<button type="button">	
				<a href="../home/main">취소</a>
			</button>
		</div>
	</form>
</body>
</html> 