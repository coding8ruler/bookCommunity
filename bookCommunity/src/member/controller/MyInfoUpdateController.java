package member.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.Member;
import member.model.User;
import member.service.MyPageService;
import mvc.command.CommandHandler;

public class MyInfoUpdateController implements CommandHandler {

	private MyPageService myPageService = new MyPageService();
	private static final String FORM_VIEW = "view/member/myPage.jsp";
	
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

	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		
		return FORM_VIEW;
	}


	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		
		User user = (User)request.getSession().getAttribute("authUser");
		
		String mPwd = request.getParameter("mPwd");
		String email = request.getParameter("email");
		String postcode = request.getParameter("postcode");
		String jibunAddress = request.getParameter("jibunAddress");
		
		myPageService.myPageUpdate(user.getmId(), mPwd, email, postcode, jibunAddress);
		return "/view/member/updateSuccess.jsp";
	}

}
