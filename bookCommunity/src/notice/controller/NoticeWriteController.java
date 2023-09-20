package notice.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.User;
import mvc.command.CommandHandler;
import notice.model.dto.WriteRequest;
import notice.model.dto.Writer;
import notice.service.WriteNoticeService;


public class NoticeWriteController implements CommandHandler {
	
	private static final String FORM_VIEW = "/view/notice/writeForm.jsp";
	private WriteNoticeService writeNoticeService = new WriteNoticeService();
	
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
		
		User authUser = loginedUser(request);
		request.setAttribute("authUser", authUser);
		request.setAttribute("rowSize", rowSize);
		
		return FORM_VIEW;
	}
	
	public User loginedUser(HttpServletRequest request) {
		
		User authUser = (User) request.getSession().getAttribute("authUser");
		return authUser;
	}
	
	private WriteRequest createWriteRequest(User authUser, HttpServletRequest request) {
		
		return new WriteRequest( new Writer(authUser.getmId(),
				authUser.getmName()), 
				request.getParameter("N_TITLE"),
				request.getParameter("N_CONTENT")
				);
	}
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String rowSize = request.getParameter("rowSize");
		User authUser = loginedUser(request);

		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		
		WriteRequest writeReq = createWriteRequest(authUser, request);
		writeReq.validate(errors);
		request.setAttribute("errors", errors);
		
		if(!errors.isEmpty()) {
			request.setAttribute("rowSize", rowSize);
			return FORM_VIEW;
		}
		
		int newNoticeNo = writeNoticeService.write(writeReq);
		
		request.setAttribute("newNoticeNo", newNoticeNo);
		request.setAttribute("rowSize", rowSize);
		
		return "/view/notice/writeSuccess02.jsp";
		}
	
	}
