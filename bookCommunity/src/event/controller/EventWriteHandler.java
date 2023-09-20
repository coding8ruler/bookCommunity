package event.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.User;
import mvc.command.CommandHandler;
import event.model.WriteRequest;
import event.model.EWriter;
import event.service.WriteEventService;


public class EventWriteHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/view/event/writeForm.jsp";
	private WriteEventService writeEventService = new WriteEventService();
	
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
		System.out.println("EventWriteHandler processForm() ");
		
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
		
		return new WriteRequest( new EWriter(authUser.getmId(),
				authUser.getmName()), 
				request.getParameter("E_TITLE"),
				request.getParameter("E_CONTENT")
				);
	}
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("EventWriteHandlerŬ������  POST��û�� processSubmit()");
		
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
		
		int newEventNo = writeEventService.write(writeReq);
		
		request.setAttribute("newEventNo", newEventNo);
		request.setAttribute("rowSize", rowSize);
		
		return "/view/event/writeSuccess02.jsp";
		}
	
	}
