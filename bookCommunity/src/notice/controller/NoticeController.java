package notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import notice.model.dto.NoticePage;
import notice.service.NoticeService;


public class NoticeController implements CommandHandler {

	private static final String FORM_VIEW = "/view/notice/noticeForm.jsp";
	private NoticeService noticeService = new NoticeService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("NoticeHandler, process()호출 ");

		String strPageNo = request.getParameter("pageNo");
		int pageNo = 1;
		if(strPageNo!=null) {
			pageNo = Integer.parseInt(strPageNo);
		}
		
		String strRowSize = request.getParameter("rowSize");
		int size = 1;
		if(strRowSize==null) {
			size = 10;
		}else if(strRowSize!=null){
			size = 10;
		}else{
			size = Integer.parseInt(strRowSize);
		}
	
		NoticePage noticePage = noticeService.getNoticePage(pageNo, size);

		request.setAttribute("noticePage", noticePage);
		request.setAttribute("size", size);
		
		return FORM_VIEW;
	}

}
