package notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import notice.service.DeleteNoticeService;

public class NoticeDeleteController implements CommandHandler {

	private DeleteNoticeService deleteNoticeService = new DeleteNoticeService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
	
		String strNo = req.getParameter("no");
		int no = Integer.parseInt(strNo);
				
		int cnt = deleteNoticeService.deleteNotice(no);
				
		req.setAttribute("cnt", cnt);
				
		return "/view/notice/deleteForm.jsp";
		
	}

}
