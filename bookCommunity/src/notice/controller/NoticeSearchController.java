package notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import notice.model.dto.SearchPage;
import notice.service.SearchNoticeService;

public class NoticeSearchController implements CommandHandler {
	
	private SearchNoticeService searchNoticeService = new SearchNoticeService();
	
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String choice = req.getParameter("choice");
		String searchText = req.getParameter("searchText");
		String strPageNo = req.getParameter("pageNo");
		String strRowSize = req.getParameter("rowSize");
		
		int pageNo = 1;
		if(strPageNo!=null) {
			pageNo = Integer.parseInt(strPageNo);
		}
		int size = 1;
		if(strRowSize==null) {
			size = 10;
		}else {
			size = Integer.parseInt(strRowSize);
		}
		SearchPage searchpage = searchNoticeService.getSearchPage(choice,searchText,pageNo,size);
		
		req.setAttribute("searchpage", searchpage);
		req.setAttribute("pageNo", pageNo);
		
		return "/view/notice/searchForm.jsp";
	}
	
}