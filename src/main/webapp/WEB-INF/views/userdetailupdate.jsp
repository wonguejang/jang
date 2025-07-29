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
				alert("수정되었습니다.");	
			});
		});
	
	</script>
</head>
<body>
	<div>
		<h1>회원관리 - 수정관리자</h1>
		<form action = "editUserAction" method = "post">
			<div class = "outb">
				<div class = "inone">
					<span>ID : </span>
					<span>PW : </span>
					<span>Name : </span>
					<span>Point : </span>
				</div>
				<div class=  "intwo">
					<input type = "text" id = "id" name = "id" value = "${dto.id}" readonly/>
					<input type = "text" id = "pw" name = "pw" value = "${dto.pw }"/>
					<input type = "text" id = "name" name = "name" value = "${dto.name}"/>
					<input type = "text" id = "point" name = "point" value = "${dto.point}"/>
				</div>
			</div>
			<button id = "sign" type = "submit">제출</button>
		</form>
	</div>
	
</body>
</html>