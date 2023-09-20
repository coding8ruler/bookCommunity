package member.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.exception.MemberNotFoundException;
import member.exception.InvalidEmailException;
import member.exception.InvalidNameException;
import member.model.Member;
import member.service.FindIdService;
import mvc.command.CommandHandler;

public class FindIdController implements CommandHandler {

	private static final String FROM_VIEW="view/member/findIdForm.jsp";
	private FindIdService findidService = new FindIdService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request, response);
		}
		else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request, response);
		}
		else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
			System.out.println("FindIdHandle.processForm()");
		return FROM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		System.out.println("FindIdHandler.processSubmit()");
		
		Map<String, Boolean> errors = new HashMap<String, Boolean> ();
		request.setAttribute("errors", errors);
		
		String mName = request.getParameter("name");
		String email = request.getParameter("email");
		
		if(mName==null || mName.isEmpty()) {
			errors.put("mName", Boolean.TRUE);
		}
		if(email==null || email.isEmpty()) {
			errors.put("email", Boolean.TRUE);
		}
		if(!errors.isEmpty()) {
			return FROM_VIEW;
		}
		try {
			Member member = this.findidService.Findid(mName, email);
			request.setAttribute("member", member);
			System.out.println(member.getmId());
			return "view/member/findIdSuccess.jsp";
		}catch(MemberNotFoundException e) {
			errors.put("idOrPwNotMatch", Boolean.TRUE);
			return FROM_VIEW;
		}
	}

}
