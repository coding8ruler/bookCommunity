package event.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import event.model.EventData;
import event.service.ReadEventService;

public class EventReadHandler implements CommandHandler {

	private ReadEventService readEventService = new ReadEventService();
	
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		
		String strNo =  req.getParameter("no");
		if(strNo==null) {
			throw new RuntimeException(); 
		}
		int no = Integer.parseInt(strNo);
	
		String strPageNo = req.getParameter("pageNo");
		
		int pageNo = 1;
		if(strPageNo!=null) {
			pageNo = Integer.parseInt(strPageNo);
		}
		
		String strRowSize = req.getParameter("rowSize");
		
		int size = 3;
		if(strRowSize!=null) {
			size = Integer.parseInt(strRowSize);
		}
		
		EventData eventData = readEventService.getEvent(no, true);
		
		req.setAttribute("eventData", eventData);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("rowSize", size);
		
		return "/view/event/readEvent.jsp";
	}

}
