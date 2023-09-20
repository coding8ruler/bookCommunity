package event.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import event.service.DeleteEventService;

public class EventDeleteHandler implements CommandHandler {

	private DeleteEventService deleteEventService = new DeleteEventService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
	
		String strNo = req.getParameter("no");
		int no = Integer.parseInt(strNo);
				
		int cnt = deleteEventService.deleteEvent(no);
				
		req.setAttribute("cnt", cnt);
				
		return "/view/event/deleteForm.jsp";
		
	}

}
