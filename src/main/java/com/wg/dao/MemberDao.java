package com.wg.dao;

import java.util.List;

import com.wg.dto.MemberDto;

public interface MemberDao {
	
	//회원 가입
	int insertSignUser(String id, String pw, String name);
	
	//로그인 체크
	int loginCheckUser(String id, String pw);
	
	//아이디로 회원정보 가져오기
	MemberDto getUserDetailById(String id);
	
	//포인트 업데이트
	boolean updatePointFromId(int newPoint, String id);
	
	List<MemberDto> getAllUser();
	
	int updateUserById(MemberDto dto);
	
	int deleteUserById(String id);

	int increaseAllMemberPoints(int addPoint);
}

