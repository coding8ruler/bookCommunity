package event.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import event.model.EventPage;
import event.service.EventService;


public class EventHandler implements CommandHandler {

	private static final String FORM_VIEW = "/view/event/eventForm.jsp";
	private EventService eventService = new EventService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("EventHandler, process()?˜¸ì¶? ");

		String strPageNo = request.getParameter("pageNo");
		int pageNo = 1;
		if(strPageNo!=null) {
			pageNo = Integer.parseInt(strPageNo);
		}
		
		String strRowSize = request.getParameter("rowSize");
		int size = 1;
		if(strRowSize==null) {
			size = 3;
		}else {
			size = Integer.parseInt(strRowSize);
		}
	
		EventPage eventPage = eventService.getEventPage(pageNo, size);

		request.setAttribute("eventPage", eventPage);
		request.setAttribute("size", size);
		
		return FORM_VIEW;
	}

}
