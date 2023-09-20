package event.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.User;
import mvc.command.CommandHandler;
import event.exception.EventNotFoundException;
import event.exception.PermissionDeniedException;
import event.model.EventData;
import event.model.UpdateRequest;
import event.service.ReadEventService;
import event.service.UpdateEventService;

public class EventUpdateHandler implements CommandHandler {

	private static final String FORM_VIEW = "/view/event/updateForm.jsp";
	
		private ReadEventService readService = new ReadEventService();		
		private UpdateEventService updateService = new UpdateEventService();
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
		System.out.println("EventUpdateHandler processForm");
		
			String strNo =  request.getParameter("no");
		try {
			if(strNo==null) {
				throw new RuntimeException();  
			}
			int no = Integer.parseInt(strNo);
			
			String strPageNo = request.getParameter("pageNo");
			int pageNo = 1;
			if(strPageNo!=null) {
				pageNo = Integer.parseInt(strPageNo);
			}
			
			String strRowSize = request.getParameter("rowSize");
			int size = 3;
			if(strRowSize!=null) {
				size = Integer.parseInt(strRowSize);;
			}
			
			String e_title = request.getParameter("title");
			String e_content = request.getParameter("content");
			
			EventData eventData = readService.getEvent(no, false);
			
			User authUser = (User) request.getSession().getAttribute("authUser");
			
			if(!canUpdate(authUser, eventData)) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}	
			
			UpdateRequest upReq = new UpdateRequest(authUser.getmId(), 
													 no, 
													 eventData.getEvent().getE_writer().getE_writername(),
													 eventData.getEvent().getE_title(), 
													 eventData.getEvent().getE_content());
			request.setAttribute("upReq", upReq);			
			
			return FORM_VIEW; 
		} catch (EventNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("NoticeUpdateHandler processSubmit");
	
		String strNo =  request.getParameter("no");
		if(strNo==null) {
			throw new RuntimeException();
		}
		int no = Integer.parseInt(strNo);

		String strPageNo = request.getParameter("pageNo");
		int pageNo = 1;
		if(strPageNo!=null) {
			pageNo = Integer.parseInt(strPageNo);
		}
		String strRowSize = request.getParameter("rowSize");
		int size = 3;   
		if(strRowSize!=null) {
			size = Integer.parseInt(strRowSize);;
		}
	
		String e_title = request.getParameter("title");
		String e_content = request.getParameter("content");
		String e_writername = request.getParameter("writername");
		User authUser = (User) request.getSession().getAttribute("authUser");
		
		UpdateRequest upReq = new UpdateRequest(authUser.getmId(), 
												 no,
												 e_writername,
												 e_title,
												 e_content);
		request.setAttribute("upReq", upReq);
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		request.setAttribute("errors", errors);
		upReq.validate(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			updateService.update(upReq);
			return "/view/event/updateSuccess.jsp";
		}catch (EventNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}catch (PermissionDeniedException e) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}	
	private boolean canUpdate(User authUser, EventData eventData) {
		String userId = authUser.getmId();
		String writerId = eventData.getEvent().getE_writer().getE_writerid();
		return userId.equals(writerId);
	}
}