package com.wg.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wg.dto.MemberDto;
import com.wg.service.MemberService;
import com.wg.service.SchedulerService;

@Controller
public class HomeController {
	
	@Autowired
    private SchedulerService schedulerService;

    @PostMapping("/scheduler/start")
    @ResponseBody
    public String startScheduler() {
        try {
            schedulerService.startScheduler();
            return "스케줄러 시작";
        } catch (SchedulerException e) {
            e.printStackTrace();
            return "스케줄러 시작 실패";
        }
    }

    @PostMapping("/scheduler/stop")
    @ResponseBody
    public String stopScheduler() {
        try {
            schedulerService.stopScheduler();
            return "스케줄러 종료 및 포인트 업데이트 완료";
        } catch (SchedulerException e) {
            e.printStackTrace();
            return "스케줄러 종료 실패";
        }
    }
	
	@Autowired
	MemberService ms;
	
	//시작화면
	@RequestMapping("/")
	public String start() {
		
		return "login";
	}
	
	//홈으로
	@RequestMapping("home")
	public String home(HttpSession session) { 
		String id = (String)session.getAttribute("id");
		
		System.out.println(id);
		
		if(id == null) {
			session.setAttribute("login_message", "로그인이 필요합니다");
			return "redirect:/";
		}
		
		return "home";
	}
	
	//로그인
	@RequestMapping("login")
	public String login() {
		
		return "login";
	}
	
	//로그인 처리
	@RequestMapping("loginCheck")
	public String loginCheck(HttpServletRequest request, HttpSession session) {
		String id = (String)request.getParameter("id");
		String pw = (String)request.getParameter("pw");
		System.out.println(id + " / " + pw);
		
		int loginResult = ms.loginCheckUserService(id, pw);
		MemberDto dto = ms.getUserDetailById(id);
		
		if(id != null && pw != null) {
			session.setAttribute("dto", dto);
			session.setAttribute("id", dto.getId());
		}
		
		if(id.equals("admin") && loginResult == 1) {
			System.out.println("관리자로 보내");
			//관리자페이지 이동
			return "redirect:/userManage";
		}
		
		if(loginResult == 1) {
			System.out.println("일반으로 보내");
		
			//일반 회원 이동
			return "redirect:/home";
		}
		
		if(loginResult != 1) {
			session.setAttribute("login_message", "아이디/비밀먼호를 다시 확인하세요.");
		}
		
		return "login";
	}
	
	//로그아웃
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/login";
	}
	
	//포인트 업데이트
	@RequestMapping("updatePoint")
	@ResponseBody
	public String updatePoint(HttpSession session, @RequestParam("newPoint") int newPoint) {
		String id = (String)session.getAttribute("id");
		
		boolean result = ms.updatePointFromIdService(newPoint, id);
		
		if(result) {
			return "success";
		}
		return "fail";
		
	}
	
	//광고보기
	@RequestMapping("randomPoint")
	@ResponseBody
	public String randomPoint(HttpSession session) {
		String id = (String)session.getAttribute("id");
		int random = (int)(Math.random()*1000)+1;
		
		boolean result = ms.updatePointFromIdService(random, id);
		
		if(result) {
			session.setAttribute("rPoint", random);
			return String.valueOf(random);
		}
		
		return "0";
	}
	
	//관리자
	@RequestMapping("userManage")
	public String userManage(HttpSession session) {
		//전체 유저 가져오기
		
		List<MemberDto> userList = ms.getAllUserService();
		
		session.setAttribute("list", userList);
		
		return "userToManage";
	}
	
	//회원 수정
	@RequestMapping("editUser")
	public String editUser(HttpServletRequest request, HttpSession session) {
		String id = (String)request.getParameter("id");

		MemberDto dto = ms.getUserDetailById(id);
		
		session.setAttribute("dto", dto);
		
		return "userdetailupdate";
	}
	
	//회원수정업
	@RequestMapping("editUserAction")
	public String editUserAction(HttpSession session, MemberDto dto) {
		
		int result = ms.updateUserByIdService(dto);
		
		if(result == 1) {
			System.out.println("성공!");
		}
		
		return "redirect:/userManage";
	}
	
	//회원삭제
	@RequestMapping("deleteUser")
	public String deleteUser(String id) {
		System.out.println("id : " + id);
		
		int result = ms.deleteUserByIdService(id);
		
		if(result == 1) {
			return "redirect:/userManage";
		}
		
		return "redirect:/userManage";
	}
	
	//회원가입
	@RequestMapping("sign")
	public String sign() {
		
		return "sign";
	}
	
	//회원가입 완려
	@RequestMapping("signAction")
	public String signAction(HttpServletRequest request) {
		String id = (String)request.getParameter("id");
		String pw = (String)request.getParameter("pw");
		String name = (String)request.getParameter("name");
		
		//여기서 db처리
		int check = ms.insertSignUserService(id, pw, name);
		
		if(check == 1) {
			return "redirect:/login";
		}
		
		return "redirect:/sign";
	}
}
