package qna.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.User;
import mvc.command.CommandHandler;
import qna.model.dto.WriteRequest;
import qna.model.dto.Writer;
import qna.service.WriteQnaService;


public class QnaWriteController implements CommandHandler {
	
	private static final String FORM_VIEW = "/view/qna/writeForm.jsp";
	private WriteQnaService writeQnaService = new WriteQnaService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request, response);  
			
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request, response);
		
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	private String processForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String rowSize = request.getParameter("rowSize");
		
		User authUser = User(request);
		request.setAttribute("authUser", authUser);
		request.setAttribute("rowSize", rowSize);
		
		return FORM_VIEW;
	}
	
	public User User(HttpServletRequest request) {
		
		User authUser = (User) request.getSession().getAttribute("authUser");
		return authUser;
	}
	
	private WriteRequest createWriteRequest(User authUser, HttpServletRequest request) {
		
		return new WriteRequest( new Writer(authUser.getmId(),
				authUser.getmName(), authUser.getM_no()), 
				request.getParameter("Q_TITLE"),
				request.getParameter("Q_CONTENT")
				);
	}
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String rowSize = request.getParameter("rowSize");
		User authUser = User(request);
		request.setAttribute("authUser", authUser);

		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		
		WriteRequest writeReq = createWriteRequest(authUser, request);
		writeReq.validate(errors);
		request.setAttribute("errors", errors);
		
		if(!errors.isEmpty()) {
			request.setAttribute("rowSize", rowSize);
			return FORM_VIEW;
		}
		
		int newQnaNo = writeQnaService.write(writeReq);
		
		request.setAttribute("newQnaNo", newQnaNo);
		request.setAttribute("rowSize", rowSize);
		
		return "/view/qna/newQnaSuccess.jsp";
		}
	
	}
