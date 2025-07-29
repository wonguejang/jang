package com.wg.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wg.dto.MemberDto;

@Repository
public class MemberDaoImpl implements MemberDao {
	@Autowired
	SqlSession sqlSession;
	
	
	@Override
	public int insertSignUser(String id, String pw, String name) {
		HashMap<String, String> map1 = new HashMap<>();
		map1.put("id", id);
		map1.put("pw", pw);
		map1.put("name", name);
		
		int result = sqlSession.insert("com.wg.dao.insertUser",map1);
		
		//1이면 성공
		if(result == 1) {
			return result;
		}
		
		return 0;
	}

	@Override
	public int loginCheckUser(String id, String pw) {
		HashMap<String, String> map1 = new HashMap<>();
		map1.put("id",id);
		map1.put("pw", pw);
		
		int check = sqlSession.selectOne("com.wg.dao.loginCheck",map1);
		
		if(check == 1) {
			return check;
		}
		
		return 0;
	}

	@Override
	public MemberDto getUserDetailById(String id) {
		System.out.println("id로 정보가져오기");

		MemberDto dto = sqlSession.selectOne("com.wg.dao.selectUserDetail",id);

		return dto;
	}

	@Override
	public boolean updatePointFromId(int newPoint, String id) {
		HashMap<String, Object> map1 = new HashMap<>();
		map1.put("id", id);
		map1.put("newPoint", newPoint);

		int result = sqlSession.update("com.wg.dao.updatePoint",map1);
		
		if(result == 1) {
			return true;
		}
		
		return false;
	}

	@Override
	public List<MemberDto> getAllUser() {
		
		List<MemberDto> userList = sqlSession.selectList("com.wg.dao.allUser");

		return userList;
	}

	@Override
	public int updateUserById(MemberDto dto) {

		int result = sqlSession.update("com.wg.dao.updateUser", dto);
		
		System.out.println("다오 : " + result);
		
		if(result == 1) {
			return result;
		}
		
		return 0;
	}

	@Override
	public int deleteUserById(String id) {

		int result = sqlSession.delete("com.wg.dao.deleteUserById",id);
		
		if(result == 1) {
			return result;
		}
		
		return 0;
	}

	@Override
	public int increaseAllMemberPoints(int addPoint) {

		int result = sqlSession.update("com.wg.dao.timepointupdate", addPoint);

		if(result > 0) {
			return result;
		}
		
		return 0;
	}

}
