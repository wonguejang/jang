package com.wg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wg.dao.MemberDao;
import com.wg.dto.MemberDto;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDao dao;
	
	@Override
	public int insertSignUserService(String id, String pw, String name) {

		int result = dao.insertSignUser(id, pw, name);	
		
		if(result == 1) {
			return result;
		}
		
		return 0;
	}

	@Override
	public int loginCheckUserService(String id, String pw) {

		int check = dao.loginCheckUser(id, pw);
		
		if(check == 1) {
			return check;
		}
		
		return 0;
	}

	@Override
	public MemberDto getUserDetailById(String id) {

		MemberDto dto = dao.getUserDetailById(id);
		
		return dto;
	}

	@Override
	public boolean updatePointFromIdService(int newPoint, String id) {
		
		boolean result = dao.updatePointFromId(newPoint, id);
		
		if(result) {
			return result;
		}
		
		return false;
	}

	@Override
	public List<MemberDto> getAllUserService() {

		List<MemberDto> userList = dao.getAllUser();
		
		return userList;
	}

	@Override
	public int updateUserByIdService(MemberDto dto) {
		
		int result = dao.updateUserById(dto);
		
		System.out.println(result + "서비스 값 1이어야함");
		
		
		if(result == 1) {
			return result;
		}
		
		return 0;
	}

	@Override
	public int deleteUserByIdService(String id) {
		
		int result = dao.deleteUserById(id);
		
		if(result > 0) {
			return result;
		}
		
		return 0;
	}

	@Override
	public int increaseAllMemberPoints(int accumulatedPoints) {

		int result = dao.increaseAllMemberPoints(accumulatedPoints);
		
		if(result > 0) {
			return result;
		}
		return 0;
	}


}
