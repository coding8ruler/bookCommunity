package bookreview.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookreview.model.BookReviewPage;
import bookreview.service.BookReviewService;
import mvc.command.CommandHandler;


public class BookReviewListController implements CommandHandler {

	private static final String FORM_VIEW = "view/bookreview/bookReviewListForm.jsp";
	private BookReviewService bookReviewService = new BookReviewService();
	 
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BookReviewController process()호출 ");

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
		BookReviewPage bookReviewPage = bookReviewService.getBookReviewPage(pageNo, size);
		request.setAttribute("bookReviewPage", bookReviewPage);
		request.setAttribute("size", size);
		request.setAttribute("pageNo",pageNo);
		
		return FORM_VIEW;
	}

}
