package bookreview.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookreview.model.BWriteRequest;
import bookreview.service.WriteBookReviewService;
import member.model.User;
import mvc.command.CommandHandler;



public class BookReviewWriteController implements CommandHandler {
	
	private static final String FORM_VIEW = "/view/bookreview/bookReviewWriteForm.jsp";
	private WriteBookReviewService writeBookReviewService = new WriteBookReviewService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request, response);  
			
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request, response);
		
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	private String processForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BookReviewWriteController processForm() ");
		
		String rowSize = request.getParameter("rowSize");
		
		User authUser = loginedUser(request);
		request.setAttribute("authUser", authUser);
		request.setAttribute("rowSize", rowSize);
		
		return FORM_VIEW;
	}
	
	public User loginedUser(HttpServletRequest request) {
		
		User authUser = (User) request.getSession().getAttribute("authUser");
		return authUser;
	}
	
	private BWriteRequest createWriteRequest(User authUser, HttpServletRequest request) {
		
		return new BWriteRequest( authUser.getmId(),
				authUser.getmName(), 
				request.getParameter("BOOK_TITLE"),
				request.getParameter("AUTHOR"),
				request.getParameter("PUBLISHER"),
				request.getParameter("B_TITLE"),
				request.getParameter("B_CONTENT")
				);
	}
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BookReviewWriteController������  POST��û�� processSubmit()");
		
		String rowSize = request.getParameter("rowSize");
		User authUser = loginedUser(request);

		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		
		BWriteRequest bwriteReq = createWriteRequest(authUser, request);
		bwriteReq.validate(errors);
		request.setAttribute("errors", errors);
		
		if(!errors.isEmpty()) {
			request.setAttribute("rowSize", rowSize);
			return FORM_VIEW;
		}
		
		int newBookReviewNo = writeBookReviewService.write(bwriteReq);
		
		request.setAttribute("newBookReviewNo", newBookReviewNo);
		request.setAttribute("rowSize", rowSize);
		
		return "/blist.do";
		}
	
	}
