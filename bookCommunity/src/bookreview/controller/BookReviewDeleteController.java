package bookreview.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookreview.service.DeleteBookReviewService;
import mvc.command.CommandHandler;


public class BookReviewDeleteController implements CommandHandler {

	private DeleteBookReviewService deleteBookReviewService = new DeleteBookReviewService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
	
		String strNo = req.getParameter("no");
		int no = Integer.parseInt(strNo);
				
		int cnt = deleteBookReviewService.deleteBookReview(no);
				
		req.setAttribute("cnt", cnt);
				
		return "/view/bookreview/bookReviewDeleteForm.jsp";
		
	}

}
