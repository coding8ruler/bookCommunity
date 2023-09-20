package admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.service.MemberDeleteService;
import mvc.command.CommandHandler;

public class MemberDeleteController implements CommandHandler {

	private MemberDeleteService memberDeleteService = new MemberDeleteService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		String m_no = request.getParameter("no");
		System.out.println("m_no"+m_no);
		int no = Integer.parseInt(m_no);
				
		int cnt = memberDeleteService.memberDelete(no);
				
		request.setAttribute("cnt", cnt);
				
		return "/view/member/deleteForm.jsp";
		
	}

}
