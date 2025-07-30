<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>홈 화면</title>
	<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
	<style>
		#main {
	    	border: 1px solid grey;
			width : auto;
		}
		
		#top_content {
		    position: absolute;
		    right: 40px;
		    top: 60px;
		}
		
		.imgbox {
		    display: flex;
		    justify-content: space-around;
		}
		
		.points {
			display: flex;
	    	justify-content: space-around;
	    	margin-bottom: 30px;
		}		
		
		#logo_img {
		    position: relative;
    		left: 840px;
		}
		#intro_btn, #java_btn, #cpp_btn {
			all : unset;
		}
//여기인가
	</style>
	<script>
		let point = "${dto.point}"
		
		function updatePoint(newPoint) {
			$.ajax({
				url : 'updatePoint',
				data : {"newPoint" : newPoint},
				success : function(response) {
					if(response === "success"){
						alert("업데이트 성공");
						point = newPoint;
					}else {
						alert("업데이트 실패");
					}
				},
				error : function(r,e,s){
					alert("포인트 업데이트 오류(ajax오류)");
				}
			});
		}	
		$(function() {
			$('.logout').click(function() {
				alert("로그아웃 되었습니다.");
				location.href = "logout"
			});

			
			$('#intro_btn').click(function() {
				if(point < 100000){
					alert("포인트가 부족 합니다. 광고를 클릭하세요");
				}else {
					alert("컨텐츠(intro)를 구입하셨습니다.");				
					let newPoint = point - 100000;
					updatePoint(newPoint);
				}
				
			});
			
			$('#java_btn').click(function() {
				if(point < 500000){
					alert("포인트가 부족 합니다. 광고를 클릭하세요");
				}else {
					alert("컨텐츠(java)를 구입하셨습니다.");				
					let newPoint = point - 500000;
					updatePoint(newPoint);
				}
			});
			
			$('#cpp_btn').click(function() {
				if(point < 300000){
					alert("포인트가 부족 합니다. 광고를 클릭하세요");
				}else {
					alert("컨텐츠(c++)를 구입하셨습니다.");				
					let newPoint = point - 300000;
					updatePoint(newPoint);
				}	
			});
			
			$('#logo_img').click(function() {
				$.ajax({
					url: '${pageContext.request.contextPath}/randomPoint',
					method : 'post',
					success : function(response) {
						if(response !== "0"){
							alert(response+"가 적립되었습니다.");		
							location.href = "https://koreaisacademy.com";

						}else{
							alert("response는 들어왓는데");							
						}
						
					},
					error : function(r, e, s) {
						alert("뭔에러?에러!");
					}
					
				});
			});
			
			
		});
	
	</script>
	
</head>
<body>
	<div id = "main">
		<h1>메인페이지</h1>
		<div id = "top_content">
			<span>${dto.name}(${dto.id}) 님 안녕하세요</span> <button type = "button" class = "logout">로그아웃</button><br/>
			<span>포인트 : ${dto.point} 점</span>
		</div>
		<div>
			<h2>구입할 컨텐츠를 선택하세요.</h2>
			<div class = "imgbox">
				<div>
					<button id = "intro_btn" type = "button">
						<img src="${pageContext.request.contextPath}/resources/images/Intro_350_408.png" />
					</button>
				</div>
				<div>
					<button id = "java_btn" type = "button">
						<img src = "${pageContext.request.contextPath}/resources/images/Java_350_408.png"/>
					</button>
				</div>
				<div>
					<button id = "cpp_btn" type = "button">
						<img src = "${pageContext.request.contextPath}/resources/images/Cpp_350_408.png"/>
					</button>
				</div>
			</div>
			<div class = "points">
				<span>100,000포인트</span>
				<span>500,000포인트</span>
				<span>300,000포인트</span>
			</div>
			<div id = "logo_img">
				<img src = "${pageContext.request.contextPath}/resources/images/korea_it.png"/>
			</div>
		</div>
	
	</div>
	
</body>
</html>
