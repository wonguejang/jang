package com.wg.service;

import java.util.List;

import com.wg.dto.MemberDto;

public interface MemberService {

	//회원가입
	int insertSignUserService(String id, String pw, String name);

	//로그인
	int loginCheckUserService(String id, String pw);

	//회원정보 가져오기
	MemberDto getUserDetailById(String id);
	
	//포인트 업데이트
	boolean updatePointFromIdService(int newPoint, String id);

	//전체 유저 가져오기
	List<MemberDto> getAllUserService();
	
	int updateUserByIdService(MemberDto dto);
	
	int deleteUserByIdService(String id);

	int increaseAllMemberPoints(int accumulatedPoints);
	

}
