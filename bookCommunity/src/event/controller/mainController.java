package event.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import event.model.EventPage;
import event.service.EventService;
import mvc.command.CommandHandler;

public class mainController implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		final String FORM_VIEW = "/index.jsp";
		EventService eventService = new EventService();
		
		String strPageNo = request.getParameter("pageNo");
		int pageNo = 1;
		if(strPageNo!=null) {
			pageNo = Integer.parseInt(strPageNo);
		}
		
		String strRowSize = request.getParameter("rowSize");
		int size = 1;
		if(strRowSize==null) {
			size = 10;
		}else {
			size = Integer.parseInt(strRowSize);
		}
	
		EventPage eventPage = eventService.getEventPage(pageNo, size);

		request.setAttribute("eventPage", eventPage);
		request.setAttribute("size", size);
		
		return FORM_VIEW;
	}

}
