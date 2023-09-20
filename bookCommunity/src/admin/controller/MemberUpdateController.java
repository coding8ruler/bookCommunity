package admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.exception.MemberNotFoundException;
import admin.service.MemberUpdateService;
import mvc.command.CommandHandler;

public class MemberUpdateController implements CommandHandler {
	
	private static final String FORM_VIEW = "/view/member/memberList.jsp";
	private MemberUpdateService mus = new MemberUpdateService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		String[] mId = request.getParameterValues("mId");
		String[] grade = request.getParameterValues("grade");
		
		try {
			for(int i=0; i<mId.length; i++) {
				mus.memberUpdate(mId[i], Integer.parseInt(grade[i]));
			}
			return "/view/member/memberUpdate.jsp";
		}catch(MemberNotFoundException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
	}

}
