<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
	<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
	<style>
		.outb {
			display : flex;
		}	
		.inone {
			display : inline-grid;

		}
		.intwo {
			display : inline-grid;
			margin-left : 10px;
		}
		#sign {
			width: 235px;
    		margin-top: 5px;
		}
	</style>
	<script>
		$(function() {
			$('#sign').click(function() {
				alert("가입되었습니다. 로그인 해주세요");	
			});
		});
	
	</script>
</head>
<body>
	<form action = "userManage">
	</form>
	<div>
		<h1>회원가입</h1>
		<form action = "signAction">
			<div class = "outb">
				<div class = "inone">
					<span>ID : </span>
					<span>PW : </span>
					<span>Name : </span>
				</div>
				<div class=  "intwo">
					<input type = "text" id = "id" name = "id"/>
					<input type = "password" id = "pw" name = "pw"/>
					<input type = "text" id = "name" name = "name"/>
				</div>
			</div>
			<button id = "sign" type = "submit">회원가입</button>
		</form>
	</div>
	
</body>
</html>