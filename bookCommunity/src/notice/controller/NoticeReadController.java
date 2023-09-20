package notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import notice.model.dto.NoticeData;
import notice.service.ReadNoticeService;

public class NoticeReadController implements CommandHandler {

	private ReadNoticeService readNoticeService = new ReadNoticeService();
	
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
		
		int size = 10;
		if(strRowSize!=null) {
			size = Integer.parseInt(strRowSize);
		}
		
		NoticeData noticeData = readNoticeService.getNotice(no, true);
		
		req.setAttribute("noticeData", noticeData);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("rowSize", size);
		
		return "/view/notice/readNotice.jsp";
	}

}
