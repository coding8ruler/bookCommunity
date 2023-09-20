package bookreview.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookreview.exception.BookReviewNotFoundException;
import bookreview.exception.PermissionDeniedException;
import bookreview.model.BUpdateRequest;
import bookreview.model.BookReviewData;
import bookreview.service.ReadBookReviewService;
import bookreview.service.UpdateBookReviewService;
import member.model.User;
import mvc.command.CommandHandler;


public class BookReviewUpdateController implements CommandHandler {

	private static final String FORM_VIEW = "/view/bookreview/bookReviewUpdateForm.jsp";
	
		private ReadBookReviewService readService = new ReadBookReviewService();		
		private UpdateBookReviewService updateService = new UpdateBookReviewService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request, response);
			
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			System.out.println("post진행");
			return processSubmit(request, response);
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	private String processForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BookReviewUpdateControllor processForm");
		
			String strNo =  request.getParameter("no");
		try {
			if(strNo==null) {
				throw new RuntimeException();  
			}
			int no = Integer.parseInt(strNo);
			
			String strPageNo = request.getParameter("pageNo");
			int pageNo = 1;
			if(strPageNo!=null) {
				pageNo = Integer.parseInt(strPageNo);
			}
			
			String rowSize = request.getParameter("rowSize");
			int size = 3;
			if(rowSize!=null) {
				size = Integer.parseInt(rowSize);
			}else {
				size =3;
			}
			

			
			
			BookReviewData bookreviewData = readService.getBookReview(no, false);
			
			User authUser = (User) request.getSession().getAttribute("authUser");
			
			if(!canUpdate(authUser, bookreviewData)) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}	
			// int no,String bookTitle, String author, String publisher,
			// User user,String bWriterId, String bWriterName, String bTitle, String bContent
			BUpdateRequest bupReq = new BUpdateRequest(no, 
														bookreviewData.getBookReview().getbookTitle(),
														bookreviewData.getBookReview().getauthor(),
														bookreviewData.getBookReview().getpublisher(),	

														bookreviewData.getBookReview().getbWriterName(),
														bookreviewData.getBookReview().getbTitle(),
														bookreviewData.getBookReview().getbContent(),
														authUser
													 );

			request.setAttribute("bupReq", bupReq);
			request.setAttribute("rowSize", rowSize);
			
			
			return FORM_VIEW; 
		} catch (BookReviewNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BookReviewUpdateController processSubmit");
	
		String strNo =  request.getParameter("no");
		if(strNo==null) {
			throw new RuntimeException();
		}
		int no = Integer.parseInt(strNo);

		String strPageNo = request.getParameter("pageNo");
		int pageNo = 1;
		if(strPageNo!=null) {
			pageNo = Integer.parseInt(strPageNo);
		}
		String strRowSize = request.getParameter("rowSize");
		int size = 3;   
		if(strRowSize!=null) {
			size = Integer.parseInt(strRowSize);
		}
		
		String bookTitle = request.getParameter("booktitle");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		String bWriterName = request.getParameter("writername");
		String bTitle = request.getParameter("title");
		String bContent = request.getParameter("content");
		User authUser = (User) request.getSession().getAttribute("authUser");
		BUpdateRequest bupReq = new BUpdateRequest(no,
												bookTitle,
												author,
												publisher,
												bWriterName,
												bTitle,
												bContent
												); 
		request.setAttribute("bupReq", bupReq);
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		request.setAttribute("errors", errors);
		bupReq.validate(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			updateService.update(bupReq);
			return "/blist.do";
		}catch (BookReviewNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}catch (PermissionDeniedException e) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}	
	private boolean canUpdate(User modifyUserId, BookReviewData bookreviewData) {
		String userId = modifyUserId.getmId();
		String writerId = bookreviewData.getBookReview().getbWriterId();
		return userId.equals(writerId);
	}
}