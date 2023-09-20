package admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.service.MemberService;
import member.model.Member;
import mvc.command.CommandHandler;

public class MemberController implements CommandHandler {

	private MemberService memberService = new MemberService();
	@Override
	
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Member> memberList = memberService.getReboardList();
		
		request.setAttribute("memberList", memberList);
		
		return "/view/member/memberList.jsp";
	}

}
