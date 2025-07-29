<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <title>회원 및 스케줄러 관리</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    <style>
        table {
            border-collapse: collapse;
            width: 60%;
            margin-bottom: 30px;
        }
        th, td {
            border: 1px solid #000;
            padding: 8px 12px;
            text-align: center;
        }
        button {
            padding: 5px 10px;
            margin: 0 5px;
        }
        .login-btn {
            float: right;
            margin-bottom: 20px;
        }
    </style>
    <script>
	   	$(function() {
	   		$('.m_btn').click(function() {
	   			
	   		 	let tr = $(this).closest('tr'); 
	   			
	   		 	let id = tr.find('.userId').text().trim();
	   		 	
	   		 	location.href = "editUser?id="+id;
	   		});
	   		
	   		$('.d_btn').click(function() {
	   			let tr = $(this).closest('tr');
	   			
	   			let id = tr.find('.userId').text().trim();
	   			
				location.href = "deleteUser?id="+id;	   			
	   		});
   		});
    
    </script>

</head>
<body>
    <h1>회원관리
        <button class="login-btn" onclick="location.href='${pageContext.request.contextPath}/'">로그인</button>
    </h1>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>PW</th>
                <th>Name</th>
                <th>Point</th>
                <th>수정</th>
                <th>삭제</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${sessionScope.list}">
		      <tr>
		        <td class = "userId">${user.id}</td>
		        <td>${user.pw}</td>
		        <td>${user.name}</td>
		        <td>${user.point}</td>
		        <td><button class = "m_btn">수정</button></td>
		        <td><button class = "d_btn">삭제</button></td>
		      </tr>
		    </c:forEach>
        </tbody>
    </table>

    <h2>스케줄러관리</h2>
    <button id = "start_btn">스케줄러(20초마다 포인트1 증가) 실행 시작</button>
    <button id = "end_btn">스케줄러 실행 종료</button>


    <script>
	    $('#start_btn').click(function() {
	        $.ajax({
	        	url: '${pageContext.request.contextPath}/scheduler/start',
	            method: 'POST',
	            success: function() {
	                alert('스케줄러가 시작되었습니다.');
	            },
	            error: function() {
	                alert('스케줄러 시작 실패');
	            }
	        });
	    });
	
	    $('#end_btn').click(function() {
	        $.ajax({
	        	url: '${pageContext.request.contextPath}/scheduler/stop',
	            method: 'POST',
	            success: function() {
	                alert('스케줄러가 종료되고 포인트 업데이트가 완료되었습니다.');
	            },
	            error: function() {
	                alert('스케줄러 종료 실패');
	            }
	        });
	    });
	</script>
</body>
</html>
