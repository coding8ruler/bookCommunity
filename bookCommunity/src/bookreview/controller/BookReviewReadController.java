package bookreview.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookreview.model.BookReview;
import bookreview.model.BookReviewData;
import bookreview.service.BookReviewService;
import bookreview.service.ReadBookReviewService;
import mvc.command.CommandHandler;


public class BookReviewReadController implements CommandHandler {

	private ReadBookReviewService readBookReviewService = new ReadBookReviewService();
	
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		
		String strNo =  req.getParameter("no");
		String strPageNo = req.getParameter("pageNo");
		String strRowSize = req.getParameter("rowSize");
		
		if(strNo==null) {
			throw new RuntimeException(); 
		}
		int pageNo = 1;
		if(strPageNo!=null) {
			pageNo = Integer.parseInt(strPageNo);
		}

		int no = Integer.parseInt(strNo);
		System.out.println("no="+no);
	
		int size = 3;
		if(strRowSize!=null) {
			size = Integer.parseInt(strRowSize);
		}
		
		
		
		
		
		
		BookReviewData bookReviewData = readBookReviewService.getBookReview(no, true);	// 원글 상세조회
		List<BookReview> brreplyList = new ArrayList<BookReview>(); 
		
		req.setAttribute("bookReviewData", bookReviewData);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("rowSize", size);
		req.setAttribute("brreplyList", brreplyList);
		
		return "/view/bookreview/readBookReviewForm.jsp";
	}

}
