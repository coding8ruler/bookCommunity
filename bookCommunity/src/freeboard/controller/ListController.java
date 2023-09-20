package freeboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freeboard.model.FreeboardPage;
import freeboard.service.ListService;
import mvc.command.CommandHandler;

//글 목록보기 컨트롤러
public class ListController implements CommandHandler {

	private static final String FORM_VIEW = "/view/freeboard/fListForm.jsp";
	private ListService listService = new ListService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ListController, process()호출 ");

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
	
		FreeboardPage freeboardPage = listService.getFreeboardPage(pageNo, size);

		request.setAttribute("freeboardPage", freeboardPage);
		request.setAttribute("size", size);
		
		return FORM_VIEW;
	}

}
