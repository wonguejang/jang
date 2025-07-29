<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" pageEncoding = "UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>Home</title>
</head>
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
		#login {
			width: 212px;
    		margin-top: 5px;
		} 
		#sign {
			width: 212px;
    		margin-top: -10px;
		}
	</style>
	<script>
	  	$(function() {
		    $('#sign').click(function() {
		        location.href = "sign";
		    });
		   
	  	});
	  	
	</script>
	<c:if test="${not empty sessionScope.login_message}">
	    <script>
	        alert('${sessionScope.login_message}');
	    </script>
	    <%
	        session.removeAttribute("login_message");
	    %>
	</c:if>
		
<body>
	<div>
		<h1>로그인</h1>
		<form action = "loginCheck" method = "post">
			<div class = "outb">
				<div class = "inone">
					<span>ID : </span>
					<span>PW : </span>
				</div>
				<div class=  "intwo">
					<input type = "text" id = "id" name = "id"/>
					<input type = "password" id = "pw" name = "pw"/>
				</div>
			</div>
			<button id = "login" type = "submit">로그인</button><br/>
		</form>
			<button id = "sign" type = "button">회원가입</button>
	</div>
	
</body>
</html>
